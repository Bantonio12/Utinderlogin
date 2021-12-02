package com.example.myapplication.login;

import com.example.myapplication.homepage.Homepage;
import com.example.myapplication.R;
import com.example.myapplication.login.user.UserManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) throws NullPointerException{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button loginbutton = findViewById(R.id.loginbutton);
        final TextInputEditText username_input = findViewById(R.id.textInputEditText);
        final EditText password_input = findViewById(R.id.Password);
        final TextView wrongPtext = findViewById(R.id.wrongPtext);
        final Button new_acc_button = findViewById(R.id.button2);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {
                String username = username_input.getText().toString();
                String password = password_input.getText().toString();
                UserManager manager = new UserManager();
                if (!manager.checkUser(username, password)) {
                    wrongPtext.setVisibility(View.VISIBLE);
                } else {
                    //textView.setText("Sign in complete!");
                    //textView.setVisibility(View.VISIBLE);
                    Intent afterLoginIntent = new Intent(MainActivity.this, Homepage.class);
                    startActivity(afterLoginIntent);
                    finish();
                }
            }
        });

        new_acc_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register_intent = new Intent(MainActivity.this, ActivityRegisterGui.class);
                startActivity(register_intent);
            }
        });
    }
}