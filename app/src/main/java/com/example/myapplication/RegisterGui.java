package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RegisterGui extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_gui);

        final Button registerButton = findViewById(R.id.registerButton);
        final TextView email_input = findViewById(R.id.email_input);
        final TextView nickname_input = findViewById(R.id.nicknameInput);
        final EditText password_input = findViewById(R.id.newUserPassword);
        final TextView warning_message = findViewById(R.id.same_acc_text);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                UserManager usermanager = new UserManager();
                UserData data = new UserData();
                String email = email_input.getText().toString();
                String name = nickname_input.getText().toString();
                String password = password_input.getText().toString();
                if (!data.findUsername(name) && !data.findEmail(email)) {
                    boolean temp = usermanager.createUser(name, email, password);
                    if (temp) {
                        Intent back_to_login_intent = new Intent(RegisterGui.this, MainActivity.class);
                        startActivity(back_to_login_intent);
                    } else {
                        warning_message.setText("Account could not be created");
                        warning_message.setVisibility(View.VISIBLE);
                    }
                } else {
                    warning_message.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}