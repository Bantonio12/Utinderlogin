package com.example.myapplication.community;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.HashMap;

public class PostDataConverter {
    private DataAccess rawData;

    public PostDataConverter(DataAccess rawData) {
        this.rawData = rawData;
    }


    public HashMap makeComment(String comment, int id, Object mention, String title) {
        PostManager p = new PostManager(rawData);
        Post newPost = p.makeComment(comment, id, mention, title);
        HashMap post = convertPost(newPost);
        return post;
    }

    private HashMap convertPost(Post newPost) {
        HashMap postMap = new HashMap();
        String title = newPost.getTitle();
        String text = newPost.getText();
        ArrayList comments = newPost.getComments();
        FirebaseUser postMaker = newPost.getPostMaker();
        postMap.put("title", title);
        postMap.put("text", text);
        postMap.put("comments", comments);
        postMap.put("postMaker", postMaker);
        return postMap;
    }
}
