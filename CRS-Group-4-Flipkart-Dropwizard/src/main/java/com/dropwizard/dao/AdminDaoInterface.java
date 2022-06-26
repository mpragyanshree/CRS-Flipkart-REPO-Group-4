package com.flipkart.dao;
import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.exception.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AdminDaoInterface {
    /**
     * Method to view pending student approval
     * @return  list of students
     */
    public ArrayList<String> viewPendingStudentApproval();

    /**
     * Method to enable fee payment
     * @param semesterId
     * @return  void
     */
    public void enableFeePayment(String semesterId);

    /**
     * Method to disable fee payment
     * @param semesterId
     * @return  void
     */
    public void disableFeePayment(String semesterId);

    /**
     * Method to add admin
     * @param admin
     * @return  admin
     */
    public Admin addAdmin(Admin admin) throws UsernameTakenException;
    /**
     * Method to remove course
     * @param courseId
     * @return  void
     */
    public void removeCourse(String courseId) throws CourseNotFoundException;
    /**
     * Method to add course
     * @param course
     * @return  void
     */
    public void addCourse(Course course)throws CourseAlreadyPresentException;
    /**
     * Method to remove admin
     * @param adminID
     * @return  void
     */
    public void removeAdmin(String adminID) throws UserNotFoundException;

    /**
     * Method to updateCourse
     * @param course_name
     * @param courseID
     * @param numOfSeats
     * @param courseInstructor
     * @return  void
     */
    public void updateCourse(String course_name, String courseID, int numOfSeats, String courseInstructor);
    /**
     * Method to update admin
     * @param adminId
     * @param name
     * @param password
     * @param contact
     * @param joiningDate
     * @param address
     * @return  void
     */
    public void updateAdmin(String adminId, String name, String password, String contact, String joiningDate, String address);
    /**
     * Method to add professor
     * @param prof
     * @return  Professor
     */
    public Professor addProfessor(Professor prof) throws UsernameTakenException;
    /**
     * Method to remove Professor
     * @param professorID
     * @return  void
     */
    public void removeProfessor(String professorID) throws UserNotFoundException;

    /**
     * Method to update Professor
     * @param username
     * @param name
     * @param password
     * @param contact
     * @param joiningDate
     * @param address
     * @param department
     * @param designation
     * @return  void
     */
    public void updateProfessor(String username, String name, String password, String contact, String joiningDate, String address, String department, String designation);

    /**
     * Method to generate grade card
     * @return  void
     */
    public void generateGradeCard();
    /**
     * Method to view availabe course
     * @return  list of course
     */
    public ArrayList<Course> viewAvailableCourses() throws CourseSeatsUnavailableException, CourseNotFoundException, InvalidSemesterException;
    /**
     * Method to approve student registration
     * @param studentId
     * @return  void
     */
    public void approveStudentRegistration(String studentId) throws FeesPendingException, UserNotFoundException, StudentNotApprovedException;
    /**
     * Method to view course student list
     * @param courseID
     * @return course list
     */
    public ArrayList<String>  viewCourseStudentList(String courseID) throws InvalidCourseException;

    /**
     * Method to view grades in respective courses
     * @param courseID
     * @return  list of courses
     */
    public ArrayList<ArrayList<String>> viewCourseGrades(String courseID) throws InvalidCourseException;
}

