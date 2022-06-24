package com.flipkart.dao;
import java.util.*;
import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredStudents;
import com.flipkart.bean.Student;
import com.flipkart.exception.GradeNotAddedException;

public interface ProfessorDaoInterface {


    public List<RegisteredStudents> getRegisteredStudents(String professorID, String courseID);

    public Boolean addGrade(String studentId,String courseID,String grade) throws GradeNotAddedException;


    public List<Course> viewAvailableCourses();

    public boolean registerCourse(String professorID, String courseID);
}


