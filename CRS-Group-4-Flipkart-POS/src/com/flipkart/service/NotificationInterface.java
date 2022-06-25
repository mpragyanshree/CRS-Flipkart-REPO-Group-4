package com.flipkart.service;

import java.sql.Time;
import java.time.LocalTime;

public interface NotificationInterface {
    /**
     * Method to send payment notification
     * @return  void
     */
    public void sendPayFeesNotification();

    /**
     * Method to send payment complete notification
     * @param StudentId
     * @param Amount
     * @param PaymentTime
     * @param PaymentStatus
     * @param payment
     * @param newID
     * @param newnotificationID
     * @param getNotificationMSG
     * @return  void
     */
    public void sendPaymentCompleteNotification(String StudentId, int Amount, Time PaymentTime, Boolean PaymentStatus, String payment, String newID,
                                                String newnotificationID, String getNotificationMSG);
}
