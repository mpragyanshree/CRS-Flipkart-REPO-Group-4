package com.flipkart.bean;
/**
Course Class
 */
public class Course {
    private String courseCode;
    private String courseName;
    private String instructorId;
    private int numberOfSeats;
    private Boolean isprimary;
    /**
    Parametrized Constructor for Course Class
     **/
    public Course(String courseCode, String courseName, String instructorId, int numberOfSeats) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.instructorId = instructorId;
        this.numberOfSeats = numberOfSeats;
        this.isprimary=false;
    }
    /**
    Default constructor
     */
    public Course() {}

    /**
     * Method to get Course ID
     * @return
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Method to set Course ID
     * @param courseCode
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * Method to get course name
     * @return
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Method to set course name
     * @param courseName
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Method to get Instructor ID
     * @return
     */
    public String getInstructorId() {
        return instructorId;
    }

    /**
     * Methos to set Instructor ID
     * @param instructorId
     */

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    /**
     * Method to get Number of seats
     * @return
     */
    public int getNumberOfSeats() { return numberOfSeats; }

    /**
     * Method to set number of seats
     * @param numberOfSeats
     */
    public void setNumberOfSeats(int numberOfSeats) { this.numberOfSeats = numberOfSeats; }

    public Boolean getIsprimary() {
        return isprimary;
    }

    /**
     * Method to set Primary course
     * @param isprimary
     */
    public void setIsprimary(Boolean isprimary) {
        this.isprimary = isprimary;
    }
}
