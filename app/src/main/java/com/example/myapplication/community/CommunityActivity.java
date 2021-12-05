package com.example.myapplication.community;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.R;
import com.example.myapplication.event.ActivityEvent;
import com.example.myapplication.homepage.Homepage;
import com.example.myapplication.me.MePage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.*;

import java.util.*;

public class CommunityActivity extends AppCompatActivity {

    // Firestore cloud database reference
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference postsRef = db.document("community/Posts");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        final Button homebutton = findViewById(R.id.homebutton);
        final Button eventbutton = findViewById(R.id.eventbutton);
        final Button communitybutton = findViewById(R.id.communitybutton);
        final Button mebutton = findViewById(R.id.mebutton);

        ListView posts = findViewById(R.id.posts);


        // Posts retrieve and presenting on Community page
        postsRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    HashMap postMap = (HashMap) document.get("PostList");
                    ArrayList titles = new ArrayList();

                    for(Object key: postMap.keySet()){
                        titles.add(key.toString());
                    }

                    ArrayAdapter arrayAdapter = new ArrayAdapter
                            (CommunityActivity.this, android.R.layout.simple_list_item_1, titles);

                    posts.setAdapter(arrayAdapter);
                }
            }
        });

        //Open individual post call:
        posts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(CommunityActivity.this, ViewPostActivity.class);
                startActivity(intent);
            }
        });



        //Navigation buttons onclick call:
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
        mebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mebutton_intent = new Intent(CommunityActivity.this, MePage.class);
                startActivity(mebutton_intent);
                finish();
            }
        });
    }

    //Make new post button click method:
    public void makePost(View btn){
        Intent intent = new Intent(this, MakingPostActivity.class);
        startActivity(intent);
    }

}


