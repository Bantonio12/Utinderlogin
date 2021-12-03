package com.example.myapplication.community;

import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.R;

import java.util.ArrayList;

public class ViewPostActivity extends AppCompatActivity {

    Button back_button;
    Button reply_button;
    TextView post_title;
    TextView post_text;
    ListView comments;
    ArrayList<String> comment_text = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);

        comment_text.add("hello");


        back_button = findViewById(R.id.back_button);
        reply_button = findViewById(R.id.reply_button);
        post_title = findViewById(R.id.post_title);
        post_text = findViewById(R.id.post_text);
        comments = findViewById(R.id.listview);

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, comment_text);
        comments.setAdapter(arrayAdapter);

        comments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                comment_text.get(position);
                startActivity(new Intent(ViewPostActivity.this, CommentActivity.class));
            }
        });
    }
    }