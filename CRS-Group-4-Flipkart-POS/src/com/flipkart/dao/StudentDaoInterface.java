package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Student;
import com.flipkart.exception.*;

import java.util.List;

public interface StudentDaoInterface {
    public Student addStudent(Student student) throws UsernameTakenException;

    public List<Grade> viewReportCard(String StudentID) throws ReportCardNotGeneratedException, GradeNotAddedException , StudentNotApprovedException, FeesPendingException;
    public Boolean checkPaymentWindow(String StudentID);

    public List<Course> viewRegisteredCourses(String studentID) throws StudentNotRegisteredException;

}
