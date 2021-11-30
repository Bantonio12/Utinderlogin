package com.example.myapplication.community;

import com.example.myapplication.login.user.User;

import java.util.ArrayList;

public class PostManager {
    private ArrayList<Post> postlist;

    public PostManager(){
        this.postlist = new ArrayList<>();
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

    public void makePost(String text, User postMaker, String title){
        Post post = new Post(text, postMaker, title);
        this.postlist.add(post);
    }

    public void makeComment(String text, User postMaker, Post post){
        Post comment = new Post(text, postMaker);
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
}
