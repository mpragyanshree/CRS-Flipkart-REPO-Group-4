package com.flipkart.service;

import com.flipkart.bean.RegisteredCourses;
import com.flipkart.bean.RegisteredStudents;
import com.flipkart.bean.Course;
import com.flipkart.dao.ProfessorDaoImplementation;
import com.flipkart.dao.ProfessorDaoInterface;
import com.flipkart.exception.CourseExistsInCartException;
import com.flipkart.exception.GradeNotAddedException;
import com.flipkart.exception.NoStudentInCourseException;
import com.flipkart.exception.StudentNotRegisteredException;

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
    public void viewRegisteredStudents(String professorID , String courseID) {

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


    }

    @Override
    /**
     * Method to view registered courses by professor

     * @return  void
     */
    public void viewRegisteredCourses()
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
