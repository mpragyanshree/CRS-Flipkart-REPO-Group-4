package com.flipkart.exception;

/**
 * @author Aeron
 * Exception arises when grade is not added but student tries to access it.
 */

public class GradeNotAddedException extends Exception {
    private String studentId;
    public GradeNotAddedException (String studentId) {
        this.studentId=studentId;
    }
    public String getStudentId() {
        return studentId;
    }

    @Override
    public String getMessage() {
        return "Grades have not been added for Student ID : " + studentId ;
    }

}
