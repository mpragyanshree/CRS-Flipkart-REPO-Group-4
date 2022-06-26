package com.dropwizard.service;
import com.dropwizard.bean.FeePayment;
import com.dropwizard.bean.Notification;
import com.dropwizard.dao.PaymentDaoInterface;
import com.dropwizard.dao.PaymentDaoImplementation;
import com.dropwizard.exception.PaymentDoneException;
import com.dropwizard.exception.PaymentFailedException;


public class PaymentImplementation implements PaymentInterface{

    /**
     * Method to make Payment
     * @param payment
     * @return  void
     */
    public void makePayment(FeePayment payment) {
        try {
            PaymentDaoInterface paymentObj = new PaymentDaoImplementation();
            paymentObj.makePayment(payment);

			/*
			Notification obj = new Notification();
			obj.showPaymentNotification(Integer.toString(payment.getStudentID()));
			 */

        } catch (PaymentFailedException e) {
            System.out.println("Payment Failed Exception");
           // logger.error(e.getMessage());
        } catch (PaymentDoneException e) {
            System.out.println("Payment Done Exception");
           // logger.error(e.getMessage());
        }
    }
}
