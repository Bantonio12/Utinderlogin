package com.example.myapplication.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.login.user.UserDataConverter;

import java.util.Timer;
import java.util.TimerTask;

public class forgotPaswordActivity extends AppCompatActivity {

    private final int DELAY_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pasword);

        final EditText emailInput = findViewById(R.id.emailInputText);
        final Button sendButton = findViewById(R.id.sendButton);
        final TextView warningText = findViewById(R.id.warningMessage);
        final TextView instruction = findViewById(R.id.instructionText);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                UserDataConverter userConverter = new UserDataConverter();
                String email = emailInput.getText().toString();
                if (email.equals("")) {
                    warningText.setVisibility(View.VISIBLE);
                } else {
                    boolean passwordResetSuccess = userConverter.resetPassword(email);
                    if (passwordResetSuccess) {
                        instruction.setText("Email Sent! Returning to sign in page!");
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(forgotPaswordActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }, DELAY_TIME);
                    } else {
                        warningText.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }
}