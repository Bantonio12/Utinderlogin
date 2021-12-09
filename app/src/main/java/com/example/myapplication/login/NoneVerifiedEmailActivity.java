package com.example.myapplication.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.login.user.UserDataConverter;

public class NoneVerifiedEmailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_none_verified_email);

        final Button reverifyButton = findViewById(R.id.reverificationButton);
        final Button back = findViewById(R.id.backButton3);

        reverifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDataConverter userConverter = new UserDataConverter();
                userConverter.sendVerification();
                reverifyButton.setVisibility(View.INVISIBLE);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToSignIn = new Intent(NoneVerifiedEmailActivity.this, MainActivity.class);
                startActivity(backToSignIn);
                finish();
            }
        });
    }
}