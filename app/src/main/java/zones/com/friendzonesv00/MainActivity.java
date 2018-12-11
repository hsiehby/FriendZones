package zones.com.friendzonesv00;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // switch background color according to time
        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        FrameLayout mainActivityLayout = findViewById(R.id.main_activity);
        if (currentHour >= 7 && currentHour <= 19) {
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