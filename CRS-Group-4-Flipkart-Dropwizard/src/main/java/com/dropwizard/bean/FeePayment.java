package com.flipkart.bean;
import java.sql.Time;
/**
Fee payment Class
 */
public class FeePayment {
    private String paymentId;
    private Boolean paymentStatus;
    private int amount;
    private int sem;
    private String studentId;
    private String paymentMode;
    private Time paymentTime;
/**
Parameterized Constructor of Fee Payment Class
 */
    public FeePayment(String paymentId, Boolean paymentStatus, int amount, int sem, String studentId, String paymentMode, Time paymentTime) {
        this.paymentId = paymentId;
        this.paymentStatus = paymentStatus;
        this.amount = amount;
        this.sem = sem;
        this.studentId = studentId;
        this.paymentMode=paymentMode;
        this.paymentTime=paymentTime;
    }
    /**
    Default Constructor
     */
    public FeePayment() {}
    /**
        Method to get Fee Payment mode
     */
    public String getPaymentMode() {
        return paymentMode;
    }
    /**
        Method to set fee payment mode
     */
    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }
    /**
        method to get fee transaction id
     */
    public String getPaymentId() {
        return paymentId;
    }
    /**
        method to set fee transaction id
     */
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
    /**
        method to get payment status
     **/
    public Boolean getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * //method to set payment Status
     * @param paymentStatus
     */
    public void setPaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    /**
     * method to get amount to payfee
     * @return
     */
    public int getAmount() {
        return amount;
    }

    /**
     * method to set amount to payFee
     * @param amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }   //method to set amount

    /**
     * method to get Student ID
     * @return
     */
    public String getStudentId() {
        return studentId;
    }      //method to set Student ID

    /**
     * Methos to get studentID
     * @param studentId
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * Methos to get payment time
     * @return
     */
    public Time getPaymentTime() {
        return paymentTime;
    }

    /**
     * Method to set payment time
     * @param paymentTime
     */
    public void setPaymentTime(Time paymentTime) {
        this.paymentTime = paymentTime;
    }


}
