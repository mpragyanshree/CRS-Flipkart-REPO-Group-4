
package com.flipkart.exception;



public class CourseLimitExceededException extends Exception {

    private String studentID;

    public CourseLimitExceededException() {

        studentID = "";
    }

    /**
     * @param studentID
     */
    public CourseLimitExceededException(String studentID) {
        super();
        this.studentID = studentID;
    }

    /**
     * @return the courseID
     */
    public String getstudentID() {
        return studentID;
    }

    @Override
    public String getMessage() {
        return "studentID: " + studentID + "has exceeded course Limit!";
    }
}

