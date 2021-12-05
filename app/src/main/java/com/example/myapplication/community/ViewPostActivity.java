package com.example.myapplication.community;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class ViewPostActivity extends AppCompatActivity {

    Button backButton;
    Button replyButton;
    Button followButton;
    TextView postText;
    TextView postTitle;
    TextView userName;
    ListView comments;
    ArrayList<String> comment_text = new ArrayList<>();

    HashMap postList = new HashMap();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);

        Intent intent = getIntent();

        String title = intent.getExtras().getString("title");

        backButton = findViewById(R.id.Back);
        followButton = findViewById(R.id.followButton);
        replyButton = findViewById(R.id.reply_button);
        postTitle = findViewById(R.id.postTitle);
        postText = findViewById(R.id.postText);
        comments = findViewById(R.id.listview);
        userName = findViewById(R.id.userName);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference postsRef = db.document("community/Posts");

        postsRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if ((task.isSuccessful())){
                    DocumentSnapshot document = task.getResult();
                    postList = (HashMap) document.get("PostList");
                    HashMap currPost = (HashMap) (postList.get(title));
                    postTitle.setText(currPost.get("title").toString());
                    postText.setText(currPost.get("text").toString());
                    userName.setText("admin");

                    for(HashMap comment: (ArrayList<HashMap>) currPost.get("comments")){
                        comment_text.add(comment.get("text").toString());
                    }
                }
            }
        });

        //Back to community call:
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewPostActivity.this, CommunityActivity.class);
                startActivity(intent);
            }
        });

        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewPostActivity.this, CommunityActivity.class);
                startActivity(intent);
            }
        });




        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, comment_text);
        comments.setAdapter(arrayAdapter);

        comments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ArrayList<String> commentPath = new ArrayList<>();
                commentPath.add(comment_text.get(position));
                Intent intent = new Intent(ViewPostActivity.this, CommentActivity.class);
//                intent.putExtra("parent", findViewById(R.id.postTitle).get);
//                intent.putExtra("comment_path", currCommentPost);

                startActivity(intent);

                });
            }
        });
    }
    }