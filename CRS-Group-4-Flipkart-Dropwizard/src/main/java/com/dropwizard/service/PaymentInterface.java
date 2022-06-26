package com.flipkart.service;

import com.flipkart.bean.FeePayment;


public interface PaymentInterface {
    /**
     * Method to make payment
     * @param payment
     * @return  void
     */
    public void makePayment(FeePayment payment);
}
