package com.flipkart.service;

import java.sql.Date;
import java.util.List;
import com.flipkart.bean.Student;

public interface StudentInterface {

    public Student registerStudent(String name, String password, String department, String joiningDate, String address, String contactnum);
    public void viewGradeCard(String StudentID, int semesterID);

}
