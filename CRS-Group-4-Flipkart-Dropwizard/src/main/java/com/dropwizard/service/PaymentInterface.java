package com.dropwizard.service;

import com.dropwizard.bean.FeePayment;


public interface PaymentInterface {
    /**
     * Method to make payment
     * @param payment
     * @return  void
     */
    public void makePayment(FeePayment payment);
}
