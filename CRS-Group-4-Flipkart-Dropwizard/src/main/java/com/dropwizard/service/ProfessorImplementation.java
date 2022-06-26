package com.dropwizard.service;

import com.dropwizard.bean.RegisteredCourses;
import com.dropwizard.bean.RegisteredStudents;
import com.dropwizard.bean.Course;
import com.dropwizard.dao.ProfessorDaoImplementation;
import com.dropwizard.dao.ProfessorDaoInterface;
import com.dropwizard.exception.CourseExistsInCartException;
import com.dropwizard.exception.GradeNotAddedException;
import com.dropwizard.exception.NoStudentInCourseException;
import com.dropwizard.exception.StudentNotRegisteredException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorImplementation implements ProfessorInterface {


    private static volatile ProfessorImplementation instance=null;
    ProfessorDaoInterface professorDAOInterface= ProfessorDaoImplementation.getInstance();
    public ProfessorImplementation()
    {

    }




    public static ProfessorImplementation getInstance()
    {
        if(instance==null)
        {
            // This is a synchronized block, when multiple threads will access this instance
            synchronized(ProfessorImplementation.class){
                instance=new ProfessorImplementation();
            }
        }
        return instance;
    }



@Override
/**
 * Method to add grade by prof
 * @param studentId
 * @param courseCode
 * @param grade
 * @return  boolean
 */
    public boolean addGrade(String studentId,String courseCode,String grade)  {
        try
        {

            professorDAOInterface.addGrade(studentId, courseCode, grade);
            System.out.println("Grade added successfully");
            return true;

        }
        catch (GradeNotAddedException | StudentNotRegisteredException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    /**
     * Method to view registered students
     * @param professorID
     * @param courseID
     * @return  void
     */
    public List<RegisteredStudents> viewRegisteredStudents(String professorID , String courseID) {

        List<RegisteredStudents>ans = new ArrayList<RegisteredStudents>();

        try {

            try
            {
                ans = professorDAOInterface.getRegisteredStudents(professorID,courseID);
                for (RegisteredStudents r:ans) {
                    System.out.println("studentID = " + r.getStudentId() + " department = "+r.getDepartment());
                }
            }
            catch(NoStudentInCourseException e) {
                System.out.println(e.getMessage());
            }

        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return ans;


    }

    @Override
    /**
     * Method to view registered courses by professor

     * @return  void
     */
    public List<Course> viewRegisteredCourses()
    {
        List<Course>ans = new ArrayList<Course>();

        try {

            try
            {
                ans = professorDAOInterface.viewAvailableCourses();
                for (Course r:ans) {
                    System.out.println("courseID = " + r.getCourseCode()+ " courseName = "+r.getCourseName()+ " ProfessorID : "+r.getInstructorId()+" numberofSeats : "+r.getNumberOfSeats());
                }
            }
            catch(Exception e) {
                System.out.println(e.getMessage());
            }

        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return ans;
    }


    @Override
    /**
     * Method to register course by professor
     * @param professorID
     * @param courseId
     * @return  boolean
     */
    public boolean registerCourse(String professorID, String courseId)
    {

        try {
            Boolean ans = professorDAOInterface.registerCourse(professorID,courseId);
            if(ans){
                System.out.println("Course has been registered");
                return true;
            }
        }
        catch(Exception e) {
            System.out.println("cannot register for this course");
        }
        catch (CourseExistsInCartException ex)
        {
            System.out.println(ex.getMessage());
        }
    return false;

    }



}
