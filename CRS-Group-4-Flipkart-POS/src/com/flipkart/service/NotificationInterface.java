package com.flipkart.service;

import java.sql.Time;
import java.time.LocalTime;

public interface NotificationInterface {
    public void sendPayFeesNotification();
    public void sendPaymentCompleteNotification(String StudentId, int Amount, Time PaymentTime, Boolean PaymentStatus, String payment, String newID,
                                                String newnotificationID, String getNotificationMSG);
}
