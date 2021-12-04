package com.example.myapplication.homepage;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.time.*;

import java.util.ArrayList;

import com.example.myapplication.R;

import com.example.myapplication.event.ActivityEvent;
import com.example.myapplication.community.Community;
import com.example.myapplication.me.MyAccount;
import com.google.android.material.textfield.TextInputEditText;


public class Homepage extends AppCompatActivity {
    private ArrayList<String> taskList = new ArrayList<>();
    private TaskAdapter arrayAdapter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

//        Intent email_intent = getIntent();
//        String uoft_email = email_intent.getStringExtra("uoft email");

        Button addTaskButton = findViewById(R.id.addtaskbutton);
        TextInputEditText newTaskName = findViewById(R.id.addingtask);

//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        DocumentReference userData = db.collection("users").document("UserNamePassword");

        String morning_greet = "Good Morning, ";
        String afternoon_greet = "Good Afternoon, " ;
        String evening_greet = "Good Evening, " ;


        LocalTime datetime = LocalTime.now();
        LocalDate date = LocalDate.now();
        final TextView gretting = findViewById(R.id.greeting_homepageview);
//        final TextView datee = findViewById(R.id.date_txt);
        if (datetime.getHour() < 12){
            gretting.setText(morning_greet);
        } else if (datetime.getHour() >= 12 && datetime.getHour() < 18){
            gretting.setText(afternoon_greet);
        } else {
            gretting.setText(evening_greet);
        }

        // TODO: Connect to the database

        @SuppressLint("WrongViewCast")
        ListView listOfTasks = findViewById(R.id.tasks_list);
        arrayAdapter = new TaskAdapter(this, R.layout.activity_taskitem, taskList);
        listOfTasks.setAdapter((arrayAdapter));

        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!newTaskName.getText().toString().isEmpty()){
                    taskList.add(newTaskName.getText().toString());
                    System.out.println(taskList.toString());
                    arrayAdapter.notifyDataSetChanged();
                    newTaskName.setText("");
                }
            }
        });


        final Button homebutton = findViewById(R.id.homebutton);
        final Button eventbutton = findViewById(R.id.eventbutton);
        final Button communitybutton = findViewById(R.id.communitybutton);
        final Button mebutton = findViewById(R.id.mebutton);
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homebutton_intent = new Intent(Homepage.this, Homepage.class);
                startActivity(homebutton_intent);
                finish();
            }
        });
        eventbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent eventbutton_intent = new Intent(Homepage.this, ActivityEvent.class);
                startActivity(eventbutton_intent);
                finish();
            }
        });
        communitybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent communitybutton_intent = new Intent(Homepage.this, Community.class);
                startActivity(communitybutton_intent);
                finish();
            }
        });
        mebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mebutton_intent = new Intent(Homepage.this, MyAccount.class);
                startActivity(mebutton_intent);
                finish();
            }
        });
    }
}