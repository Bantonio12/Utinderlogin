package com.example.myapplication.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.example.myapplication.login.user.UserDataConverter;

public class ActivityAfterLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);

        final Button backToLogin = findViewById(R.id.button);
        backToLogin.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                UserDataConverter userConverter = new UserDataConverter();
                userConverter.singOutCurrentUser();
                Intent back_to_login_intent = new Intent(ActivityAfterLogin.this, MainActivity.class);
                startActivity(back_to_login_intent);
                finish();
            }
        });
    }
}