package com.flipkart.dao;
import com.flipkart.bean.Notification;
import com.flipkart.bean.FeePayment;
import com.flipkart.constant.SQLQueries;
import com.flipkart.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NotificationDaoImplementation implements  NotificationDaoInterface{

    private static volatile NotificationDaoImplementation instance=null;
    private final Connection connection=DBUtil.getConnection();

    public static NotificationDaoImplementation getInstance() {
        if (instance != null)
            return instance;
        synchronized (NotificationDaoImplementation.class) {
            instance = new NotificationDaoImplementation();
        }
        return instance;
    }

    public String getNewTransactionID() {

        String newNotificationID = "0";
        try
        {
            PreparedStatement stmt = connection.prepareStatement(SQLQueries.GET_MAX_NOTIFICATION_ID);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                newNotificationID =rs.getString(8);
            }
            int nextId = Integer.parseInt(newNotificationID)+1;
            newNotificationID = Integer.toString(nextId);
        }
        catch(Exception ex) {
            //logger.error(ex.getMessage());
        }
        return newNotificationID;

        }

}
