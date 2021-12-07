package com.example.myapplication.community;

import java.util.HashMap;

public class PostDataConverter {
    HashMap postData;

    public PostDataConverter(HashMap data) {
        this.postData = data;
    }

    public void addNewPost(String title, String text, String id) {
        PostManager p = new PostManager();
        p.addComment();
    }
}
