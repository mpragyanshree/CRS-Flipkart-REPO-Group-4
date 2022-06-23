package com.flipkart.dao;
import com.flipkart.bean.Course;
import java.sql.SQLException;
import java.util.ArrayList;

public interface SemesterRegistrationDaoInterface {
    public boolean registerCourses(String studentId, int semesterId);
    public ArrayList<Course> viewAvailableCourses();
}
