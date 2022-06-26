package com.flipkart.bean;

import java.sql.Date;

/**
 * Professor class extended from user
 */
public class Professor extends User {

    public Professor(String joiningDate, String userId, String name, String role, String password, String address, String contactnum, String professorId, String department, String designation) {
        super(joiningDate, userId, name, role, password, address, contactnum);
        this.professorId = professorId;
        this.department = department;
        this.designation = designation;
    }
    /**
     * Default constructor
     */
    public Professor() {
    }

    private String professorId;
    private String department;
    private String designation;
    /**
     * Method to get Professor ID
     */
    public String getProfessorId() {
        return professorId;
    }
    /**
     * Method to set Professor ID
     */
    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }
    /**
     * Method to get department
     */
    public String getDepartment() {
        return department;
    }
    /**
     * Method to set department
     */
    public void setDepartment(String department) {
        this.department = department;
    }
    /**
     * Method to get designation
     */
    public String getDesignation() {
        return designation;
    }
    /**
     * Method to set designation
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }




}
