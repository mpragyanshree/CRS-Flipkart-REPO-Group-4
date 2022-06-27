package com.dropwizard.restcontroller;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dropwizard.bean.Student;
import com.dropwizard.service.StudentImplementation;
import com.dropwizard.service.UserImplementation;


/**
 *
 * @author JEDI-04
 * This class is used as the main entry point of the application
 * In main menu to login, register are displayed
 *
 */



@Path("/application")

public class ApplicationController {
    //private Scanner sc = new Scanner(System.in);
    StudentImplementation so=new StudentImplementation();





    /**
     * Method to Create Main Menu
     */


   /* public void createMenu() {
        try {
            while(true) {
                System.out.println("\n\n==~~=~~= Course Registration System ~=~~=~~=~~==");
                System.out.println("   Welcome !    ");
                System.out.println("\nChoose an option: ");
                System.out.println("---------------------------------------");
                System.out.println("1 : Login");
                System.out.println("2 : Student Self Registration");
                System.out.println("3 : Update Password");
                System.out.println("4 : Exit menu");
                System.out.println("=======================================");

                int menuOption = sc.nextInt();
                sc.nextLine();

                switch(menuOption) {
                    case 1:
                        // Login functionality (by student / professor / admin).
                        loginUser();
                        break;

                    case 2:
                        // Self Registration for a new student.
                        selfRegisterStudent();
                        break;

                    case 3:
                        // Functionality to update login password.
                        updatePassword();
                        break;

                    case 4:
                        // Close / Exit the portal.
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid input");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

*/

    /**
     * Method for Login functionality
     */

    @PUT
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUser(@NotNull
                                  @QueryParam("username") String username,
                              @NotNull
                                  @QueryParam("password") String password,
                              @NotNull
                                  @QueryParam("role") String role)
    {

        try {
            System.out.println(username);
            UserImplementation uo = new UserImplementation();

            if (uo.loginUser(username, password, role)) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                switch (role) {
                    case "student":
                        System.out.println("=======================================");
                        System.out.println("Logged In Successfully as a Student");
                        System.out.println("Welcome " + username + " !!");
                        System.out.println("Login Time: "+ dtf.format(now) );
                        break;

                    case "professor":
                        System.out.println("=======================================");
                        System.out.println("Logged In Successfully as a Professor");
                        System.out.println("Welcome " + username + " !!");
                        System.out.println("Login Time: "+ dtf.format(now) );
                        break;

                    case "admin":
                        System.out.println("=======================================");
                        System.out.println("Logged In Successfully as a Admin");
                        System.out.println("Welcome " + username + " !!");
                        System.out.println("Login Time: "+ dtf.format(now) );
                        break;

                    default:
                        System.out.println("Invalid Role");
                        System.out.println("=======================================");
                }
                return Response.status(200).entity("Login successful at "+ dtf.format(now)).build();
            }
            else {
                return Response.status(500).entity("Invalid credentials!").build();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }


    /**
     * Method to update password of User
     */

    @POST
    @Path("/updatePassword")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePassword(@QueryParam("username") String username,
                               @NotNull
                               @QueryParam("oldPassword") String oldPassword,
                               @NotNull
                                   @QueryParam("role") String role,
                               @NotNull
                                   @QueryParam("newPassword") String newPassword)
    {
        try {
          /*  System.out.println("=======================================");
            System.out.print("Enter UserID: ");
            username = sc.nextLine();
            System.out.print("Enter existing Password: ");
            oldPassword = sc.nextLine();*/
            UserImplementation uo = new UserImplementation();
            if(uo.loginUser(username, oldPassword, role)) {
                uo.updatePassword(username, newPassword); //function calling
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
        return Response.status(201).entity("Password Update Successful!!!").build();
    }



    /**
     * Method to help Student register themselves, pending admin approval
     */

    @POST
    @Path("/register")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response selfRegisterStudent(@QueryParam("username") String studentId,
                                     @NotNull
                                     @QueryParam("Password") String password,
                                     @NotNull
                                         @QueryParam("Name") String name,
                                     @NotNull
                                         @QueryParam("JoiningDate") String joiningDate,
                                     @NotNull
                                         @QueryParam("Address") String address,
                                     @NotNull
                                         @QueryParam("ContactNumber") String contactnum,
                                     @NotNull
                                         @QueryParam("Department") String department)
    {

        try {
           /* System.out.println("=======================================");
            System.out.println("Enter your details");
            System.out.println("---------------------------------------");
            System.out.print("Student ID: ");
            studentId = sc.nextLine();
            System.out.print("Password: ");
            password = sc.nextLine();
            System.out.print("Name: ");
            name = sc.nextLine();
            System.out.print("Department: ");
            department = sc.nextLine();
            System.out.print("Jining Date, Please enter in format dd-MM-YYYY: ");
            joiningDate = sc.nextLine();
            System.out.print("Address: ");
            address = sc.nextLine();
            System.out.print("Contact Number: ");
            contactnum = sc.nextLine();
            System.out.println("=======================================");*/
            Student stud = so.registerStudent(studentId,name, password, department, joiningDate,address,contactnum);
            if(stud == null) {
                System.out.println("Student Was not added");
                System.out.println("=======================================");
            }
            else {
                System.out.println("Student Added Successfully! Wait for admin approval!");
                System.out.println("=======================================");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
        String result = "Added student : " + name +  " | Student ID : "+ studentId + ". Wait for admin approval!";
        return Response.status(201).entity(result).build();
    }

}
