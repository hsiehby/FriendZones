package zones.com.friendzonesv00;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ContactListAdaptor extends ArrayAdapter<String> {
    private final Activity context;

    public ContactListAdaptor(Activity context, String[] names) {
        // call super
        super(context, R.layout.list_single, names);
        this.context = context;
    }
    public View getView(int position, View view, ViewGroup parent) {
        // variables
        final Contact theContact = ContactData.contacts[position];
        final Calendar localTimeAsCalendar = Time.getLocalTimeAsCalendar(theContact.getTimeZone());

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_single, null, true);
        CardView singleCard = rowView.findViewById(R.id.single_card);
        TextView nameView = rowView.findViewById(R.id.name);

        // set card onClick
        CardView cardButton = rowView.findViewById(R.id.single_card);
        cardButton.setTag(position);
        cardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer) v.getTag();
                Intent detailIntent = new Intent(v.getContext(), ViewDetailsActivity.class);
                detailIntent.putExtra("position", position);
                v.getContext().startActivity(detailIntent);
            }
        });

        // set callButton
        ImageButton callButton = rowView.findViewById(R.id.call_button);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + theContact.getPhone()));
                v.getContext().startActivity(intent);
            }
        });

        // set SMSButton
        ImageButton messageButton = rowView.findViewById(R.id.message_button);
        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + theContact.getPhone()));
                v.getContext().startActivity(sendIntent);
            }
        });

        // set time
        TextView currentTimeView = rowView.findViewById(R.id.current_converted_time);
        currentTimeView.setText(new SimpleDateFormat("HH:mm").format(localTimeAsCalendar.getTimeInMillis()));

        // set name
        nameView.setText(theContact.getName());

        // set status
        TextView statusView = rowView.findViewById(R.id.status);
        statusView.setText(Time.getStatus(localTimeAsCalendar, theContact.getBedTime(), theContact.getGetUpTime()));

        // set reminderButton
        ImageButton reminderButton = rowView.findViewById(R.id.reminder_button);
        reminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Time.isAvailable(localTimeAsCalendar, theContact.getBedTime(), theContact.getGetUpTime())) {
                    Intent reminderIntent = new Intent(Intent.ACTION_EDIT);
                    reminderIntent.setType("vnd.android.cursor.item/event");
                    long startTime = Calendar.getInstance().getTimeInMillis() + Time.getTimeUntilGetUpInMillis(localTimeAsCalendar, theContact.getGetUpTime());
                    reminderIntent.putExtra("beginTime", startTime);
                    reminderIntent.putExtra("allDay", false);
                    reminderIntent.putExtra("endTime", startTime + 30 * 60 * 1000);
                    reminderIntent.putExtra(CalendarContract.Events.TITLE, String.format("Call " + theContact.getName()));
                    v.getContext().startActivity(reminderIntent);
                } else {
                    Toast.makeText(v.getContext(), String.format("%s is currently awake!", theContact.getName()), Toast.LENGTH_SHORT).show();
                }
            }
        });

        // set background color for a card
        if (Time.isAvailable(localTimeAsCalendar, theContact.getBedTime(), theContact.getGetUpTime())) {
            singleCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.green));
        } else {
            singleCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.red));
        }

        return rowView;
    }
}