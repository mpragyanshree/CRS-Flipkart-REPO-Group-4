package com.flipkart.dao;
import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.exception.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AdminDaoInterface {
    public ArrayList<String> viewPendingStudentApproval();
    public void enableFeePayment(String semesterId);
    public void disableFeePayment(String semesterId);
    public Admin addAdmin(Admin admin) throws UsernameTakenException;
    public void removeCourse(String courseId) throws CourseNotFoundException;
    public Course addCourse(Course course) throws CourseAlreadyPresentException;
    public void removeAdmin(String adminID) throws UserNotFoundException;
    public void updateCourse(String course_name, String courseID, int numOfSeats, String courseInstructor);
    public void updateAdmin(String adminId, String name, String password, String contact, String joiningDate, String address);
    public Professor addProfessor(Professor prof) throws UsernameTakenException;
    public void removeProfessor(String professorID) throws UserNotFoundException;
    public void updateProfessor(String username, String name, String password, String contact, String joiningDate, String address, String department, String designation);
    public void generateGradeCard();
    public ArrayList<Course> viewAvailableCourses() throws CourseSeatsUnavailableException, CourseNotFoundException, InvalidSemesterException;
    public void approveStudentRegistration(String studentId) throws FeesPendingException, UserNotFoundException, StudentNotApprovedException;
    public ArrayList<ArrayList<String>>  viewCourseStudentList(String courseID) throws InvalidCourseException;
    public ArrayList<ArrayList<String>> viewCourseGrades(String courseID) throws InvalidCourseException;
}

