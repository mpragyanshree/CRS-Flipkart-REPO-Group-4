package com.flipkart.dao;
import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface AdminDaoInterface {
    public ArrayList<String> viewPendingStudentApproval();
    public void enableFeePayment(int semesterId) throws SQLException;
    public void disableFeePayment(int semesterId) throws SQLException;
    public Admin addAdmin(Admin admin);
    public void removeCourse(String courseId);
    public Course addCourse(Course course);
    public void removeAdmin(String adminId);
    public void updateCourse(String course_name, String courseID, int numOfSeats, String courseInstructor);
    public void updateAdmin(String adminId, String name, String password, String contact, Date joiningDate, String address);
    public Professor addProfessor(Professor prof);
    public void removeProfessor(String professorID);
    public void updateProfessor(String username, String name, String password, String contact, Date joiningDate, String address, String department, String designation);
    public void generateGradeCard();
    public ArrayList<Course> viewAvailableCourses();
    public void approveStudentRegistration(String studentId);
    public ArrayList<ArrayList<String>>  viewCourseStudentList(String courseID);
    public ArrayList<ArrayList<String>> viewCourseGrades(String courseID);
}

