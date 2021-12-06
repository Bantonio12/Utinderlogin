package com.example.myapplication.event.entities;

/**
 * This is a subclass of Event, which represents any assignments from the courses
 */
public class Assignment extends Event {

    public Assignment(String name, String date, String time, String code) {
        super(name, date, time, code, "assignment");
    }

}
