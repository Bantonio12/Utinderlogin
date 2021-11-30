package com.example.myapplication.community;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class PostManager {
    private final ArrayList<Post> postlist;

    public PostManager(){
        this.postlist = new ArrayList<Post>();
    }

    public ArrayList<Post> getPostList(){
        return this.postlist;
    }

    public ArrayList<String> displayTitle() {
        ArrayList<String> titles = new ArrayList<>();
        for (Post mainPost : postlist) {
            titles.add(mainPost.getTitle());
        }
        return titles;
    }

    public void makePost(String text, /*User postMaker,*/ String title){//not include user for now!!
        Post post = new Post(text, /*postMaker,*/ title);
        this.postlist.add(post);
    }

    public void makeComment(String text/*, User postMaker*/, Post post){//not include user for now!!
        Post comment = new Post(text/*, postMaker*/);//not include user for now!!
        post.addComment(comment);
    }

    public void deleteMainPost(Post current_post){
        this.postlist.remove(current_post);
    }

    public void deleteComment(Post current_post, Post current_comment){
        current_post.removeComment(current_comment);
    }

    public void likePost(Post post){
        post.addLike();
    }

    public void unlikePost(Post post) { post.removeLike(); }

    public void editPost(Post post, String newText) {
        post.editText(newText);
    }

    public ArrayList<String> getTitleList() {
        ArrayList<String> title_lst = new ArrayList<>();

        for (Post post : postlist) {
            title_lst.add(post.getTitle());

        }
        return title_lst;
    }
}
