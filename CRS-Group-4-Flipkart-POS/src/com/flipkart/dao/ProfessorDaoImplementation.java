package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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

    public static ProfessorDaoImplementation getInstance()
    {
        if(instance==null)
        {
            // This is a synchronized block, when multiple threads will access this instance
            synchronized(ProfessorDaoImplementation.class){
                instance=new ProfessorDaoImplementation();
            }
        }
        return instance;
    }


    @Override
    public List<Course> getCoursesByProfessor(String profId) {
        Connection connection= DBUtil.getConnection();
        List<Course> courseList=new ArrayList<Course>();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.GET_COURSES);

            statement.setString(1, profId);

            ResultSet results=statement.executeQuery();
            while(results.next())
            {
                courseList.add(new Course(results.getString("courseCode"),results.getString("courseName"),results.getString("professorId"),results.getInt("seats")));
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
        return courseList;

    }


    @Override
    public List<RegisteredStudents> getRegisteredStudents(String professorID, String courseID){
        Connection connection=DBUtil.getConnection();
        List<RegisteredStudents> registeredStudents=new ArrayList<RegisteredStudents>();


        try {



            PreparedStatement statement = connection.prepareStatement(SQLQueries.GET_REGISTERED_STUDENTS);
            statement.setString(1, professorID);
            statement.setString(1, courseID);

            ResultSet results = statement.executeQuery();
            while(results.next())
            {
                //public EnrolledStudent(String courseCode, String courseName, int studentId)
                registeredStudents.add(new RegisteredStudents(results.getString("courseID"),results.getString("courseName"),results.getInt("studentId")));
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
        return registeredStudents;
    }


    public Boolean addGrade(int studentID,String courseID,String grade, int semester) {
        Connection connection=DBUtil.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.ADD_GRADE);

            statement.setString(1, grade);
            statement.setString(2, courseID);
            statement.setInt(3, studentID);
            statement.setInt(4, semester);

            int row = statement.executeUpdate();

            if(row==1)
                return true;
            else
                return false;
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
    public String getProfessorById(String profId)
    {
        String prof_Name = null;
        Connection connection=DBUtil.getConnection();
        try
        {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.GET_PROF_NAME);

            statement.setString(1, profId);
            ResultSet rs = statement.executeQuery();
            rs.next();

            prof_Name = rs.getString(1);

        }
        catch(SQLException e)
        {
            System.out.println("error encountered");
        }
        finally
        {
            try
            {
                connection.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        return prof_Name;
    }

    @Override
    public List<Course> viewAvailableCouses(String semester) {
        Connection connection = DBUtil.getConnection();
        List<Course> viewAvailableCouse = new ArrayList<Course>();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.AVAILABLE_COURSES_PROFESSOR);

            statement.setString(1, semester);
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
    public boolean registerCourse(String professorID, String courseId, int semester){
        Connection connection = DBUtil.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.IS_AVAILABLE_COURSE_PROFESSOR);
            statement.setString(1, courseId);
            statement.setString(2,String.valueOf(semester) );
            ResultSet rs = statement.executeQuery();



            if(rs.next()) {

                PreparedStatement statement2 = connection.prepareStatement(SQLQueries.REGISTER_COURSE_PROFESSOR);

                statement2.setString(1, professorID);
                statement2.setString(1, courseId);
                statement2.setString(1, String.valueOf(semester));

                ResultSet rs2 = statement2.executeQuery();
                return true;
            } else {
                return false;
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
        return false;
    }


}