package com.flipkart.service;
// import com.flipkart.exception.UserNotFoundException;

public interface UserInterface {

    public void updatePassword(String userId, String newPassword, String Role);
    public boolean loginUser(String userId,String password, String role);
    public void updateUserDetails(String UserId,String Name);


}
