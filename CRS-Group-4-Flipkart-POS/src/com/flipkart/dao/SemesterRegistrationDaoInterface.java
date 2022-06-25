package com.flipkart.dao;
import com.flipkart.bean.Course;
import java.sql.SQLException;
import java.util.ArrayList;
import com.flipkart.exception.*;

public interface SemesterRegistrationDaoInterface {
    public boolean registerCourses(String studentId) throws InvalidSemesterRegistration,PaymentDoneException;
    public ArrayList<Course> viewAvailableCourses();
    public boolean addCourse(String studentId,  String courseId, boolean isPrimary) throws CourseNotFoundException, CourseSeatsUnavailableException, CourseExistsInCartException;
    public boolean checkRegisteredCourseExists(String studentId,  String courseId);
    public boolean dropCourse(String studentId, int semesterId, String courseId)  throws CourseNotFoundException, CourseNotInCart;
    public Course getCourseDetails(String courseId);
    public void changeCourseSeats(String courseId, int change);
}
