package com.example.myapplication.event;

// Still okay to put as a class since the subclass will only extend this class

// Should we use Observable? So when there is new event created, then these changes can be updated
//
public class Event {
    private String eventName;
    private String eventDate;
    private String eventTime;
    private String eventLocation; // actual place for GeneralEvent and course code for Assignment/Exam/Miscellaneous
    private String eventType;

    public Event(String name, String date, String time, String location, String type) {
        this.eventName = name;
        this.eventDate = date;
        this.eventTime = time;
        this.eventLocation = location;
        this.eventType = type;
    }

    public String getEventName() {
        return this.eventName;
    }

    public String getEventDate() {
        return this.eventDate;
    }

    public String getEventTime() {
        return this.eventTime;
    }

    public String getEventLocation() { return this.eventLocation; }

    public String getEventType() { return this.eventType; }

    // We can use the observer design pattern here
    public void setEventName(String newName) {
        this.eventName = newName;
    }

    public void setEventDate(String newDate) {
        this.eventDate = newDate;
        // change in the data
    }

    public void setEventTime(String newTime) {
        this.eventTime = newTime;
        // change in the data
    }

    public void setEventLoation(String newLocation) {
        this.eventLocation = newLocation;
    }

    public void setEventType (String newType) {
        this.eventType = newType;
    }

}
