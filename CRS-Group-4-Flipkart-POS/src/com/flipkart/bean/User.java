package com.flipkart.bean;
import java.util.Date;

public class User {

    private Date joiningDate;
    private String userId;
    private String name;
    private String role;
    private String password;


    public User(String userId, String name, String role, String password, Date joiningDate) {
        this.userId = userId;
        this.name = name;
        this.role = role;
        this.password = password;
        this.joiningDate = joiningDate;
    }

    public User()
    {

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

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

}
