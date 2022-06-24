package com.flipkart.bean;
/*
Course Class
 */
public class Course {
    private String courseCode;
    private String courseName;
    private String instructorId;
    private int numberOfSeats;
    private Boolean isprimary;
    /*
    Constructor for Course Class
     */
    public Course(String courseCode, String courseName, String instructorId, int numberOfSeats) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.instructorId = instructorId;
        this.numberOfSeats = numberOfSeats;
        this.isprimary=false;
    }
    /*
    Default constructor
     */
    public Course() {}
    /*
    Method to get Course ID
     */
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

    public int getNumberOfSeats() { return numberOfSeats; }

    public void setNumberOfSeats(int numberOfSeats) { this.numberOfSeats = numberOfSeats; }

    public Boolean getIsprimary() {
        return isprimary;
    }

    public void setIsprimary(Boolean isprimary) {
        this.isprimary = isprimary;
    }
}
