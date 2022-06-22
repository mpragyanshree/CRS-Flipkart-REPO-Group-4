package com.flipkart.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Notification;


public class SemesterRegistrationImplementation implements SemesterRegistrationInterface {
    ArrayList<String> Mycourses1 = new ArrayList<String>();
    ArrayList<String> Mycourses2 = new ArrayList<String>();
    public boolean registerCourses(String studentId, int Semester)
    {
        if(Mycourses1.size() == 4 && Mycourses2.size()==2) return true;
        else return false;
    }
    public boolean addCourse(String studentId, int Semester, String CourseId, boolean isPrimary)
    {
        //added in dataBase
        if(isPrimary) Mycourses1.add(CourseId);
        else Mycourses2.add(CourseId);
        return true;
    }
    public boolean dropCourse(String studentId, int Semester, String CourseId)
    {
        if(Mycourses1.contains(CourseId)) Mycourses1.remove(CourseId);
        else Mycourses2.remove(CourseId);
        return true;
    }
    public HashMap<String,Course> viewCourseCatalog()
    {
        HashMap<String, Course> courseCatalog = new HashMap<String,Course>();
        courseCatalog.put("CSE01",new Course("CSE01","Operating systems", "PROF01",true));
        courseCatalog.put("CSE02",new Course("CSE02","DBMS", "PROF02",true));
        courseCatalog.put("CSE03",new Course("CSE03","Data Structures", "PROF03",true));
        courseCatalog.put("CSE04",new Course("CSE04","Algorithms", "PROF04",true));
        courseCatalog.put("CSE05",new Course("CSE05","Compiler Design", "PROF05",false));
        if(courseCatalog.isEmpty()) {
            System.out.println("Course catalog is empty");
        }
        return courseCatalog;
    }
    public void viewRegisteredCourses(String studentId, int Semester)
    {
        HashMap<String,Course> courses = viewCourseCatalog();
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
    public boolean payFee(String studentId)
    {
        //check payment window exception
        return true; // need to modify this function, as of now we are assuming the payment is being done through payment gateway
    }
}
