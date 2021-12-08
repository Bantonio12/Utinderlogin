package com.example.myapplication.event.ui;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.example.myapplication.R;
import android.widget.TextView;

/**
 * A class that helps to render the listview
 * Reference: https://pillsfromtheweb.blogspot.com/2014/10/android-listview-inside-alertdialog.html
 */
public class EventsAdapter extends ArrayAdapter<HashMap<String, String>>{

    public EventsAdapter(Context context, int resourceId, ArrayList<HashMap<String, String>> events) {
        super(context, resourceId, events);
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        View curView = convertView;
        if (curView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            curView = layoutInflater.inflate(R.layout.activity_event_popup_item, null);
        }

        HashMap<String, String> e = getItem(position);
        TextView name = curView.findViewById(R.id.eventname);
        TextView time = curView.findViewById(R.id.eventtime);
        TextView location = curView.findViewById(R.id.eventlocation);

        name.setText(e.get("Name"));
        time.setText(e.get("Time"));
        location.setText(e.get("Location"));

        return curView;
    }
}
