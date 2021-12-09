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

        Post newPost = new Post((String) currPost.get("text"), (String) currPost.get("postMaker"), (String) currPost.get("title"));

        newPost.setComments((ArrayList<HashMap>) currPost.get("comments"));


        HashMap newComment = new HashMap();

        if (comment.equals("")) {
            newComment.put("text", "empty_comment");
        }
        else {
            newComment.put("text", comment);
        }


        newComment.put("id", id);
        newComment.put("mention", mention);

        newPost.addComment(newComment);

        return newPost;
    }

}