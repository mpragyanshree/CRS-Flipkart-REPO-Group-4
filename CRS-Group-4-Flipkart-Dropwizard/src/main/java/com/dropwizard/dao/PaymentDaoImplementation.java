package com.flipkart.dao;
import com.flipkart.bean.FeePayment;
import com.flipkart.bean.Notification;
import com.flipkart.service.NotificationInterface;
import com.flipkart.service.NotificationImplementation;
import com.flipkart.constant.SQLQueries;
import com.flipkart.exception.PaymentDoneException;
import com.flipkart.exception.PaymentFailedException;
import com.flipkart.service.PaymentImplementation;
import com.flipkart.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class PaymentDaoImplementation implements PaymentDaoInterface {
    public static PaymentDaoImplementation getInstance()
    {
        if(instance==null)
        {
            // This is a synchronized block, when multiple threads will access this instance
            synchronized(PaymentImplementation.class){
                instance = new PaymentDaoImplementation();
            }
        }
        return instance;
    }
    private static volatile PaymentDaoImplementation instance=null;
    private final Connection connection=DBUtil.getConnection();
    public void makePayment(FeePayment payment) throws PaymentFailedException,PaymentDoneException {
        Notification n=new Notification();

        try {

            PreparedStatement statement, stmt2;

            stmt2 = connection.prepareStatement(SQLQueries.PAYMENT_STATUS_STUDENT, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            stmt2.setString(1, payment.getStudentId());
            ResultSet rs2 = stmt2.executeQuery();

            if(rs2.next()==true){
                throw new PaymentDoneException();
            }
            else {
                String newID = getNewTransactionID();
                NotificationDaoImplementation nn=new NotificationDaoImplementation();
                String newnotificationID=nn.getNewTransactionID();

                if (newID == null) {
                    throw new SQLException();
                }

                payment.setPaymentId(newID);
                payment.setPaymentStatus(true);
                payment.setAmount(10000);
                n.setNotificationSent(true);
                n.setNotificationId(newnotificationID);
                System.out.println("Hello3");
                String sql = SQLQueries.MAKE_PAYMENT;

                statement = connection.prepareStatement(sql);
                statement.setString(1, payment.getStudentId());
                statement.setInt(2, payment.getAmount());
                //statement.setTime(3, payment.getPaymentTime());
                statement.setTimestamp(3,new java.sql.Timestamp(new java.util.Date().getTime()));
                statement.setBoolean(4, payment.getPaymentStatus());
                statement.setString(5, payment.getPaymentMode());
                statement.setString(6, payment.getPaymentId());
                statement.setBoolean(7,n.getNotificationSent());
                statement.setString(8,n.getNotificationId());
                statement.executeUpdate();
                // Generate Notification
                NotificationInterface notificationObj = new NotificationImplementation();
                notificationObj.sendPaymentCompleteNotification(payment.getStudentId(), payment.getAmount(), payment.getPaymentTime(), payment.getPaymentStatus(), payment.getPaymentMode(), newID, newnotificationID, n.getNotificationMSG());

            }
        }
        catch (SQLException e) {
            throw new PaymentFailedException();

        }
    }

    private String getNewTransactionID() {

        String newTransactionID = "0";
        try
        {
            PreparedStatement stmt = connection.prepareStatement(SQLQueries.GET_MAX_TRANSACTION_ID);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                newTransactionID =rs.getString(6);
            }
            int nextId = Integer.parseInt(newTransactionID)+1;
            newTransactionID = Integer.toString(nextId);
        }
        catch(Exception ex) {
            //logger.error(ex.getMessage());
        }
        return newTransactionID;
    }

}
