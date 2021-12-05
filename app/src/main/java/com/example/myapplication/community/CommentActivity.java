package com.example.myapplication.community;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.R;
import com.example.myapplication.homepage.Homepage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class CommentActivity extends AppCompatActivity {

    Button backButton;
    Button replyButton;
    TextView commentContent;
    ListView comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        Intent intent = getIntent();

        String parentTitle = intent.getStringExtra("parent");
        String currText = intent.getStringExtra("comment_path");

        backButton = findViewById(R.id.backToPostButton);
        replyButton = findViewById(R.id.replyCommentButton);
        commentContent = findViewById(R.id.commentText);
        comments = findViewById(R.id.listview);

        ArrayList commentText = new ArrayList();

        //get reference to firestore database
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference postsRef = db.document("community/Posts");

        //update current page's comment content
        commentContent.setText(currText);

        //update current page's comments list
        postsRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    HashMap postList = (HashMap) document.get("PostList");
                    HashMap parentPost = (HashMap) (postList.get(parentTitle));
                    ArrayList<HashMap> parentComments = (ArrayList<HashMap>) parentPost.get("comments");

                    HashMap currComment = new HashMap();

                    for (HashMap comment : parentComments) {
                        if (comment.get("text").toString() == currText) {
                            currComment = comment;
                        }
                    }

                    //still need to add comment's comments to the list view.

                }
            }
        });

        // visualize the scrollable comments list
        ArrayAdapter arrayAdapter = new ArrayAdapter
                (getApplicationContext(), android.R.layout.simple_list_item_1, commentText);

                    comments.setAdapter(arrayAdapter);


        // command to enter individual comment page
        comments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0) {
                    startActivity(new Intent(CommentActivity.this, CommentActivity.class));
                } else if(position==1) {
                    startActivity(new Intent(CommentActivity.this, MakingPostActivity.class));
                } else {
                    startActivity(new Intent(CommentActivity.this, MakingPostActivity.class));
                }
            }
        });

        // back to the main post page
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CommentActivity.this, ViewPostActivity.class);
                intent.putExtra("title", parentTitle);
                startActivity(intent);
            }
        });

        // reply to the comment
        replyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CommentActivity.this, CreateCommentActivity.class);
                intent.putExtra("title", parentTitle);
                startActivity(intent);
                // still need to add new reply comment page and update database.
            }
        });

        }
}