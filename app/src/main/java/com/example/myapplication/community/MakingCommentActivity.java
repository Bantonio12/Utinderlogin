package com.example.myapplication.community;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

public class MakingCommentActivity extends AppCompatActivity {

    Button postComment = findViewById(R.id.postComment);
    TextView commentInput = findViewById(R.id.makingCommentText);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_making_comment);

//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        DocumentReference postsRef = db.document("community/Posts");
//        FirebaseUser postMaker = FirebaseAuth.getInstance().getCurrentUser();
//
//        Intent intent = getIntent();
//
//        String title = intent.getExtras().getString("title");

//        postComment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MakingCommentActivity.this, ViewPostActivity.class);
//                String comment = commentInput.getText().toString();
//
//                Post newComment = new Post(comment, postMaker);
//
//                postsRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                        if(task.isSuccessful()){
//                            DocumentSnapshot document = task.getResult();
//                            HashMap postList = (HashMap) document.get("PostList");
//                            HashMap currPost = (HashMap) postList.get(title);
//
//                            ArrayList currComments = (ArrayList) currPost.get("comments");
//                            currComments.add(newComment);
//
//                            currPost.put("comments", currComments);
//
//                            postList.put(title, currPost);
//
//                            postsRef.set(postList);
//                        }
//                    }
//                });
//            }
//        });
    }
}