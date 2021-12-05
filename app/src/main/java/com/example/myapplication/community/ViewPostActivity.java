package com.example.myapplication.community;

import android.content.Intent;
import android.view.View;
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
        replyButton = findViewById(R.id.replyCommentButton);
        postTitle = findViewById(R.id.postTitle);
        postText = findViewById(R.id.postText);
        comments = findViewById(R.id.listview);
        userName = findViewById(R.id.userName);

        // reference to firestore database
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference postsRef = db.document("community/Posts");

        // obtain current post info from firebase and visualize in current page
        postsRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if ((task.isSuccessful())){
                    DocumentSnapshot document = task.getResult();
                    postList = (HashMap) document.get("PostList"); // gets the post list
                    HashMap currPost = (HashMap) (postList.get(title)); // currPost is in the dictionary for the current post
                    postTitle.setText(currPost.get("title").toString());
                    postText.setText(currPost.get("text").toString());
                    userName.setText("admin");

                    for(HashMap comment: (ArrayList<HashMap>) currPost.get("comments")){ // currPost.get("comments") is an array list of dictionarys (which are comments)
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

        // Command to enter creating comment page
        replyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewPostActivity.this, CreateCommentActivity.class);
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });

        // Command to follow the current user
        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewPostActivity.this, CommunityActivity.class);
                startActivity(intent);
            }
        });


        // Visualizing the scrollable list view
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, comment_text);
        comments.setAdapter(arrayAdapter);

        // Command to enter individual comment page
        comments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ArrayList<String> commentPath = new ArrayList<>();
                commentPath.add(comment_text.get(position));
                Intent intent = new Intent(ViewPostActivity.this, CommentActivity.class);
                intent.putExtra("parent", postTitle.getText());
                intent.putExtra("comment_path", comment_text.get(position));
                startActivity(intent);
            }
        });
    }
}