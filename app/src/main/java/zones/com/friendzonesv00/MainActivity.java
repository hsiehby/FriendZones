package zones.com.friendzonesv00;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView list;
    //Contact[] contacts;
    //String[] names = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create contacts
        ContactData.add("Eric", "(217) 819-6132", -5);
        ContactData.add("John", "(237) 829-3332", 3);
        /*Contact contact1 =  new Contact("Eric", "(217) 819-6132", -5);
        Contact contact2 = new Contact("John", "(237) 829-3332", 3);
        contacts = new Contact[] {contact1, contact2};
        */

        /*
        // create names array required by the Adaptor constructor
        names = new String[contacts.length];
        for (int i = 0; i < contacts.length; i++) {
            names[i] = contacts[i].getName();
        }
        */
        // initiate Adaptor
        ContactListAdaptor adaptor = new ContactListAdaptor(MainActivity.this, ContactData.contacts, ContactData.names);
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