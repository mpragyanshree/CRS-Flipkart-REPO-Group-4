package com.flipkart.bean;

import java.util.Date;

public class Admin extends User {
    private String adminId;

    public Admin(String userId, String name, String role, String password, Date joiningDate, String adminId) {
        super(userId, name, role, password, joiningDate);
        this.adminId = adminId;
    }

    public void Admin() {}

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }


}
