package com.example.myapplication.event;

/*
    A subclass of Event that represents the events, such as meeting, extracurricular events, etc.
 */
public class GeneralEvent extends Event{

    public GeneralEvent(String eventName, String eventDate, String eventTime, String eventLocation, String eventType) {
        super(eventName, eventDate, eventTime, eventLocation, eventType);
    }
}
