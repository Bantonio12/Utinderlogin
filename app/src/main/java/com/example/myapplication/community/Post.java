package com.example.myapplication.community;

import java.util.ArrayList;

public class Post {
    private String title;
    private String text;
    private ArrayList<Post> comments; //a sub post can only be a comment.
    private int likes;
    //private User postMaker;//not include user for now!!

    public Post(){
        //public no-arg constructor needed
    }

    public Post(String text, /*User postmaker,*/ String title){//Constructor for a post //not include user for now!!
        this.title = title;
        this.text = text;
        this.comments = new ArrayList<>();
        this.likes = 0;
        //this.postMaker = postmaker;//not include user for now!!
    }

    public Post(String text/*, User postmaker*/){//Constructor for a comment //not include user for now!!
        this.title = null;
        this.text = text;
        this.comments = new ArrayList<>();
        this.likes = 0;
        //this.postMaker = postmaker;
    }

    public String getTitle() { return  this.title; }

    public String getText(){
        return this.text;
    }

    public void addComment(Post comment) {
        this.comments.add(comment);
    }

    public void removeComment(Post current_comment){
        this.comments.remove(current_comment);
    }

    public ArrayList<Post> getComments(){
        return comments;
    }

    public void addLike(){
        this.likes += 1;
    }

    public void removeLike() {
        if (this.likes > 0) {
            this.likes -= 1;
        }
    }

    public int getLikes(){
        return this.likes;
    }

}