package com.example.myapplication.community;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityCommunityBinding;
import com.example.myapplication.event.ActivityEvent;
import com.example.myapplication.homepage.Homepage;
import com.example.myapplication.me.MePage;

import java.util.ArrayList;

public class CommunityActivity extends AppCompatActivity {

    PostManager postManager = new PostManager();
    ActivityCommunityBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        binding = ActivityCommunityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle extras = getIntent().getExtras();

        final Button homebutton = findViewById(R.id.homebutton);
        final Button eventbutton = findViewById(R.id.eventbutton);
        final Button communitybutton = findViewById(R.id.communitybutton);
        final Button mebutton = findViewById(R.id.mebutton);


        if (extras != null) {
            postManager.makePost(extras.get("text").toString(), extras.get("title").toString());
        }

        ListAdapter listAdapter = new ListAdapter(CommunityActivity.this, postManager.getPostList());

        binding.postList.setAdapter(listAdapter);
        binding.postList.setClickable(true);

//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
//                postManager.getPostList());
//        binding.listView.setAdapter(listAdapter);
//        listView.setClickable(true);

        //ListView onclick methods:
        binding.postList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(CommunityActivity.this, ViewPostActivity.class);
                intent.putExtra("postTitle", postManager.getPostList().get(i).getTitle());
                intent.putExtra("postText", postManager.getPostList().get(i).getText());
                startActivity(intent);

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
        Intent intent = new Intent(this, MakingPostActivity.class);
        startActivity(intent);
    }
}
