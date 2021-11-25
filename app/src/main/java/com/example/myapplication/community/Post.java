package com.example.myapplication.community;
import com.example.myapplication.login.user.User;

import java.util.ArrayList;

public class Post {
    private String title;
    private String text;
    private ArrayList<Post> comments; //a sub post can only be a comment.
    private int likes;
    private User postMaker;

    public Post(String text, User postmaker, String title){//Constructor for a post
        this.title = title;
        this.text = text;
        this.comments = new ArrayList<>();
        this.likes = 0;
        this.postMaker = postmaker;
    }

    public Post(String text, User postmaker){//Constructor for a comment
        this.title = null;
        this.text = text;
        this.comments = new ArrayList<>();
        this.likes = 0;
        this.postMaker = postmaker;
    }

    public Post getPost(PostManager manager, int postIndex){
        return manager.getPostList().get(postIndex);
    }

    public String getTitle() { return  this.title; }

    public String getText(){
        return this.text;
    }

    public void editText(String newText) { this.text = newText;}

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