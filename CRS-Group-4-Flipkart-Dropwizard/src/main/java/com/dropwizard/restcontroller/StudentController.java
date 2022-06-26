package com.dropwizard.restcontroller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.dropwizard.bean.*;
import com.dropwizard.dao.StudentDaoImplementation;
import com.dropwizard.service.AdminImplementation;
import com.dropwizard.service.SemesterRegistrationImplementation;
import com.dropwizard.service.StudentImplementation;
import com.sun.istack.NotNull;
import com.sun.org.apache.xpath.internal.operations.Bool;
import jdk.nashorn.internal.runtime.Context;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

@Path("/student")
public class StudentController {
    private Scanner sc = new Scanner(System.in);
    SemesterRegistrationImplementation sro = new SemesterRegistrationImplementation();
    StudentImplementation so = new StudentImplementation();
    StudentDaoImplementation sdo = new StudentDaoImplementation();
    private static final Logger logger = Logger.getLogger(AdminController.class);

    @POST
    @Path("/createregistrationdashboard")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCourse(@NotNull @QueryParam("course_name") String course_name,
                              @NotNull @QueryParam("course_id") String courseID,
                              @NotNull @QueryParam("number_of_seats")  int numOfSeats) {



        String courseInstructor = "";

        try{
//            Course crs = ao.addCourse(courseID,course_name,courseInstructor,numOfSeats);

            //Student stud = so.registerStudent(studentId, name, password, department, formatter.parse(joiningDate),address,contactnum);
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
        return Response.status(201).entity("Course Added sucessfully!!! ").build();
    }

    @POST
    @Path("/addcourse")
    @Produces(MediaType.APPLICATION_JSON)
    //-------------
   // for the time bieng i have returned the above response kindly go through it
    public Response addCourse(@NotNull @QueryParam("username") String username,
                              @NotNull @QueryParam("courseID") String courseId,
                              @NotNull @QueryParam("isPrimary") Boolean isPrimary ){
        return Response.status(201).entity("Course Added sucessfully!!! ").build();


    }
    //------------------

    //View all registered courses for a given student in a given course
    @GET
     @Path("/viewRegisteredCourses")
     @Produces(MediaType.APPLICATION_JSON)
    public Response viewRegisteredCourses(@NotNull
                                          @QueryParam("username") String username,
                                          @NotNull
                                          @QueryParam("semesterID")String semesterID)  {

        try {

            String studentID = getStudentID(username);
            return Response.ok(sro.viewRegisteredCourses(studentID),MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }

    }


    //    View all available courses for student in a given semester
    @GET
     @Path("/viewavailablecourses")
     @Produces(MediaType.APPLICATION_JSON)
    public Response viewGradeCard() {
        try {
            ArrayList<Course> courseCatalog = sro.viewCourseCatalog();
            return Response.ok(courseCatalog,MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    //    View report card of student for a given semester
    @GET
     @Path("/viewGradeCard")
     @Produces(MediaType.APPLICATION_JSON)
    public Response viewGradeCard(@NotNull
                                  @QueryParam("username") String username,
                                  @NotNull
                                  @QueryParam("semesterID")int semesterID) {
        try {

            String studentID = getStudentID(username);
            List<Grade> R = sdo.viewReportCard(studentID);
            return Response.ok(R,MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    //    Get student id from username
    private String getStudentID(String username)throws Exception{

        String res;
        try {
            res = so.getStudentIDFromUserName(username);
            return res;
        } catch (Exception e) {
            throw e;
        }
    }
}
//                        createRegistrationDashboard();
//
//                    case 2 :
//                        // Select a course for semester registration.
//                        addCourse();
//                        break;
//
//                    case 3:
//                        // Drop out one selected course.
//                        dropCourse();
//                        break;
//
//                    case 4:
//                        // Finish the selection of courses.
//                        finishRegistration();
//                        break;
//
//                    case 5:
//                        // Pay the semester fees after successful registration.
//                        payRegistrationFee();
//                        break;
//
//                    case 6:
//                        return;
//
//                    default:
//                        System.out.println("Invalid input");
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Function that marks selection of courses is done, and checks if the choice made is valid.
//    private void finishRegistration() {
//
//        System.out.println("=======================================");
//        System.out.println("Finishing registration...");
//
//        finishedRegistration = sro.registerCourses(studentID);
//
//        if(finishedRegistration) {
//            System.out.println("Registration completed successfully!");
//        }
//        else {
//            System.out.println("You have not selected 4 primary and 2 alternative courses. Please select again !!");
//        }
//    }
//
//
//    // Function to redirect on payment portal depending on the payment choice made.
//    private void payRegistrationFee() {
//
//        if (sro.checkPaymentWindow(studentID)){ //check payment window is up or not and student registered or not, then proceed for payment
//            Scanner sc = new Scanner(System.in);
//            FeePayment payment = new FeePayment();
//            PaymentImplementation po = new PaymentImplementation();
//
//            payment.setStudentId(studentID);
//
//            try {
//
//                if(!finishedRegistration) {
//                    throw new Exception("Your registration is incomplete!");
//                }
//
//                System.out.println("=======================================");
//                System.out.println("Choose a Payment type : ");
//                System.out.println("---------------------------------------");
//                System.out.println("1 : Card");
//                System.out.println("2 : NetBanking/UPI");
//                System.out.println("3 : Cash");
//                System.out.println("4 : Cheque");
//                System.out.println("=======================================");
//
//                int menuOption = sc.nextInt();
//                sc.nextLine();
//
//                switch (menuOption) {
//                    case 1:
//                        System.out.println("=======================================");
//                        System.out.println("Enter your card details");
//                        System.out.println("---------------------------------------");
//                        System.out.println("Enter card number : ");
//                        String cardNumber = sc.nextLine();
//                        payment.setPaymentMode("Card");
//                        break;
//                    case 2:
//                        System.out.println("=======================================");
//                        System.out.println("Enter your bank details");
//                        System.out.println("---------------------------------------");
//                        System.out.println("Enter account number : ");
//                        String accountNumber = sc.nextLine();
//                        payment.setPaymentMode("NetBanking");
//                        break;
//                    case 3:
//                        payment.setPaymentMode("Cash");
//                        break;
//                    case 4:
//                        payment.setPaymentMode("Cheque");
//                        break;
//
//                    default:
//                        System.out.println("---------------------------------------");
//                        System.out.println("Invalid input");
//                        return;
//                }
//
//                po.makePayment(payment);
//
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }
//
//    }
//// Function to drop course
//    private void dropCourse() {
//
//        System.out.println("=======================================");
//        System.out.println("Delete Course");
//        System.out.println("Enter course ID: ");
//
//        String courseID = sc.nextLine();
//
//        boolean courseDropped = false;
//        try {
//            courseDropped = sro.dropCourse(studentID, 1, courseID);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        if(courseDropped) {
//            System.out.println("Course dropped successfully!");
//        }
//        else {
//            System.out.println("Course was not dropped from the cart.");
//        }
//    }
////Function to add course
//    private void addCourse() {
//
//        System.out.println("=======================================");
//        System.out.println("Add Course");
//        System.out.println("Enter course ID: ");
//        String courseID = sc.nextLine();
//        System.out.println("Is primary(0/1) ? : ");
//        int isPrimaryInt = sc.nextInt();
//        sc.nextLine();
//        boolean isPrimary = isPrimaryInt == 1;
//
//        boolean courseAdded = false;
//        try {
//            courseAdded = sro.addCourse(studentID,courseID, isPrimary);
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }
//
//        if(courseAdded) {
//            System.out.println("Course added successfully!");//STILL WE NEED TO GET THE APPROVAL OF THE ADMIN FOR THE AVAILABLE COURSE
//        }
//        else {
//            System.out.println("Course was not added to the cart.");
//        }
//    }
