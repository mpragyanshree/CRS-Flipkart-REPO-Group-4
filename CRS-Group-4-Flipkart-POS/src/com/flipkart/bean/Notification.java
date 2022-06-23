package com.flipkart.bean;

import java.sql.Time;
import java.time.LocalTime;

public class Notification {
    private String studentId;
    private String notificationId;
    private String referenceId;
    private String notificationMSG;

    private Boolean notificationSent;

    public Notification(String studentId, String notificationId, String referenceId, String notificationMSG, Boolean notificationSent) {
        this.studentId = studentId;
        this.notificationId = notificationId;
        this.referenceId = referenceId;
        this.notificationMSG = notificationMSG;
        this.notificationSent=notificationSent;
    }

    public Notification() {}


    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getNotificationMSG() {
        return notificationMSG;
    }

    public void setNotificationMSG(String notificationMSG) {
        this.notificationMSG = notificationMSG;
    }

    public Boolean getNotificationSent() {
        return notificationSent;
    }

    public void setNotificationSent(Boolean notificationSent) {
        this.notificationSent = notificationSent;
    }

    public void showRegistrationNotification(){
        System.out.println("+-----------------------------------+");
        System.out.println("|         Notification Alert!       |");
        System.out.println("+-----------------------------------+");
        System.out.println("|      Registration Completed!      |");
        System.out.println("|    Please Complete Fee Payment!   |");
        System.out.println("+-----------------------------------+");
    }

    public void showPaymentNotification(String StudentId, int Amount, Time PaymentTime, Boolean PaymentStatus, String paymentmode, String newID,
                                        String newnotificationID, String message){
        System.out.println("+-----------------------------------+");
        System.out.println("|         Notification Alert!       |");
        System.out.println("+-----------------------------------+");
        System.out.println("|          Payment Completed!       |");
        System.out.println("|   Student ID: " + StudentId);
        System.out.println("|   Amount    : " + Amount);
        System.out.println("|   Transaction ID: " + newnotificationID);
        System.out.println("|   Payment Time: " + PaymentTime);
        System.out.println("|   Payment Status: " +PaymentStatus);
        System.out.println("|   Payment Mode: " + paymentmode);
        System.out.println("|   Mesage " + message);
        System.out.println("+-----------------------------------+");
    }

}
