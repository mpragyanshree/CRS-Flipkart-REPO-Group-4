package com.flipkart.bean;

import java.sql.Time;
import java.time.LocalTime;

/**
 * Notification class
 */
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

    /**
     * Default constructor
     */
    public Notification() {}


    /**
     * Methos to get student ID
     * @return
     */
    public String getStudentId() {
        return studentId;
    }
    /**
     * Methos to set student ID
     * @return
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    /**
     * Methos to get notification ID
     * @return
     */
    public String getNotificationId() {
        return notificationId;
    }
    /**
     * Methos to set notification ID
     * @return
     */
    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }
    /**
     * Methos to get reference ID
     * @return
     */
    public String getReferenceId() {
        return referenceId;
    }
    /**
     * Methos to set reference ID
     * @return
     */
    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }
    /**
     * Methos to get notification
     * @return
     */
    public String getNotificationMSG() {
        return notificationMSG;
    }
    /**
     * Methos to set notification
     * @return
     */
    public void setNotificationMSG(String notificationMSG) {
        this.notificationMSG = notificationMSG;
    }
    /**
     * Methos to check notification sent
     * @return
     */
    public Boolean getNotificationSent() {
        return notificationSent;
    }
    /**
     * Methos to check notification sent
     * @return
     */
    public void setNotificationSent(Boolean notificationSent) {
        this.notificationSent = notificationSent;
    }
    /**
     * Methos to show Registration Notification
     * @return
     */
    public void showRegistrationNotification(){
        System.out.println("+-----------------------------------+");
        System.out.println("|         Notification Alert!       |");
        System.out.println("+-----------------------------------+");
        System.out.println("|      Registration Completed!      |");
        System.out.println("|    Please Complete Fee Payment!   |");
        System.out.println("+-----------------------------------+");
    }

    /**
     * Methos to show Payment Notification
     * @return
     */
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
