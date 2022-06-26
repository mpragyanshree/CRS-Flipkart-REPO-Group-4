package com.flipkart.bean;


/*
Course Class
 */

/**
 * Registered Course class
 */
public class RegisteredCourses {


    private String studentId;
    private String coursecode;
    private String professorId;
    private String grade;

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
     * Method to get course ID
     */
    public String getCoursecode() {
        return coursecode;
    }
    /**
     * Method to set course ID
     */
    public void setCoursecode(String coursecode) {
        this.coursecode = coursecode;
    }
    /**
     * Method to get Professor ID
     */
    public String getProfessorId() {
        return professorId;
    }
    /**
     * Method to set Professor ID
     */
    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }
    /**
     * Method to get Grade
     */
    public String getGrade() {
        return grade;
    }
    /**
     * Method to set Grade
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }
    /**
     * Parameterized Constructor
     */
    public RegisteredCourses(String studentID, String courseCode, String professorID, String Grade) {
        this.studentId = studentID;
        this.coursecode = courseCode;
        this.professorId = professorID;
        this.grade = Grade;

    }


}
