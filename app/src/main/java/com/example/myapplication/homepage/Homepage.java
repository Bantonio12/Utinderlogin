package com.example.myapplication.homepage;

import android.util.Log;
import android.widget.AdapterView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.time.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.myapplication.R;
import com.example.myapplication.me.Pomodoro;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.example.myapplication.event.ui.ActivityEvent;
import com.example.myapplication.me.MyAccount;
import com.example.myapplication.community.CommunityActivity;
import com.google.android.material.textfield.TextInputEditText;

/**
 * An activity class for displaying components in the Homepage
 */
public class Homepage extends AppCompatActivity {
    private ListView listOfTasks;
    private ArrayList<String> taskList = new ArrayList<>();
    private ArrayList<String> prevData = new ArrayList<>();
    private TaskAdapter arrayAdapter;
    private HashMap<String, ArrayList<String>> allData = new HashMap<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        Button addTaskButton = findViewById(R.id.addtaskbutton);
        TextInputEditText newTaskName = findViewById(R.id.addingtask);

        String morning_greet = "Good Morning, ";
        String afternoon_greet = "Good Afternoon, " ;
        String evening_greet = "Good Evening, " ;


        LocalTime datetime = LocalTime.now();
        final TextView gretting = findViewById(R.id.greeting_homepageview);
        final TextView datee = findViewById(R.id.date_txt);
        if (datetime.getHour() < 12){
            gretting.setText(morning_greet);
        } else if (datetime.getHour() >= 12 && datetime.getHour() < 18){
            gretting.setText(afternoon_greet);
        } else {
            gretting.setText(evening_greet);
        }

        listOfTasks = findViewById(R.id.tasks_list);
        arrayAdapter = new TaskAdapter(this, R.layout.activity_taskitem, taskList);
        listOfTasks.setAdapter((arrayAdapter));

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentUserId = user.getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference userTaskData = db.collection("users").document("UserTask");

        userTaskData.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    allData = (HashMap) document.getData();
                    System.out.println(allData.toString());
                    if (document.get(currentUserId) != null) {
                        prevData = (ArrayList) document.get(currentUserId);
                        for (int i = 0; i < prevData.size(); i++) {
                            taskList.add(prevData.get(i));
                            arrayAdapter.notifyDataSetChanged2();
                        }
                    }
                    return;
                }else {
                    Log.w("Adding task", "Failed to load previous tasks");
                }
            }
        });


        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!newTaskName.getText().toString().isEmpty()){
                    taskList.add(newTaskName.getText().toString());
                    allData.put(currentUserId, taskList);
                    userTaskData.set(allData);
                    arrayAdapter.notifyDataSetChanged2();
                    newTaskName.setText("");
                }
            }
        });

        final Button homebutton = findViewById(R.id.homebutton);
        final Button eventbutton = findViewById(R.id.eventbutton);
        final Button communitybutton = findViewById(R.id.communitybutton);
        final Button pomodoroButton = findViewById(R.id.podomorobutton);
        final Button mebutton = findViewById(R.id.mebutton);
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeButtonIntent = new Intent(Homepage.this, Homepage.class);
                startActivity(homeButtonIntent);
                finish();
            }
        });
        eventbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent eventButtonIntent = new Intent(Homepage.this, ActivityEvent.class);
                startActivity(eventButtonIntent);
                finish();
            }
        });
        communitybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent communityButtonIntent = new Intent(Homepage.this, CommunityActivity.class);
                startActivity(communityButtonIntent);
                finish();
            }
        });
        pomodoroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pomodoroButtonIntent = new Intent(Homepage.this, Pomodoro.class);
                startActivity(pomodoroButtonIntent);
                finish();
            }
        });
        mebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent meButtonIntent = new Intent(Homepage.this, MyAccount.class);
                startActivity(meButtonIntent);
                finish();
            }
        });
    }
}