package com.flipkart.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.flipkart.exception.*;
import com.flipkart.dao.SemesterRegistrationDaoInterface;
import com.flipkart.dao.SemesterRegistrationDaoImplementation;
import com.flipkart.dao.StudentDaoImplementation;
import com.flipkart.dao.StudentDaoInterface;


import com.flipkart.bean.Course;
import com.flipkart.bean.Notification;
import jdk.nashorn.internal.runtime.Context;


public class SemesterRegistrationImplementation implements SemesterRegistrationInterface {
    ArrayList<String> Mycourses1 = new ArrayList<String>();
    ArrayList<String> Mycourses2 = new ArrayList<String>();
    SemesterRegistrationDaoImplementation srdo = new SemesterRegistrationDaoImplementation();
    StudentDaoImplementation SDO = new StudentDaoImplementation();
    private Context.ThrowErrorManager logger;

    public boolean registerCourses(String studentId, int Semester)
    {
        try {

            Boolean val = srdo.registerCourses(studentId, Semester);
            NotificationInterface notificationObj = new NotificationImplementation();
            notificationObj.sendPayFeesNotification();
            return val;

       } catch (InvalidSemesterRegistration e) {
            logger.error(e.getMessage());
        } catch (PaymentDoneException e) {
            logger.error(e.getMessage());
        }


    }
    public boolean addCourse(String studentId, int Semester, String CourseId, boolean isPrimary)
    {
        //added in dataBase
        try {

            return srdo.addCourse(studentId, 1, CourseId, isPrimary);

        } catch (CourseNotFoundException | CourseSeatsUnavailableException | CourseExistsInCartException e ) {
            //System.out.println("Exception in adding course");
            logger.error(e.getMessage());
        }
        return false;
    }
    public boolean dropCourse(String studentId, int SemesterId, String CourseId)
    {
        try {

            return srdo.dropCourse(studentId, SemesterId, CourseId);

        } catch (CourseNotFoundException | CourseNotInCart e) {
            logger.error(e.getMessage());
        }
        return false;

    }
    public ArrayList<Course> viewCourseCatalog()
    {
        /* HashMap<String, Course> courseCatalog = new HashMap<String,Course>();
        courseCatalog.put("CSE01",new Course("CSE01","Operating systems", "PROF01",true));
        courseCatalog.put("CSE02",new Course("CSE02","DBMS", "PROF02",true));
        courseCatalog.put("CSE03",new Course("CSE03","Data Structures", "PROF03",true));
        courseCatalog.put("CSE04",new Course("CSE04","Algorithms", "PROF04",true));
        courseCatalog.put("CSE05",new Course("CSE05","Compiler Design", "PROF05",false));*/
        try {

        ArrayList<Course> courseCatalog = srdo.viewAvailableCourses();
        if(courseCatalog == null) {
            //System.out.println("Error encountered while retrieving course catalog");
            throw new Exception("Error encountered while retrieving course catalog");
        }
        return srdo.viewAvailableCourses();

    } catch (Exception e) {
            System.out.println("Some exception generated while viewing course catalog");
        logger.error(e.getMessage());
    }
        return null;
    }
    public void viewRegisteredCourses(String studentId, int Semester)
    {
        ArrayList<Course> courses = viewCourseCatalog();
        if(courses.isEmpty())
        {
            System.out.println("You have not registered for any courses");
            return;
        }
        System.out.println("Primary Courses are: ");
        System.out.println(Mycourses1);
        System.out.println("Alternative Courses are: ");
        System.out.println(Mycourses2);
    }
    public Boolean checkPaymentWindow(String StudentID)   {

       try {
            return SDO.checkPaymentWindow(StudentID);
        } catch (PaymentWindowException | StudentNotRegisteredException e ) {
            logger.error(e.getMessage());
        }

       // return false;
    }
}
