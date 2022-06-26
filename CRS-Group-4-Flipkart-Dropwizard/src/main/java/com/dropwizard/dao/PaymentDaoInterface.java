package com.dropwizard.dao;
import com.dropwizard.bean.FeePayment;
import com.dropwizard.exception.PaymentDoneException;
import com.dropwizard.exception.PaymentFailedException;

public interface PaymentDaoInterface {
    public void makePayment(FeePayment payment) throws PaymentFailedException, PaymentDoneException;
}
