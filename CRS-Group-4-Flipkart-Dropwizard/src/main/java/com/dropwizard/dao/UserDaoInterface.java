package com.dropwizard.dao;

public interface UserDaoInterface {
    public void updatePassword(String userId, String newPassword);
    public boolean loginUser(String userID, String password);
}
