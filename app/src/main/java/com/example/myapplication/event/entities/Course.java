package com.example.myapplication.event.entities;

import java.util.ArrayList;
import java.util.HashMap;

/**
    This class is for creating either one lecture or tutorial section for one course!
 */
public class Course {
    final private String courseCode;
    final private String section;
    // key: day, value: [startTime, endTime];
    final private HashMap<String, String[]> time;
    private ArrayList<Assignment> assignment;
    private ArrayList<Exam> exam;
    // list of homeworks that are not assignments/exam
    private ArrayList<Others> others;

    public Course(String code, String s, HashMap<String, String[]> t) {
        this.courseCode = code;
        this.section = s;
        this.time = t;
        this.assignment = new ArrayList<>();
        this.exam = new ArrayList<>();
        this.others = new ArrayList<>();
    }

    /**
     * Get the course code of this Course
     * @return  String
     */
    public String getCourseCode() {
        return this.courseCode;
    }

    /**
     * Get the lecture section of this Course
     * @return  String
     */
    public String getSection() {
        return this.section;
    }

    /**
     * Get the list of all assignments for this course
     * @return  ArrayList of Assignment
     */
    public ArrayList<Assignment> getAllAssignments() {
        return this.assignment;
    }

    /**
     * Add a new Assignment
     * @param newAssignment a new Assignment that would be added
     */
    public void addAssignment(Assignment newAssignment) {
        // TODO: Add the duplicated assignments created message
        this.assignment.add(newAssignment);
    }

    /**
     * Get the list of all exams for this course
     * @return  ArrayList of Exams
     */
    public ArrayList<Exam> getAllExams() {
        return this.exam;
    }

    /**
     * Add a new Exam
     * @param newExam a new Exam that would be added
     */
    public void addExam(Exam newExam) {
        this.exam.add(newExam);
    }

    /**
     * Get the list of all other extra homeworks for this course
     * @return  ArrayList of Others
     */
    public ArrayList<Others> getAllExtra() {
        return this.others;
    }

    /**
     * Add a new Others
     * @param newOther  a new Other that would be added
     */
    public void addExtra(Others newOther) {
        this.others.add(newOther);
    }

}
