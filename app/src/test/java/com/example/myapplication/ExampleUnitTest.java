package com.example.myapplication;

import org.junit.Test;

import static org.junit.Assert.*;
import com.example.myapplication.login.user.UserManager;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void UserCreationFail() {
        UserManager testManager = new UserManager();
        String username = "admin";
        String email = "hello.hi@mail.utoronto.ca";
        String password = "hello_world12";
        boolean test_bool = testManager.createUser(username, email, password);
        assertEquals(false, test_bool);
    }

    @Test
    public void UserCreationSuccess() {
        UserManager testManager = new UserManager();
        String username = "hello";
        String email = "hello.hi@mail.utoronto.ca";
        String password = "hello_world12";
        boolean test_bool = testManager.createUser(username, email, password);
        assertEquals(true, test_bool);
    }
}