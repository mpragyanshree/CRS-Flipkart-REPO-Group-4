package com.flipkart.application;

import com.flipkart.bean.Course;
import com.flipkart.bean.FeePayment;
import com.flipkart.bean.Grade;
import com.flipkart.service.SemesterRegistrationImplementation;
import com.flipkart.service.PaymentImplementation;
import com.flipkart.service.StudentImplementation;
import com.flipkart.exception.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.*;
import java.util.Scanner;

public class CRSStudent {
    static Scanner sc = new Scanner(System.in);
    private String studentID;
    private boolean finishedRegistration = false;


    SemesterRegistrationImplementation sro = new SemesterRegistrationImplementation();
    StudentImplementation so = new StudentImplementation();


    /**
     * Home page for a Student Login.
     */

    public void createStudentMenu(String username) {

        try {
            studentID=username;
            //studentID = getStudentID(username);
            //assert studentID!= -1;

            while(true) {
                System.out.println("\n\n==~~=~~=~~=~~=~Student Panel~=~~=~~=~~=~~==");
                System.out.println("Choose an option: ");
                System.out.println("---------------------------------------");
                System.out.println("1 : Open Semester Registration Dashboard");
                System.out.println("2 : View Registered Courses");
                System.out.println("3 : View Grade Card");
                System.out.println("4 : Logout");
                System.out.println("=======================================");

                int menuOption = sc.nextInt();
                sc.nextLine();

                switch(menuOption) {
                    case 1 :
                        // Redirect to semester registration dashboard.
                        createRegistrationDashboard();
                        break;


                    case 2 :
                        // View all the courses registered by the student.
                        viewRegisteredCourses(studentID);
                        break;

                    case 3:
                        // View report card for logged-in student.
                        viewGradeCard(studentID,1);
                        break;

                    case 4:
                        // Logging out from the system.
                        System.out.println("Logging Out ...");
                        CRSApplication crsapp = new CRSApplication();
                        crsapp.createMenu();
                        break;

                    default:
                        System.out.println("Invalid input");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Function that Create Registration Dashboard
    private void createRegistrationDashboard() {
        Scanner sc = new Scanner(System.in);

        try {

            while(true) {
                System.out.println("=======================================");
                System.out.println("Semester registration : ");
                System.out.println("---------------------------------------");
                System.out.println("1 : View available course details");
                System.out.println("2 : Add course");
                System.out.println("3 : Drop course");
                System.out.println("4 : Finish registration");
                System.out.println("5 : Make a payment and exit");
                System.out.println("6 : Exit");
                System.out.println("=======================================");
                int menuOption = sc.nextInt();
                sc.nextLine();
                switch(menuOption) {
                    case 1 :
                        // View all the courses available to register in the semester.
                        viewAvailableCourses();
                        break;

                    case 2 :
                        // Select a course for semester registration.
                        addCourse();
                        break;

                    case 3:
                        // Drop out one selected course.
                        dropCourse();
                        break;

                    case 4:
                        // Finish the selection of courses.
                        finishRegistration();
                        break;

                    case 5:
                        // Pay the semester fees after successful registration.
                        payRegistrationFee();
                        break;

                    case 6:
                        return;

                    default:
                        System.out.println("Invalid input");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Function that marks selection of courses is done, and checks if the choice made is valid.
    private void finishRegistration() {

        System.out.println("=======================================");
        System.out.println("Finishing registration...");

        finishedRegistration = sro.registerCourses(studentID);

        if(finishedRegistration) {
            System.out.println("Registration completed successfully!");
        }
        else {
            System.out.println("You have not selected 4 primary and 2 alternative courses. Please select again !!");
        }
    }


    // Function to redirect on payment portal depending on the payment choice made.
    private void payRegistrationFee() {

        if (sro.checkPaymentWindow(studentID)){ //check payment window is up or not and student registered or not, then proceed for payment
            Scanner sc = new Scanner(System.in);
            FeePayment payment = new FeePayment();
            PaymentImplementation po = new PaymentImplementation();

            payment.setStudentId(studentID);

            try {

                if(!finishedRegistration) {
                    throw new Exception("Your registration is incomplete!");
                }

                System.out.println("=======================================");
                System.out.println("Choose a Payment type : ");
                System.out.println("---------------------------------------");
                System.out.println("1 : Card");
                System.out.println("2 : NetBanking/UPI");
                System.out.println("3 : Cash");
                System.out.println("4 : Cheque");
                System.out.println("=======================================");

                int menuOption = sc.nextInt();
                sc.nextLine();

                switch (menuOption) {
                    case 1:
                        System.out.println("=======================================");
                        System.out.println("Enter your card details");
                        System.out.println("---------------------------------------");
                        System.out.println("Enter card number : ");
                        String cardNumber = sc.nextLine();
                        payment.setPaymentMode("Card");
                        break;
                    case 2:
                        System.out.println("=======================================");
                        System.out.println("Enter your bank details");
                        System.out.println("---------------------------------------");
                        System.out.println("Enter account number : ");
                        String accountNumber = sc.nextLine();
                        payment.setPaymentMode("NetBanking");
                        break;
                    case 3:
                        payment.setPaymentMode("Cash");
                        break;
                    case 4:
                        payment.setPaymentMode("Cheque");
                        break;

                    default:
                        System.out.println("---------------------------------------");
                        System.out.println("Invalid input");
                        return;
                }

                po.makePayment(payment);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }
// Function to drop course
    private void dropCourse() {

        System.out.println("=======================================");
        System.out.println("Delete Course");
        System.out.println("Enter course ID: ");

        String courseID = sc.nextLine();

        boolean courseDropped = false;
        try {
            courseDropped = sro.dropCourse(studentID, 1, courseID);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(courseDropped) {
            System.out.println("Course dropped successfully!");
        }
        else {
            System.out.println("Course was not dropped from the cart.");
        }
    }
//Function to add course
    private void addCourse() {

        System.out.println("=======================================");
        System.out.println("Add Course");
        System.out.println("Enter course ID: ");
        String courseID = sc.nextLine();
        System.out.println("Is primary(0/1) ? : ");
        int isPrimaryInt = sc.nextInt();
        sc.nextLine();
        boolean isPrimary = isPrimaryInt == 1;

        boolean courseAdded = false;
        try {
            courseAdded = sro.addCourse(studentID,courseID, isPrimary);
        } catch (Exception e) {

            e.printStackTrace();
        }

        if(courseAdded) {
            System.out.println("Course added successfully!");//STILL WE NEED TO GET THE APPROVAL OF THE ADMIN FOR THE AVAILABLE COURSE
        }
        else {
            System.out.println("Course was not added to the cart.");
        }
    }
// Function to view Available Course
    private void viewAvailableCourses() {
        /* HashMap<String, Course> courseCatalog = null;
        courseCatalog = sro.viewCourseCatalog();
        System.out.println("Course catalog : \n");
        for (HashMap.Entry<String, Course> entry : courseCatalog.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getCourseCode() + " " + entry.getValue().getCourseName() + " " + entry.getValue().getInstructorId() + " " + entry.getValue().isAvailable());
        }*/

        ArrayList<Course> courseCatalog = null;
        courseCatalog = sro.viewCourseCatalog();

        System.out.println("Course catalog : ");
        for(Course c : courseCatalog) {
            System.out.println("Course ID : "+c.getCourseCode() +"\t Available seats : "+c.getNumberOfSeats()+" \t Instructor : "+c.getInstructorId()+" \t Course Name : "+ c.getCourseName());
        }
    }
//Function View Registered Course
    private void viewRegisteredCourses(String studentID) throws StudentNotRegisteredException, SQLException {

        List<Course> courses = sro.viewRegisteredCourses(studentID);
        for(Course c:courses)
        {
            System.out.println("Coursename: "+c.getCourseCode()+"\t InstructorID: "+ c.getInstructorId());
        }
        createStudentMenu(studentID);
    }
// Function to view grade card
    private void viewGradeCard(String studentID, int semesterID) throws SQLException, GradeNotAddedException, StudentNotApprovedException, FeesPendingException, StudentNotApprovedException {
        Grade R;
        so.viewGradeCard(studentID, semesterID);
    }
}