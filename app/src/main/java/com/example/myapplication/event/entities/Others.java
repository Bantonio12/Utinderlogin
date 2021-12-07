package com.example.myapplication.event.entities;

/**
   A subclass of Event that represents extra homeworks
   that are not assignment or exams, for the particular course
 */
public class Others extends Event {
    public Others(String name, String date, String time, String code) {
        super(name, date, time, code, "miscellaneous");
    }
}
