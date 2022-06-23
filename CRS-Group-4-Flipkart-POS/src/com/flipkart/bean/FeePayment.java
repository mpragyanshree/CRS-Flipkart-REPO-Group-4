package com.flipkart.bean;

import java.time.LocalTime;
import java.sql.Time;

public class FeePayment {
    private String paymentId;
    private Boolean paymentStatus;
    private int amount;
    private int sem;
    private String studentId;
    private String paymentMode;
    private Time paymentTime;

    public FeePayment(String paymentId, Boolean paymentStatus, int amount, int sem, String studentId, String paymentMode, Time paymentTime) {
        this.paymentId = paymentId;
        this.paymentStatus = paymentStatus;
        this.amount = amount;
        this.sem = sem;
        this.studentId = studentId;
        this.paymentMode=paymentMode;
        this.paymentTime=paymentTime;
    }

    public FeePayment() {}

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Boolean getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getSem() {
        return sem;
    }

    public void setSem(int sem) {
        this.sem = sem;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Time getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Time paymentTime) {
        this.paymentTime = paymentTime;
    }


}
