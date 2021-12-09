package com.example.myapplication.community;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.myapplication.R;
import java.util.ArrayList;

/**
 * An adapter to fit the posts info retrieved from database into usable layout to
 * visualize in the community page.
 */
public class PostAdapter extends ArrayAdapter<String> {
    public PostAdapter (Context context, int resourceId, ArrayList<String> tasks) {
        super(context, resourceId, tasks);
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        View curView = convertView;
        if (curView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            curView = layoutInflater.inflate(R.layout.activity_post_item, null);
        }
        String title = getItem(position);
        TextView name = curView.findViewById(R.id.postTitle);
        name.setText(title);
        return curView;
    }
}
