package com.example.myapplication.community;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * An use case class that manages and manipulates the customized Post objects.
 */
public class PostManager {

    private HashMap postList;

    public PostManager(HashMap postList){
        this.postList = postList;
    }

    /**
     * Method to create a revised Post object that includes the recent made comment, and
     * return the new created Post object.
     * @param comment the content of the new comment
     * @param id the id of the comment
     * @param mention the comment that the new comment is replying to.
     * @param title the title of the original post.
     * @return Post object that includes the recent made comment.
     */
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