package com.flipkart.dao;

import com.flipkart.bean.Admin;
import com.flipkart.constant.SQLQueries;
import com.flipkart.exception.*;
import com.flipkart.utils.DBUtil;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.service.StudentImplementation;
import jdk.nashorn.internal.runtime.Context;

import java.sql.*;
import java.util.*;


public class AdminDaoImplementation implements AdminDaoInterface {
    private Context.ThrowErrorManager logger;

    private static volatile AdminDaoImplementation instance = null;

    private PreparedStatement statement = null;

    private AdminDaoImplementation() {
    }

    public static AdminDaoImplementation getInstance() {
        if (instance == null) {
            synchronized (AdminDaoImplementation.class) {
                instance = new AdminDaoImplementation();
            }
        }
        return instance;
    }
    public Course addCourse(Course course) throws CourseAlreadyPresentException
    {
        Connection connection = DBUtil.getConnection();
        //String courseId = "0";
        try {
           /* PreparedStatement st = connection.prepareStatement(SQLQueries.GET_MAX_COURSE_ID);
            ResultSet results = st.executeQuery();

            if (results.next()) {
                courseId = results.getString(1);
            }
//            int nextId = Integer.parseInt(courseId) + 1;
//            courseId = Integer.toString(nextId);*/
            //course.setCourseCode(courseId);
            PreparedStatement st1 = connection.prepareStatement(SQLQueries.ADD_COURSE);
            st1.setString(1,course.getCourseCode());
            st1.setString(2,course.getCourseName());
            st1.setString(3,course.getInstructorId());
            st1.setInt(4,course.getNumberOfSeats());
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
        return course;
    }
    public void removeCourse(String courseId) throws CourseNotFoundException
    {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement st = connection.prepareStatement(SQLQueries.REMOVE_COURSE);
            st.setString(1, courseId);
            st.executeUpdate();
        } catch (SQLException ex) {

            logger.error(ex.getMessage());

        }
        return;
    }
    public void updateCourse(String course_name, String courseID, int numOfSeats, String courseInstructor)
    {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement st = connection.prepareStatement(SQLQueries.UPDATE_COURSE);
            st.setString(1, course_name);
            st.setString(2, courseID);
            st.setInt(3, numOfSeats);
            st.setString(4, courseInstructor);
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Exception while updating course");
            //throw new UsernameTakenException();
            logger.error(ex.getMessage());
        }
    }
    public Professor addProfessor(Professor prof) throws UsernameTakenException {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement st = connection.prepareStatement(SQLQueries.GET_MAX_USER_ID);
            ResultSet results = st.executeQuery();
            String profId = "0";
            if (results.next()) {
                profId = results.getString(1);
            }
            int nextprofid = Integer.parseInt(profId) + 1;
            profId = Integer.toString(nextprofid);
            prof.setProfessorId(profId);

            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.ADD_USER);
            preparedStatement.setString(1, prof.getUserId());
            preparedStatement.setString(2, prof.getName());
            preparedStatement.setString(3, prof.getPassword());
            preparedStatement.setString(4, prof.getJoiningDate());
            preparedStatement.setString(5, "professor");
            preparedStatement.setString(6, prof.getAddress());
            preparedStatement.setString(7, prof.getContactnum());
            preparedStatement.executeUpdate();

            PreparedStatement preparedStatement1 = connection.prepareStatement(SQLQueries.ADD_PROFESSOR);
            preparedStatement1.setString(1, prof.getProfessorId());
            preparedStatement1.setString(2, prof.getDepartment());
            preparedStatement1.setString(3, prof.getDesignation());
            preparedStatement1.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Username taken exception");
            throw new UsernameTakenException();
        }
        return prof;
    }

    public void removeProfessor(String professorID) throws UserNotFoundException{
        String sql = SQLQueries.ADMIN_REMOVE_PROFESSOR;
        Connection connection = DBUtil.getConnection();

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, professorID);

            int row = statement.executeUpdate();


            System.out.println(row + " user deleted.");



        } catch (SQLException e) {
            System.out.println("Professor not found !! Not registered !!");
            logger.error(e.getMessage());
        }
    }

    public void updateProfessor(String username,String name,String password,String contact,String joiningDate,String address,String department,String designation)
    {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement st = connection.prepareStatement(SQLQueries.UPDATE_PROFESSOR);
            st.setString(1, name);
            st.setString(2, password);
            st.setString(3, joiningDate);
            st.setString(4, address);
            st.setString(5, contact);
            st.setString(6, username);
            st.executeUpdate();

            PreparedStatement st1 = connection.prepareStatement(SQLQueries.UPDATE_PROFESSOR1);
            st1.setString(1, department);
            st1.setString(2,designation);
            st1.setString(3,username);
            st1.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Exception while updating professor");
            //throw new UsernameTakenException();
            logger.error(ex.getMessage());
        }
    }

    @Override
    public ArrayList<String> viewPendingStudentApproval() {
        Connection connection = DBUtil.getConnection();
        ArrayList<String> pendingApprovals = new ArrayList<String>();
        try {
            PreparedStatement update_statement = connection.prepareStatement(SQLQueries.VIEW_PENDING_STUDENT_APPROVAL);
            ResultSet rs = update_statement.executeQuery();

            do {
                pendingApprovals.add(rs.getString(1));
            } while (rs.next());


        } catch (SQLException e) {
            logger.error((e.getMessage()));
        }
        return pendingApprovals;
    }

    public Admin addAdmin(Admin admin) throws UsernameTakenException{
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement st = connection.prepareStatement(SQLQueries.GET_MAX_USER_ID);
            ResultSet results = st.executeQuery();
            String adminId = "0";
            if (results.next()) {
                adminId = results.getString(1);
            }
            int nextadminid = Integer.parseInt(adminId) + 1;
            adminId = Integer.toString(nextadminid);
            admin.setAdminId(adminId);

            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.ADD_USER);
            preparedStatement.setString(1, admin.getUserId());
            preparedStatement.setString(2, admin.getName());
            preparedStatement.setString(3, admin.getPassword());
            preparedStatement.setString(4, admin.getJoiningDate());
            preparedStatement.setString(5, "admin");
            preparedStatement.setString(6, admin.getAddress());
            preparedStatement.setString(7, admin.getContactnum());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
        return admin;
    }

    public void removeAdmin(String adminID) throws UserNotFoundException{
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement st = connection.prepareStatement(SQLQueries.REMOVE_ADMIN);
            st.setString(1, adminID);
            st.executeUpdate();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

    public void updateAdmin (String adminId, String name, String password, String contact, String joiningDate, String address) {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement st = connection.prepareStatement(SQLQueries.UPDATE_ADMIN);
            st.setString(1, name);
            st.setString(2, password);
            st.setString(3, contact);
            st.setString(4, joiningDate);
            st.setString(5, address);
            st.setString(6, adminId);
            st.executeUpdate();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

    public void approveStudentRegistration(String studentId) throws FeesPendingException, UserNotFoundException, StudentNotApprovedException {

        Connection connection = DBUtil.getConnection();

        try {
            PreparedStatement update_statement = connection.prepareStatement(SQLQueries.APPROVE_STUDENT_QUERY);
            update_statement.setString(1, studentId);
            update_statement.executeUpdate();
            System.out.println("Approved");
        } catch (SQLException e) {
           logger.error(e.getMessage());
        }

    }

    public void enableFeePayment(String semesterId)  {
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement queryStatement = conn.prepareStatement(SQLQueries.CHANGE_PAYMENT_WINDOW_STATUS);
            queryStatement.setBoolean(1, true);
            queryStatement.setString(2, semesterId);
            queryStatement.executeUpdate();
            System.out.println("******* Payment Window Opened Successfully for Semester " + semesterId + " ********");
        }
        catch(SQLException e){
            logger.error(e.getMessage());
        }

    }

    public void disableFeePayment(String semesterId){
    try{
        Connection conn = DBUtil.getConnection();
        PreparedStatement queryStatement = conn.prepareStatement(SQLQueries.CHANGE_PAYMENT_WINDOW_STATUS);
        queryStatement.setBoolean(1, false);
        queryStatement.setString(2, semesterId);
        queryStatement.executeUpdate();
        System.out.println("******* Payment Window Closed Successfully for Semester " + semesterId + " ********");

        }
        catch(SQLException e){
            logger.error(e.getMessage());
        }
    }
    public void generateGradeCard(){
       try {
            Connection conn = DBUtil.getConnection();
            statement = conn.prepareStatement(SQLQueries.GENERATE_GRADE_CARD);
            statement.setInt(1, 1);
            statement.executeUpdate();
            return;
        }
        catch (SQLException e) {

			logger.error(e.getMessage());
        }


    }
    public ArrayList<Course> viewAvailableCourses() throws CourseSeatsUnavailableException, CourseNotFoundException, InvalidSemesterException{
        ArrayList<Course> available_courses = new ArrayList<Course>();
        try {
            Connection conn = DBUtil.getConnection();
            statement = conn.prepareStatement(SQLQueries.ADMIN_VIEW_AVAILABLE_COURSES);
            ResultSet rs = statement.executeQuery();

            do {
                Course i = new Course();
                i.setCourseName(rs.getString(1));
                i.setCourseCode(rs.getString(2));
                i.setInstructorId(rs.getString(3));
                i.setNumberOfSeats(rs.getInt(4));
                available_courses.add(i);

            } while (rs.next());


        }
        catch (SQLException e) {
			logger.error(e.getMessage());
        }
        return available_courses;
    }
    public ArrayList<ArrayList<String>>  viewCourseStudentList(String courseID) throws InvalidCourseException{
        ArrayList<ArrayList<String>> course_wise_students = new ArrayList<ArrayList<String>>();
        try {
            Connection conn = DBUtil.getConnection();
            statement = conn.prepareStatement(SQLQueries.ADMIN_GET_REGISTERED_STUDENTS);
            statement.setString(1, courseID);
            ResultSet rs = statement.executeQuery();

            do {
                ArrayList<String> i = new ArrayList<String>();
                i.add(rs.getString(1));
                i.add(rs.getString(2));

                course_wise_students.add(i);

            } while (rs.next());


        }
        catch (SQLException e) {

			logger.error(e.getMessage());
        }
        return course_wise_students;
    }
    public ArrayList<ArrayList<String>> viewCourseGrades(String courseID) throws InvalidCourseException{
        ArrayList<ArrayList<String>> student_grades = new ArrayList<ArrayList<String>>();
        try {
            Connection conn = DBUtil.getConnection();
            statement = conn.prepareStatement(SQLQueries.ADMIN_GET_COURSE_GRADES);
            statement.setString(1, courseID);
            ResultSet rs = statement.executeQuery();

            do {
                ArrayList<String> i = new ArrayList<String>();
                i.add(rs.getString(1));
                i.add(rs.getString(2));

                student_grades.add(i);

            } while (rs.next());


        }
        catch (SQLException e) {

			logger.error(e.getMessage());
        }

        return student_grades;
    }


}
