package com.dropwizard.dao;
import java.sql.SQLException;
import java.util.*;
import com.dropwizard.bean.Course;
import com.dropwizard.bean.RegisteredStudents;
import com.dropwizard.bean.Student;
import com.dropwizard.exception.CourseExistsInCartException;
import com.dropwizard.exception.GradeNotAddedException;
import com.dropwizard.exception.NoStudentInCourseException;
import com.dropwizard.exception.StudentNotRegisteredException;

public interface ProfessorDaoInterface {

    public List<RegisteredStudents> getRegisteredStudents(String professorID, String courseID) throws NoStudentInCourseException;

    public Boolean addGrade(String studentId,String courseID,String grade) throws GradeNotAddedException,StudentNotRegisteredException, SQLException;

    public List<Course> viewAvailableCourses();

    public boolean registerCourse(String professorID, String courseID) throws CourseExistsInCartException;
}


