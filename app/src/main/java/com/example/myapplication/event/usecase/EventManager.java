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

    // TODO: add the javadoc
    /*
        In this method, we retrieve the list of Events from the database via EventDataConverter
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

    // TODO: add the javadoc
    /*
       If the event is already created, return the index of the event. Otherwise, return -1
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

    // TODO: add the javadoc
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

    // TODO: add the javadoc
    /*
       This method is responsible for creating an Event using the Simple Factory Design Pattern
     */
    public boolean createEvent(String name, String date, String time, String location, String type) {
        int result = findEvent(name, date, time, location, type);
        if (result == -1) {
            EventFactory factory = new EventFactory();
            Event newEvent = factory.createEvent(name, date, time, location, type);
            this.events.add(newEvent);
            // Update it in CourseManager

            return true;
        } else {
            return false;
        }
    }

    // TODO: add the javadoc
    public boolean removeEvent(Event old_event) {
        this.events.remove(old_event);
        return this.findEvent(old_event.getEventName(), old_event.getEventDate(), old_event.getEventTime(),
                old_event.getEventLocation(), old_event.getEventType()) == -1;
    }

    // TODO: add the javadoc; decide whether to put toString() in Event class
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
