package com.flipkart.service;

public class ProfessorImplementation implements ProfessorInterface {
    public void viewEnrolledStudents()
    {
        System.out.println("enrolled students list : ");
    }
    public void addGrade()
    {
        System.out.println("grade added");
    }
    public void viewRegisteredCourses()
    {
        System.out.println("registered course : ");
    }
    public void registerCourses(String professorID, String courseId, int semester)
    {

        System.out.println("course registered");

    }
    public void viewCourseProf()
    {
        System.out.println("prof : ");
    }
    public String getProfessorID(String username)
    {
        System.out.println("prof id is ");
        return "";
    }

}
