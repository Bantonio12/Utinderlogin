package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) throws NullPointerException{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button loginbutton = findViewById(R.id.loginbutton);
        final TextInputEditText username_input = findViewById(R.id.textInputEditText);
        final EditText password_input = findViewById(R.id.Password);
        final TextView textView = findViewById(R.id.textView);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {
                String username = username_input.getText().toString();
                String password = password_input.getText().toString();
                UserManager manager = new UserManager();
                if (!manager.checkUser(username, password)) {
                    textView.setVisibility(View.VISIBLE);
                } else {
                    textView.setText("Sign in complete!");
                    textView.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}