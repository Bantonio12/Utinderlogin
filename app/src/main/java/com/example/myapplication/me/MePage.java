package com.example.myapplication.me;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

import com.example.myapplication.community.CommunityActivity;
import com.example.myapplication.homepage.Homepage;
import com.example.myapplication.event.ActivityEvent;

public class MePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_page);

        final Button homebutton = findViewById(R.id.homebutton);
        final Button eventbutton = findViewById(R.id.eventbutton);
        final Button communitybutton = findViewById(R.id.communitybutton);
        final Button mebutton = findViewById(R.id.mebutton);
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homebutton_intent = new Intent(MePage.this, Homepage.class);
                startActivity(homebutton_intent);
                finish();
            }
        });
        eventbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent eventbutton_intent = new Intent(MePage.this, ActivityEvent.class);
                startActivity(eventbutton_intent);
                finish();
            }
        });
        communitybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent communitybutton_intent = new Intent(MePage.this, CommunityActivity.class);
                startActivity(communitybutton_intent);
                finish();
            }
        });
        mebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mebutton_intent = new Intent(MePage.this, MePage.class);
                startActivity(mebutton_intent);
                finish();
            }
        });
    }
}