package com.example.myapplication.community;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.*;

import java.util.ArrayList;
import java.util.Collection;

public class PostManager {

//    public PostManager(){}
//    public void makeComment(String text/*, User postMaker*/, Post post){//not include user for now!!
//        Post comment = new Post(text/*, postMaker*/);//not include user for now!!
//        post.addComment(comment);
//    }

    public void deleteComment(Post current_post, Post current_comment){
        current_post.removeComment(current_comment);
    }

    public void likePost(Post post){
        post.addLike();
    }

    public void unlikePost(Post post) { post.removeLike(); }
}
