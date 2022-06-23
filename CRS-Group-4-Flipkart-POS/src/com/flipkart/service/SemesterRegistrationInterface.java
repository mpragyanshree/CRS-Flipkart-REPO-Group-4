package com.flipkart.service;
import com.flipkart.bean.Course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public interface SemesterRegistrationInterface {
    public boolean registerCourses(String studentId, int Semester);
    public boolean addCourse(String studentId, int Semester, String CourseId, boolean isPrimary);
    public boolean dropCourse(String studentId, int Semester, String CourseId);
    public ArrayList<Course> viewCourseCatalog();
    public void viewRegisteredCourses(String studentId, int Semester);
    public Boolean checkPaymentWindow(String StudentID);

}
