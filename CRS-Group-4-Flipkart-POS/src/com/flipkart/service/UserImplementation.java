package com.flipkart.service;

public class UserImplementation implements UserInterface {
    public void updatePassword(String userID, String newPassword,String role){
        UserImplementation uo = new UserImplementation();
        switch (role) {
            case "student":
                //update password in DB
                //uo.updateStudentPassword(username,newPassword);
                break;

            case "professor":
                //uo.updateProfPassword(username,newPassword);
                break;

            case "admin":
                //uo.updateAdminPassword(username,newPassword);
                break;

            default:
                System.out.println("Invalid Role");
                System.out.println("=======================================");
        }
    }

    public boolean loginUser(String userID,String password, String role){
        return true; //check credentials
    }
    public void updateUserDetails(String UserId, String UserName){

    }
}
