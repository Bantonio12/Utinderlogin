package com.example.myapplication.me;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.example.myapplication.community.CommunityActivity;
import com.example.myapplication.event.ui.ActivityEvent;
import com.example.myapplication.homepage.Homepage;
import com.example.myapplication.pomodoro.Pomodoro;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class OtherAccount extends AppCompatActivity {
    private HashMap<String, ArrayList<String>> otherUserFollowList = new HashMap<>();
    private ArrayList<String> otherUserFollower = new ArrayList<>();
    private ArrayList<String> otherUserFollowing = new ArrayList<>();
    private HashMap<String, ArrayList<String>> currUserFollowList = new HashMap<>();
    private ArrayList<String> currUserFollowing = new ArrayList<>();
    ArrayAdapter<String> followerAdapter;
    ArrayAdapter<String> followingAdapter;
    private Context context = this;
    private String nickname;
    private String otherID;
    private HashMap oldData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otheraccount);
        TextView userName = findViewById(R.id.username);
        final Button backButton = findViewById(R.id.backbutton);
        Button followingButton = findViewById(R.id.followingbutton);
        Button followButton = findViewById(R.id.followbutton);

        final Button homebutton = findViewById(R.id.homebutton);
        final Button eventbutton = findViewById(R.id.eventbutton);
        final Button communitybutton = findViewById(R.id.communitybutton);
        final Button pomodoroButton = findViewById(R.id.podomorobutton);
        final Button mebutton = findViewById(R.id.mebutton);

        Bundle bundle = getIntent().getExtras();
        userName.setText(bundle.getString("userName"));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent communityButtonIntent = new Intent(OtherAccount.this, CommunityActivity.class);
                startActivity(communityButtonIntent);
                finish();
            }
        });

        final TextView followingNumber = findViewById(R.id.following_number);
        final TextView followerNumber = findViewById(R.id.follower_number);

        final ListView followers = findViewById(R.id.followerlist);
        final ListView following = findViewById(R.id.followinglist);

        FirebaseUser currUser = FirebaseAuth.getInstance().getCurrentUser();
        String currUserID = currUser.getUid();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference userFollow = db.collection("users").document("UserFollow");
        DocumentReference userNickName = db.collection("users").document("UserNickName");

        userNickName.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.get(currUserID) != null) {
                    nickname = (String) document.get(currUserID);
                }
                HashMap<String, String> data = (HashMap) document.getData();
                for (String k : data.keySet()) {
                    if (document.get(k).equals(bundle.getString("userName"))) {
                        otherID = k;
                    }
                }
            }
        });

        userFollow.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                oldData = (HashMap) document.getData();
                if (document.get(otherID) != null) {
                    otherUserFollowList = (HashMap) document.get(otherID);
                    if (otherUserFollowList.get("follower") != null) {
                        otherUserFollower = otherUserFollowList.get("follower");
                    }
                    if (otherUserFollowList.get("following") != null) {
                        otherUserFollowing = otherUserFollowList.get("following");
                    }
                }

                if (document.get(currUserID) != null) {
                    currUserFollowList = (HashMap) document.get(currUserID);
                    if (currUserFollowList.get("following") != null) {
                        currUserFollowing = currUserFollowList.get("following");
                    }
                }

                if (otherUserFollower.contains(nickname)) {
                    if (followingButton.getVisibility() == View.GONE) {
                        followingButton.setVisibility(View.VISIBLE);
                        followButton.setVisibility(View.GONE);
                    }
                }

                followingNumber.setText(Integer.toString(otherUserFollowing.size()));
                followerNumber.setText(Integer.toString(otherUserFollower.size()));

                followerAdapter = new FollowAdapter(context, R.layout.activity_followitem, otherUserFollower);
                followingAdapter = new FollowAdapter(context, R.layout.activity_followitem, otherUserFollowing);

                followers.setAdapter(followerAdapter);
                following.setAdapter(followingAdapter);

            }
        });

        followingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                followButton.setVisibility(View.VISIBLE);
                followingButton.setVisibility(View.INVISIBLE);
                int followerNum = Integer.parseInt(followerNumber.getText().toString());
                followerNumber.setText(Integer.toString(followerNum + 1));

                // Update on Other Account
                otherUserFollower.add(nickname);
                otherUserFollowList.put("follower", otherUserFollower);
                oldData.put(otherID, otherUserFollowList);
                followerAdapter.notifyDataSetChanged();

                // Update on My Account
                currUserFollowing.add(bundle.getString("userName"));
                currUserFollowList.put("following", currUserFollowing);
                oldData.put(currUserID, currUserFollowList);

                userFollow.set(oldData);
                followerAdapter.notifyDataSetChanged();
            }
        });

        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                followingButton.setVisibility(View.VISIBLE);
                followButton.setVisibility(View.INVISIBLE);
                int followerNum = Integer.parseInt(followerNumber.getText().toString());
                followerNumber.setText(Integer.toString(followerNum - 1));

                // Update on Other Account
                otherUserFollower.remove(nickname);
                otherUserFollowList.put("follower", otherUserFollower);
                oldData.put(otherID, otherUserFollowList);

                // Update on My Account
                currUserFollowing.remove(bundle.getString("userName"));
                currUserFollowList.put("following", currUserFollowing);
                oldData.put(currUserID, currUserFollowList);

                userFollow.set(oldData);
                followerAdapter.notifyDataSetChanged();
            }
        });

        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeButtonIntent = new Intent(OtherAccount.this, Homepage.class);
                startActivity(homeButtonIntent);
                finish();
            }
        });
        eventbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent eventButtonIntent = new Intent(OtherAccount.this, ActivityEvent.class);
                startActivity(eventButtonIntent);
                finish();
            }
        });
        communitybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent communityButtonIntent = new Intent(OtherAccount.this, CommunityActivity.class);
                startActivity(communityButtonIntent);
                finish();
            }
        });
        mebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent meButtonIntent = new Intent(OtherAccount.this, MyAccount.class);
                startActivity(meButtonIntent);
                finish();
            }
        });
        pomodoroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pomodoroButtonIntent = new Intent(OtherAccount.this, Pomodoro.class);
                startActivity(pomodoroButtonIntent);
                finish();
            }
        });
    }
}
