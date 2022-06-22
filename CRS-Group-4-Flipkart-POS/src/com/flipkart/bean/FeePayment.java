package com.flipkart.bean;

public class FeePayment {
    private String paymentId;
    private String paymentStatus;
    private int amount;
    private int sem;
    private String studentId;
    private String paymentMode;

    public FeePayment(String paymentId, String paymentStatus, int amount, int sem, String studentId, String paymentMode) {
        this.paymentId = paymentId;
        this.paymentStatus = paymentStatus;
        this.amount = amount;
        this.sem = sem;
        this.studentId = studentId;
        this.paymentMode=paymentMode;
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

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
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


}
