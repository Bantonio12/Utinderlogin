package com.example.myapplication.event.usecase;

import com.example.myapplication.event.entities.EventFactory;
import com.example.myapplication.event.entities.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EventManager {

    private ArrayList<Event> events;

    public EventManager() {
        this.events = new ArrayList<>();
    }

    /**
     * Add all the events to the list
     * @param listOfEvents  the list of events we want to add
     */
    public void setEvents(HashMap<String, ArrayList<Map<String, String>>> listOfEvents) {
        ArrayList<Event> newEvents = new ArrayList<>();
        EventFactory factory = new EventFactory();

        for (ArrayList<Map<String, String>> a : listOfEvents.values()) {
            for (Map<String, String> e : a) {
                String name = e.get("name");
                String date = e.get("date");
                String time = e.get("time");
                String type = e.get("type");

                if (e.containsKey("code")) {
                    assert type != null;
                    Event newCourseEvent = factory.createEvent(name, date, time, e.get("code"), type);
                    newEvents.add(newCourseEvent);
                } else {
                    assert type != null;
                    Event newGeneralEvent = factory.createEvent(name, date, time, e.get("location"), type);
                    newEvents.add(newGeneralEvent);
                }
            }
        }

        this.events = newEvents;
    }

    /**
     * Return the index of event in the list.
     * If not found, return -1
     * @param name  name of event
     * @param date  date of event
     * @param time  time of event
     * @param location  location of event
     * @param type  type of event
     * @return  the index of event in the list
     */
    public int findEvent(String name, String date, String time, String location, String type) {
        for (int i = 0; i < this.events.size(); i++) {
            Event e = this.events.get(i);
            boolean result = e.getEventName().equals(name) && e.getEventDate().equals(date) && e.getEventTime().equals(time) && e.getEventLocation().equals(location) && e.getEventType().equals(type);

            if (result) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Return the list of events that happen on the specific date
     * @param date  a date of events we're interested in
     * @return  the list of events happening on that date
     */
    public ArrayList<HashMap<String, String>> findEventOnDate(String date) {
        ArrayList<HashMap<String, String>> events = new ArrayList<>();
        for (int i = 0; i < this.events.size(); i++) {
            Event e = this.events.get(i);
            if (e.getEventDate().equals(date)) {
                HashMap<String, String> eventInfo = new HashMap<>();
                eventInfo.put("Name", e.getEventName());
                eventInfo.put("Type", e.getEventType());
                eventInfo.put("Time", e.getEventTime());
                eventInfo.put("Location", e.getEventLocation());
                events.add(eventInfo);
            }
        }
        return events;
    }

    /**
     * If the event is new, then create, add to the list of events, and return True.
     * Otherwise, return False
     * @param name  a name of event
     * @param date  a date of event
     * @param time  a time of event
     * @param location  a location of event
     * @param type  a type of event
     * @return  true/false of creating a new event
     */
    public boolean createEvent(String name, String date, String time, String location, String type) {
        int result = findEvent(name, date, time, location, type);
        if (result == -1) {
            EventFactory factory = new EventFactory();
            Event newEvent = factory.createEvent(name, date, time, location, type);
            this.events.add(newEvent);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Remove the specific event from the list and return true if it is successfully removed.
     * @param old_event the event we want to remove
     * @return  the status of removing event
     */
    public boolean removeEvent(Event old_event) {
        this.events.remove(old_event);
        return this.findEvent(old_event.getEventName(), old_event.getEventDate(), old_event.getEventTime(),
                old_event.getEventLocation(), old_event.getEventType()) == -1;
    }

    /**
     * Return the string representation of the list of events
     * @return  the description of all the events
     */
    @Override
    public String toString() {
        String statement = "This user has following events: ";
        for (Event e : this.events) {
            statement += "Name: " + e.getEventName() + " Date: " + e.getEventDate() + " Time: " + e.getEventTime()
                    + " Type: " + e.getEventType();
            statement += " \n";
        }
        return statement;
    }

}
