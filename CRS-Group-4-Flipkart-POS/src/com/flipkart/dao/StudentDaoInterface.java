package com.flipkart.dao;

import com.flipkart.bean.Grade;
import com.flipkart.bean.Student;

public interface StudentDaoInterface {
    public Student addStudent(Student student);

    public Grade viewReportCard(String StudentID);
    public Boolean checkPaymentWindow(String StudentID);

}
