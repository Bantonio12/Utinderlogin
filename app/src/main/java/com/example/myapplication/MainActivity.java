package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button loginbutton = findViewById(R.id.loginbutton);
        final TextInputEditText username_input = findViewById(R.id.textInputEditText);
        final EditText password_input = findViewById(R.id.Password);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                username_input.getText();
                password_input.getText();
            }
        });
    }
}