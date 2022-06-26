package com.flipkart.bean;

import java.sql.Date;
/**
Admin Class
 */
public class Admin extends User {
    private String adminId;

   // Constructor for Admin Class

    public Admin(String joiningDate, String userId, String name, String role, String password, String address, String contactnum, String adminId) {
        super(joiningDate, userId, name, role, password, address, contactnum);
        this.adminId = adminId;
    }

    //Default Constructor for Admin Class

    public Admin()
    {

    }
    public Admin(String adminId) {
        this.adminId = adminId;
    }
    /**
    Method to get Admin ID
     */
    public String getAdminId() {
        return adminId;
    }
    /**
    Method to set Admin ID
     */
    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }


}
