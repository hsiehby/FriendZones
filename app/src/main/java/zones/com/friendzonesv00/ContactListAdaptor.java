package zones.com.friendzonesv00;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class ContactListAdaptor extends ArrayAdapter<String> {
    private final Activity context;
    private final Contact[] contacts;

    public ContactListAdaptor(Activity context, Contact[] contacts, String[] names) {
        // call super
        super(context, R.layout.list_single, names);
        this.context = context;
        this.contacts = contacts;
    }
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_single, null, true);
        TextView nameView = rowView.findViewById(R.id.name);
        TextView timeZoneView = rowView.findViewById(R.id.time_zone);
        TextView phoneNumberView = rowView.findViewById(R.id.phone);
        nameView.setText(contacts[position].getName());
        timeZoneView.setText(String.format("Time Zone: %d", contacts[position].getTimeZone()));
        phoneNumberView.setText(contacts[position].getPhone());
        return rowView;
    }
    /*
    private final String[] name;
    private final String[] phone;
    private final Integer[] timeZone;
    */

    /*
    public ContactListAdaptor(Activity context, String[] name, String[] phone, Integer[] timeZone) {
        super(context, R.layout.list_single, name);
        this.context = context;
        this.name = name;
        this.phone = phone;
        this.timeZone = timeZone;
    }
    */


    /*
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_single, null, true);
        TextView nameView = rowView.findViewById(R.id.name);
        TextView timeZoneView = rowView.findViewById(R.id.time_zone);
        TextView phoneNumberView = rowView.findViewById(R.id.phone);
        nameView.setText(name[position]);
        timeZoneView.setText(String.format("Time Zone: %d", timeZone[position]));
        phoneNumberView.setText(phone[position]);
        return rowView;
    }
    */

}