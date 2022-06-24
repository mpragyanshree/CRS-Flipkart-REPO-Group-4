package com.flipkart.service;
// import com.flipkart.exception.UserNotFoundException;

import com.flipkart.exception.UserNotFoundException;

public interface UserInterface {

    public void updatePassword(String userId, String newPassword);
    public boolean loginUser(String userId,String password, String role) throws UserNotFoundException;
    public void updateUserDetails(String UserId,String Name);


}
