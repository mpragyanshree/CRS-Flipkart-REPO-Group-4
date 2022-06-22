package com.flipkart.service;

public interface ProfessorInterface {
    public void viewEnrolledStudents();
    public void addGrade();
    public void viewRegisteredCourses();
    public void registerCourses(String professorID, String courseId, int semester);
    void viewCourseProf();
    String getProfessorID(String username);
}
