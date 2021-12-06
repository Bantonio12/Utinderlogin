package com.example.myapplication.event.entities;

/**
 * A class representing one Event that the User added
 */
public class Event {
    private String eventName;
    private String eventDate;
    private String eventTime;
    private String eventLocation;
    private String eventType;

    /**
     * Constructor for the Event
     * @param name  an event name
     * @param date  an event date
     * @param time  an event time
     * @param location  a place where the event will be held or the course code of assignment/exam/others that is added
     * @param type  a type of events (meeting, extracurricular, assignment, exam, others)
     */
    public Event(String name, String date, String time, String location, String type) {
        this.eventName = name;
        this.eventDate = date;
        this.eventTime = time;
        this.eventLocation = location;
        this.eventType = type;
    }

    /**
     * Return the name of this event
     * @return String
     */
    public String getEventName() {
        return this.eventName;
    }

    /**
     * Return the date of this event in the format of YYYY-MM-DD
     * @return String
     */
    public String getEventDate() {
        return this.eventDate;
    }

    /**
     * Return the time of this event in the format of HH:MM PM/AM
     * @return String
     */
    public String getEventTime() {
        return this.eventTime;
    }

    /**
     * Return the location or the course code of this event
     * @return String
     */
    public String getEventLocation() { return this.eventLocation; }

    /**
     * Return the type of this event
     * There are 5 possible options for the types: meeting, extracurricular, assignment, exam, others
     * @return String
     */
    public String getEventType() { return this.eventType; }

    /**
     * Change the name of this event
     * @param newName   a new name that would be assigned to this event
     */
    public void setEventName(String newName) {
        this.eventName = newName;
    }

    /**
     * Change the name of this event
     * @param newDate   a new name that would be assigned to this event
     */
    public void setEventDate(String newDate) {
        this.eventDate = newDate;
        // change in the data
    }

    /**
     * Change the name of this event
     * @param newTime   a new name that would be assigned to this event
     */
    public void setEventTime(String newTime) {
        this.eventTime = newTime;
        // change in the data
    }

    /**
     * Change the name of this event
     * @param newLocation   a new name that would be assigned to this event
     */
    public void setEventLoation(String newLocation) {
        this.eventLocation = newLocation;
    }

    /**
     * Change the type of this event
     * @param newType  a new name that would be assigned to this event
     */
    public void setEventType (String newType) {
        this.eventType = newType;
    }

}
