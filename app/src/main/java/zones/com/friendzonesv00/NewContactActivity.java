package zones.com.friendzonesv00;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class NewContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
    }

    public void onClickSave(View view) {
        EditText nameField = findViewById(R.id.name_text_field);
        EditText phoneField = findViewById(R.id.phone_text_field);
        Spinner timeZoneField = findViewById(R.id.time_zone_text_field);

        String name = nameField.getText().toString();
        String phone = phoneField.getText().toString();
        int timeZone = Integer.parseInt(timeZoneField.getSelectedItem().toString().replace("UTC ", ""));
        ContactData.add(name, phone, timeZone);

        // Display success message
        Toast toast = Toast.makeText(this, "Saved", Toast.LENGTH_SHORT);
        toast.show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickCancel(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

