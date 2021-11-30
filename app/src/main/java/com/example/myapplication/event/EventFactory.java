package com.example.myapplication.event;

public class EventFactory {
    public Event createEvent(String name, String date, String time, String location, String type) {
        switch (type) {
            case "meeting":
                return new Meeting(name, date, time, location);
            case "extracurricular":
                return new ExtracurricularEvent(name, date, time, location);
            case "assignment":
                return new Assignment(name, date, time, location);
            case "exam":
                return new Exam(name, date, time, location);
            default:
                return new Others(name, date, time, location);
        }
    }
}
