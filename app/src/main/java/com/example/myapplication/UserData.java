package com.example.myapplication;

import java.util.ArrayList;

public class UserData {
    private static ArrayList<User> user_list = new ArrayList<>();

    public void addUserData(User u) {
        user_list.add(u);
    }

    public User getUser(int i) {
        return user_list.get(i);
    }
}
