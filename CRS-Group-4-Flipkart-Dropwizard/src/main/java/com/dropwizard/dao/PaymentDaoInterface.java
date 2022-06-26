package com.flipkart.dao;
import com.flipkart.bean.FeePayment;
import com.flipkart.exception.PaymentDoneException;
import com.flipkart.exception.PaymentFailedException;

public interface PaymentDaoInterface {
    public void makePayment(FeePayment payment) throws PaymentFailedException, PaymentDoneException;
}
