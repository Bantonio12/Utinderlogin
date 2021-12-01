package com.example.myapplication.event;

import java.util.HashMap;
import java.util.ArrayList;

public class EventDataConverter {
    private HashMap userData;

    public EventDataConverter(HashMap data) {
        this.userData = data;
    }

    // TODO: add the javadoc
    public boolean addNewEvent(String name, String date, String time, String location, String type) {
        EventManager e = new EventManager();
        e.setEvents(this.userData);
        return e.createEvent(name, date, time, location, type);
    }

    public ArrayList<HashMap<String, String>> findEvent(String date) {
        EventManager e = new EventManager();
        e.setEvents(this.userData);
        ArrayList<HashMap<String, String>> allEventsOnDate = e.findEventOnDate(date);
        return allEventsOnDate;
    }
}
