package com.flipkart.dao;
import com.flipkart.constant.SQLQueries;
import com.flipkart.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.flipkart.exception.*;

public class UserDaoImplementation implements UserDaoInterface{
    private static volatile UserDaoImplementation instance=null;
    public static UserDaoImplementation getInstance() {
        if(instance==null)
        {
            // This is a synchronized block, when multiple threads will access this instance
            synchronized(UserDaoImplementation.class){
                instance = new UserDaoImplementation();
            }
        }
        return instance;
    }
    public void updatePassword(String userId, String newPassword)
    {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement st = connection.prepareStatement(SQLQueries.UPDATE_PASSWORD);
            st.setString(1, newPassword);
            st.setString(2, userId);
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Exception while updating password");
            //throw new UsernameTakenException();
            ex.printStackTrace();
        }
    }
    public boolean loginUser(String userID, String password)
    {
        boolean flag = false;
        Connection connection = DBUtil.getConnection();
        try{
            PreparedStatement st = connection.prepareStatement(SQLQueries.GET_PASSWORD);
            st.setString(1,userID);
            ResultSet rs = st.executeQuery();
            String passwordDB = null;
            while(rs.next())
            {
                passwordDB = rs.getString("password");
            }
            if(password == null) {
                throw new UserNotFoundException();
            }
            if(password.equals(passwordDB))
            {
                flag=true;
                return true;
            }
            if(!flag)
            {
                System.out.println("Wrong Password");
                throw new LoginFailedException(userID);
            }
            }catch (SQLException ex) {
                    ex.printStackTrace();
            }
        catch(LoginFailedException ex){
            System.out.println(ex.getMessage());
        }
        catch (UserNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

}
