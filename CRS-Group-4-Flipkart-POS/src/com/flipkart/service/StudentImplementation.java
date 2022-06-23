package com.flipkart.service;

import com.flipkart.bean.Student;

import java.util.Date;
import java.util.List;
import com.flipkart.dao.StudentDaoInterface;
import com.flipkart.dao.StudentDaoImplementation;

public class StudentImplementation implements StudentInterface {
    StudentDaoInterface SDO =StudentDaoImplementation.getInstance();
    public Student registerStudent(String studentId, String name, String password, String department, Date joiningDate, String address, String contactnum)
    {
        Student newStudent = new Student();
        newStudent.setUserId(studentId);
        newStudent.setStudentId(studentId);
        newStudent.setName(name);
        newStudent.setPassword(password);
        newStudent.setDepartment(department);
        newStudent.setJoiningDate(joiningDate);
        newStudent.setAddress(address);
        newStudent.setContactnum(contactnum);
        SDO.addStudent(newStudent);
        return newStudent;
    }
    public void viewGradeCard(String studentID, int semesterID)
    {
        SDO.viewReportCard(studentID);
        System.out.println("Your Grade Card");
        //print grade card fetching from the DB
        return;
    }
}
