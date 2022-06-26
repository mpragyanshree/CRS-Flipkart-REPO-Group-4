package com.flipkart.service;
import com.flipkart.bean.Course;
import com.flipkart.exception.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public interface SemesterRegistrationInterface {

    /**
     * Method to register Course
     * @param studentId

     * @return the boolean if it is added successfully, else null
     */
    public boolean registerCourses(String studentId);
    /**
     * Method to add Course selected by student
     * @param studentId
     * @param CourseId
     * @param isPrimary
     * @return the course if it is added successfully, else null
     */
    public boolean addCourse(String studentId, String CourseId, boolean isPrimary);
    /**
     * Method to drop Course selected by student
     * @param studentId
     * @param Semester
     * @param CourseId
     * @return Boolean value indicating if it is/was dropped successfully
     */
    public boolean dropCourse(String studentId, int Semester, String CourseId) ;

    /**
     * Method to view Course catalog

     * @return list of Courses
     */
    public ArrayList<Course> viewCourseCatalog();

    /**
     * Method to view Register Course
     * @return  List</course>
     */
    public List<Course> viewRegisteredCourses(String studentID) throws StudentNotRegisteredException;

    /**
     * Method to check payment window
     * @param StudentID
     * @return  Boolean
     */
    public Boolean checkPaymentWindow(String StudentID);

}
