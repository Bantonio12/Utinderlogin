package com.example.myapplication.event;

import java.util.HashMap;

public class EventDataConverter {
    private HashMap userData;

    public EventDataConverter(HashMap data) {
        this.userData = data;
    }

    public boolean addNewEvent(String name, String date, String time, String location, String type) {
        EventManager e = new EventManager();
        e.setEvents(this.userData);
        return e.createEvent(name, date, time, location, type);
    }
}
