package com.flipkart.service;


import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;

import java.util.HashMap;

public class AdminImplementation {
    public void approveStudentRegistration(String studentID)
    {
        // update the student database based on the studentID

    }
    public void addCourse(String course_name, String courseID, String courseInstructor, int offeredSemester)
    {
//  add course details to the database
    }
    public void removeCourse(String courseID)
    {
// remove course based on courseID
    }
    public void generateGradeCard(String studentID)
    {
       int cpi;
       cpi = calculateCPI();
       //display grade and cpi;
    }


    public void viewCourseDetails(String courseID)
    {
        // view course details based on courseID
    }
    public void addProfessor(String username, String name, String password, String department, String designation, String contact, int joiningYear)
    {
// Add params to prof
    }
    public void viewStudentDetails(String studentID)
    {
// View student details based on student ID
    }
    public void viewProfessorDetails(String professorID)
    {
// view professor details based on professor ID
    }
    public HashMap<String, Grade> viewCourseGrades(String courseID)
    {
// view course grades based on courseID
        HashMap<String, Grade> courseGrade = new HashMap<String,Grade>();
        courseGrade.put("1801CS01",new Grade("CSE01","DBMS", "AA"));
        courseGrade.put("1801CS53",new Grade("CSE01","DBMS", "AB"));
        courseGrade.put("1801EE72",new Grade("CSE01","DBMS", "CC"));
        courseGrade.put("1801CS44",new Grade("CSE01","DBMS", "BC"));
        courseGrade.put("1801ME24",new Grade("CSE01","DBMS", "F"));
        if(courseGrade.isEmpty()) {
            System.out.println("Course catalog is empty");
        }
        return courseGrade;


    }
    private int calculateCPI() {
    // calculate cpi here
        return 0;
    }
}
