package com.example.myapplication.community;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class CreateCommentActivity extends AppCompatActivity {

    Button postComment;
    Button backToPost;
    TextView commentInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_comment);

        postComment = findViewById(R.id.postComment);
        backToPost = findViewById(R.id.backToPost);
        commentInput = findViewById(R.id.makingCommentText);

        Intent intent = getIntent();

        String title = intent.getExtras().getString("title");

        int id = intent.getExtras().getInt("id");

        Object mention = intent.getExtras().get("mention");

        //Command to create the comment and update database
        postComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CreateCommentActivity.this, ViewPostActivity.class);

                String comment = commentInput.getText().toString();

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                DocumentReference postsRef = db.document("community/Posts");


                HashMap newPostMap = new HashMap();
                postsRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            HashMap postMap = (HashMap) document.get("PostList");

                            PostDataConverter converter = new PostDataConverter(postMap);
                            HashMap newPost = converter.makeComment(id, comment, mention, title);

                            postMap.put(title, newPost);

                            HashMap newPostList = new HashMap();
                            newPostList.put("PostList", postMap);

                            postsRef.set(newPostList);

                        }
                    }
                });

                intent.putExtra("title", title);
                startActivity(intent);
            }
        });

        // Command to go back to main post
        backToPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateCommentActivity.this, ViewPostActivity.class);
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });
    }
}