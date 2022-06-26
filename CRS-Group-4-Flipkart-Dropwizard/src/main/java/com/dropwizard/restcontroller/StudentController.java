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

    @PUT
     @Path("/addcourse")
     @Produces(MediaType.APPLICATION_JSON)
    public Response addCourse(@NotNull @QueryParam("username") String username,
                              @NotNull @QueryParam("courseID") String courseID,
                              @NotNull @QueryParam("isPrimary") Boolean isprimary ){
        String studentID = getStudentID(username);
        boolean isPrimary = isprimary == 1;
        try {
            boolean courseAdded = false;
            courseAdded = sro.addCourse(studentID, courseID, isPrimary);
            if(courseAdded) {
                System.out.println("Course added successfully!");
                return Response.status(200).entity("Course added successfully!").build();
            }
            else {
                return Response.status(500).entity("Course was not added to the cart.").build();
            }
        }catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }catch (CourseExistsInCartException e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    //    DELETE API to drop course
    @DELETE
     @Path("/dropCourse")
     @Produces(MediaType.APPLICATION_JSON)
    public Response dropCourse(@NotNull @QueryParam("courseID") String courseID,
                               @NotNull @QueryParam("username")String username){

        try {
            boolean courseDropped = false;
            String studentID = getStudentID(username);
            courseDropped = sro.dropCourse(studentID, courseID);
            if(courseDropped) {
                System.out.println("Course dropped successfully!");
                return Response.status(200).entity("Course dropped successfully!").build();
            }
            else {
                return Response.status(500).entity("Course was not dropped from the cart.").build();
            }

        } catch (Throwable e) {
            return Response.status(500).entity(e.getMessage()).build();
        }

    }

    @GET
     @Path("/finishStudentRegistration")
     @Produces(MediaType.APPLICATION_JSON)
    public Response finishRegistration(@NotNull @QueryParam("username") String username){

        try {
            boolean finishedRegistration = false;

            System.out.println("=======================================");
            System.out.println("Finishing registration...");
            String studentID = getStudentID(username);

            finishedRegistration = sro.registerCourses(studentID);

            if(finishedRegistration) {
                return Response.status(200).entity("Registration completed successfully!").build();
            }
            else {
                return Response.status(500).entity("Finish Registration action not done!!").build();

            }

        }
        catch(Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
        catch(Throwable e) {
            return Response.status(500).entity(e.getMessage()).build();
        }

    }
    //    POST API to pay student semester fees

    @POST
     @Path("/payRegistrationFee")
     @Produces(MediaType.APPLICATION_JSON)
    public Response payRegistrationFee(@NotNull @QueryParam("username") String username,
                                       @NotNull @QueryParam("type") String type){

        try {
            String studentID = getStudentID(username);
            boolean finishedRegistration = false;
            finishedRegistration = sro.registerCourses(studentID);

            if(!finishedRegistration) {
                return Response.status(500).entity("You registration is incomplete!").build();
            }else if (so.checkPaymentWindow(studentID)) {

                FeePayment payment = new FeePayment();
                PaymentImplementation po = new PaymentImplementation();

                payment.setStudentId(studentID);
                if(!type.equals("Card") && !type.equals("NetBanking") && !type.equals("Cash") &&
                        !type.equals("Cheque") && !type.equals("Scholarship")  ) {
                    return Response.status(500).entity("Invalid Mode of payment!!").build();
                }

                payment.setPaymentMode(type);
                po.makePayment(payment);

                return Response.status(200).entity("Payment done successfully!").build();
            }else {
                return Response.status(500).entity("Payment window not opened!").build();

            }
        }
        catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
        catch(Throwable e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

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