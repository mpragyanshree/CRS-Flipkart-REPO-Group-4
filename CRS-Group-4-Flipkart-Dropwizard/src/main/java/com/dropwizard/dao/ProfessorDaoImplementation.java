package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.flipkart.exception.*;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredStudents;
import com.flipkart.bean.Student;

import com.flipkart.constant.SQLQueries;
import com.flipkart.service.StudentImplementation;
import com.flipkart.utils.DBUtil;


public  class ProfessorDaoImplementation implements ProfessorDaoInterface {

    private static volatile ProfessorDaoImplementation instance=null;

    private ProfessorDaoImplementation()
    {

    }

    public static ProfessorDaoImplementation getInstance() {
        if (instance == null) {
            // This is a synchronized block, when multiple threads will access this instance
            synchronized (ProfessorDaoImplementation.class) {
                instance = new ProfessorDaoImplementation();
            }
        }
        return instance;
    }

    public List<RegisteredStudents> getRegisteredStudents(String professorID, String courseID) throws NoStudentInCourseException{
        Connection connection=DBUtil.getConnection();
        List<RegisteredStudents> registeredStudents=new ArrayList<RegisteredStudents>();


        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.GET_REGISTERED_STUDENTS);
            statement.setString(1, courseID);
            statement.setString(2, professorID);
            ResultSet results = statement.executeQuery();



            while(results.next()){
                //public EnrolledStudent(String courseCode, String courseName, int studentId)
                registeredStudents.add(new RegisteredStudents(results.getString("coursecode"),results.getString("studentid")));

            }
           if(registeredStudents.isEmpty()) throw new NoStudentInCourseException(courseID);

        }
        catch (SQLException e) {

            e.printStackTrace();
        }


        return registeredStudents;
    }



    public Boolean addGrade(String studentID,String courseID,String grade) throws GradeNotAddedException,StudentNotRegisteredException  {
        Connection connection=DBUtil.getConnection();
        try {

            PreparedStatement checkStmt = connection.prepareStatement(SQLQueries.CHECK_REGISTRATION);
            checkStmt.setString(1, studentID);
            checkStmt.setString(2, courseID);
            ResultSet rs = checkStmt.executeQuery();

            if(!rs.next()) {
                throw new StudentNotRegisteredException();
            }

            if(!rs.getBoolean("isapproved")) {
                throw new StudentNotRegisteredException();

            }




            PreparedStatement statement = connection.prepareStatement(SQLQueries.ADD_GRADE);

            statement.setString(1, grade);
            statement.setString(2, courseID);
            statement.setString(3, studentID);

            int row = statement.executeUpdate();

            if(row==1)
                return true;
            else {
                throw new GradeNotAddedException(studentID);

            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            try {
                connection.close();
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
        return false;
    }



    @Override
    public List<Course> viewAvailableCourses() {
        Connection connection = DBUtil.getConnection();
        List<Course> viewAvailableCouse = new ArrayList<Course>();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.AVAILABLE_COURSES_PROFESSOR);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
             //   System.out.println(rs.getString("coursecode"));


                viewAvailableCouse.add(new Course(rs.getString("coursecode"), rs.getString("coursename"), rs.getString("instructorid"), rs.getInt("numberOfSeats")));
            }


        } catch (SQLException e) {
            System.out.println("error encountered");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return viewAvailableCouse;
    }

    @Override
    public boolean registerCourse(String professorID, String courseId) throws CourseExistsInCartException{
        Connection connection = DBUtil.getConnection();

        try {


         //   System.out.println("wowow");
            PreparedStatement statement = connection.prepareStatement(SQLQueries.IS_AVAILABLE_COURSE_PROFESSOR);
            statement.setString(1, courseId);
            ResultSet rs = statement.executeQuery();

       //    System.out.println(rs.getString("coursecode"));

            if(rs.next()) {
                PreparedStatement statement2 = connection.prepareStatement(SQLQueries.REGISTER_COURSE_PROFESSOR);

                statement2.setString(1, professorID);
                statement2.setString(2, courseId);

                statement2.executeUpdate();
                return true;
            } else {
                throw new CourseExistsInCartException(courseId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}