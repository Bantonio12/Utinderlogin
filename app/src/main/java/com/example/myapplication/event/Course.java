package com.example.myapplication.event;

import java.util.ArrayList;
import java.util.HashMap;

/*
    This class is for creating either one lecture or tutorial section for one course!
 */
public class Course {
    final private String courseCode;
    final private String section;
    final private HashMap<String, String[]> time; // key: day, value: [startTime, endTime];
    private ArrayList<Assignment> assignment;
    private ArrayList<Exam> exam;
    private ArrayList<Others> others; // list of homeworks that are not assignments/exam

    public Course(String code, String s, HashMap<String, String[]> t) {
        this.courseCode = code;
        this.section = s;
        this.time = t;
        this.assignment = new ArrayList<>();
        this.exam = new ArrayList<>();
        this.others = new ArrayList<>();
    }

    public String getCourseCode() {
        return this.courseCode;
    }

    public String getSection() {
        return this.section;
    }

    public ArrayList<Assignment> getAllAssignments() {
        return this.assignment;
    }

    // Since there is a specific course code written in the Assignment object
    public void addAssignment(Assignment a) {
        // Add the duplicated assignments created message
        this.assignment.add(a);
    }

    public ArrayList<Exam> getAllExams() {
        return this.exam;
    }

    public void addExam(Exam e) {
        this.exam.add(e);
    }

    public ArrayList<Others> getAllExtra() {
        return this.others;
    }

    public void addExtra(Others e) {
        this.others.add(e);
    }

}
