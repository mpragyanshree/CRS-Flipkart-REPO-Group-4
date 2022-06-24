package com.flipkart.bean;
import java.sql.Date;

public class User {

    private String joiningDate;
    private String userId;
    private String name;
    private String role;

    private String password;
    private String address;
    private String contactnum;

    public User(String joiningDate, String userId, String name, String role, String password, String address, String contactnum) {
        this.joiningDate = joiningDate;
        this.userId = userId;
        this.name = name;
        this.role = role;
        this.password = password;
        this.address = address;
        this.contactnum = contactnum;
    }

    public User()
    {

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactnum() {
        return contactnum;
    }

    public void setContactnum(String contactnum) {
        this.contactnum = contactnum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

}
