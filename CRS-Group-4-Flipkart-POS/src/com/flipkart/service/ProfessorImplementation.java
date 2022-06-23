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
    public boolean addGrade(int studentId,String courseCode,String grade, int semester)  {
        try
        {
            return professorDAOInterface.addGrade(studentId, courseCode, grade,semester);
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
    public List<Course> viewCourseProfessor(String professorID) {

        List<Course> taughtCourses=new ArrayList<Course>();
        try
        {
            taughtCourses=professorDAOInterface.getCoursesByProfessor(professorID);
        }
        catch(Exception ex)
        {
            System.out.println("error encountered");
        }
        return taughtCourses;
    }


 @Override
    public String getProfessorID(String profId)
    {
        return professorDAOInterface.getProfessorById(profId);
    }

    @Override
    public List<Course> viewRegisteredCourses(String semester)
    {
        return professorDAOInterface.viewAvailableCouses(semester);
    }
    @Override
    public boolean registerCourse(String professorID, String courseId, int semester)
    {
       return professorDAOInterface.registerCourse(professorID,courseId,semester);
    }


}
