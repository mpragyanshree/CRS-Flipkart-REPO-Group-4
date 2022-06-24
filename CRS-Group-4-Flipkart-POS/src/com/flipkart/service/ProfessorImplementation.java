package com.flipkart.service;

import com.flipkart.bean.RegisteredStudents;
import com.flipkart.bean.Course;
import com.flipkart.dao.ProfessorDaoImplementation;
import com.flipkart.dao.ProfessorDaoInterface;

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
    public boolean addGrade(String studentId,String courseCode,String grade)  {
        try
        {
            return professorDAOInterface.addGrade(studentId, courseCode, grade);
        }
        catch(Exception ex)
        {
           System.out.println("error encountered");
        }
        return false;
    }

 @Override
    public List<RegisteredStudents> viewRegisteredStudents(String professorID , String courseID) {
        List<RegisteredStudents> registeredStudents=new ArrayList<RegisteredStudents>();
        try
        {
            registeredStudents = professorDAOInterface.getRegisteredStudents(professorID,courseID);
        }
        catch(Exception ex)
        {
            System.out.println("error encountered");
        }
        return registeredStudents;
    }

    @Override
    public List<Course> viewRegisteredCourses()
    {
        return professorDAOInterface.viewAvailableCourses();
    }
    @Override
    public boolean registerCourse(String professorID, String courseId)
    {
       return professorDAOInterface.registerCourse(professorID,courseId);
    }


}
