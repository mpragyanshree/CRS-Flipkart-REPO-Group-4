package com.flipkart.service;

import java.sql.Date;
import java.util.List;
import com.flipkart.bean.Student;

public interface StudentInterface {

    /**
     * Method to view Register student
     * @param name
     * @param password
     * @param department
     * @param joiningDate
     * @param address
     * @param contactnum
     * @return  void
     */

    public Student registerStudent(String studentID, String name, String password, String department, String joiningDate, String address, String contactnum);

    /**
     * Method to view Grade card by student
     * @param StudentID
     * @param semesterID
     * @return  void
     */
    public void viewGradeCard(String StudentID, int semesterID);

}
