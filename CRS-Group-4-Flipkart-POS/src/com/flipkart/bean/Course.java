package com.flipkart.bean;

public class Course {
    private String courseCode;
    private String courseName;
    private String instructorId;
    private boolean isAvailable;

    public Course(String courseCode, String courseName, String instructorId, boolean isAvailable) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.instructorId = instructorId;
        this.isAvailable = isAvailable;
    }

    public Course() {}

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

}
