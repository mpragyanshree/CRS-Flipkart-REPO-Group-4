package com.flipkart.service;

import com.flipkart.dao.AdminDaoImplementation;
import com.flipkart.dao.AdminDaoInterface;
import com.flipkart.dao.UserDaoImplementation;
import com.flipkart.dao.UserDaoInterface;

public class UserImplementation implements UserInterface {
    UserDaoInterface udo  = UserDaoImplementation.getInstance();
    public void updatePassword(String userID, String newPassword){
        udo.updatePassword(userID,newPassword);
    }

    public boolean loginUser(String userID,String password, String role){
        return udo.loginUser(userID,password); //check credentials
    }
    public void updateUserDetails(String UserId, String UserName){

    }
}
