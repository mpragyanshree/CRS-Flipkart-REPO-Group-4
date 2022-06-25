package com.flipkart.bean;


/**
 * Registered Student Class
 */
public class RegisteredStudents extends Student{
    private String courseCode;
    private String courseName;
    private String  studentId;

    /**
     * Method to get course code
     */
    public String getCourseCode() {
        return courseCode;
    }
    /**
     * Method to set course code
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    /**
     * Method to get course name
     */
    public String getCourseName() {
        return courseName;
    }
    /**
     * Method to set course name
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    /**
     * Method to get student ID
     */
    public String getStudentId() {
        return studentId;
    }
    /**
     * Method to set student ID
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    /**
     * Constructor
     */
    public RegisteredStudents(String courseCode, String studentId) {
        super();
        this.courseCode = courseCode;
        this.studentId = studentId;
    }
    /**
     * Constructor
     */
    public RegisteredStudents(String courseCode, String courseName ,String studentId) {
        super();
        this.courseCode = courseCode;
        this.studentId = studentId;
        this.courseName = courseName;
    }

}