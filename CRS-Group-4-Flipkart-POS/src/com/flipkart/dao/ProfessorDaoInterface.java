package com.flipkart.dao;
import java.sql.SQLException;
import java.util.*;
import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredStudents;
import com.flipkart.bean.Student;
import com.flipkart.exception.CourseExistsInCartException;
import com.flipkart.exception.GradeNotAddedException;
import com.flipkart.exception.NoStudentInCourseException;
import com.flipkart.exception.StudentNotRegisteredException;

public interface ProfessorDaoInterface {

    public List<RegisteredStudents> getRegisteredStudents(String professorID, String courseID) throws NoStudentInCourseException;

    public Boolean addGrade(String studentId,String courseID,String grade) throws GradeNotAddedException,StudentNotRegisteredException, SQLException;

    public List<Course> viewAvailableCourses();

    public boolean registerCourse(String professorID, String courseID) throws CourseExistsInCartException;
}


