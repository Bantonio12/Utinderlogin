package com.example.myapplication.post;

import com.example.myapplication.event.ui.ActivityEvent;
import com.example.myapplication.login.user.User;
import com.example.myapplication.community.*;
import com.google.firebase.auth.FirebaseUser;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class PostTest {
    private HashMap testingPostList;
    private HashMap post1;
    private HashMap post2;
    private ArrayList comments;
    private HashMap comment;
    private PostManager testManager;
    private FirebaseUser testUser1;
    private User testUser2;

    @Before
    public void setUp() {
        testingPostList = new HashMap();
        post1 = new HashMap();
        post2 = new HashMap();

        comments = new ArrayList();
        comment = new HashMap();
        comment.put("text", "hasda");
        comment.put("id", 3);
        comment.put("mention", "id0");
        comments.add(comment);


        testUser1 = null;
        testUser2 = new User("kex", "456@utoronto.ca", "456def");

        post1.put("title", "hello_world");
        post1.put("text", "im aliiiveee");
        post1.put("comments", comments);
        post1.put("postMaker", (FirebaseUser) testUser1);

        post2.put("title", "good morning");
        post2.put("text", "wonderful blue skieesss for daysss");
        post2.put("comments", comments);
        post2.put("postMaker", (FirebaseUser) testUser1);

        testingPostList.put("hello_world", post1);
        testingPostList.put("good morning", post2);

        testManager = new PostManager(testingPostList);

    }


    @Test
    public void makeCommentTitleTest() {
        String comment = "Testing testing testingggg!!";
        int id = 0;
        Object mention = "_main";;
        String title = "hello_world";
        Post createdPost = testManager.makeComment(comment, id, mention, title);
        String createdTitle = createdPost.getTitle();
        assertTrue(createdTitle.equals(title));
    }


    @Test
    public void makeCommentTextTest() {
        String comment = "Testing testing testingggg!!";
        int id = 0;
        Object mention = "_main";;
        String title = "hello_world";
        Post createdPost = testManager.makeComment(comment, id, mention, title);
        String createdText = createdPost.getText();
        assertTrue(createdText.equals("im aliiiveee"));
    }




    @Test
    public void makeCommentCommentTextTest() {
        String comment = "Testing testing testingggg!!";
        int id = 0;
        Object mention = "_main";;
        String title = "hello_world";
        Post createdPost = testManager.makeComment(comment, id, mention, title);
        ArrayList createdCommentList = createdPost.getComments();
        HashMap createdComment = (HashMap) createdCommentList.get(1);
        assertTrue(createdComment.get("text") == comment);
    }

    @Test
    public void makeCommentCommentIdTest() {
        String comment = "Testing testing testingggg!!";
        int id = 0;
        Object mention = "_main";;
        String title = "hello_world";
        Post createdPost = testManager.makeComment(comment, id, mention, title);
        ArrayList createdCommentList = createdPost.getComments();
        HashMap createdComment = (HashMap) createdCommentList.get(1);
        assertTrue(createdComment.get("id").equals(id));
    }

    @Test
    public void makeCommentCommentMentionTest() {
        String comment = "Testing testing testingggg!!";
        int id = 0;
        Object mention = "_main";;
        String title = "hello_world";
        Post createdPost = testManager.makeComment(comment, id, mention, title);
        ArrayList createdCommentList = createdPost.getComments();
        HashMap createdComment = (HashMap) createdCommentList.get(1);
        assertTrue(createdComment.get("mention").equals(mention));
    }

//
//    @Test
//    // Test to see if posts can be deleted
//    public void deletePostTest() {
//        String text = "Testing testing testingggg!!";
//        User user = testUser1;
//        String title = "Test 2!!!";
//        testManager.makePost(text,/* user,*/ title);
//        Post createdPost = testManager.getPostList().get(0);
//        testManager.deleteMainPost(createdPost);
//        int postListLength = 0;
//        int actualPostListLength = testManager.getPostList().size();
//        assert postListLength == actualPostListLength;
//    }
//
//    @Test
//    // Test to see if posts can be edited
//    public void editPostTest() {
//        String text = "Testing testing testingggg!!";
//        String newText = "Oopsies I made a typo!!";
//        User user = testUser1;
//        String title = "Test 3!!!";
//        testManager.makePost(text, user, title);
//        Post createdPost = testManager.getPostList().get(0);
//        testManager.editPost(createdPost, newText);
//        String actualText = createdPost.getText();
//
//        assert newText.equals(actualText);
//    }
//
//    @Test
//    // Test to see if comments can be made under a post
//    public void makeCommentTest() {
//        String postText = "Testing testing testingggg!!";
//        String commentText = "Woahhh I'm apart of the test too!!";
//        User user1 = testUser1;
//        User user2 = testUser2;
//        String title = "Test 4!!!";
//        testManager.makePost(postText, user1, title);
//        Post createdPost = testManager.getPostList().get(0);
//        testManager.makeComment(commentText, user2, createdPost);
//        Post createdComment = createdPost.getComments().get(0);
//        assert commentText.equals(createdComment.getText());
//    }
//
//    @Test
//    // Test to see if comments can be deleted under a post
//    public void deleteCommentTest() {
//        String postText = "Testing testing testingggg!!";
//        String commentText = "Woahhh I'm apart of the test too!!";
//        User user1 = testUser1;
//        User user2 = testUser2;
//        String title = "Test 5!!!";
//        testManager.makePost(postText, user1, title);
//        Post createdPost = testManager.getPostList().get(0);
//        testManager.makeComment(commentText, user2, createdPost);
//        Post createdComment = createdPost.getComments().get(0);
//        testManager.deleteComment(createdPost, createdComment);
//        int numComments = 0;
//        int actualNumComments = createdPost.getComments().size();
//        assert numComments == actualNumComments;
//    }
//
//    @Test
//    // Test to see if like feature works
//    public void likeTest1() {
//        String postText = "Testing testing testingggg!!";
//        User user1 = testUser1;
//        String title = "Test 6!!!";
//        testManager.makePost(postText, user1, title);
//        Post createdPost = testManager.getPostList().get(0);
//        testManager.likePost(createdPost);
//        testManager.likePost(createdPost);
//        testManager.likePost(createdPost);
//        int numLikes = 3;
//        int actualLikes = createdPost.getLikes();
//        assert numLikes == actualLikes;
//    }
//
//    @Test
//    // Test to see if the unlike feature works
//    public void likeTest2() {
//        String postText = "Testing testing testingggg!!";
//        User user1 = testUser1;
//        String title = "Test 7!!!";
//        testManager.makePost(postText, user1, title);
//        Post createdPost = testManager.getPostList().get(0);
//        testManager.likePost(createdPost);
//        testManager.likePost(createdPost);
//        testManager.unlikePost(createdPost);
//        testManager.unlikePost(createdPost);
//        int numLikes = 0;
//        int actualLikes = createdPost.getLikes();
//        assert numLikes == actualLikes;
//    }
//
//    @Test
//    // Test to see that likes won't become negative
//    public void likeTest3() {
//        String postText = "Testing testing testingggg!!";
//        User user1 = testUser1;
//        String title = "Test 8!!!";
//        testManager.makePost(postText, user1, title);
//        Post createdPost = testManager.getPostList().get(0);
//        testManager.likePost(createdPost);
//        testManager.unlikePost(createdPost);
//        testManager.unlikePost(createdPost);
//        int numLikes = 0;
//        int actualLikes = createdPost.getLikes();
//        assert numLikes == actualLikes;
//    }


}