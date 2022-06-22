package com.flipkart.service;

import com.flipkart.bean.Student;

import java.util.Date;
import java.util.List;

public class StudentImplementation implements StudentInterface {
    public Student registerStudent(String studentId, String name, String password, String department, Date joiningDate)
    {
        Student newStudent = new Student();
        newStudent.setStudentId(studentId);
        newStudent.setName(name);
        newStudent.setPassword(password);
        newStudent.setDepartment(department);
        newStudent.setJoiningDate(joiningDate);
        //SDO.addStudent(newStudent); adding in db
        return newStudent;
    }
    public void viewGradeCard(String StudentID, int semesterID)
    {
        System.out.println("Your Grade Card");
        //print grade card fetching from the DB
        return;
    }
}
