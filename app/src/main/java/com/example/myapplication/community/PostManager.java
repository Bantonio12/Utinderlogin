package com.example.myapplication.community;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class PostManager {

    ArrayList<Post> mainPosts;

    public PostManager(){
        this.mainPosts = new ArrayList<>();
    }

    public void setMainPosts(HashMap posts) {

        if (posts != null) {
        for (HashMap m : posts.values()) {
            String title = (String) m.get("title");
            String text = (String) m.get("text");
            ArrayList comments = (ArrayList) posts.get("comments");
            FirebaseUser user = (FirebaseUser) posts.get("user");
            int likes = (int) posts.get("likes");
            mainPosts.add(createPost(title, text, user));

        }}

    }

    public Post createPost(String title, String text, FirebaseUser user) {
        Post post = new Post(text, user, title);
        return post;
    }

    public void addComment(Post mainPost, String title, String id, String mention) {
        HashMap<String, String> comment = new HashMap();
        comment.put("title", title);
        comment.put("id", id);
        comment.put("mention", mention);
        ArrayList<HashMap> comments = mainPost.getComments();
        comments.add(comment);
        mainPost.setComments(comments);
    }

    public ArrayList<HashMap> getComments(String postTitle) {
        for (Post post : mainPosts) {
            if (post.getTitle().equals(postTitle)) {
                return post.getComments();
            }
        }
    }

}