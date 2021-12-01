package com.example.myapplication;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.myapplication.event.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EventTest {
    private EventManager testManager;
    private CourseManager testManager2;

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
        testManager2 = new CourseManager();
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
    public void CourseCreationFail() {
        String code = "CSC207F1";
        String section = "LEC0101";
        HashMap<String, String[]> sectionTime = new HashMap<>();
        sectionTime.put("Tuesday", new String[]{"1:00PM", "2:00PM"});
        sectionTime.put("Thursday", new String[]{"1:00PM", "2:00PM"});
        boolean actualResult = testManager2.createCourse(code, section, sectionTime);
        assertFalse(actualResult);
    }

    @Test
    public void CourseCreationSuccess() {
        String code = "CSC209S1";
        String section = "LEC0101";
        HashMap<String, String[]> time = new HashMap<>();
        time.put("Tuesday", new String[]{"2:00PM", "3:00PM"});
        time.put("Thursday", new String[]{"2:00PM", "3:00PM"});
        boolean actualResult = testManager2.createCourse(code, section, time);
        assertTrue(actualResult);
    }

    @Test
    public void CourseRemovalSuccess() {
        String code = "CSC207F1";
        String section = "LEC0101";
        boolean actualResult = testManager2.removeCourse(code, section);
        assertTrue(actualResult);
    }

    // Tests for the course events
    @Test
    public void AssignmentCreationSuccess() {
        String name = "Code Smells Quiz";
        String date = "2021-11-02";
        String time = "12:00PM";
        String course = "CSC207F1";
        boolean actual_result = testManager2.addAssignment(new Assignment(name, date, time, course));
        assertTrue(actual_result);
    }

    @Test
    public void AssignmentCreationFail() {
        String name = "Code Smells Quiz";
        String date = "2021-11-02";
        String time = "12:00PM";
        String course = "CSC209S1";
        boolean actual_result = testManager2.addAssignment(new Assignment(name, date, time, course));
        assertFalse(actual_result);
    }

    @Test
    public void ExamCreationSuccess() {
        String name = "Final Exam";
        String date = "2021-12-15";
        String time = "6:00PM";
        String course = "CSC207F1";
        boolean actual_result = testManager2.addExam(new Exam(name, date, time, course));
        assertTrue(actual_result);
    }

    @Test
    public void ExamCreationFail() {
        String name = "Final Exam";
        String date = "2021-12-15";
        String time = "8:00PM";
        String course = "CSC236F1";
        boolean actual_result = testManager2.addExam(new Exam(name, date, time, course));
        assertFalse(actual_result);
    }

    @Test
    public void ExtraHomeworkCreationSuccess() {
        String name = "Weekly Quiz 10";
        String date = "2021-12-01";
        String time = "12:00PM";
        String course = "CSC207F1";
        boolean actual_result = testManager2.addOthers(new Others(name, date, time, course));
        assertTrue(actual_result);
    }

    @Test
    public void ExtraHomeworkCreationFail() {
        String name = "Exercise 10";
        String date = "2021-12-03";
        String time = "10:00AM";
        String course = "CSC236F1";
        boolean actual_result = testManager2.addOthers(new Others(name, date, time, course));
        assertFalse(actual_result);
    }

    //TODO: Add the test for finding event
    @Test
    public void FindEventSuccess() {

    }
}
