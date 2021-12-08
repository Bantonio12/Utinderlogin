package com.example.myapplication.community;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.R;
import com.example.myapplication.event.ui.ActivityEvent;
import com.example.myapplication.homepage.Homepage;
import com.example.myapplication.me.MyAccount;
import com.example.myapplication.me.Pomodoro;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.*;

import java.util.*;

/**
 * Page for showing all the main posts
 * The main posts will be shown in a ListView showing each posts Title
 * Main posts will be clickable and will enter into a new page once clicked
 */
public class CommunityActivity extends AppCompatActivity {

    /**
     * Firestore database reference
     */
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference postsRef = db.document("community/Posts");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        final Button makePostButton = findViewById(R.id.make_post);
        final Button homebutton = findViewById(R.id.homebutton);
        final Button eventbutton = findViewById(R.id.eventbutton);
        final Button communitybutton = findViewById(R.id.communitybutton);
        final Button pomodorobutton = findViewById(R.id.podomorobutton);
        final Button mebutton = findViewById(R.id.mebutton);

        ListView posts = findViewById(R.id.posts);

        ArrayList titles = new ArrayList();

        /**
         * Posts retrieve and presenting on Community page
         */
        postsRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    HashMap postMap = (HashMap) document.get("PostList");

                    for(Object key: postMap.keySet()){
                        titles.add(key.toString());
                    }

                    ArrayAdapter arrayAdapter = new PostAdapter(CommunityActivity.this, R.layout.activity_post_item, titles);

                    posts.setAdapter(arrayAdapter);
                }
            }
        });

        /**
         * Command to enter individual post:
         */
        posts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String title = (String) titles.get(i);

                Intent intent = new Intent(CommunityActivity.this, ViewPostActivity.class);

                intent.putExtra("title", title);

                startActivity(intent);
            }
        });


        /**
         * Navigation buttons onclick calls:
         */
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homebutton_intent = new Intent(CommunityActivity.this, Homepage.class);
                startActivity(homebutton_intent);
                finish();
            }
        });
        eventbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent eventbutton_intent = new Intent(CommunityActivity.this, ActivityEvent.class);
                startActivity(eventbutton_intent);
                finish();
            }
        });
        communitybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent communitybutton_intent = new Intent(CommunityActivity.this, CommunityActivity.class);
                startActivity(communitybutton_intent);
                finish();
            }
        });

        pomodorobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mebutton_intent = new Intent(CommunityActivity.this, Pomodoro.class);
                startActivity(mebutton_intent);
                finish();
            }
        });

        mebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mebutton_intent = new Intent(CommunityActivity.this, MyAccount.class);
                startActivity(mebutton_intent);
                finish();
            }
        });

        makePostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CommunityActivity.this, MakingPostActivity.class);
                startActivity(intent);
            }
        });
    }

}

