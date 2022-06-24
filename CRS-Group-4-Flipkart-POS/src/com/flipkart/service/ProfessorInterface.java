package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredStudents;

import java.util.List;

public interface ProfessorInterface {

    public boolean addGrade(String studentId,String courseID,String grade);
    public List<RegisteredStudents> viewRegisteredStudents(String professorID , String course);
    public List<Course> viewRegisteredCourses();
    public boolean registerCourse(String professorID, String courseID);

}
