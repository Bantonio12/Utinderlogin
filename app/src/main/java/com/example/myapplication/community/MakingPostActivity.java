package com.example.myapplication.community;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.R;
import com.example.myapplication.login.user.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MakingPostActivity extends AppCompatActivity {

    EditText titleInput;
    EditText textInput;

    Button post_button;
    Button back_button;


    private HashMap posts = new HashMap<>();
    private ArrayList userPosts = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_making_post);

        titleInput = findViewById(R.id.post_title);
        textInput = findViewById(R.id.post_text);

        post_button = findViewById(R.id.post_button);
        back_button = findViewById(R.id.back_button);

        //Confirm making post call (update firestore database simultaneously)
        post_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get firestore database reference:
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                DocumentReference postsRef = db.collection("community")
                        .document("Posts");
                DocumentReference userPostsRef = db.collection("community")
                        .document("UserPosts");

                String title = titleInput.getText().toString();
                String text = textInput.getText().toString();

                //write post into firestore Posts document call:
                postsRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){

                            DocumentSnapshot document = task.getResult();
                            posts = (HashMap) document.get("Posts");

                            HashMap newData = new HashMap<>();
                            posts.put(title, text);
                            newData.put("Posts", posts);
                            postsRef.set(newData);

                        }
                        else {
                            Log.d("Making Post", "failed to obtain posts", task.getException());
                        }
                    }
                });

                //write post into firestore UserPosts document call:
                userPostsRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){

                            DocumentSnapshot document = task.getResult();
                            userPosts = (ArrayList) document.get("admin");

                            userPosts.add(title);
                            HashMap newData = new HashMap();

                            newData.put("admin", userPosts);

                            userPostsRef.set(newData);
                        }
                        else {
                            Log.d("Making User Post", "failed to obtain user posts", task.getException());
                        }
                    }
                });

                Intent intent = new Intent(MakingPostActivity.this, CommunityActivity.class);
                startActivity(intent);
                showToast("Successful post");
            }
        });

        //Back to community call:
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


