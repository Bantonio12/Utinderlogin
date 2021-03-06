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

/**
 * Page for creating comments:
 * Should add comments to the specified posts
 * Comments should consist of three elements: text, id, mention
 * text: The text within the comment
 * id: The id of the specific comment
 * mention: The comment/ post this comment is mentioning
 */

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

        /**
         * Command to create the comment in form: "@" + id of the original post or comment + input comment
         * and update corresponding post data in database. Then navigate back to the original post:
         */
        postComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CreateCommentActivity.this, ViewPostActivity.class);

                String comment = commentInput.getText().toString();

                /**
                 * Reference to the firestore firebase, and corresponding document saving information of
                 * current posts from all users.
                 */
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                DocumentReference postsRef = db.document("community/Posts");


                HashMap newPostMap = new HashMap();

                /**
                 * Updating the post in database by adding the comment made to the corresponding
                 * comments list.
                 */
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

        /**
         * Command to navigate back to the original post page without changing any thing:
         */
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