package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredStudents;

import java.util.List;

public interface ProfessorInterface {

    public boolean addGrade(int studentId,String courseID,String grade,int semester);
    public List<RegisteredStudents> viewRegisteredStudents(String professorID , String course);
    public List<Course>  viewCourseProfessor(String professorID);
    public String getProfessorID(String username);
    public List<Course> viewRegisteredCourses(int semester);
    public boolean registerCourse(String professorID, String courseID, int semester);

}
