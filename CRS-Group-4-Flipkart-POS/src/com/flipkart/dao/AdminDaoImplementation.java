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
    /**
     * Method to add course
     * @param course
     * @return  void
     */
    public void addCourse(Course course) throws CourseAlreadyPresentException
    {
        //String courseId = "0";
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement st1 = connection.prepareStatement(SQLQueries.ADD_COURSE);
            st1.setString(1,course.getCourseCode());
            st1.setString(2,course.getCourseName());
//            st1.setString(3,course.getInstructorId());
            st1.setInt(3,course.getNumberOfSeats());
//            System.out.println("check "+course.getCourseName()+" "+course.getCourseName()+" "+course.getInstructorId()+" "+course.getNumberOfSeats());
//            System.out.println(st1);
            st1.executeUpdate();

            return;
        } catch (SQLException ex){
            logger.error(ex.getMessage());
            return;
        }
    }

    /**
     * Method to remove course
     * @param courseId
     * @return  void
     */
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

    /**
     * Method to updateCourse
     * @param course_name
     * @param courseID
     * @param numOfSeats
     * @param courseInstructor
     * @return  void
     */
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
    /**
     * Method to add professor
     * @param prof
     * @return  Professor
     */
    public Professor addProfessor(Professor prof) throws UsernameTakenException {
        Connection connection = DBUtil.getConnection();
        try {
//            PreparedStatement st = connection.prepareStatement(SQLQueries.GET_MAX_USER_ID);
//            ResultSet results = st.executeQuery();
//            String profId = "0";
//            if (results.next()) {
//                profId = results.getString(1);
//            }
//            int nextprofid = Integer.parseInt(profId) + 1;
//            profId = Integer.toString(nextprofid);
//            prof.setProfessorId(profId);

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
    /**
     * Method to remove Professor
     * @param professorID
     * @return  void
     */
    public void removeProfessor(String professorID) throws UserNotFoundException{
        String sql2 = SQLQueries.REMOVE_PROFESSOR_FROM_CATALOG;
        String sql1 = SQLQueries.ADMIN_REMOVE_PROFESSOR_FROM_USER_TABLE;
        String sql = SQLQueries.ADMIN_REMOVE_PROFESSOR_FROM_PROFESSOR_TABLE;

        Connection connection = DBUtil.getConnection();

        try {
            try {
                statement = connection.prepareStatement(sql2);
                statement.setString(1, "");
                statement.setString(2, professorID);

                statement.executeUpdate();
            }
            catch(SQLException e){
                System.out.println("Professor doesn't teach any course!");
            }
            statement = connection.prepareStatement(sql);
            statement.setString(1, professorID);

            statement.executeUpdate();
            statement = connection.prepareStatement(sql1);
            statement.setString(1, professorID);

            int row =  statement.executeUpdate();
            System.out.println(row + " user deleted.");



        } catch (SQLException e) {
            System.out.println("Professor not found !! Not registered !!");
            logger.error(e.getMessage());
        }
    }
    /**
     * Method to update Professor
     * @param username
     * @param name
     * @param password
     * @param contact
     * @param joiningDate
     * @param address
     * @param department
     * @param designation
     * @return  void
     */
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
//            System.out.println("check1");
            st.executeUpdate();

            PreparedStatement st1 = connection.prepareStatement(SQLQueries.UPDATE_PROFESSOR1);
            st1.setString(1, department);
            st1.setString(2,designation);
            st1.setString(3,username);
//            System.out.println("check2");
            st1.executeUpdate();
//            System.out.println("check3");

        } catch (SQLException ex) {
            System.out.println("Exception while updating professor");
            //throw new UsernameTakenException();
            logger.error(ex.getMessage());
        }
    }

    @Override
    /**
     * Method to view pending student approval
     * @return  list of students
     */
    public ArrayList<String> viewPendingStudentApproval() {
        Connection connection = DBUtil.getConnection();
        ArrayList<String> pendingApprovals = new ArrayList<String>();
        try {
            PreparedStatement update_statement = connection.prepareStatement(SQLQueries.VIEW_PENDING_STUDENT_APPROVAL);
            ResultSet rs = update_statement.executeQuery();

            while (rs.next()){
                String studentId = rs.getString("studentid");
                pendingApprovals.add(studentId);
            }
        } catch (SQLException e) {
            logger.error((e.getMessage()));
        }
        return pendingApprovals;
    }
    /**
     * Method to add admin
     * @param admin
     * @return  admin
     */
    public Admin addAdmin(Admin admin) throws UsernameTakenException{
        try {
            Connection connection = DBUtil.getConnection();
//            PreparedStatement st = connection.prepareStatement(SQLQueries.GET_MAX_USER_ID);
//            ResultSet results = st.executeQuery();
//            String adminId = "0";
//            if (results.next()) {
//                adminId = results.getString(1);
//            }
//            int nextadminid = Integer.parseInt(adminId) + 1;
//            adminId = Integer.toString(nextadminid);
//            admin.setAdminId(adminId);

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
    /**
     * Method to remove admin
     * @param adminID
     * @return  void
     */
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
    /**
     * Method to update admin
     * @param adminId
     * @param name
     * @param password
     * @param contact
     * @param joiningDate
     * @param address
     * @return  void
     */
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
    /**
     * Method to approve student registration
     * @param studentId
     * @return  void
     */
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
    /**
     * Method to enable fee payment
     * @param semesterId
     * @return  void
     */
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
    /**
     * Method to disable fee payment
     * @param semesterId
     * @return  void
     */
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
    /**
     * Method to generate grade card
     * @return  void
     */
    public void generateGradeCard(){
       try {
            Connection conn = DBUtil.getConnection();
            statement = conn.prepareStatement(SQLQueries.GENERATE_GRADE_CARD);
            statement.setBoolean(1, true);
           statement.setInt(2, 1);
            statement.executeUpdate();
            return;
        }
        catch (SQLException e) {
			logger.error(e.getMessage());
        }


    }

    /**
     * Method to view availabe course
     * @return  list of course
     */
    public ArrayList<Course> viewAvailableCourses() throws CourseSeatsUnavailableException, CourseNotFoundException, InvalidSemesterException{
        ArrayList<Course> available_courses = new ArrayList<Course>();
        try {
            Connection conn = DBUtil.getConnection();
            statement = conn.prepareStatement(SQLQueries.ADMIN_VIEW_AVAILABLE_COURSES);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Course i = new Course();
                i.setCourseName(rs.getString(1));
                i.setCourseCode(rs.getString(2));
                i.setInstructorId(rs.getString(3));
                i.setNumberOfSeats(rs.getInt(4));
                available_courses.add(i);
            }

        }
        catch (SQLException e) {
			logger.error(e.getMessage());
        }
        return available_courses;
    }

    /**
     * Method to view course student list
     * @param courseID
     * @return course list
     */
    public ArrayList<String>  viewCourseStudentList(String courseID) throws InvalidCourseException{
        ArrayList<String> course_wise_students = new ArrayList<String>();
        try {
            Connection conn = DBUtil.getConnection();
//            System.out.println("check");
            statement = conn.prepareStatement(SQLQueries.ADMIN_GET_REGISTERED_STUDENTS);
            statement.setString(1, courseID);
//            System.out.println("check1");
            ResultSet rs = statement.executeQuery();
            System.out.println(rs);

            while (rs.next()){
                String studentId = rs.getString("studentid");
                System.out.println(studentId);
                course_wise_students.add(studentId);
            }
//            System.out.println("check2");
        }
        catch (SQLException e) {

			logger.error(e.getMessage());
        }
        return course_wise_students;
    }
    /**
     * Method to view grades in respective courses
     * @param courseID
     * @return  list of courses
     */
    public ArrayList<ArrayList<String>> viewCourseGrades(String courseID) throws InvalidCourseException{
        ArrayList<ArrayList<String>> student_grades = new ArrayList<ArrayList<String>>();
        try {
            Connection conn = DBUtil.getConnection();
            statement = conn.prepareStatement(SQLQueries.ADMIN_GET_COURSE_GRADES);
            statement.setString(1, courseID);

            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                ArrayList<String> i = new ArrayList<String>();
                i.add(rs.getString(1));
                i.add(rs.getString(2));
                student_grades.add(i);

            }


        }
        catch (SQLException e) {

			logger.error(e.getMessage());
        }

        return student_grades;
    }


}
