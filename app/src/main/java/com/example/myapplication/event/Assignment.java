package com.example.myapplication.event;

public class Assignment extends Event{

    public Assignment(String name, String date, String time, String code) {
        super(name, date, time, code, "assignment");
    }

}
