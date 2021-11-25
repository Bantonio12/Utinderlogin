package com.example.myapplication.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EventManager {

    private ArrayList<Event> events;

    public EventManager() {
        this.events = new ArrayList<>();
    }

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
                System.out.println(type);

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

    /*
       This method is responsible for creating an Event using the Simple Factory Design Pattern
     */
    public boolean createEvent(String name, String date, String time, String location, String type) {
        int result = findEvent(name, date, time, location, type);
        System.out.println(result);
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

    public boolean removeEvent(Event old_event) {
        this.events.remove(old_event);
        return this.findEvent(old_event.getEventName(), old_event.getEventDate(), old_event.getEventTime(),
                old_event.getEventLocation(), old_event.getEventType()) == -1;
    }


}
