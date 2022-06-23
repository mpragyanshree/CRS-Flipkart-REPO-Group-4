package com.flipkart.dao;
import java.util.*;
import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredStudents;
import com.flipkart.bean.Student;

public interface ProfessorDaoInterface {

    public List<Course> getCoursesByProfessor(String userID);

    public List<RegisteredStudents> getRegisteredStudents(String professorID, String courseID);

    public Boolean addGrade(int studentId,String courseID,String grade,int semester);

    public String getProfessorById(String professorID);

    public List<Course> viewAvailableCouses(String semester);

    public boolean registerCourse(String professorID, String courseID, int semester);
}


