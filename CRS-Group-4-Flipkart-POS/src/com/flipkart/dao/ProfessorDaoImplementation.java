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

    @Override
    public List<RegisteredStudents> getRegisteredStudents(String professorID, String courseID){
        Connection connection=DBUtil.getConnection();
        List<RegisteredStudents> registeredStudents=new ArrayList<RegisteredStudents>();


        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.GET_REGISTERED_STUDENTS);
            statement.setString(1, courseID);
            ResultSet results = statement.executeQuery();
            while(results.next())
            {
                //public EnrolledStudent(String courseCode, String courseName, int studentId)
                registeredStudents.add(new RegisteredStudents(results.getString("coursecode"),results.getString("studentid")));
            }
        }
        catch (SQLException e) {

            e.printStackTrace();
        }

        return registeredStudents;
    }


    public Boolean addGrade(String studentID,String courseID,String grade)  {
        Connection connection=DBUtil.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.ADD_GRADE);

            statement.setString(1, grade);
            statement.setString(2, courseID);
            statement.setString(3, studentID);

            int row = statement.executeUpdate();

            if(row==1)
                return true;
            else {
                return false;
            }
        }
        catch(SQLException e)
        {
            System.out.println("error encountered");
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
                viewAvailableCouse.add(new Course(rs.getString("courseCode"), rs.getString("courseName"), rs.getString("professorId"), rs.getInt("seats")));
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
    public boolean registerCourse(String professorID, String courseId){
        Connection connection = DBUtil.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.IS_AVAILABLE_COURSE_PROFESSOR);
            statement.setString(1, courseId);
            ResultSet rs = statement.executeQuery();

            if(rs.next()) {
                PreparedStatement statement2 = connection.prepareStatement(SQLQueries.REGISTER_COURSE_PROFESSOR);

                statement2.setString(1, professorID);
                statement2.setString(2, courseId);

                ResultSet rs2 = statement2.executeQuery();
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}