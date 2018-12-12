package zones.com.friendzonesv00;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class NewContactActivity extends AppCompatActivity {
    EditText nameField;
    EditText phoneField;
    Spinner timeZoneField;
    EditText bedtimeHourField;
    EditText bedtimeMinuteField;
    EditText getUpTimeHourField;
    EditText getUpTimeMinuteField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        // get views
        nameField = findViewById(R.id.name_text_field);
        phoneField = findViewById(R.id.phone_text_field);
        timeZoneField = findViewById(R.id.time_zone_text_field);
        bedtimeHourField = findViewById(R.id.bedtime_hour_field);
        bedtimeMinuteField = findViewById(R.id.bedtime_minute_field);
        getUpTimeHourField = findViewById(R.id.get_up_time_hour_field);
        getUpTimeMinuteField = findViewById(R.id.get_up_time_minute_field);
    }

    // create actionBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle actionBar events
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.action_create:
                onClickCreateContact();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void onClickCreateContact() {
        int timeZone = Integer.parseInt(timeZoneField.getSelectedItem().toString().replace("UTC ", ""));
        Time bedtime = new Time(Integer.parseInt(bedtimeHourField.getText().toString()), Integer.parseInt(bedtimeMinuteField.getText().toString()));
        Time getUpTime = new Time(Integer.parseInt(getUpTimeHourField.getText().toString()), Integer.parseInt(getUpTimeMinuteField.getText().toString()));
        ContactData.add(nameField.getText().toString(), phoneField.getText().toString(), timeZone, bedtime, getUpTime);
        this.startActivity(new Intent(this, MainActivity.class));
        Toast.makeText(this, "New contact created", Toast.LENGTH_SHORT).show();
    }
}

