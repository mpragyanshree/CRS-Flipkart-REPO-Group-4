package com.dropwizard.service;

import com.dropwizard.bean.Course;
import com.dropwizard.bean.RegisteredStudents;

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
    public List<RegisteredStudents> viewRegisteredStudents(String professorID , String course);

    /**
     * Method to view Registered courses
     * @return  list of registered courses
     */
    public List<Course> viewRegisteredCourses(String professorID);

    /**
     * Method to register course
     * @param professorID
     * @param courseID
     * @return  boolean
     */
    public boolean registerCourse(String professorID, String courseID);

    public boolean unregisterCourse(String professorID, String courseID);



}
