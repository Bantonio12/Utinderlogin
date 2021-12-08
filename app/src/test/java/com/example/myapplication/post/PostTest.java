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

    /**
     * Test to see whether the created comment was created in the correct Post
     */
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

    /**
     * Test to see whether the created comment was created in the correct Post
     */
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


    /**
     * Test to see whether the created comment has the correct text
     */
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

    /**
     * Test to see whether the created comment has the correct id
     */
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

    /**
     * Test to see whether the created comment has teh correct mention
     */
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

    /**
     * Test to see if multiple comments can be created and created correctly
     */
    @Test
    public void makeMultiCommentTest() {
        String comment = "Testing testing testingggg!!";
        int id = 0;
        Object mention = "_main";;
        String title = "hello_world";
        Post createdPost1 = testManager.makeComment(comment, id, mention, title);
        Post createdPost2 = testManager.makeComment(comment, id, mention, title);
        Post createdPost3 = testManager.makeComment(comment, id, mention, title);
        ArrayList createdCommentList = createdPost1.getComments();
        int size =  createdCommentList.size();
        assertTrue(size == 4);
    }

    /**
     * Test to see if the program recognizes empty comment texts and converts them to "empty_comment"
     */
    @Test
    public void makeCommentNoTextTest() {
        String comment = "";
        int id = 0;
        Object mention = "_main";;
        String title = "hello_world";
        Post createdPost = testManager.makeComment(comment, id, mention, title);
        assertTrue(createdPost.getComments().get(1).get("text").equals("empty_comment"));
    }

    /**
     * Test to see whether the function can be called on multiple different posts using the same comment
     */
    @Test
    public void makeCommentOnDifferentPostTest() {
        String comment = "";
        int id = 0;
        Object mention = "_main";;
        String title1 = "hello_world";
        String title2 = "good morning";
        Post createdPost1 = testManager.makeComment(comment, id, mention, title1);
        Post createdPost2 = testManager.makeComment(comment, id, mention, title2);
        String createdComment1 = (String) createdPost1.getComments().get(1).get("text");
        String createcComment2 = (String) createdPost1.getComments().get(1).get("text");
        assertTrue(createdComment1.equals(createcComment2));
    }

    /**
     * Test to see that if the same comment was posted twice then the comment text's should be the same
     */
    @Test
    public void makeCommentDuplicatedTest() {
        String comment = "";
        int id = 0;
        Object mention = "_main";;
        String title1 = "hello_world";
        Post createdPost1 = testManager.makeComment(comment, id, mention, title1);
        Post createdPost2 = testManager.makeComment(comment, id, mention, title1);
        String createdComment1 = (String) createdPost1.getComments().get(1).get("text");
        String createcComment2 = (String) createdPost1.getComments().get(1).get("text");
        assertTrue(createdComment1.equals(createcComment2));
    }


}