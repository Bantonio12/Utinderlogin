package com.example.myapplication.homepage;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.myapplication.R;

public class TaskAdapter extends ArrayAdapter<String> {
    public TaskAdapter(Context context, int resourceId, ArrayList<String> tasks) {
        super(context, resourceId, tasks);
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        View curView = convertView;
        if (curView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            curView = layoutInflater.inflate(R.layout.activity_taskitem, null);
        }
        String task = getItem(position);
        TextView name = curView.findViewById(R.id.taskname);
        name.setText(task);
        return curView;
    }
}
