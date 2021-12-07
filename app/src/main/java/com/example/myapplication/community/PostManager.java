package com.example.myapplication.community;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class PostManager {

    private HashMap postList;

    public PostManager(HashMap postList){
        this.postList = postList;
    }

    public Post makeComment(String comment, int id, Object mention, String title) {

            HashMap currPost = (HashMap) postList.get(title);

            Post newPost = new Post((String) currPost.get("text"),
                    (FirebaseUser) currPost.get("postMaker"), (String) currPost.get("title"));
            System.out.println(newPost.getComments().toString());

            System.out.println(postList);
            newPost.setComments((ArrayList<HashMap>) currPost.get("comments"));


            HashMap newComment = new HashMap();

            newComment.put("text", comment);
            newComment.put("id", id);
            newComment.put("mention", mention);

            newPost.addComment(newComment);

            return newPost;
    }

}