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

    /*public static void main(String[] args) {
        SemesterRegistrationDaoInterface test = new SemesterRegistrationDaoImplementation();
        test.registerCourses("5",1);
    }*/

    public boolean registerCourses(String studentId) throws InvalidSemesterRegistration,PaymentDoneException {

        PreparedStatement stmt,stmt2,stmt1;

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
                String query1 = SQLQueries.UPDATE_REG_STATUS;
                stmt1 = conn.prepareStatement(query1);
                stmt1.setBoolean(1, true);
                stmt1.setString(2, studentId);
                ResultSet rs1 = stmt.executeQuery();

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

            courseObj = getCourseDetails(courseId);

            if(courseObj == null) {
                throw new CourseNotFoundException();
            }

            if(!checkRegisteredCourseExists(studentId, courseId)) {
                throw new CourseNotInCart(courseId);
            }

            String query = SQLQueries.REGISTRATION_DROP_COURSE;
            stmt = conn.prepareStatement(query);
            stmt.setString(1, studentId);
            stmt.setString(2, courseObj.getCourseCode());
            stmt.execute();

            if(courseObj.getIsprimary()) changeCourseSeats(courseId,1); //check

            return true;

        } catch (Exception e) {
            System.out.println("Error while dropping course");
        } catch (CourseNotInCart e) {
            throw new RuntimeException(e);
        }

        return false;
    }
    public Course getCourseDetails(String courseId) {
        PreparedStatement stmt;
        Course courseObj = null;

        try {

            String query = SQLQueries.REGISTRATION_GET_COURSES;
            stmt = conn.prepareStatement(query);
            stmt.setString(1, courseId);
            ResultSet rs = stmt.executeQuery();

            String courseID, courseName, instructor;
            int availableSeats;
            while (rs.next()) {
                courseID = rs.getString("coursecode");
                courseName = rs.getString("coursename");
                instructor = rs.getString("instructorid");
                availableSeats = rs.getInt("numberofseats");
                courseObj = new Course(courseID, courseName, instructor, availableSeats);
            }
            return courseObj;

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return null;
    }
    public boolean checkRegisteredCourseExists(String studentId, String courseId) {
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
   public void changeCourseSeats(String courseId, int change) {
        PreparedStatement stmt;

        try {
            int currentAvailableSeats = Objects.requireNonNull(getCourseDetails(courseId)).getNumberOfSeats();
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

    public boolean addCourse(String studentId,  String courseId, boolean isPrimary) throws CourseNotFoundException, CourseSeatsUnavailableException, CourseExistsInCartException {


        PreparedStatement stmt;
        Course courseObj;
        try {

            courseObj = getCourseDetails(courseId);
            if(courseObj == null) {
                throw new CourseNotFoundException();            }

            if(courseObj.getNumberOfSeats() <= 0) {
                throw new CourseSeatsUnavailableException(courseId);
            }
            if(checkRegisteredCourseExists(studentId, courseId)) {
                throw new CourseExistsInCartException(courseId);
            }

            String query = SQLQueries.REGISTRATION_ADD_COURSE;

            stmt = conn.prepareStatement(query);
            stmt.setString(1, studentId);
            stmt.setString(2, courseObj.getCourseCode());
            stmt.setBoolean(3, isPrimary);
            stmt.setString(4, courseObj.getInstructorId());
            stmt.setString(5, null);
            stmt.execute();
            if(isPrimary) changeCourseSeats(courseId, 0);

            return true;

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return false;
    }






}
