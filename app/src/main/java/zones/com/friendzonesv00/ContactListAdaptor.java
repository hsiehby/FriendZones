package zones.com.friendzonesv00;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ContactListAdaptor extends ArrayAdapter<String> {
    private final Activity context;
    private final Contact[] contacts;
    private View.OnClickListener callButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (Integer) v.getTag();
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + ContactData.contacts[position].getPhone()));
            v.getContext().startActivity(intent);
        }
    };


    public ContactListAdaptor(Activity context, Contact[] contacts, String[] names) {
        // call super
        super(context, R.layout.list_single, names);
        this.context = context;
        this.contacts = contacts;
    }
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_single, null, true);
        CardView singleCard = rowView.findViewById(R.id.single_card);
        TextView nameView = rowView.findViewById(R.id.name);
        TextView timeZoneView = rowView.findViewById(R.id.time_zone);
        TextView phoneNumberView = rowView.findViewById(R.id.phone);

        ImageButton callButton = rowView.findViewById(R.id.call_button);
        callButton.setTag(position); // position used by callButtonClickListener to retrieve phone number
        callButton.setOnClickListener(callButtonClickListener);

        // set time
        TextView currentTimeView = rowView.findViewById(R.id.current_converted_time);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("z YYYY MM/dd HH:mm");

        // get GMT (+0:00) time for conversion purpose
        Calendar GMTTime = Calendar.getInstance();
        GMTTime.add(Calendar.MILLISECOND, -GMTTime.getTimeZone().getOffset(GMTTime.getTimeInMillis()));
        long convertedTime = GMTTime.getTimeInMillis() + 3600000 * contacts[position].getTimeZone();
        //format convertedTime for display
        String strDate = mdformat.format(convertedTime);
        currentTimeView.setText(strDate);

        // Testing converting convertedTime into Calendar so we can get hour more easily;
        Calendar convertedTimeAsCalendar = Calendar.getInstance();
        Date convertedTimeAsDate = new Date(convertedTime);
        convertedTimeAsCalendar.setTime(convertedTimeAsDate);

        // display name, timeZone, and phone number
        nameView.setText(contacts[position].getName());
        timeZoneView.setText(String.format("Time Zone: %d", contacts[position].getTimeZone()));
        phoneNumberView.setText(contacts[position].getPhone());

        // Debug : Need to use HOUR_OF_DAY instead of HOUR, so it returns military time.
        Log.e("XYZIII", ((Integer) convertedTimeAsCalendar.get(Calendar.HOUR_OF_DAY)).toString());
        // Testing setting background color for a card
        if (convertedTimeAsCalendar.get(Calendar.HOUR_OF_DAY) < 23 && convertedTimeAsCalendar.get(Calendar.HOUR_OF_DAY) > 9) {
            singleCard.setCardBackgroundColor(Color.GREEN);
        } else {
            singleCard.setCardBackgroundColor(Color.RED);
        }

        return rowView;
    }
    /*
    private final String[] name;
    private final String[] phone;
    private final Integer[] timeZone;
    */

    /*
    public ContactListAdaptor(Activity context, String[] name, String[] phone, Integer[] timeZone) {
        super(context, R.layout.list_single, name);
        this.context = context;
        this.name = name;
        this.phone = phone;
        this.timeZone = timeZone;
    }
    */


    /*
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_single, null, true);
        TextView nameView = rowView.findViewById(R.id.name);
        TextView timeZoneView = rowView.findViewById(R.id.time_zone);
        TextView phoneNumberView = rowView.findViewById(R.id.phone);
        nameView.setText(name[position]);
        timeZoneView.setText(String.format("Time Zone: %d", timeZone[position]));
        phoneNumberView.setText(phone[position]);
        return rowView;
    }
    */

}