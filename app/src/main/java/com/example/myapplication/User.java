package com.example.myapplication;

public class User {
    private String nickname;
    private String email;
    private String password;
    private int index;

    public User(String n, String e, String p) {
        this.nickname = n;
        this.email = e;
        this.password = p;
    }

    public String getNickname() {
        return this.nickname;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }
}
