package com.flipkart.bean;


/**
 * Registered Student Class
 */
public class RegisteredStudents extends Student{

    private String courseCode;
    private String courseName;
    private String  studentId;
    /**
     * Constructor
    */

     public RegisteredStudents(String joiningDate, String userId, String name, String role, String password, String address, String contactnum, String department, String studentId, boolean isApproved, String courseCode, String courseName, String studentId1) {
        super(joiningDate, userId, name, role, password, address, contactnum, department, studentId, isApproved);
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.studentId = studentId1;
    }


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
}