package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Student;
import com.flipkart.constant.SQLQueries;
import com.flipkart.exception.*;
import com.flipkart.utils.DBUtil;
import jdk.nashorn.internal.runtime.Context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.*;
import java.sql.Date;
//import java.util.Date;
import java.util.Scanner;

public class StudentDaoImplementation implements StudentDaoInterface {

    private Context.ThrowErrorManager logger;

    public static void main(String[]args) {
        StudentDaoImplementation test = new StudentDaoImplementation();
        //test.checkPaymentWindow(5);//need to see check payment window
    }


    private static volatile StudentDaoImplementation instance=null;




    public static StudentDaoImplementation getInstance() {
        if(instance==null)
        {
            // This is a synchronized block, when multiple threads will access this instance
            synchronized(StudentDaoImplementation.class){
                instance = new StudentDaoImplementation();
            }
        }
        return instance;
    }

    public Student addStudent(Student student){

        Connection connection = DBUtil.getConnection();

        try
        {
            PreparedStatement stmt = connection.prepareStatement(SQLQueries.GET_MAX_USER_ID);
            ResultSet results = stmt.executeQuery();
            String studentId="0";
            if(results.next()) {
                studentId=results.getString(1);
            }
            int nextstudentid=Integer.parseInt(studentId)+1;
            studentId=Integer.toString(nextstudentid);
            student.setStudentId(studentId);

            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.ADD_USER);
            preparedStatement.setString(1, student.getUserId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getPassword());
            preparedStatement.setDate(4, (Date) student.getJoiningDate());
            preparedStatement.setString(5, "student");
            preparedStatement.setString(6, student.getAddress());
            preparedStatement.setString(7, student.getContactnum());
            preparedStatement.executeUpdate();


            PreparedStatement preparedStatement1 = connection.prepareStatement(SQLQueries.ADD_STUDENT);
            preparedStatement1.setString(1, student.getStudentId());
            preparedStatement1.setString(2, student.getDepartment());
            preparedStatement1.setBoolean(3, false);
            preparedStatement1.executeUpdate();

        }
        catch(SQLException ex) {
            System.out.println("Username taken exception");
            //throw new UsernameTakenException();

        }
        return student;
    }

    public Grade viewReportCard(String StudentID){

        Connection connection = DBUtil.getConnection();
        Grade R = new Grade();


        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.CHECK_GRADECARD);
            preparedStatement.setString(1, "1");
            ResultSet rc = preparedStatement.executeQuery();
            if(rc.getBoolean(1)) {


                PreparedStatement prepareStatement = connection.prepareStatement(SQLQueries.GET_GRADECARD);
                prepareStatement.setString(1, StudentID);

                ResultSet rs = prepareStatement.executeQuery();
            }
            else{
                System.out.println("Report Card not yet generated! Please contact admin for further details.");
            }

            //System.out.println(rs);

        } catch(SQLException e) {
            logger.error(e.getMessage());
        }

        return R;
    }

    public List<Course> viewRegisteredCourses(String studentID) {

        Connection connection=DBUtil.getConnection();
        List<Course> registeredCourses = new ArrayList<Course>();

        try
        {
            PreparedStatement preparedStatement=connection.prepareStatement(SQLQueries.GET_COURSES);
            preparedStatement.setString(1, studentID);


            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String courseId = rs.getString("courseid");

                PreparedStatement preparedStatement0 = connection.prepareStatement(SQLQueries.GET_COURSE_BY_ID);
                preparedStatement0.setString(1, courseId);

                ResultSet rs0 = preparedStatement0.executeQuery();

                if(rs0.next()) {
                    Course c = new Course();
                    c.setCourseCode(courseId);
                    c.setCourseName(rs0.getString("coursename"));
                    c.setInstructorId(rs0.getString("instructor"));
                    c.setIsprimary(rs.getBoolean("isprimary"));

                    registeredCourses.add(c);
                }
            }
        }

        catch(SQLException e) {
            logger.error(e.getMessage());
        }

        if(registeredCourses.isEmpty()) {
            System.out.println("Student not registered");
        }

        return registeredCourses;
    }

    public Boolean checkPaymentWindow(String StudentID)  throws PaymentWindowException , StudentNotRegisteredException {

       /*boolean isPaymentOpen = false;
        Connection conn = DBUtil.getConnection();

        try {

            PreparedStatement stmt, stmt2;
            stmt = conn.prepareStatement(SQLQueries.GET_SEM_BY_STUDENT, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            stmt.setString(1, StudentID);
            ResultSet rs = stmt.executeQuery();
            if(!rs.next()){
                System.out.println("Student Not Registered");
                //throw new StudentNotRegisteredException();
            }
            else{

                rs.first();
                int sem_id = rs.getInt(1);

                stmt2 = conn.prepareStatement(SQLQueries.SEM_PAYMENT_WINDOW,ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                stmt2.setString(1, Integer.toString(sem_id));
                ResultSet rs2 = stmt2.executeQuery();
                rs2.first();
                int isOpen = rs2.getInt(1);
                if (isOpen == 1)
                    isPaymentOpen = true;

                if (isPaymentOpen == false) {
                    System.out.println("Payment Window Exception");
                    //throw new PaymentWindowException();
                }

            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return isPaymentOpen;*/
        return true;

    }



}