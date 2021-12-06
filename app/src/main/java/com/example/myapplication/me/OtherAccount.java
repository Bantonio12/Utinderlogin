package com.example.myapplication.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.example.myapplication.community.CommunityActivity;

public class OtherAccount extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TextView userName = findViewById(R.id.username);
        final Button backButton = findViewById(R.id.backbutton);
        final Button followingButton = findViewById(R.id.followingbutton);
        final Button followButton = findViewById(R.id.followbutton);
        TextView followerNumber = findViewById(R.id.follower_number);

        Bundle bundle = getIntent().getExtras();
        userName.setText(bundle.getString("userName"));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go back to the post button
                Intent communitybutton_intent = new Intent(OtherAccount.this, CommunityActivity.class);
                startActivity(communitybutton_intent);
                finish();
            }
        });

        followingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                followButton.setVisibility(View.VISIBLE);
                followingButton.setVisibility(View.INVISIBLE);
                int followerNum = Integer.parseInt(followerNumber.getText().toString());
                followerNumber.setText(Integer.toString(followerNum + 1));
                // Change the user's follower in the User class
            }
        });

        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                followingButton.setVisibility(View.VISIBLE);
                followButton.setVisibility(View.INVISIBLE);
                int followerNum = Integer.parseInt(followerNumber.getText().toString());
                followerNumber.setText(Integer.toString(followerNum - 1));
                // Change the user's follower in the User class
            }
        });
    }
}
