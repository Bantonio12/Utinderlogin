package com.example.myapplication;



public class UserManager {
    private final UserData data = new UserData();
    public UserManager() {

    }

    public boolean createUser(String n, String e, String p) {
        if (!this.data.findUsername(n) && !this.data.findEmail(e)) {
            this.data.addUserData(new User(n, e, p));
            return true;
        } else {
            return false;
        }
    }

    public boolean checkUser(String n, String p) {
        if (this.data.findUsername(n)) {
            return this.data.findCorrespondingPassword(n).equals(p);
        } else {
            return false;
        }
    }
}
