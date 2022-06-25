package com.flipkart.bean;

/**
 * Grade Class
 */
public class Grade {

    private String courseId;
    private String courseName;
    private String grade;
    private String StudentID;

    /**
     * Parameterized Constructor
     * @param courseId
     * @param courseName
     * @param grade
     */
    public Grade(String courseId, String courseName, String grade) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.grade = grade;
    }

    /**
     * Default Constructor
     */
    public Grade() {}

    /**
     * Method to get Course ID
     * @return
     */
    public String getCourseId() {
        return courseId;
    }
    /**
     * Method to set Course ID
     * @return
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId;
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
     * Method to get Grades
     * @return
     */
    public String getGrade() {
        return grade;
    }

    /**
     * Method to set Grades
     * @param grade
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }


}
