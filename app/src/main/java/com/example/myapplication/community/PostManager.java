package com.example.myapplication.community;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.*;

import java.util.ArrayList;
import java.util.Collection;

public class PostManager {
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final CollectionReference posts = db.collection("community");

    public PostManager(){}

    public void makePost(String text, String title){
        Post post = new Post(text, title);
        posts.add(post);
    }

    public ArrayList<Post> loadPosts(){
        ArrayList<Post> postlist = new ArrayList<>();

        posts.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                for (QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots){
                    Post post = documentSnapshot.toObject(Post.class);

                    postlist.add(post);
                }
            }
        });

        return postlist;
    }

    public void makeComment(String text/*, User postMaker*/, Post post){//not include user for now!!
        Post comment = new Post(text/*, postMaker*/);//not include user for now!!
        post.addComment(comment);
    }

    public void deleteComment(Post current_post, Post current_comment){
        current_post.removeComment(current_comment);
    }

    public void likePost(Post post){
        post.addLike();
    }

    public void unlikePost(Post post) { post.removeLike(); }
}
