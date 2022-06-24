package com.flipkart.dao;
import com.flipkart.bean.Course;
import java.sql.SQLException;
import java.util.ArrayList;
import com.flipkart.exception.*;

public interface SemesterRegistrationDaoInterface {
    public boolean registerCourses(String studentId, int semesterId) throws InvalidSemesterRegistration,PaymentDoneException;
    public ArrayList<Course> viewAvailableCourses();
}
