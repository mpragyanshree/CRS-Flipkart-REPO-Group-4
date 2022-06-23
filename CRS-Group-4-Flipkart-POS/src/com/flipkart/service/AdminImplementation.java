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

//import java.util.Date;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

public class AdminImplementation {
    private static volatile AdminImplementation instance = null;
    ProfessorDaoInterface PDO = ProfessorDaoImplementation.getInstance();
    AdminDaoInterface ado  = AdminDaoImplementation.getInstance();


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
    public Course addCourse(String course_name, String courseID, String courseInstructor, int numOfSeats)
    {
        Course newCourse = new Course();
        newCourse.setCourseCode(courseID);
        newCourse.setCourseName(course_name);
        newCourse.setInstructorId(courseInstructor);
        newCourse.setNumberOfSeats(numOfSeats);
        ado.addCourse(newCourse);
        return newCourse;
    }
    public void removeCourse(String courseID)
    {
        ado.removeCourse(courseID);
        // remove course based on courseID
    }
    public void updateCourse(String course_name, String courseID, int numOfSeats, String courseInstructor){
        // update course as per the given details
        ado.updateCourse(course_name,courseID,numOfSeats,courseInstructor);
    }

    public Professor addProfessor(String username, String name, String password, String department, Date joiningDate,String address,String contact)
    {
        Professor newProf = new Professor();
        newProf.setUserId(username);
        newProf.setProfessorId(username);
        newProf.setName(name);
        newProf.setPassword(password);
        newProf.setDepartment(department);
        newProf.setJoiningDate(joiningDate);
        newProf.setAddress(address);
        newProf.setContactnum(contact);
        ado.addProfessor(newProf);
        return newProf;
    }
    public void removeProfessor(String professorID){
        ado.removeProfessor(professorID);
    }

    public void updateProfessor(String username, String name, String password, String department, String designation, String address, String contact, Date joiningDate){
        ado.updateProfessor(username,name,password,contact,joiningDate,address,department,designation);
    }

    public Admin addAdmin(String username, String name, String password, String contact, Date joiningdate, String address)
    {
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

    public void removeAdmin(String adminID)
    {
        ado.removeAdmin(adminID);
        return;
    }
    public void updateAdmin(String adminId, String name, String password, String contact, Date joiningDate,String address)
    {
        ado.updateAdmin(adminId,name,password,contact,joiningDate,address);
        return;
    }

    public void generateGradeCard(String studentID)
    {
       ado.generateGradeCard();
    }


    public void viewCourseDetails(String courseID)
    {
        // view course details based on courseID
    }
    public void enabledisableFeePaymentWindow(){

    }

    public ArrayList<Course> viewAvailableCourses(){
        return ado.viewAvailableCourses();
    }

    public void viewStudentDetails(String studentID)
    {
// View student details based on student ID
    }
    public void viewProfessorDetails(String professorID)
    {
// view professor details based on professor ID
    }
    public ArrayList<String> viewPendingStudentApproval() {
        return ado.viewPendingStudentApproval();
    }
    public void approveStudentRegistration(String studentID)
    {
        ado.approveStudentRegistration(studentID);

    }
    public ArrayList<ArrayList<String>>  viewCourseStudentList(String courseID){
        return ado.viewCourseStudentList(courseID);
    }
    public ArrayList<ArrayList<String>> viewCourseGrades(String courseID){
        return ado.viewCourseGrades(courseID);
    }
    private int calculateCPI() {
    // calculate cpi here
        return 0;
    }
}
