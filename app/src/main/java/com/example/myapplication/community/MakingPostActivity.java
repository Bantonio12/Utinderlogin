package com.example.myapplication.community;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.R;

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

        titleInput = (EditText) findViewById(R.id.post_title);
        textInput = (EditText) findViewById(R.id.post_text);

        post_button = (Button) findViewById(R.id.post_button);
        back_button = (Button) findViewById(R.id.back_button);

        post_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MakingPostActivity.this, CommunityActivity.class);
                Bundle extras = new Bundle();
                extras.putString("title", titleInput.toString());
                extras.putString("text", textInput.toString());
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


