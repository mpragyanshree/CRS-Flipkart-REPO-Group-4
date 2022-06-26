package com.flipkart.service;


import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Student;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Admin;
import com.flipkart.dao.AdminDaoImplementation;
import com.flipkart.dao.AdminDaoInterface;
import com.flipkart.dao.StudentDaoImplementation;
import com.flipkart.dao.StudentDaoInterface;
import com.flipkart.dao.ProfessorDaoImplementation;
import com.flipkart.dao.ProfessorDaoInterface;
import com.flipkart.exception.*;
import jdk.nashorn.internal.runtime.Context;

//import java.util.Date;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

public class AdminImplementation {
    private static volatile AdminImplementation instance = null;
    private Context.ThrowErrorManager logger;
    ProfessorDaoImplementation PDO = ProfessorDaoImplementation.getInstance();
    AdminDaoImplementation ado  = AdminDaoImplementation.getInstance();


    public static AdminImplementation getInstance()
    {
        if(instance == null)
        {
            synchronized(AdminImplementation.class){
                instance = new AdminImplementation();
            }
        }
        return instance;
    }

    // method to add course
    public Course addCourse(String courseID, String course_name, String courseInstructor, int numOfSeats)
    {
        try {
            Course newCourse = new Course();
            newCourse.setCourseCode(courseID);
            newCourse.setCourseName(course_name);
//            newCourse.setInstructorId(courseInstructor);
            newCourse.setNumberOfSeats(numOfSeats);
//            System.out.println("Check impl " + newCourse.getCourseName()+" "+newCourse.getCourseCode()+" "+newCourse.getInstructorId()+" "+newCourse.getNumberOfSeats());
            ado.addCourse(newCourse);
            return newCourse;
        }
        catch(CourseAlreadyPresentException e) {
            CourseAlreadyPresentException er = new CourseAlreadyPresentException(courseID);
            logger.error(er.getMessage());
            return null;
        }
    }

    // method to remove course
    public void removeCourse(String courseID)
    {
        try {
            ado.removeCourse(courseID);
        }
        catch (CourseNotFoundException e){
            CourseNotFoundException ex = new CourseNotFoundException(courseID);
            logger.error(ex.getMessage());

        }
        // remove course based on courseID
    }
    // method to update course
    public void updateCourse(String course_name, String courseID, int numOfSeats, String courseInstructor){
        // update course as per the given details
        ado.updateCourse(course_name,courseID,numOfSeats,courseInstructor);
    }

    //method to add professor
    public Professor addProfessor(String username, String name, String password, String department, String designation, String joiningDate,String address,String contact)
    {
        try {
            Professor newProf = new Professor();
            newProf.setUserId(username);
            newProf.setProfessorId(username);
            newProf.setName(name);
            newProf.setPassword(password);
            newProf.setDepartment(department);
            newProf.setDesignation(designation);
            newProf.setJoiningDate(joiningDate);
            newProf.setAddress(address);
            newProf.setContactnum(contact);
            ado.addProfessor(newProf);
            return newProf;
        }
        catch(UsernameTakenException e){
            logger.error(e.getMessage());
            return null;
        }
    }
    //method to remove professor
    public void removeProfessor(String professorID){
        try {
            ado.removeProfessor(professorID);
        }
        catch (UserNotFoundException ex){
            logger.error(ex.getMessage());
        }
    }
    // method to update professor
    public void updateProfessor(String username, String name, String password, String department, String designation, String address, String contact,String joiningDate){
        ado.updateProfessor(username,name,password,contact,joiningDate,address,department,designation);
    }
    //method to add admin
    public Admin addAdmin(String username, String name, String password, String contact, String joiningdate, String address)
    {
        try {
            Admin newAdmin = new Admin();
            newAdmin.setUserId(username);
            newAdmin.setAdminId(username);
            newAdmin.setName(name);
            newAdmin.setPassword(password);
            newAdmin.setJoiningDate(joiningdate);
            newAdmin.setAddress(address);
            newAdmin.setContactnum(contact);
            ado.addAdmin(newAdmin);
            return newAdmin;
        }
        catch(UsernameTakenException e){
            logger.error(e.getMessage());
            return null;
        }
    }
    //method to remove admin
    public void removeAdmin(String adminID)
    {
        try {
            ado.removeAdmin(adminID);
        }
        catch(UserNotFoundException e){
            logger.error(e.getMessage());
        }
        return;
    }

    // method to update admin
    public void updateAdmin(String adminId, String name, String password, String contact, String joiningDate,String address)
    {
        ado.updateAdmin(adminId,name,password,contact,joiningDate,address);
        return;
    }
    //method to generate grade card
    public void generateGradeCard()
    {
       ado.generateGradeCard();
    }


    public void viewCourseDetails(String courseID)
    {
        // view course details based on courseID
    }
    //method to enable fee payment window
    public void enableFeePaymentWindow(String semesterID){
        ado.enableFeePayment(semesterID);
    }
    //method to disable fee payment window
    public void disableFeePaymentWindow(String semesterID){
        ado.disableFeePayment(semesterID);
    }

    //method to view available courses
    public ArrayList<Course> viewAvailableCourses(){
        try {
            return ado.viewAvailableCourses();
        }
        catch (CourseSeatsUnavailableException | CourseNotFoundException | InvalidSemesterException e){
            logger.error(e.getMessage());
            return null;
        }
    }

    //method to view student details
    public void viewStudentDetails(String studentID)
    {
// View student details based on student ID
    }

    //method to view professor details
    public void viewProfessorDetails(String professorID)
    {
// view professor details based on professor ID
    }

    //method to view pending student approval list
    public ArrayList<String> viewPendingStudentApproval() {
        return ado.viewPendingStudentApproval();
    }
    public void approveStudentRegistration(String studentID)
    {
        try {
            ado.approveStudentRegistration(studentID);
        }
        catch(FeesPendingException | UserNotFoundException | StudentNotApprovedException e){
            logger.error(e.getMessage());

        }

    }

    //method to view student details
    public ArrayList<String>  viewCourseStudentList(String courseID){
        try {
            return ado.viewCourseStudentList(courseID);
        }
        catch(InvalidCourseException e){
            logger.error(e.getMessage());
            return null;
        }
    }

    //method to view course grades
    public ArrayList<ArrayList<String>> viewCourseGrades(String courseID){
        try {
            return ado.viewCourseGrades(courseID);
        }
        catch (InvalidCourseException e){
            logger.error(e.getMessage());
            return null;
        }
    }
    private int calculateCPI() {
    // calculate cpi here
        return 0;
    }
}
