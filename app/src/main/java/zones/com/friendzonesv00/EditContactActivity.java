package zones.com.friendzonesv00;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EditContactActivity extends AppCompatActivity {
    int position = 0;
    Contact theContact;
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
        setContentView(R.layout.activity_edit_contact);

        // add back button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // get position from intent extra
        position = getIntent().getIntExtra("position", 0);
        theContact = ContactData.contacts[position];

        // get views
        nameField = findViewById(R.id.name_text_field_edit);
        phoneField = findViewById(R.id.phone_text_field_edit);
        timeZoneField = findViewById(R.id.time_zone_text_field_edit);
        bedtimeHourField = findViewById(R.id.bedtime_hour_field_edit);
        bedtimeMinuteField = findViewById(R.id.bedtime_minute_field_edit);
        getUpTimeHourField = findViewById(R.id.get_up_time_hour_field_edit);
        getUpTimeMinuteField = findViewById(R.id.get_up_time_minute_field_edit);

        // set original values
        nameField.setText(theContact.getName());
        phoneField.setText(theContact.getPhone());
        timeZoneField.setSelection(theContact.getTimeZone() + 11);
        bedtimeHourField.setText(String.format("%d", theContact.getBedTime().hour));
        bedtimeMinuteField.setText(String.format("%d", theContact.getBedTime().minute));
        getUpTimeHourField.setText(String.format("%d", theContact.getGetUpTime().hour));
        getUpTimeMinuteField.setText(String.format("%d", theContact.getGetUpTime().minute));
    }
    // create actionBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle actionBar events
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.action_save_edit:
                onClickSaveChanges();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void onClickSaveChanges() {
        int timeZone = Integer.parseInt(timeZoneField.getSelectedItem().toString().replace("UTC ", ""));
        Time bedtime = new Time(Integer.parseInt(bedtimeHourField.getText().toString()), Integer.parseInt(bedtimeMinuteField.getText().toString()));
        Time getUpTime = new Time(Integer.parseInt(getUpTimeHourField.getText().toString()), Integer.parseInt(getUpTimeMinuteField.getText().toString()));
        ContactData.edit(position, nameField.getText().toString(), phoneField.getText().toString(), timeZone, bedtime, getUpTime);
        this.startActivity(new Intent(this, ViewDetailsActivity.class));
        Toast.makeText(this, "Changes saved", Toast.LENGTH_SHORT).show();
    }
}
