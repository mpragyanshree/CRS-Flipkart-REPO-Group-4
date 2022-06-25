package com.flipkart.exception;


    /**
     *
     */


    /**
     * @author Dell
     * Exception arises when we try to add an existing course to our catalogue
     */

public class CourseAlreadyPresentException extends Exception{
    private final String courseID;

    public CourseAlreadyPresentException() {
        // TODO Auto-generated constructor stub
        courseID = "";
    }
    /*
     * @param courseID
     */
    public CourseAlreadyPresentException(String courseID) {
        super();
        this.courseID = courseID;
    }
    /**
     * @return the courseID
     */
    public String getCourseID() {
        return courseID;
    }

    @Override
    public String getMessage() {
        return "CourseID: " + "none" + " is already present in catalog!";
    }

}

