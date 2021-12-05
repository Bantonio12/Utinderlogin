package com.example.myapplication.community;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;

public class Post implements Parcelable {
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

    protected Post(Parcel in) {
        title = in.readString();
        text = in.readString();
        comments = in.createTypedArrayList(Post.CREATOR);
        likes = in.readInt();
        postMaker = in.readParcelable(FirebaseUser.class.getClassLoader());
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(text);
        parcel.writeTypedList(comments);
        parcel.writeInt(likes);
        parcel.writeParcelable(postMaker, i);
    }
}