package com.dropwizard.dao;

import com.dropwizard.bean.Course;
import com.dropwizard.bean.Grade;
import com.dropwizard.bean.Student;
import com.dropwizard.exception.*;

import java.util.List;

public interface StudentDaoInterface {
    public Student addStudent(Student student) throws UsernameTakenException;

    public List<Grade> viewReportCard(String StudentID) throws ReportCardNotGeneratedException, GradeNotAddedException , StudentNotApprovedException, FeesPendingException;
    public Boolean checkPaymentWindow(String StudentID);

    public List<Course> viewRegisteredCourses(String studentID) throws StudentNotRegisteredException;
    public String getStudentIDFromUserName(String username) throws StudentNotRegisteredException;
}
