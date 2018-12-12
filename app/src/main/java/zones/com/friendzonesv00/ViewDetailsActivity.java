package zones.com.friendzonesv00;

import android.content.Intent;
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
        Contact contact = ContactData.contacts[position];
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
        getUpTimeView.setText(String.format("%d:%2d", contact.getGetUpTime().hour, contact.getGetUpTime().minute));
        // set bedtime
        TextView bedtimeView = findViewById(R.id.bedtime_detail);
        bedtimeView.setText(String.format("%d:%02d", contact.getBedTime().hour, contact.getBedTime().minute));
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
