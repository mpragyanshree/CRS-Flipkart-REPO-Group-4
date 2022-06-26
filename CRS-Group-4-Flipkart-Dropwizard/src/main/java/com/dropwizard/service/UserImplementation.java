package com.dropwizard.service;

import com.dropwizard.dao.AdminDaoImplementation;
import com.dropwizard.dao.AdminDaoInterface;
import com.dropwizard.dao.UserDaoImplementation;
import com.dropwizard.dao.UserDaoInterface;
import com.dropwizard.exception.*;

public class UserImplementation implements UserInterface {
    UserDaoInterface udo  = UserDaoImplementation.getInstance();
    /**
     * Method to update password
     * @param userID
     * @param newPassword
     * @return  void
     */
    public void updatePassword(String userID, String newPassword){
        udo.updatePassword(userID,newPassword);
    }


    /**
     * Method to login user
     * @param userID
     * @param password
     * @param role
     * @return  boolean
     */
    public boolean loginUser(String userID,String password, String role) throws UserNotFoundException {
        return udo.loginUser(userID,password); //check credentials
    }
    /**
     * Method to update user details
     * @param UserId
     * @param UserName
     * @return  void
     */
    public void updateUserDetails(String UserId, String UserName){

    }
}
