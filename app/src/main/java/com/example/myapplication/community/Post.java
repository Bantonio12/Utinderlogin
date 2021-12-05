package com.example.myapplication.community;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;

public class Post {
    private String title;
    private String text;
    private ArrayList<Post> comments; //a sub post can only be a comment.
    private int likes;
    private FirebaseUser postMaker;

    public Post(){
        //public no-arg constructor needed
    }

    public Post(String text, FirebaseUser postMaker, String title){//Constructor for a post //not include user for now!!
        this.title = title;
        this.text = text;
        this.comments = new ArrayList<>();
        this.likes = 0;
        this.postMaker = postMaker;
    }

    public Post(String text, FirebaseUser postMaker){//Constructor for a comment //not include user for now!!
        this.title = null;
        this.text = text;
        this.comments = new ArrayList<>();
        this.likes = 0;
        this.postMaker = postMaker;
    }

    public String getTitle() { return  this.title; }

    public String getText(){ return this.text; }

    public ArrayList<Post> getComments(){ return this.comments; }

    public int getLikes(){ return this.likes; }

    public FirebaseUser getPostMaker(){return this.postMaker; }

    public void addComment(Post comment) { this.comments.add(comment); }

    public void removeComment(Post current_comment){ this.comments.remove(current_comment); }

    public void addLike(){ this.likes += 1; }

    public void removeLike() {
        if (this.likes > 0) {
            this.likes -= 1;
        }
    }

}