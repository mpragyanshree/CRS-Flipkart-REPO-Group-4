package com.dropwizard.dao;
import com.dropwizard.bean.Notification;
import java.sql.SQLException;

public interface NotificationDaoInterface {
   // public Boolean sendPaymentCompleteNotification(String transactionID, String studentid);
    public String getNewTransactionID();
}
