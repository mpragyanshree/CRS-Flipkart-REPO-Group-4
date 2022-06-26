package com.flipkart.bean;

import java.sql.Date;

/**
 * Student class
 */
public class Student extends User{

    private String department;
    private String studentId;
    private boolean isApproved;

    /**
     * Parameterized constructor
     * @param joiningDate
     * @param userId
     * @param name
     * @param role
     * @param password
     * @param address
     * @param contactnum
     * @param department
     * @param studentId
     * @param isApproved
     */
    public Student(String joiningDate, String userId, String name, String role, String password, String address, String contactnum, String department, String studentId, boolean isApproved) {
        super(joiningDate, userId, name, role, password, address, contactnum);
        this.department = department;
        this.studentId = studentId;
        this.isApproved = isApproved;
    }

    /**
     * Default Constructor
     */
    public Student() {

    }

    /**
     * Method to get department
     * @return
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Method to set department
     * @param department
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Method to get Student ID
     * @return
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Method to set student ID
     * @param studentId
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * Method to get approval status
     * @return
     */
    public boolean isApproved() {
        return isApproved;
    }

    /**
     * Method to set approval status
     * @param approved
     */

    public void setApproved(boolean approved) {
        isApproved = approved;
    }


}
