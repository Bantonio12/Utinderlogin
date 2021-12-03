package com.example.myapplication.community;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.R;
import com.example.myapplication.event.ActivityEvent;
import com.example.myapplication.homepage.Homepage;
import com.example.myapplication.me.MePage;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormatSymbols;
import java.util.ArrayList;

public class CommunityActivity extends AppCompatActivity {

    PostManager postManager = new PostManager();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference postsRef = db.collection("community");

    ListView posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        Bundle extras = getIntent().getExtras();

        final Button homebutton = findViewById(R.id.homebutton);
        final Button eventbutton = findViewById(R.id.eventbutton);
        final Button communitybutton = findViewById(R.id.communitybutton);
        final Button mebutton = findViewById(R.id.mebutton);

        posts = findViewById(R.id.posts);


        if (extras != null) {
            postManager.makePost(extras.get("text").toString(), extras.get("title").toString());
        }

        postsRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                for (QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots){
                    Post post = documentSnapshot.toObject(Post.class);

                }
            }
        });




        //Navigation buttons onclick methods:
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


    public void makePost(View btn){
        Intent intent = new Intent(this, ViewPostActivity.class);
        startActivity(intent);
    }
}
