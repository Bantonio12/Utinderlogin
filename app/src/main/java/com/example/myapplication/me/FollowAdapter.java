package com.example.myapplication.me;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class FollowAdapter extends ArrayAdapter<String> {
    public FollowAdapter(Context context, int resourceId, ArrayList<String> followName) {
        super(context, resourceId, followName);
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        View curView = convertView;
        if (curView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            curView = layoutInflater.inflate(R.layout.activity_followitem, null);
        }
        String follow = getItem(position);
        TextView name = curView.findViewById(R.id.followname);
        name.setText(follow);

        return curView;
    }
}
