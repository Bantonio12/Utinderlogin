package com.example.myapplication;

import com.example.myapplication.login.user.User;
import com.example.myapplication.community.*;
import org.junit.Before;
import org.junit.Test;

public class PostTest {
    private PostManager testManager;
    private User testUser1;
    private User testUser2;

    @Before
    public void setUp() {
        testManager = new PostManager();
        testUser1 = new User("michael", "123@utoronto.ca", "123abc");
        testUser2 = new User("kex", "456@utoronto.ca", "456def");
    }

    @Test
    // Test to see if posts can be created
    public void createPostTest() {
        String text = "Testing testing testingggg!!";
        User user = testUser1;
        String title = "Test 1!!!";
        testManager.makePost(text, user, title);
        Post createdPost = testManager.getPostList().get(0);
        String actualText = createdPost.getText();
        String actualTitle = createdPost.getTitle();
        assert text.equals(actualText) && title.equals(actualTitle);
    }

    @Test
    // Test to see if posts can be deleted
    public void deletePostTest() {
        String text = "Testing testing testingggg!!";
        User user = testUser1;
        String title = "Test 2!!!";
        testManager.makePost(text, user, title);
        Post createdPost = testManager.getPostList().get(0);
        testManager.deleteMainPost(createdPost);
        int postListLength = 0;
        int actualPostListLength = testManager.getPostList().size();
        assert postListLength == actualPostListLength;
    }

    @Test
    // Test to see if posts can be edited
    public void editPostTest() {
        String text = "Testing testing testingggg!!";
        String newText = "Oopsies I made a typo!!";
        User user = testUser1;
        String title = "Test 3!!!";
        testManager.makePost(text, user, title);
        Post createdPost = testManager.getPostList().get(0);
        testManager.editPost(createdPost, newText);
        String actualText = createdPost.getText();

        assert newText.equals(actualText);
    }

    @Test
    // Test to see if comments can be made under a post
    public void makeCommentTest() {
        String postText = "Testing testing testingggg!!";
        String commentText = "Woahhh I'm apart of the test too!!";
        User user1 = testUser1;
        User user2 = testUser2;
        String title = "Test 4!!!";
        testManager.makePost(postText, user1, title);
        Post createdPost = testManager.getPostList().get(0);
        testManager.makeComment(commentText, user2, createdPost);
        Post createdComment = createdPost.getComments().get(0);
        assert commentText.equals(createdComment.getText());
    }

    @Test
    // Test to see if comments can be deleted under a post
    public void deleteCommentTest() {
        String postText = "Testing testing testingggg!!";
        String commentText = "Woahhh I'm apart of the test too!!";
        User user1 = testUser1;
        User user2 = testUser2;
        String title = "Test 5!!!";
        testManager.makePost(postText, user1, title);
        Post createdPost = testManager.getPostList().get(0);
        testManager.makeComment(commentText, user2, createdPost);
        Post createdComment = createdPost.getComments().get(0);
        testManager.deleteComment(createdPost, createdComment);
        int numComments = 0;
        int actualNumComments = createdPost.getComments().size();
        assert numComments == actualNumComments;
    }

    @Test
    // Test to see if like feature works
    public void likeTest1() {
        String postText = "Testing testing testingggg!!";
        User user1 = testUser1;
        String title = "Test 6!!!";
        testManager.makePost(postText, user1, title);
        Post createdPost = testManager.getPostList().get(0);
        testManager.likePost(createdPost);
        testManager.likePost(createdPost);
        testManager.likePost(createdPost);
        int numLikes = 3;
        int actualLikes = createdPost.getLikes();
        assert numLikes == actualLikes;
    }

    @Test
    // Test to see if the unlike feature works
    public void likeTest2() {
        String postText = "Testing testing testingggg!!";
        User user1 = testUser1;
        String title = "Test 7!!!";
        testManager.makePost(postText, user1, title);
        Post createdPost = testManager.getPostList().get(0);
        testManager.likePost(createdPost);
        testManager.likePost(createdPost);
        testManager.unlikePost(createdPost);
        testManager.unlikePost(createdPost);
        int numLikes = 0;
        int actualLikes = createdPost.getLikes();
        assert numLikes == actualLikes;
    }

    @Test
    // Test to see that likes won't become negative
    public void likeTest3() {
        String postText = "Testing testing testingggg!!";
        User user1 = testUser1;
        String title = "Test 8!!!";
        testManager.makePost(postText, user1, title);
        Post createdPost = testManager.getPostList().get(0);
        testManager.likePost(createdPost);
        testManager.unlikePost(createdPost);
        testManager.unlikePost(createdPost);
        int numLikes = 0;
        int actualLikes = createdPost.getLikes();
        assert numLikes == actualLikes;
    }

}