package com.flipkart.service;

import java.util.ArrayList;
import java.util.Date;
import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;

public interface AdminInterface {

    /**
     * Method to view Pending Student Approval

     * @return  list of pending student
     */
    public ArrayList<String> viewPendingStudentApproval();

    /**
     * Method to approve student registration
     * @param studentID

     * @return  boolean
     */
    public void approveStudentRegistration(String studentID);

    /**
     * Method to add course
     * @param course_name
     * @param courseID
     * @param courseInstructor
     * @param numOfSeats
     * @return  boolean
     */
    public Course addCourse(String course_name, String courseID, String courseInstructor, int numOfSeats);

    /**
     * Method to remove course
     * @param courseId
     * @return  boolean
     */
    public void removeCourse(String courseId);

    /**
     * Method to update course
     * @param course_name
     * @param courseID
     * @param numOfSeats
     * @param courseInstructor
     * @return  boolean
     */
    public void updateCourse(String course_name, String courseID, int numOfSeats, String courseInstructor);

    /**
     * Method to generate grade card
     * @return  grade card
     */
    public void generateGradeCard();

    /**
     * Method to view course details
     * @return  course details
     */
    public void viewCourseDetails();

    /**
     * Method to add professor
     * @return  Professor
     */
    public Professor addProfessor();

    /**
     * Method to view student details
     * @return  void
     */
    public void viewStudentDetails();

    /**
     * Method to view professor details
     * @return  void
     */
    public void viewProfessorDetails();

    /**
     * Method to view course grades
     * @return  list of grades
     */
    public ArrayList<ArrayList<String>> viewCourseGrades(String courseID);

    /**
     * Method to enable disable fee payment window
     * @return  void
     */
    public void enabledisableFeePaymentWindow();

    /**
     * Method to add admin
     * @param username
     * @param name
     * @param password
     * @param contact
     * @param joiningdate
     * @param address
     * @return  admin
     */
    public Admin addAdmin(String username, String name, String password, String contact, String joiningdate, String address);
    /**
     * Method to addProfessor
     * @param username
     * @param name
     * @param password
     * @param department
     * @param designation
     * @param joiningDate
     * @param address
     * @param contact
     * @return  professor
     */
    public Professor addProfessor(String username, String name, String password, String department, String designation, String joiningDate,String address,String contact);

    /**
     * Method to remove professor
     * @param professorID
     * @return  void
     */
    public void removeProfessor(String professorID);

    /**
     * Method to update professor
     * @param username
     * @param name
     * @param password
     * @param department
     * @param designation
     * @param address
     * @param contact
     * @param joiningDate
     * @return  boolean
     */
    public void updateProfessor(String username, String name, String password, String department, String designation, String address, String contact, String joiningDate);

    /**
     * Method to remove admin
     * @param adminId
     * @return  boolean
     */
    public void removeAdmin(String adminId);

    /**
     * Method to update admin
     * @param name
     * @param password
     * @param contact
     * @param joiningDate
     * @param address
     * @return  boolean
     */
    public void updateAdmin(String name, String password, String contact, String joiningDate,String address);
    /**
     * Method to view Available Courses

     * @return  list of courses
     */
    public ArrayList<Course> viewAvailableCourses();
    /**
     * Method to login user
     * @param courseID
     * @return  list of student list
     */
    public ArrayList<ArrayList<String>>  viewCourseStudentList(String courseID);
}
