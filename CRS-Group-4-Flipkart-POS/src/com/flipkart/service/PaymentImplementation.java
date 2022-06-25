package com.flipkart.service;
import com.flipkart.bean.FeePayment;
import com.flipkart.bean.Notification;
import com.flipkart.dao.PaymentDaoInterface;
import com.flipkart.dao.PaymentDaoImplementation;
import com.flipkart.exception.PaymentDoneException;
import com.flipkart.exception.PaymentFailedException;


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
