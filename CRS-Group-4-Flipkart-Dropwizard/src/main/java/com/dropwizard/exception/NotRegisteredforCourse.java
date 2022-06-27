package com.dropwizard.exception;

public class NotRegisteredforCourse extends Throwable {


    private final String courseId;

    public NotRegisteredforCourse(String courseId) {
        this.courseId = courseId;
    }
    public String getCourseID() {
        return this.courseId;
    }

    @Override
    public String getMessage() {
        return "CourseID: " + this.courseId + " is not registered by you!";
    }
}
