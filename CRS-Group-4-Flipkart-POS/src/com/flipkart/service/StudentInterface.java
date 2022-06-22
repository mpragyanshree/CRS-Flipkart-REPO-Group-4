package com.flipkart.service;

import java.util.Date;
import java.util.List;
import com.flipkart.bean.Student;

public interface StudentInterface {

    public Student registerStudent(String studentId, String name, String password, String department, Date joiningDate);
    public void viewGradeCard(String StudentID, int semesterID);

}
