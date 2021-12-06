package com.example.myapplication.event.entities;

/**
 * A subclass of Event which represents the exam-related events
 */
public class Exam extends Event {
    public Exam (String name, String date, String time, String code) {
        super(name, date, time, code, "exam");
    }
}
