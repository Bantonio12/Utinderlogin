package com.example.myapplication.community;

import android.annotation.SuppressLint;
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

    Button backButton;
    Button replyButton;
    Button followButton;
    TextView postText;
    TextView postTitle;
    ListView comments;
    ArrayList<String> comment_text = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        String title = b.getString("title");
        String text = b.getString("text");

        backButton = findViewById(R.id.Back);
        followButton = findViewById(R.id.followButton);
        replyButton = findViewById(R.id.reply_button);
        postTitle = (TextView) findViewById(R.id.postTitle);
        postText = (TextView) findViewById(R.id.postText);
        comments = findViewById(R.id.listview);
        postTitle.setText(title);
        postText.setText(text);

        //Back to community call:
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewPostActivity.this, CommunityActivity.class);
                startActivity(intent);
            }
        });

        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewPostActivity.this, CommunityActivity.class);
                startActivity(intent);
            }
        });




        //comment_text.add(title);


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