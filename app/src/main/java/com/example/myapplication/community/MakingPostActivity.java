package com.example.myapplication.community;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.R;

import java.util.ArrayList;

public class MakingPostActivity extends AppCompatActivity {

    String title, text;

    EditText titleInput;
    EditText textInput;

    Button post_button;
    Button back_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_making_post);

        titleInput = findViewById(R.id.post_title);
        textInput = findViewById(R.id.post_text);

        post_button = findViewById(R.id.post_button);
        back_button = findViewById(R.id.back_button);

        post_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MakingPostActivity.this, CommunityActivity.class);
                Bundle extras = new Bundle();
                extras.putString("title", titleInput.getText().toString());
                extras.putString("text", textInput.getText().toString());
                intent.putExtras(extras);
                startActivity(intent);
                showToast("Successful post");
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MakingPostActivity.this, CommunityActivity.class);
                startActivity(intent);
            }
        });

    }

    private void showToast(String text){
        Toast.makeText(MakingPostActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}


