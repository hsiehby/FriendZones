package zones.com.friendzonesv00;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ListView list;
    String[] names = {"Eric", "Tom"};
    String[] phones = {"(217) 819-6132", "(217) 219-9302"};
    Integer[] timeZones = {5, -8};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ContactListAdaptor adaptor = new ContactListAdaptor(MainActivity.this, names, phones, timeZones);
        list = findViewById(R.id.list);
        list.setAdapter(adaptor);

        /*Contact contact1 = new Contact("Eric", "(217) 819-6132", -5);
        TextView nameView = findViewById(R.id.name);
        TextView timeZoneView = findViewById(R.id.time_zone);
        TextView phoneNumberView = findViewById(R.id.phone);
        nameView.setText(contact1.getName());
        timeZoneView.setText(String.format("Time Zone: %d", contact1.getTimeZone()));
        phoneNumberView.setText(contact1.getPhone());
        */


    }
}