package com.flipkart.bean;

import java.util.Date;

public class Admin extends User {
    private String adminId;
    public Admin(Date joiningDate, String userId, String name, String role, String password, String address, String contactnum, String adminId) {
        super(joiningDate, userId, name, role, password, address, contactnum);
        this.adminId = adminId;
    }
    public Admin()
    {

    }
    public Admin(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }


}
