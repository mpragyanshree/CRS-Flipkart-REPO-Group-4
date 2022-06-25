package com.flipkart.bean;
import java.sql.Date;
/**
 * USER class
 */
public class User {

    private String joiningDate;
    private String userId;
    private String name;
    private String role;

    private String password;
    private String address;
    private String contactnum;

    /**
     * Parameterized Constructor
     * @param joiningDate
     * @param userId
     * @param name
     * @param role
     * @param password
     * @param address
     * @param contactnum
     */
    public User(String joiningDate, String userId, String name, String role, String password, String address, String contactnum) {
        this.joiningDate = joiningDate;
        this.userId = userId;
        this.name = name;
        this.role = role;
        this.password = password;
        this.address = address;
        this.contactnum = contactnum;
    }

    /**
     * Default constructor
     */
    public User()
    {

    }

    /**
     * Method to get address
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * Method to set user address
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Method to get contact number
     * @return
     */
    public String getContactnum() {
        return contactnum;
    }

    /**
     * Method to set contact number
     * @param contactnum
     */

    public void setContactnum(String contactnum) {
        this.contactnum = contactnum;
    }

    /**
     * Method to get user ID
     * @return
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Method to set user Id
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Method to get name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Method to set name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to get role
     * @return
     */

    public String getRole() {
        return role;
    }

    /**
     * Method to set role
     * @param role
     */

    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Method to get password
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *Method to set password
     * @param password
     */

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method to getjoining date
     * @return
     */
    public String getJoiningDate() {
        return joiningDate;
    }

    /**
     * Method to set joining date
     * @param joiningDate
     */

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

}
