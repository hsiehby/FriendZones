package zones.com.friendzonesv00;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set actionBar title
        this.getSupportActionBar().setDisplayShowCustomEnabled(true);
        this.getSupportActionBar().setDisplayShowTitleEnabled(false);

        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.action_bar_title, null);
        //if you need to customize anything else about the text, do it here.
        // I'm using a custom TextView with a custom font in my layout xml so all I need to do is set title
        ((TextView) v.findViewById(R.id.action_bar_title)).setText(this.getTitle());
        //assign the view to the actionbar
        this.getSupportActionBar().setCustomView(v);


        // switch background color according to time
        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        FrameLayout mainActivityLayout = findViewById(R.id.main_activity);
        if (currentHour >= 7 && currentHour <= 16) {
            mainActivityLayout.setBackgroundColor(getResources().getColor(R.color.pink));
        } else {
            mainActivityLayout.setBackgroundColor(getResources().getColor(R.color.darkPurple));
        }
        //
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                // initiate Adaptor
                ContactListAdaptor adaptor = new ContactListAdaptor(MainActivity.this, ContactData.names);
                list = findViewById(R.id.list);
                list.setAdapter(adaptor);

                handler.postDelayed(this, 60000);
            }
        });
    }
    public void onClickNewContact(View view) {
        Intent intent = new Intent(this, NewContactActivity.class);
        startActivity(intent);
    }
}