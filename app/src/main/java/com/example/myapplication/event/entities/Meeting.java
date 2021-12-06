package com.example.myapplication.event.entities;

/**
 * A subclass of GeneralEvent which represents any academic-related meeting
 */
public class Meeting extends GeneralEvent{
    public Meeting(String eventName, String eventDate, String eventTime, String eventLocation) {
        super(eventName, eventDate, eventTime, eventLocation, "academic");
    }
}
