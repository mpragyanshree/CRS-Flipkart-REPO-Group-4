package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.constant.SQLQueries;
import com.flipkart.exception.*;
import com.flipkart.utils.DBUtil;
import jdk.nashorn.internal.runtime.Context;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;


public class SemesterRegistrationDaoImplementation implements SemesterRegistrationDaoInterface {
    private static volatile SemesterRegistrationDaoImplementation instance=null;
    private static Connection conn = DBUtil.getConnection();
    private Context.ThrowErrorManager logger;

    public static void main(String[] args) {
        SemesterRegistrationDaoInterface test = new SemesterRegistrationDaoImplementation();
        test.registerCourses("5",1);
    }

    public boolean registerCourses(String studentId, int semesterId) {

        PreparedStatement stmt,stmt2;

        try {

            stmt2 = conn.prepareStatement(SQLQueries.VERIFY_PAYMENT, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            stmt2.setString(1, studentId);
            ResultSet rs2 = stmt2.executeQuery();
            if(rs2.next()==true){
                //System.out.println("Payment already done");
                throw new PaymentDoneException();
            }

            String query = SQLQueries.REGISTRATION_FINISH_REG;
            stmt = conn.prepareStatement(query);
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();

            int totalPrimaryCourse = 0, totalAlternateCourses = 0;

            while(rs.next()) {
                if(rs.getBoolean("isprimary")) {
                    totalPrimaryCourse++;
                }
                else {
                    totalAlternateCourses++;
                }
            }

            if(totalPrimaryCourse == 4 && totalAlternateCourses == 2) {

				System.out.println("+-----------------------------------+");
				System.out.println("|         Notification Alert!       |");
				System.out.println("+-----------------------------------+");
				System.out.println("|      Registration Completed!      |");
				System.out.println("|    Please Complete Fee Payment!   |");
				System.out.println("+-----------------------------------+");

                return true;
            }

            else {
                    throw new InvalidSemesterRegistration();
            }

        } catch (SQLException e) {
            //System.out.println("SQL Exception\n");
            logger.error(e.getMessage());
        } catch (PaymentDoneException e) {
            throw new RuntimeException(e);
        } catch (InvalidSemesterRegistration e) {
            throw new RuntimeException(e);
        }

        return false;
    }
    public ArrayList<Course> viewAvailableCourses() {

        PreparedStatement stmt;
        ArrayList<Course> courseCatalog = null;

        try {


            String query = SQLQueries.REGISTRATION_GET_ALL_COURSES;
            stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            courseCatalog = new ArrayList<>();

            while(rs.next()) {
                String courseID = rs.getString("coursecode");
                String courseName = rs.getString("coursename");
                String instructor = rs.getString("instructorid");
                Integer availableSeats = rs.getInt("numberofseats");

                Course course = new Course(courseID, courseName, instructor,availableSeats);
                courseCatalog.add(course);
            }
        } catch (SQLException e) {

            //System.out.println("sql exception while viewing available courses");
            logger.error(e.getMessage());
        }

        return courseCatalog;
    }


    public boolean dropCourse(String studentId, int semesterId, String courseId)  throws CourseNotFoundException, CourseNotInCart {

        PreparedStatement stmt;
        Course courseObj;

        try {

            courseObj = getCourseDetails(courseId, semesterId);

            if(courseObj == null) {
                throw new CourseNotFoundException();
            }

            if(!checkRegisteredCourseExists(studentId, semesterId, courseId)) {
                throw new CourseNotInCart(courseId);
            }

            String query = SQLQueries.REGISTRATION_DROP_COURSE;

            stmt = conn.prepareStatement(query);
            stmt.setString(1, studentId);
            stmt.setString(2, courseObj.getCourseCode());
            stmt.execute();

            changeCourseSeats(courseId, semesterId, 1);

            return true;

        } catch (Exception e) {
            System.out.println("Error while dropping course");
        } catch (CourseNotInCart e) {
            throw new RuntimeException(e);
        }

        return false;
    }
    private Course getCourseDetails(String courseId, Integer semesterId) {
        PreparedStatement stmt;
        Course courseObj = null;

        try {

            String query = SQLQueries.REGISTRATION_GET_COURSES;

            stmt = conn.prepareStatement(query);
            stmt.setString(1, courseId);
            ResultSet rs = stmt.executeQuery();

            String courseID = null, courseName, instructor;
            int offeredSemester, availableSeats;

            while (rs.next()) {
                courseID = rs.getString("courseID");
                courseName = rs.getString("course_name");
                instructor = rs.getString("instructor");
                offeredSemester = rs.getInt("offered_semester");
                availableSeats = rs.getInt("available_seats");

                courseObj = new Course(courseID, courseName, instructor, availableSeats);
            }

            return courseObj;

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return null;
    }
    private boolean checkRegisteredCourseExists(String studentId, int semesterId, String courseId) {
        PreparedStatement stmt;

        try {

            String query = SQLQueries.REGISTRATION_COURSE_EXISTS;

            stmt = conn.prepareStatement(query);
            stmt.setString(1, studentId);
            stmt.setString(2, courseId);
            ResultSet rs = stmt.executeQuery();

            rs.next();

            if(rs.getInt("COUNT(1)") == 1) {
                return true;
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return false;
    }
    private void changeCourseSeats(String courseId, int semesterId, int change) {
        PreparedStatement stmt;

        try {

            int currentAvailableSeats = Objects.requireNonNull(getCourseDetails(courseId, semesterId)).getNumberOfSeats();
            String query = SQLQueries.REGISTRATION_UPDATE_SEATS;
            int seatChange =  (change == 0 ? -1 : 1);
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, currentAvailableSeats + seatChange);
            stmt.setString(2, courseId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public boolean addCourse(String studentId, int semesterId, String courseId, boolean isPrimary) throws CourseNotFoundException, CourseSeatsUnavailableException, CourseExistsInCartException {


        PreparedStatement stmt;
        Course courseObj;

        try {

            courseObj = getCourseDetails(courseId, semesterId);

            if(courseObj == null) {
                throw new CourseNotFoundException();            }

            if(courseObj.getNumberOfSeats() <= 0) {
                throw new CourseNotInCart(courseId);
            }

            if(checkRegisteredCourseExists(studentId, semesterId, courseId)) {
                System.out.println("Course seats Exception");
            }

            String query = SQLQueries.REGISTRATION_ADD_COURSE;

            stmt = conn.prepareStatement(query);
            stmt.setString(1, studentId);
            stmt.setString(2, courseObj.getCourseCode());
            stmt.setBoolean(3, isPrimary);
            stmt.setString(4, courseObj.getInstructorId());
            stmt.setString(5, null);
            stmt.execute();

            changeCourseSeats(courseId, semesterId, 0);

            return true;

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return false;
    }






}
