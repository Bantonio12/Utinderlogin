package com.example.myapplication.community;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.myapplication.R;

import java.util.ArrayList;

public class PostsAdapter extends ArrayAdapter<Post> {

    public PostsAdapter(Context context, ArrayList<Post> posts){
        super(context, R.layout.post_row, posts);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Post post = getItem(position);

        if (convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.post_row, parent, false);
        }

        TextView title = convertView.findViewById(R.id.postTitle);

        title.setText(post.getTitle());

        return super.getView(position, convertView, parent);
    }
}
