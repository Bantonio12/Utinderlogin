package com.example.myapplication.community;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.*;
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
 * Page for making posts
 * Can insert title / text of the post, and it will be created
 */
public class MakingPostActivity extends AppCompatActivity {

    EditText titleInput;
    EditText textInput;

    Button post_button;
    Button back_button;


    private HashMap posts = new HashMap<>();
    private HashMap userPosts = new HashMap();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_making_post);

        titleInput = findViewById(R.id.post_title);
        textInput = findViewById(R.id.post_text);

        post_button = findViewById(R.id.post_button);
        back_button = findViewById(R.id.backToPostButton);

        /**
         * Command to create the new post and update database
         */
        post_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                DocumentReference postsRef = db.document("community/Posts");
                DocumentReference userPostsTestRef = db.document("community/UserPosts");

                String postMaker = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
                String title = titleInput.getText().toString();
                String text = textInput.getText().toString();

                HashMap postContent = new HashMap();
                postContent.put("title", title);
                postContent.put("text", text);
                postContent.put("comments", new ArrayList<HashMap>());
                postContent.put("postMaker", postMaker);


                /**
                 * update Posts document in database
                 */
                postsRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){

                            DocumentSnapshot document = task.getResult();
                            posts = (HashMap) document.get("PostList");

                            posts.put(title, postContent);

                            HashMap newData = new HashMap<>();
                            newData.put("PostList", posts);
                            postsRef.set(newData);

                        }
                        else {
                            Log.d("Making Post", "failed to obtain posts", task.getException());
                        }
                    }
                });

                /**
                 * update UserPosts document in database
                 */
                userPostsTestRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){

                            DocumentSnapshot document = task.getResult();
                            userPosts = (HashMap) document.get("UserPostsList");

                            ArrayList currPostsList = (ArrayList) userPosts.get("admin");

                            currPostsList.add(postContent);

                            userPosts.put("admin", currPostsList);

                            HashMap newData = new HashMap();

                            newData.put("UserPostsList", userPosts);

                            userPostsTestRef.set(newData);
                        }
                        else {
                            Log.d("Making User Post", "failed to obtain user posts", task.getException());
                        }
                    }
                });

                Intent intent = new Intent(MakingPostActivity.this, CommunityActivity.class);

                if (!title.isEmpty()) {
                    startActivity(intent);
                    showToast("Successful post");
                } else {
                    startActivity(intent);
                    showToast("Post Unsuccessful, Please include a Title");}

            }
        });

        /**
         * Command to go back to community page:
         */
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MakingPostActivity.this, CommunityActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
     * message when making post successfully
     * @param text the text that is going to be displayed
     */
    private void showToast(String text){
        Toast.makeText(MakingPostActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}