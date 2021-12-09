package com.example.myapplication.community;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A data converter that converts the data type retrieved from database into
 * usable types for PostManager to create customized post object.
 */
public class PostDataConverter {

    private HashMap postList;

    public PostDataConverter(HashMap postList){
        this.postList = postList;
    }

    /**
     * Method to create an updated version of given post including the recent made comment,
     * and return a hashmap object(usable type) for updating the database.
     * @param id the id of the new comment.
     * @param comment the content of the new comment.
     * @param mention the comment that the new comment is replying to.
     * @param title the title of the original post.
     * @return a HashMap representing the revised Post object that includes recent made comment.
     */
    public HashMap makeComment(int id, String comment, Object mention, String title){
        PostManager p = new PostManager(postList);
        Post newPost = p.makeComment(comment, id, mention, title);

        HashMap newPostMap = new HashMap();

        newPostMap.put("title", newPost.getTitle());
        newPostMap.put("text", newPost.getText());
        newPostMap.put("postMaker", newPost.getPostMaker());
        newPostMap.put("comments", newPost.getComments());

        return newPostMap;

    }

}
