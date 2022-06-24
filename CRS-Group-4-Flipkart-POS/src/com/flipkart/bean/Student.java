package com.flipkart.bean;

import java.sql.Date;

public class Student extends User{

    private String department;
    private String studentId;
    private boolean isApproved;

    public Student(String joiningDate, String userId, String name, String role, String password, String address, String contactnum, String department, String studentId, boolean isApproved) {
        super(joiningDate, userId, name, role, password, address, contactnum);
        this.department = department;
        this.studentId = studentId;
        this.isApproved = isApproved;
    }

    public Student() {

    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }


}
