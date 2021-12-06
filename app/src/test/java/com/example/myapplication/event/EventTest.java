package com.example.myapplication.event;

import com.example.myapplication.event.usecase.EventManager;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class EventTest {
    private EventManager testManager;

    @Before
    public void setUp() {
        testManager = new EventManager();
        HashMap<String, ArrayList<Map<String, String>>> events = new HashMap<>();
        Map<String, String> meeting = new HashMap<>();
        meeting.put("name", "KOVA Meeting");
        meeting.put("date", "2021-11-01");
        meeting.put("time", "9:00PM");
        meeting.put("type", "extracurricular");
        meeting.put("location", "BA1201");

        ArrayList<Map<String, String>> meetingList = new ArrayList<>();
        meetingList.add(meeting);

        events.put("CourseEvents", new ArrayList<>());
        events.put("GeneralEvents", meetingList);

        testManager.setEvents(events);
    }

    // Test for the general events
    @Test
    public void EventCreationFail() {
        // When there is a duplicated event, then it returns false!
        String eventName = "KOVA Meeting";
        String eventDate = "2021-11-01";
        String eventTime = "9:00PM";
        String eventLocation = "BA1201";
        String eventType = "extracurricular";
        boolean actual_result = testManager.createEvent(eventName, eventDate, eventTime, eventLocation, eventType);
        assertFalse(actual_result);
    }

    @Test
    public void EventCreationSuccess() {
        String eventName = "Meeting with CSC207 Prof";
        String eventDate = "2021-11-04";
        String eventTime = "3:00PM";
        String eventLocation = "BA2201";
        String eventType = "academic";
        boolean actual_result = testManager.createEvent(eventName, eventDate, eventTime, eventLocation, eventType);
        assertTrue(actual_result);
    }

    @Test
    public void FindEventFound() {
        String eventName = "KOVA Meeting";
        String eventDate = "2021-11-01";
        String eventTime = "9:00PM";
        String eventLocation = "BA1201";
        String eventType = "extracurricular";
        int actualResult = testManager.findEvent(eventName, eventDate, eventTime, eventLocation, eventType);
        assertEquals(actualResult, 0);
    }

    @Test
    public void FindEventNotFound() {
        String name = "Exercise 10";
        String date = "2021-12-03";
        String time = "10:00AM";
        String course = "CSC236F1";
        String type = "exam";
        int actualResult = testManager.findEvent(name, date, time, course, type);
        assertEquals(actualResult, -1);
    }

    @Test
    public void FindEventOnThisDateFound() {
        String date = "2021-11-01";
        ArrayList<HashMap<String, String>> actualResult = testManager.findEventOnDate(date);
        ArrayList<HashMap<String, String>> expectedResult = new ArrayList<>();

        HashMap<String, String> meeting = new HashMap<>();
        meeting.put("Name", "KOVA Meeting");
        meeting.put("Time", "9:00PM");
        meeting.put("Type", "extracurricular");
        meeting.put("Location", "BA1201");

        expectedResult.add(meeting);

        assertEquals(actualResult, expectedResult);

    }

    @Test
    public void FindEventOnThisDateNotFound() {
        String date = "2021-11-03";
        ArrayList<HashMap<String, String>> actualResult = testManager.findEventOnDate(date);
        ArrayList<HashMap<String, String>> expectedResult = new ArrayList<>();

        assertEquals(actualResult, expectedResult);
    }

    // TODO: Add the test for changing the event information
}
