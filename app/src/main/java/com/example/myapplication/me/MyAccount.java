package com.example.myapplication.me;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

import com.example.myapplication.community.CommunityActivity;
import com.example.myapplication.homepage.Homepage;
import com.example.myapplication.event.ui.ActivityEvent;
import com.example.myapplication.login.MainActivity;
import com.example.myapplication.login.user.UserDataConverter;


public class MyAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaccount);

        final Button homebutton = findViewById(R.id.homebutton);
        final Button eventbutton = findViewById(R.id.eventbutton);
        final Button communitybutton = findViewById(R.id.communitybutton);
        final Button mebutton = findViewById(R.id.mebutton);
        final Button logOutButton = findViewById(R.id.logoutbutton);
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homebutton_intent = new Intent(MyAccount.this, Homepage.class);
                startActivity(homebutton_intent);
                finish();
            }
        });
        eventbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent eventbutton_intent = new Intent(MyAccount.this, ActivityEvent.class);
                startActivity(eventbutton_intent);
                finish();
            }
        });
        communitybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent communitybutton_intent = new Intent(MyAccount.this, CommunityActivity.class);
                startActivity(communitybutton_intent);
                finish();
            }
        });
        mebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mebutton_intent = new Intent(MyAccount.this, OtherAccount.class);
                startActivity(mebutton_intent);
                finish();
            }
        });
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserDataConverter userConverter = new UserDataConverter();
                userConverter.singOutCurrentUser();

                Intent logOutButtonIntent = new Intent(MyAccount.this, MainActivity.class);
                startActivity(logOutButtonIntent);
                finish();
            }
        });
    }
}