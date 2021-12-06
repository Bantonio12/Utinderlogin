package com.example.myapplication.event.converter;

import com.example.myapplication.event.usecase.EventManager;

import java.util.HashMap;
import java.util.ArrayList;

/**
 * This class acts as a middle man between the database and EventManager.
 */
public class EventDataConverter {
    private HashMap userData;

    public EventDataConverter(HashMap data) {
        this.userData = data;
    }

    /**
     * Returns whether the new event is in the database already or not
     * @param name  a name of the event that we want to add to the database
     * @param date  a date of the event that we want to add to the database
     * @param time  a time of the event that we want to add to the database
     * @param location  a location of the event that we want to add to the database
     * @param type  a type of the event that we want to add to the database
     * @return      true/false representing whether the new data is not duplicated and can be added
     */
    public boolean addNewEvent(String name, String date, String time, String location, String type) {
        EventManager e = new EventManager();
        e.setEvents(this.userData);
        return e.createEvent(name, date, time, location, type);
    }

    /**
     * Return the ArrayList of descriptions of all events that happen on the selected date
     * @param date  a date of the specific event
     * @return      a list of all the events that happen on the specific date
     */
    public ArrayList<HashMap<String, String>> findEvent(String date) {
        EventManager e = new EventManager();
        e.setEvents(this.userData);
        return e.findEventOnDate(date);
    }
}
