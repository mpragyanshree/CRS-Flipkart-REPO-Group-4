/**
 *
 */
package com.dropwizard.restcontroller;

import java.sql.SQLException;
import java.util.Scanner;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dropwizard.service.ProfessorImplementation;
import org.apache.log4j.Logger;


/**
 * @author Dell
 *
 */
@Path("/professor")
public class ProfessorController {

    private static Logger logger = Logger.getLogger(ProfessorController.class);

    ProfessorImplementation profObj = ProfessorImplementation.getInstance();
    private int professorID;



    // register for a course
    @POST
    @Path("/registerCourse")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerCourse(@NotNull
                                   @QueryParam("username") String username,
                                   @NotNull
                                   @QueryParam("courseId") String courseId
                                   ) {

        try {

            profObj.registerCourse(username, courseId);
        }
        catch(Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
        return Response.status(200).entity( "Successfully Registered").build();


    }

    // View all available courses for a given professor
    @GET
    @Path("/getAvailableCourses")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewAvailableCourses() {


        try {
            return Response.ok(profObj.viewRegisteredCourses(),MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Response.status(500).entity(e.getMessage()).build();

        }


    }


    // Add grade for a given student in a specific course and semester
    @POST
    @Path("/addGrade")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addGrade(@NotNull
                             @QueryParam("studentId") String studentId,

                             @NotNull
                             @QueryParam("courseId") String courseId,

                             @QueryParam("grade") String grade)  {

        try {

            profObj.addGrade(studentId, courseId, grade);

        }
        catch(Exception ex){
            return Response.status(500).entity(ex.getMessage()).build();
        }


        return Response.status(200).entity( "Grade updated for student: "+studentId).build();
    }


    // View all registered students for a given course and semesterID
    @GET
    @Path("/getRegisteredStudents")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewEnrolledStudents(@NotNull
                                         @QueryParam("username") String username,
                                         @NotNull
                                         @QueryParam("courseId") String courseId){


        try {

            return Response.ok(profObj.viewRegisteredStudents(username, courseId),MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e) {

            return Response.status(500).entity(e.getMessage()).build();
        }


    }



}