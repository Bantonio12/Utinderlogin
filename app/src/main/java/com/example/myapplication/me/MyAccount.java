package com.example.myapplication.me;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.community.ui.CommunityActivity;
import com.example.myapplication.homepage.Homepage;
import com.example.myapplication.event.ui.ActivityEvent;
import com.example.myapplication.login.ui.MainActivity;
import com.example.myapplication.pomodoro.Pomodoro;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MyAccount extends AppCompatActivity {
    private HashMap<String, ArrayList<String>> userFollowList = new HashMap<>();
    private ArrayList<String> userFollower = new ArrayList<>();
    private ArrayList<String> userFollowing = new ArrayList<>();
    ArrayAdapter<String> followerAdapter;
    ArrayAdapter<String> followingAdapter;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaccount);

        final Button homebutton = findViewById(R.id.homebutton);
        final Button eventbutton = findViewById(R.id.eventbutton);
        final Button communitybutton = findViewById(R.id.communitybutton);
        final Button pomodoroButton = findViewById(R.id.podomorobutton);
        final Button mebutton = findViewById(R.id.mebutton);
        final Button logOutButton = findViewById(R.id.logoutbutton);

        final TextView followingNumber = findViewById(R.id.following_number);
        final TextView followerNumber = findViewById(R.id.follower_number);

        final ListView followers = findViewById(R.id.followerlist);
        final ListView following = findViewById(R.id.followinglist);

        FirebaseUser currUser = FirebaseAuth.getInstance().getCurrentUser();
        String currUserID = currUser.getUid();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference userFollow = db.collection("users").document("UserFollow");

        userFollow.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.get(currUserID) != null) {
                    userFollowList = (HashMap) document.get(currUserID);
                    if (userFollowList.get("follower") != null) {
                        userFollower = userFollowList.get("follower");
                    }
                    if (userFollowList.get("following") != null) {
                        userFollowing = userFollowList.get("following");
                    }
                }

                followingNumber.setText(Integer.toString(userFollowing.size()));
                followerNumber.setText(Integer.toString(userFollower.size()));

                followerAdapter = new FollowAdapter(context, R.layout.activity_followitem, userFollower);
                followingAdapter = new FollowAdapter(context, R.layout.activity_followitem, userFollowing);

                followers.setAdapter(followerAdapter);
                following.setAdapter(followingAdapter);
            }
        });

        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeButtonIntent = new Intent(MyAccount.this, Homepage.class);
                startActivity(homeButtonIntent);
                finish();
            }
        });
        eventbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent eventButtonIntent = new Intent(MyAccount.this, ActivityEvent.class);
                startActivity(eventButtonIntent);
                finish();
            }
        });
        communitybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent communityButtonIntent = new Intent(MyAccount.this, CommunityActivity.class);
                startActivity(communityButtonIntent);
                finish();
            }
        });
        mebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent meButtonIntent = new Intent(MyAccount.this, OtherAccount.class);
                startActivity(meButtonIntent);
                finish();
            }
        });
        pomodoroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pomodoroButtonIntent = new Intent(MyAccount.this, Pomodoro.class);
                startActivity(pomodoroButtonIntent);
                finish();
            }
        });
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logoutButtonIntent = new Intent(MyAccount.this, MainActivity.class);
                startActivity(logoutButtonIntent);
                finish();
            }
        });
    }
}