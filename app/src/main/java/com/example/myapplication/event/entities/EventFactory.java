package com.example.myapplication.event.entities;

/**
 * This class represents a Factory which has only one method of creating one particular type of event
 */
public class EventFactory {
    /**
     *
     * @param name  an event name
     * @param date  an event date
     * @param time  an event time
     * @param location  a place where the event will be held or the course code of assignment/exam/others
     * @param type  a type of event (it should be one from the following
     *              types: meeting, extracurricular, assignment, exam, others)
     * @return  an Event object with all passed information
     */
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
