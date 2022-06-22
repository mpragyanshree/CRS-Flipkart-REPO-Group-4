package com.flipkart.bean;

public class Grade {

    private String courseId;
    private String courseName;
    private String grade;

    public Grade(String courseId, String courseName, String grade) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.grade = grade;
    }

    public Grade() {}

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }


}
