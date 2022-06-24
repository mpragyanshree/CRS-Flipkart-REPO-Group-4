package com.flipkart.bean;

public class RegisteredStudents {
    private String courseCode;
    private String courseName;
    private String  studentId;


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

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public RegisteredStudents(String courseCode, String studentId) {
        super();
        this.courseCode = courseCode;
        this.studentId = studentId;
    }
    public RegisteredStudents(String courseCode, String courseName ,String studentId) {
        super();
        this.courseCode = courseCode;
        this.studentId = studentId;
        this.courseName = courseName;
    }

}