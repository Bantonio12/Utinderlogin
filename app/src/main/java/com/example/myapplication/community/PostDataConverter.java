package com.example.myapplication.community;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.HashMap;

public class PostDataConverter {

    private HashMap postList;

    public PostDataConverter(HashMap postList){
        this.postList = postList;
    }

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
