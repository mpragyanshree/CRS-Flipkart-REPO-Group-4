package com.dropwizard.restcontroller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.dropwizard.bean.*;
import com.dropwizard.service.AdminImplementation;
import com.sun.istack.NotNull;
import jdk.nashorn.internal.runtime.Context;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;


/**
 *
 * @author JEDI-04
 * Class that display Admin Client Menu
 *
 */

@Path("/admin")
public class AdminController {
    private Scanner sc = new Scanner(System.in);
    AdminImplementation ao = AdminImplementation.getInstance();
    private static final Logger logger = Logger.getLogger(AdminController.class);



    /**
     * Method to add course by admin
     */

    @POST
    @Path("/addcourse")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCourse(@NotNull @QueryParam("course_name") String course_name,
                              @NotNull @QueryParam("course_id") String courseID,
                              @NotNull @QueryParam("number_of_seats")  int numOfSeats){
        String courseInstructor = "";

        try{
            Course crs = ao.addCourse(courseID,course_name,courseInstructor,numOfSeats);

            //Student stud = so.registerStudent(studentId, name, password, department, formatter.parse(joiningDate),address,contactnum);
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
        return Response.status(201).entity("Course Added sucessfully!!! ").build();
    }

    /**
     * Method to assign Course to a Professor
     */

    @DELETE
    @Path("/removecourse")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeCourse(@NotNull @QueryParam("course_id") String courseID) {

        try {
            ao.removeCourse(courseID);
            // remove course
        } catch (Exception e) {
//            logger.error(e.getMessage());
            return Response.status(500).entity(e.getMessage()).build();
        }
        return Response.status(201).entity("Course Removed sucessfully!!! ").build();
    }

    /**
     * Method to update course details by admin
     */
    @PUT
    @Path("/updatecourse")
    public Response updateCourse(@NotNull @QueryParam("course_name") String course_name,
                                 @NotNull @QueryParam("course_id") String courseID,
                                 @NotNull @QueryParam("number_of_seats")  int numOfSeats,
                                 @NotNull @QueryParam("course_instructor") String courseInstructor) {

        try{

            ao.updateCourse(course_name, courseID, numOfSeats, courseInstructor);
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
        return Response.status(201).entity("Course updated successfully!!! Returning you to main menu").build();
    }



    /**
     * Method to add professor by admin
     */

    @POST
    @Path("/addprofessor")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProfessor(@NotNull @QueryParam("username") String username,
                                 @NotNull @QueryParam("name") String name,
                                 @NotNull @QueryParam("password") String password,
                                 @NotNull @QueryParam("address") String address,
                                 @NotNull @QueryParam("department") String department,
                                 @NotNull @QueryParam("designation")String designation,
                                 @NotNull @QueryParam("contact") String contact,
                                 @NotNull @QueryParam("joiningdate") String joiningDate){
        try {

            Professor prof = ao.addProfessor(username, name, password, department, designation, joiningDate,address,contact);

        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
        return Response.status(201).entity("Professor added successfully!!! Returning you to main menu").build();

    }

    /**
     *
     * Function for admin to remove professor
     */
    @DELETE
    @Path("/removeprofessor")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeProfessor(@NotNull @QueryParam("professor_id") String professorID) {




        try {
            ao.removeProfessor(professorID);


        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
        return Response.status(201).entity("Professor removed successfully!!! Returning you to main menu").build();

    }

    /**
     *
     * Function for admin to update professor details
     */

    @PUT
    @Path("/updateprofessor")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProfessor(@NotNull @QueryParam("username") String username,
                                    @NotNull @QueryParam("name") String name,
                                    @NotNull @QueryParam("password") String password,
                                    @NotNull @QueryParam("address") String address,
                                    @NotNull @QueryParam("department") String department,
                                    @NotNull @QueryParam("designation")String designation,
                                    @NotNull @QueryParam("contact") String contact,
                                    @NotNull @QueryParam("joiningdate") String joiningDate){

        try {
            ao.updateProfessor(username, name, password, department, designation, address, contact, joiningDate);


        }  catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
        return Response.status(201).entity("Professor updated successfully!!! Returning you to main menu").build();

    }




    /**
     *
     * Function to add admin by another admin
     */
    @POST
    @Path("/addadmin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAdmin(@NotNull @QueryParam("username") String username,
                             @NotNull @QueryParam("name") String name,
                             @NotNull @QueryParam("password") String password,
                             @NotNull @QueryParam("contact") String contact,
                             @NotNull @QueryParam("address") String address,
                             @NotNull @QueryParam("joiningdate") String joiningdate){
        try {


            Admin ad = ao.addAdmin(username, name, password, contact, joiningdate,address);


        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
        return Response.status(201).entity("Admin added successfully!!! Returning you to main menu").build();

    }

    /**
     *
     * Function remove admin by another admin
     */
    @DELETE
    @Path("/removeadmin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeAdmin(@NotNull @QueryParam("admin_id") String adminID) {

        //sc.nextLine();

        try {
            ao.removeAdmin(adminID);


        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
        return Response.status(201).entity("Admin removed successfully!!! Returning you to main menu").build();

    }

    /**
     *
     * Function to update admin details by admin
     */
    @PUT
    @Path("/updateadmin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAdmin(@NotNull @QueryParam("admin_id") String adminId,
                                @NotNull @QueryParam("name") String name,
                                @NotNull @QueryParam("password") String password,
                                @NotNull @QueryParam("contact") String contact,
                                @NotNull @QueryParam("address") String address,
                                @NotNull @QueryParam("joiningdate") String joiningDate) {

        try {

            ao.updateAdmin(adminId, name, password, contact, joiningDate,address);


        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
        return Response.status(201).entity("Admin updated successfully!!! Returning you to main menu").build();


    }

    /**
     *
     * Function to enable disable payment window by admin
     */
    @PUT
    @Path("/enabledisablefeepayment")
    @Produces(MediaType.APPLICATION_JSON)
    public Response enabledisableFeePaymentWindow(@NotNull @QueryParam("fee_flag") int a, @NotNull @QueryParam("semester_id") String sem){


        if (a > 0 && a < 9) {
            try {
                ao.enableFeePaymentWindow(sem);
            }
            catch (Exception e) {
                return Response.status(500).entity(e.getMessage()).build();
            }


        }
        else {
            try {
                ao.disableFeePaymentWindow(sem);
            }
            catch (Exception e) {
                return Response.status(500).entity(e.getMessage()).build();
            }

        }
        return Response.status(201).entity("Fee window updated successfully!!! Returning you to main menu").build();


    }







    /**
     *
     * Function to view available course
     */
    @POST
    @Path("/adminviewcourses")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewAvailableCourses() {
        try {
            return Response.ok(ao.viewAvailableCourses(),MediaType.APPLICATION_JSON).build();
            }

        catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }

    }

    /**
     *
     * Function to approve student registration
     */

    @PUT
    @Path("/adminapprovestudent")
    @Produces(MediaType.APPLICATION_JSON)
    public Response approveStudentRegistration(@NotNull @QueryParam("studentID") String studentID) {


        try{
                ao.approveStudentRegistration(studentID);

            }
        catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }



        return Response.status(201).entity("Student" + studentID +"Approved successfully!!! Returning you to main menu").build();


    }

    /**
     *
     * Function to view registered students for a particular course
     */
    @POST
    @Path("/adminviewstudentlist")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewCourseStudentList(@NotNull @QueryParam("course_id") String courseID){
        try {
            return Response.ok(ao.viewCourseStudentList(courseID), MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }

    }

    /**
     *
     * Function to view Grades of all Registered Students for a particular course
     */

    @POST
    @Path("/adminviewgradelist")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewCourseGrades(@NotNull @QueryParam("course_id") String courseID){
        try {
            return Response.ok(ao.viewCourseGrades(courseID), MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }

    }

    /**
     *
     * Function to Generate Grade Card
     */

    @PUT
    @Path("/generategradecard")
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateGradeCard() {
        try{
            ao.generateGradeCard();

        }
        catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }



        return Response.status(201).entity("Grade Cards generated successfully, now available for students to view!").build();

    }
}
