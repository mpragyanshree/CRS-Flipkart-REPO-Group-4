package com.flipkart.service;

import com.flipkart.bean.Grade;
import com.flipkart.bean.Student;
import com.flipkart.exception.*;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import com.flipkart.dao.StudentDaoInterface;
import com.flipkart.dao.StudentDaoImplementation;
import jdk.nashorn.internal.runtime.Context;

//import static java.awt.event.InputEvent.logger;

public class StudentImplementation implements StudentInterface {
    StudentDaoInterface SDO =StudentDaoImplementation.getInstance();

    //Function for student to register
    /**
     * Method to register student
     * @param name
     * @param password
     * @param department
     * @param joiningDate
     * @param address
     * @param contactnum
     * @return  student
     */
    public Student registerStudent(String studentId,String name, String password, String department, String joiningDate, String address, String contactnum)
    {
        try {
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
        catch (UsernameTakenException e) {

            System.out.println(e.getMessage());
            return null;

        }

    }

    // Function to view the grade card
    /**
     * Method to view grade card
     * @param studentID
     * @param semesterID
     * @return  void
     */
    public void viewGradeCard(String studentID, int semesterID)
    {
        try {
            List<Grade> R = SDO.viewReportCard(studentID);
            System.out.println("Semester: 1 Grade Card");
            for(Grade g: R)
            {
                System.out.println("courseid :" +g.getCourseId() + "   Grade: "+ g.getGrade());
            }
        }
        catch (ReportCardNotGeneratedException | GradeNotAddedException | StudentNotApprovedException | FeesPendingException e) {
            System.out.println("Exception while viewing Grade Card");
            //logger.error(e.getMessage());
        }
        //print grade card fetching from the DB
        return;
    }
}
