package com.flipkart.bean;

import java.sql.Date;

public class Professor extends User {

    public Professor(String joiningDate, String userId, String name, String role, String password, String address, String contactnum, String professorId, String department, String designation) {
        super(joiningDate, userId, name, role, password, address, contactnum);
        this.professorId = professorId;
        this.department = department;
        this.designation = designation;
    }

    public Professor() {
    }

    private String professorId;
    private String department;
    private String designation;

    public String getProfessorId() {
        return professorId;
    }

    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }




}
