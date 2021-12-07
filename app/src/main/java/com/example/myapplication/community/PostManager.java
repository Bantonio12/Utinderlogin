package com.example.myapplication.community;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class PostManager {

    private HashMap postList;

    public PostManager(DataAccess rawData){
        this.postList = rawData.getData();
    }

    public Post makeComment(String comment, int id, Object mention, String mainTitle) {

            HashMap mainPost = (HashMap) postList.get(mainTitle);
            Post post = new Post((String) mainPost.get("text"), (FirebaseUser) mainPost.get("postMaker"), (String) mainPost.get("title"));
            post.setComments((ArrayList<HashMap>) mainPost.get("comments"));
            HashMap newComment = new HashMap();
            newComment.put("text", comment);
            newComment.put("id", id);
            newComment.put("mention", mention);
            post.addComment(newComment);
            return post;
    }

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