package com.example.myapplication.event;

/*
   A subclass of Event that represents extra
   homeworks for the particular course
 */
public class Others extends Event{
    public Others(String name, String date, String time, String code) {
        super(name, date, time, code, "miscellaneous");
    }
}
