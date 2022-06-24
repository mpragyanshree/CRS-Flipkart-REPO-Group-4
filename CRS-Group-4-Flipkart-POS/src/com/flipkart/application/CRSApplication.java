package com.flipkart.application;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;
import java.sql.Date;

import com.flipkart.bean.Student;
import com.flipkart.service.StudentInterface;
import com.flipkart.service.StudentImplementation;
import com.flipkart.service.UserInterface;
import com.flipkart.service.UserImplementation;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

public class CRSApplication {
    private Scanner sc = new Scanner(System.in);
    //private static final Logger logger =  LogManager.getLogger(CRSApplication.class);
    StudentImplementation so=new StudentImplementation();

    public static void main(String[] args) {
        CRSApplication newUser = new CRSApplication();
        newUser.createMenu();
    }
    public void createMenu() {
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

    public void loginUser()
    {
        String username, password, role;

        try {

            System.out.println("=======================================");
            System.out.print("UserID: ");
            username = sc.nextLine();
            System.out.print("Password: ");
            password = sc.nextLine();
            System.out.print("Enter Role (student/professor/admin): ");
            role = sc.nextLine();

            UserImplementation uo = new UserImplementation();

            if (uo.loginUser(username, password, role)) {
//           DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
//            LocalDateTime now = LocalDateTime.now();
                switch (role) {
                    case "student":
                        System.out.println("=======================================");
                        System.out.println("Logged In Successfully as a Student");
                        System.out.println("Welcome " + username + " !!");
//                        System.out.println("Login Time: "+ dtf.format(now) );
                        CRSStudent sc = new CRSStudent();
                        sc.createStudentMenu(username);
                        break;

                    case "professor":
                        System.out.println("=======================================");
                        System.out.println("Logged In Successfully as a Professor");
                        System.out.println("Welcome " + username + " !!");
//                        System.out.println("Login Time: "+ dtf.format(now) );
                        CRSProfessor pc = new CRSProfessor();
                        pc.createProfessorMenu(username);
                        break;

                    case "admin":
                        System.out.println("=======================================");
                        System.out.println("Logged In Successfully as a Admin");
                        System.out.println("Welcome " + username + " !!");
//                        System.out.println("Login Time: "+ dtf.format(now) );
                        CRSAdmin ac = new CRSAdmin();
                        ac.createAdminMenu(username);
                        break;

                    default:
                        System.out.println("Invalid Role");
                        System.out.println("=======================================");
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
       // System.out.println("User Logged in !! \n");
    }

    public void updatePassword()
    {
        String oldPassword,newPassword,username;
        try {
            System.out.println("=======================================");
            System.out.print("Enter UserID: ");
            username = sc.nextLine();
            System.out.print("Enter existing Password: ");
            oldPassword = sc.nextLine();

            UserImplementation uo = new UserImplementation();
           // if(uo.loginUser(username, oldPassword, role))
            //{
            System.out.println("=======================================");
            System.out.print("Enter new Password: ");
            newPassword = sc.nextLine();
            uo.updatePassword(username, newPassword); //function calling
           // }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Updated Password !! \n");
    }

    public void  selfRegisterStudent()
    {
        String studentId, password, name, department;
        String joiningDate,address,contactnum;

        try {
            System.out.println("=======================================");
            System.out.println("Enter your details");
            System.out.println("---------------------------------------");
            //System.out.print("StudentId: ");
            //studentId = sc.nextLine();
            System.out.print("Password: ");
            password = sc.nextLine();
            System.out.print("Name: ");
            name = sc.nextLine();
            System.out.print("Department: ");
            department = sc.nextLine();
            System.out.print("Joining Date, Please enter in format dd-MM-YYYY: ");
            joiningDate = sc.nextLine();
            System.out.print("Address: ");
            address = sc.nextLine();
            System.out.print("Contact Number: ");
            contactnum = sc.nextLine();
            System.out.println("=======================================");
            //impleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Student stud = so.registerStudent(name, password, department, joiningDate,address,contactnum);
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
        }
    }

}
