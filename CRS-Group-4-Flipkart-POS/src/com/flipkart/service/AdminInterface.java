package com.flipkart.service;

import java.util.ArrayList;
import java.util.Date;
import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;

public interface AdminInterface {
    public ArrayList<String> viewPendingStudentApproval();
    public void approveStudentRegistration(String studentID);
    public Course addCourse(String course_name, String courseID, String courseInstructor, int numOfSeats);
    public void removeCourse(String courseId);
    public void updateCourse(String course_name, String courseID, int numOfSeats, String courseInstructor);
    public void generateGradeCard();
    public void viewCourseDetails();
    public Professor addProfessor();
    public void viewStudentDetails();
    public void viewProfessorDetails();
    public ArrayList<ArrayList<String>> viewCourseGrades(String courseID);
    public void enabledisableFeePaymentWindow();
    public Admin addAdmin(String username, String name, String password, String contact, String joiningdate, String address);
    public Professor addProfessor(String username, String name, String password, String department, String joiningDate,String address,String contact);
    public void removeProfessor(String professorID);
    public void updateProfessor(String username, String name, String password, String department, String designation, String address, String contact, String joiningDate);
    public void removeAdmin(String adminId);
    public void updateAdmin(String name, String password, String contact, String joiningDate,String address);
    public ArrayList<Course> viewAvailableCourses();
    public ArrayList<ArrayList<String>>  viewCourseStudentList(String courseID);
}
