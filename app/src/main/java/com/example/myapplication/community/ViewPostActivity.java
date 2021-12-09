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

/**
 * Page for viewing individual posts
 * Button for making posts that extend to a new page
 * Username displayed on screen
 * Title displayed on screen
 * Text displayed on screen
 * ListView of all the comments displayed on screen
 * Comments are clickable and can be replied to
 */
public class ViewPostActivity extends AppCompatActivity {

    Button backButton;
    Button replyButton;
    TextView postText;
    TextView postTitle;
    TextView userName;
    ListView comments;
    ArrayList<String> comment_text = new ArrayList<>();
    ArrayList<HashMap> allComments = new ArrayList<>();

    HashMap postList = new HashMap();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);

        Intent intent = getIntent();

        String title = intent.getExtras().getString("title");

        backButton = findViewById(R.id.Back);
        replyButton = findViewById(R.id.replyCommentButton);
        postTitle = findViewById(R.id.postTitle);
        postText = findViewById(R.id.postText);
        comments = findViewById(R.id.listview);
        userName = findViewById(R.id.userName);

        /**
         * Visualizing the comments of the current post in form of scrollable/ clickable
         * list view.
         */
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, comment_text);
        comments.setAdapter(arrayAdapter);

        /**
         * reference to firestore database and corresponding document
         */
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference postsRef = db.document("community/Posts");

        /**
         * Retrieving current post info from firestore database and visualize in the current page
         */
        postsRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if ((task.isSuccessful())){
                    DocumentSnapshot document = task.getResult();
                    postList = (HashMap) document.get("PostList"); // gets the post list
                    HashMap currPost = (HashMap) (postList.get(title)); // currPost is in the dictionary for the current post
                    postTitle.setText(currPost.get("title").toString());
                    postText.setText(currPost.get("text").toString());
                    allComments = (ArrayList<HashMap>) currPost.get("comments");
                    userName.setText(currPost.get("postMaker").toString());

                    /** currPost.get("comments") is an array list of dictionarys (which are comments) */
                    for(HashMap comment: allComments){
                        if (!comment.equals(null)){
                            comment_text.add("id" + comment.get("id") + ":" + "\n      @id" + comment.get("mention")
                                    + "   " + comment.get("text"));
                        }
                    }
                }
            }
        });

        /**
         * Command navigate back to community page without changing anything
         */
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewPostActivity.this, CommunityActivity.class);
                startActivity(intent);
            }
        });

        userName.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewPostActivity.this, CommunityActivity.class);
                intent.putExtra("userName", userName.getText());
                startActivity(intent);
            }
        });

        /**
         * Command to reply to the current post by entering the Create Comment Page
         */
        replyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewPostActivity.this, CreateCommentActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("id", allComments.size());
                intent.putExtra("mention", "_Main");
                startActivity(intent);
            }
        });

        /**
         * Command to reply individual comment by entering Create Comment Page
         */
        comments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ArrayList<String> commentPath = new ArrayList<>();
                int newId = position;
                commentPath.add(comment_text.get(position));
                Intent intent = new Intent(ViewPostActivity.this, CreateCommentActivity.class);
                intent.putExtra("title", postTitle.getText());
                intent.putExtra("id", allComments.size());
                intent.putExtra("mention", newId);
                startActivity(intent);
            }
        });
    }
}