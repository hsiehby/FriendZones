package zones.com.friendzonesv00;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ViewDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        int position = getIntent().getIntExtra("position", 0);
        Contact contact = ContactData.contacts[position];
        // set name
        TextView nameView = findViewById(R.id.name_detail);
        nameView.setText(contact.getName());
        // set phone
        TextView phoneView = findViewById(R.id.phone_detail);
        phoneView.setText(contact.getPhone());

        // set time zone
        TextView timeZoneView = findViewById(R.id.time_zone_detail);
        timeZoneView.setText(String.format("Time Zone: " + contact.getTimeZone()));
        // set current time
        TextView currentTimeView = findViewById(R.id.current_converted_time_detail);
        SimpleDateFormat mdformat = new SimpleDateFormat("z YYYY MM/dd HH:mm");
        Calendar GMTTime = Calendar.getInstance();
        GMTTime.add(Calendar.MILLISECOND, -GMTTime.getTimeZone().getOffset(GMTTime.getTimeInMillis()));
        long convertedTime = GMTTime.getTimeInMillis() + 3600000 * contact.getTimeZone();
        String strDate = mdformat.format(convertedTime);
        currentTimeView.setText(strDate);
        /////
        Calendar convertedTimeAsCalendar = Calendar.getInstance();
        convertedTimeAsCalendar.setTimeInMillis(convertedTime);


        // set status
        TextView statusView = findViewById(R.id.status_detail);
        statusView.setText(Time.getStatus(convertedTimeAsCalendar, contact.getBedTime(), contact.getGetUpTime()));

        // set remove button
        ImageButton removeButton = findViewById(R.id.removeButton);
        removeButton.setTag(position);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactData.remove((Integer) v.getTag());
                Toast toast = Toast.makeText(v.getContext(), "Deleted", Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }
}