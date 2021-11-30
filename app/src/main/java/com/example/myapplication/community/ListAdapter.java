package com.example.myapplication.community;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.myapplication.R;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter {

    public ListAdapter(Context context, ArrayList<Post> postArrayList){

        super(context, R.layout.list_post, postArrayList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Post post = (Post)getItem(position);

        if (convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_post, parent, false);

        }

        TextView postTitle = convertView.findViewById(R.id.postTitle);

        postTitle.setText(post.getTitle());

        return super.getView(position, convertView, parent);
    }
}
