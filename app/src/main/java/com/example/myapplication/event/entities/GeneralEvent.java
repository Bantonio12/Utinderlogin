package com.example.myapplication.event.entities;

/**
    A subclass of Event that represents the events, such as meeting and extracurricular events
 */
public class GeneralEvent extends Event {

    public GeneralEvent(String eventName, String eventDate, String eventTime, String eventLocation, String eventType) {
        super(eventName, eventDate, eventTime, eventLocation, eventType);
    }
}
