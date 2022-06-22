package com.flipkart.bean;

public class Notification {
    private String studentId;
    private String notificationId;
    private String referenceId;
    private String notificationMSG;

    public Notification(String studentId, String notificationId, String referenceId, String notificationMSG) {
        this.studentId = studentId;
        this.notificationId = notificationId;
        this.referenceId = referenceId;
        this.notificationMSG = notificationMSG;
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

}
