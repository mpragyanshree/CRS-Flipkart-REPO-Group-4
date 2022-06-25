package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredStudents;

import java.util.List;

public interface ProfessorInterface {


    /**
     * Method to add grade by professor
     * @param studentId
     * @param courseID
     * @param grade
     * @return  boolean
     */
    public boolean addGrade(String studentId,String courseID,String grade);

    /**
     * Method to view registered students by professor
     * @param professorID
     * @param course
     * @return  list of registered students
     */
    public void viewRegisteredStudents(String professorID , String course);

    /**
     * Method to view Registered courses
     * @return  list of registered courses
     */
    public void viewRegisteredCourses();

    /**
     * Method to register course
     * @param professorID
     * @param courseID
     * @return  boolean
     */
    public boolean registerCourse(String professorID, String courseID);

}
