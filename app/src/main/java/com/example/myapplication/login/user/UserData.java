package com.example.myapplication.login.user;

import java.util.ArrayList;

public class UserData {
    private static ArrayList<User> user_list = new ArrayList<>();


    public UserData() {
        user_list.add(new User("admin", "justin.cha@mail.utoronto.ca","IluvUofT"));
    }

    public void addUserData(User u) {
        user_list.add(u);
    }

    public boolean findUsername(String n) {
        for (int i = 0; i < user_list.size(); i++) {
            if (user_list.get(i).getNickname().equals(n)) {
                return true;
            }
        }
        return false;
    }

    public String findCorrespondingPassword(String n) {
        for (int i = 0; i < user_list.size(); i++) {
            if (user_list.get(i).getNickname().equals(n)) {
                return user_list.get(i).getPassword();
            }
        }
        return "";
    }

    public boolean findEmail(String e) {
        for (int i = 0; i < user_list.size(); i++) {
            if (user_list.get(i).getEmail().equals(e)) {
                return true;
            }
        }
        return false;
    }
}