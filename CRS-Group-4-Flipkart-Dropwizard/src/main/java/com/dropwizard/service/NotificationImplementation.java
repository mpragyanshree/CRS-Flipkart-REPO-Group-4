package com.flipkart.service;
import com.flipkart.bean.Notification;
import com.flipkart.dao.NotificationDaoImplementation;
import com.flipkart.dao.NotificationDaoInterface;

import java.sql.Time;
import java.time.LocalTime;

public class NotificationImplementation implements NotificationInterface {
	public void sendPayFeesNotification() {
		Notification obj = new Notification();
		obj.showRegistrationNotification();

		/*
		System.out.println("+-----------------------------------+");
		System.out.println("|         Notification Alert!       |");
		System.out.println("+-----------------------------------+");
		System.out.println("|      Registration Completed!      |");
		System.out.println("|    Please Complete Fee Payment!   |");
		System.out.println("+-----------------------------------+");
		*/
	}

	/**
	 * Method to send payment complete notification
	 * @param StudentId
	 * @param Amount
	 * @param PaymentStatus
	 * @param PaymentTime
	 * @param paymentmode
	 * @param newID
	 * @param newnotificationID
	 * @param getNotificationMSG
	 * @param getNotificationMSG
	 * @return  void
	 */

	public void sendPaymentCompleteNotification(String StudentId, int Amount, Time PaymentTime, Boolean PaymentStatus, String paymentmode, String newID,
												String newnotificationID, String getNotificationMSG) {

		//NotificationDaoInterface notificationObj = new NotificationDaoImplementation();
		//notificationObj.sendPaymentCompleteNotification(transactionId, studentid);
		Notification obj = new Notification();
		obj.showPaymentNotification(StudentId,Amount,PaymentTime, PaymentStatus, paymentmode,  newID,
				 newnotificationID,  getNotificationMSG);
	}
}
