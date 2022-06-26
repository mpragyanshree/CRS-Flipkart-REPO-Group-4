package com.flipkart.exception;

//Exception arises when appropriate student details can't be found or are not present
public class StudentNotRegisteredException extends Exception {

    @Override
    public String getMessage() {
        return "No such registered student was found";
    }
}
