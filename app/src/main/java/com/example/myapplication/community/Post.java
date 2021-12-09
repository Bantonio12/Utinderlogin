package com.example.myapplication.community;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Post {
    private String title;
    private String text;
    private ArrayList<HashMap> comments;
    private int likes;
    private String postMaker;

    /**
     * public no-arg constructor needed for firebase to work
     */
    public Post(){

    }

    /**
     * Constructor for a post
     * @param text the text for the post
     * @param postMaker the post makers ID of the post
     * @param title the title of the post
     */
    public Post(String text, String postMaker, String title){
        this.title = title;
        this.text = text;
        this.comments = new ArrayList<>();
        this.likes = 0;
        this.postMaker = postMaker;
    }

    /**
     * Getter method for the title of posts
     * @return the string title
     */
    public String getTitle() { return  this.title; }

    /**
     * Getter method for the text of posts
     * @return the string text
     */
    public String getText(){ return this.text; }

    /**
     * Getter method for the comments of the post
     * @return Returns an array list of Post objects
     */
    public ArrayList<HashMap> getComments(){ return this.comments; }

    /**
     * Getter method for the User of the post from Firebase
     * @return returns a String object, the post maker for this post (post makers ID)
     */
    public String getPostMaker(){ return this.postMaker; }

    /**
     * Adder method for the comments of post
     * @param comment the comment that should be added into the post
     */
    public void addComment(HashMap comment) { this.comments.add(comment); }

    /**
     * Settter method for the comments of a post
     * @param comments array list of hashmaps (comments)
     */
    public void setComments(ArrayList<HashMap> comments) {this.comments = comments; }

    /**
     * Remove the comment from the post
     * @param current_comment Post object that you want to remove
     */
    public void removeComment(Post current_comment){ this.comments.remove(current_comment); }

}