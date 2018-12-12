package zones.com.friendzonesv00;

import android.content.Intent;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ViewDetailsActivity extends AppCompatActivity {
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        // add back button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // variables
        position = getIntent().getIntExtra("position", 0);
        final Contact contact = ContactData.contacts[position];
        Calendar localTimeAsCalendar = Time.getLocalTimeAsCalendar(contact.getTimeZone());

        // set name
        TextView nameView = findViewById(R.id.name_detail);
        nameView.setText(contact.getName());
        // set phone
        TextView phoneView = findViewById(R.id.phone_detail);
        phoneView.setText(contact.getPhone());
        // set time zone
        TextView timeZoneView = findViewById(R.id.time_zone_detail);
        timeZoneView.setText(String.format("UTC %+d", contact.getTimeZone()));
        // set current time
        TextView currentTimeView = findViewById(R.id.current_converted_time_detail);
        SimpleDateFormat currentTimeFormat = new SimpleDateFormat("YYYY MM/dd HH:mm");
        String strDate = currentTimeFormat.format(localTimeAsCalendar.getTimeInMillis());
        currentTimeView.setText(strDate);
        // set status
        TextView statusView = findViewById(R.id.status_detail);
        statusView.setText(Time.getStatus(localTimeAsCalendar, contact.getBedTime(), contact.getGetUpTime()));
        // set get up time
        TextView getUpTimeView = findViewById(R.id.get_up_time_detail);
        getUpTimeView.setText(String.format("%d:%02d", contact.getGetUpTime().hour, contact.getGetUpTime().minute));
        // set bedtime
        TextView bedtimeView = findViewById(R.id.bedtime_detail);
        bedtimeView.setText(String.format("%d:%02d", contact.getBedTime().hour, contact.getBedTime().minute));

        // set call button
        ImageButton callButton = findViewById(R.id.call_button_detail);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + contact.getPhone()));
                v.getContext().startActivity(intent);
            }
        });

        // set SMS button
        ImageButton messageButton = findViewById(R.id.message_button_detail);
        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + contact.getPhone()));
                v.getContext().startActivity(sendIntent);
            }
        });

        // set reminderButton
        ImageButton reminderButton = findViewById(R.id.reminder_button_detail);
        reminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Time.isAvailable(Time.getLocalTimeAsCalendar(contact.getTimeZone()), contact.getBedTime(), contact.getGetUpTime())) {
                    Intent reminderIntent = new Intent(Intent.ACTION_EDIT);
                    reminderIntent.setType("vnd.android.cursor.item/event");
                    long startTime = Calendar.getInstance().getTimeInMillis() + Time.getTimeUntilGetUpInMillis(Time.getLocalTimeAsCalendar(contact.getTimeZone()), contact.getGetUpTime());
                    reminderIntent.putExtra("beginTime", startTime);
                    reminderIntent.putExtra("allDay", false);
                    reminderIntent.putExtra("endTime", startTime + 30 * 60 * 1000);
                    reminderIntent.putExtra(CalendarContract.Events.TITLE, String.format("Call " + contact.getName()));
                    v.getContext().startActivity(reminderIntent);
                } else {
                    Toast.makeText(v.getContext(), String.format("%s is currently awake!", contact.getName()), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // create actionBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle actionBar events
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.action_delete:
                ContactData.remove(position);
                Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
                this.startActivity(new Intent(this, MainActivity.class));
                return true;
            case R.id.action_edit:
                Intent editIntent = new Intent(this, EditContactActivity.class);
                editIntent.putExtra("position", position);
                startActivity(editIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
