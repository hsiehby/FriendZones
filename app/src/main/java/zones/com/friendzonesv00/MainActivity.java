package zones.com.friendzonesv00;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    ListView list;
    //Contact[] contacts;
    //String[] names = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat mdformat = new SimpleDateFormat("z YYYY MM/dd HH:mm");
                String strDate = mdformat.format(calendar.getTime());
                TextView currentTimeView = findViewById(R.id.current_local_time);
                currentTimeView.setText(strDate);

                // initiate Adaptor
                ContactListAdaptor adaptor = new ContactListAdaptor(MainActivity.this, ContactData.contacts, ContactData.names);
                list = findViewById(R.id.list);
                list.setAdapter(adaptor);

                handler.postDelayed(this, 60000);
            }
        });


        // create contacts
        //ContactData.add("Eric", "(217) 819-6132", -5);
        //ContactData.add("John", "(237) 829-3332", 3);
        //ContactData.add("Tim", "(237) 828-3398", 4);





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


        /*Contact contact1 = new Contact("Eric", "(217) 819-6132", -5);
        TextView nameView = findViewById(R.id.name);
        TextView timeZoneView = findViewById(R.id.time_zone);
        TextView phoneNumberView = findViewById(R.id.phone);
        nameView.setText(contact1.getName());
        timeZoneView.setText(String.format("Time Zone: %d", contact1.getTimeZone()));
        phoneNumberView.setText(contact1.getPhone());
        */
    }
    public void onClickNewContact(View view) {
        Intent intent = new Intent(this, NewContactActivity.class);
        startActivity(intent);
    }

    /*
    public void onClickCall(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + ContactData.contacts[view.getId()].getPhone()));
        Log.d("onClickCall", Integer.toString(view.getId()));
        startActivity(intent);
    }
    */
}