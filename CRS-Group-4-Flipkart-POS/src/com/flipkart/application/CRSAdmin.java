package com.flipkart.application;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.flipkart.bean.*;
import com.flipkart.service.AdminImplementation;
import com.flipkart.service.AdminInterface;
import jdk.nashorn.internal.runtime.Context;

/**
 *
 * @author JEDI-04
 * Class that display Admin Client Menu
 *
 */


public class CRSAdmin {
    private Scanner sc = new Scanner(System.in);
    AdminImplementation ao = AdminImplementation.getInstance();
    private Context.ThrowErrorManager logger;


    /**
     * Method to Create Admin Menu
     */


    public void createAdminMenu (String adminId){
        System.out.println("*************Admin Menu**************");
        System.out.println("--------------------------------------");
        System.out.println("Kindly select one option from the menu");
        System.out.println("--------------------------------------");
        System.out.println("1. Edit Course Details in the course catalog");
        System.out.println("2. Add/Remove Professor");
        System.out.println("3. Add/Remove Admin");
        System.out.println("4. Enable/Disable Payment window");
        System.out.println("5. View available Courses for seat availability");
        System.out.println("6. Approve Student registration");
        System.out.println("7. View course wise student list");
        System.out.println("8. View Course wise grades");
        System.out.println("9. Generate Grade Card");
        System.out.println("10. Logout");
        int menuChoice = sc.nextInt();
        sc.nextLine();

        switch (menuChoice){
            case 1:
                // Edit course details
                editCourseDetails();
                createAdminMenu ("adminx");
                break;
            case 2:
                // add remove professor
                addRemoveProfessor();
                createAdminMenu ("adminx");
                break;
            case 3:
                // add remove admin
                addRemoveAdmin();
                createAdminMenu ("adminx");
                break;
            case 4:
                // enable/disbale payment window
                enabledisableFeePaymentWindow();
                createAdminMenu ("adminx");
                break;
            case 5:
                // view available courses
                viewAvailableCourses();
                createAdminMenu ("adminx");
                break;
            case 6:
                // approve student registration
                approveStudentRegistration();
                createAdminMenu ("adminx");
                break;
            case 7:
                // view course wise student list - fetch from db
                viewCourseStudentList();
                createAdminMenu ("adminx");
                break;
            case 8:
                // view course wise grades - fetch from db
                viewCourseGrades();
                createAdminMenu ("adminx");
                break;
            case 9:
                // generate grade card;
                generateGradeCard();
                createAdminMenu ("adminx");
                break;
            case 10:
                break;



        }



    }

    /**
     * Method to edit course details by admin - add Course, Remove Course, Update course functionalities implemented
     */


    private void editCourseDetails(){
        try {
            while(true) {
                System.out.println("=======================================");
                System.out.println("Options : ");
                System.out.println("---------------------------------------");
                System.out.println("1 : Add course");
                System.out.println("2 : Remove course");
                System.out.println("3 : Update Existing Course Details");
                System.out.println("4 : Exit");
                System.out.println("=======================================");

                int menuOption = sc.nextInt();

                sc.nextLine();

                switch(menuOption) {
                    case 1 :
                        // Add a new course in the catalog.
                        addCourse();
                        break;

                    case 2 :
                        // Remove an existing course from the catalog.
                        removeCourse();
                        break;
                    case 3 :
                        // update an existing course from the catalog.
                        updateCourse();
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Invalid input");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to add course by admin
     */


    private void addCourse() {

        try{
            String course_name, courseID, courseInstructor;
            int numOfSeats;
            System.out.println("=======================================");
            System.out.println("Kindly enter course details below");
            System.out.println("Course Name: ");
            course_name = sc.nextLine();
            System.out.println("Course ID: ");
            courseID = sc.nextLine();
//            System.out.println("InstructorId for the course: ");
            courseInstructor = "";
            System.out.println("Number of seats available");
            numOfSeats = sc.nextInt();
            sc.nextLine();
            Course crs = ao.addCourse(courseID,course_name,courseInstructor,numOfSeats);
            //Student stud = so.registerStudent(studentId, name, password, department, formatter.parse(joiningDate),address,contactnum);
            if(crs == null) {
                System.out.println("Course Was not added");
                System.out.println("=======================================");
            }
            else {
                System.out.println(crs);
                System.out.println("Course Added Successfully!");
                System.out.println("=======================================");
            }
            //Student stud = so.registerStudent(studentId, name, password, department, formatter.parse(joiningDate),address,contactnum);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return;
    }

    /**
     * Method to assign Course to a Professor
     */


    private void removeCourse() {
        String courseID;
        System.out.println("=======================================");
        System.out.println("Enter course ID for course removal: ");
        courseID = sc.nextLine();
        System.out.println("Course removed successfully!!! Returning you to main menu");
        try {
            TimeUnit.SECONDS.sleep(3);
            ao.removeCourse(courseID);
            // remove course
        } catch (Exception e) {
//            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
	 * Method to update course details by admin
	 */

    private void updateCourse() {

        try{
            String course_name, courseID, courseInstructor;
            int numOfSeats;
            System.out.println("=======================================");
            System.out.println("Kindly enter the course ID for updation");
            System.out.print("Course ID: ");
            courseID = sc.nextLine();
            // search course based on course ID
            System.out.println("Please enter the updated details below");
            System.out.print("Course Name: ");
            course_name = sc.nextLine();
            System.out.print("Number of seats: ");
            numOfSeats = sc.nextInt();
            sc.nextLine();
            System.out.print("Instructor for the course: ");
            courseInstructor = sc.nextLine();
            // update course details here

            ao.updateCourse(course_name, courseID, numOfSeats, courseInstructor);
            System.out.println("Course updated successfully!!! Returning you to main menu");
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
	 * Method to add or remove professor by admin
	 */


    private void addRemoveProfessor(){
        try {

            while(true) {
                System.out.println("=======================================");
                System.out.println("Options : ");
                System.out.println("---------------------------------------");
                System.out.println("1 : Add new professor");
                System.out.println("2 : Remove professor");
                System.out.println("3 : Update Existing professor Details");
                System.out.println("4 : Exit");
                System.out.println("=======================================");

                int menuOption = sc.nextInt();
                sc.nextLine();

                switch(menuOption) {
                    case 1 :
                        // Add a new professor .
                        addProfessor();
                        break;

                    case 2 :
                        // Remove an existing professor.
                        removeProfessor();
                        break;
                    case 3 :
                        // Update an existing professor.
                        updateProfessor();
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Invalid input");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
	 * Method to add professor by admin
	 */


    private void addProfessor(){

        try {

            String username, name, password, address, department, designation, contact;
            String joiningDate;

            System.out.println("=======================================");
            System.out.println("Kindly enter new professor details");
            System.out.println("---------------------------------------");
            System.out.println("User Name: ");
            username = sc.nextLine();
            System.out.println("Password: ");
            password = sc.nextLine();
            System.out.println("Name: ");
            name = sc.nextLine();
            System.out.println("Department: ");
            department = sc.nextLine();
            System.out.println("Designation: ");
            designation = sc.nextLine();
            System.out.println("Contact Number");
            contact = sc.nextLine();
            System.out.println("Address");
            address = sc.nextLine();
            System.out.println("Joining Date, Please enter in format dd-mm-yyyy");
            joiningDate = sc.nextLine();
//            sc.nextLine();

            //SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Professor prof = ao.addProfessor(username, name, password, department, designation, joiningDate,address,contact);
            if(prof == null) {
                System.out.println("User Was not added");
                System.out.println("=======================================");
            }
            else {
                System.out.println("User Added Successfully!");
                System.out.println("=======================================");
            }

            TimeUnit.SECONDS.sleep(3);
            System.out.println("=======================================");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * Function for admin to remove professor
     */

    private void removeProfessor() {
        System.out.println("Enter Instructor ID :");
        String professorID = sc.nextLine();



        try {
            ao.removeProfessor(professorID);
            TimeUnit.SECONDS.sleep(3);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * Function for admin to update professor details
     */

    private void updateProfessor(){

        try {

            String username, name, password, department, address, designation, contact;
            String joiningDate;

            System.out.println("=======================================");
            System.out.println("Kindly enter professor ID for updating details");
            System.out.println("---------------------------------------");
            username = sc.nextLine();
            //check prof id and fetch details, print details alongside.
            System.out.println("For updating details, please enter new details except professorid");
            System.out.println("Password: ");
            password = sc.nextLine();
            System.out.println("Name: ");
            name = sc.nextLine();
            System.out.println("Department: ");
            department = sc.nextLine();
            System.out.println("Designation: ");
            designation = sc.nextLine();
            System.out.println("Address: ");
            address = sc.nextLine();
            System.out.println("Contact Number");
            contact = sc.nextLine();
            System.out.println("Joining date. Please enter in format dd-mm-yyyy");
            joiningDate = sc.nextLine();
//            sc.nextLine();
            //SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            ao.updateProfessor(username, name, password, department, designation, address, contact, joiningDate);
            System.out.println("Professor updated successfully!!! Returning you to main menu");
            TimeUnit.SECONDS.sleep(3);
            System.out.println("=======================================");


            // add prof details - interface link


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * Function to add or remove admin
     */


    private void addRemoveAdmin(){
        try {

            while(true) {
                System.out.println("=======================================");
                System.out.println("Options : ");
                System.out.println("---------------------------------------");
                System.out.println("1 : Add new admin");
                System.out.println("2 : Remove admin");
                System.out.println("3 : Update Existing admin Details");
                System.out.println("4 : Exit");
                System.out.println("=======================================");

                int menuOption = sc.nextInt();
                sc.nextLine();

                switch(menuOption) {
                    case 1 :
                        // Add a new professor .
                        addAdmin();
                        break;

                    case 2 :
                        // Remove an existing professor.
                        removeAdmin();
                        break;
                    case 3 :
                        // Update an existing professor.
                        updateAdmin();
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Invalid input");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * Function to add admin by another admin
     */
    private void addAdmin(){
        try {

            String username, name, password, contact,address;
            String joiningdate;

            System.out.println("=======================================");
            System.out.println("Kindly enter new admin details");
            System.out.println("---------------------------------------");
            System.out.println("User Name: ");
            username = sc.nextLine();
            System.out.println("Password: ");
            password = sc.nextLine();
            System.out.println("Name: ");
            name = sc.nextLine();

            System.out.println("Contact Number");
            contact = sc.nextLine();
            System.out.println("Address");
            address = sc.nextLine();
            System.out.println("Joining Date, Please add in format dd-mm-yyyy");
            joiningdate = sc.nextLine();
//            sc.nextLine();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Admin ad = ao.addAdmin(username, name, password, contact, joiningdate,address);
            if(ad == null) {
                System.out.println("User Was not added");
                System.out.println("=======================================");
            }
            else {
                System.out.println("User Added Successfully!");
                System.out.println("=======================================");
            }
            TimeUnit.SECONDS.sleep(3);
            System.out.println("=======================================");


            // add admin details - interface link


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
             * Function remove admin by another admin
     */
    private void removeAdmin() {
        System.out.println("Enter Admin's user ID to remove :");
        String adminID;
        adminID = sc.nextLine();
        //sc.nextLine();

        try {
            ao.removeAdmin(adminID);
            System.out.println("Admin removed successfully!!! Returning you to main menu");
            TimeUnit.SECONDS.sleep(3);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * Function to update admin details by admin
     */

    private void updateAdmin(){

        try {

            String adminId, name, password, contact,address;
            String joiningDate;

            System.out.println("=======================================");
            System.out.println("Kindly enter admin ID for updating details");
            System.out.println("=======================================");
            adminId = sc.nextLine();
            //check admin id and fetch details, print details alongside.
            System.out.println("For updating details, please enter new details.");
            System.out.println("Enter New Password: ");
            password = sc.nextLine();
            /*if (password.isEmpty()){
                // fetch existing password
            }*/
            System.out.println("Name: ");
            name = sc.nextLine();
            /*if (name.isEmpty()) {
                // fetch existing name
            }*/
            System.out.println("Contact Number");
            contact = sc.nextLine();
            /*if (contact.isEmpty()){
                // fetch existing contact
            }*/
            System.out.println("Joining Date, Please add in format dd-mm-yyyy");
            joiningDate = sc.nextLine();
            /*if (joiningDate.isEmpty()){
                // fetch existing joining date
            }*/
            System.out.println("Address");
            address = sc.nextLine();
            /*if (address.isEmpty()){
                // fetch existing joining date
            }*/
            // add admin details - interface link
            //SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            ao.updateAdmin(adminId, name, password, contact, joiningDate,address);
            System.out.println("=======================================");
            System.out.println("Admin updated successfully!!! Returning you to main menu");
            TimeUnit.SECONDS.sleep(3);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return;

    }

    /**
     *
     * Function to enable disable payment window by admin
     */

    private void enabledisableFeePaymentWindow(){
        System.out.println("=======================================");
        int a = 0 ;
        System.out.println("Enter Semester No. to enable Payment Window : ");
        String sem = sc.nextLine();

        System.out.println("Enter 0 to disable payment window: ");
        System.out.println("Enter -1 to exit: ");
        a = sc.nextInt();
        sc.nextLine();

        if (a == -1) {
            System.out.println("*********** Exit Successful *************");
        }

        else if (a > 0 && a < 9){
            ao.enableFeePaymentWindow(sem);
            System.out.println("Payment window is now open!!! Returning you to main menu");


        }
        else if (a == 0){
            ao.disableFeePaymentWindow(sem);
            System.out.println("Payment window is now closed!!! Returning you to main menu");
//            ao.disableFeePayment(sem);
        }

        else {
//            try {
//                throw new InvalidSemesterException();
//            }
//            catch (InvalidSemesterException ex) {
//                System.out.println(ex.getMessage());
//            }
            System.out.println("Invalid Input");
            return;

        }

    }

    /**
     *
     * Function to view available course
     */

    private void viewAvailableCourses() {

        ArrayList<Course> alcourse = ao.viewAvailableCourses();
        for (Course val : alcourse) {
            System.out.println("--------------------------");
            System.out.println(val.getCourseCode());
            System.out.println(val.getCourseName());
            System.out.println(val.getInstructorId());
            System.out.println(val.getNumberOfSeats());
        }
        return;


    }

    /**
     *
     * Function to approve student registration
     */

    private void approveStudentRegistration() {
        int semester;
        String studentID;
        // fetch pending students data - dummy for now

        ArrayList<String> pendingstudents = new ArrayList<>();
        pendingstudents = ao.viewPendingStudentApproval();
        int i;
        System.out.println("check");
        for (i = 0; i<pendingstudents.size(); i++) {
            String studID = pendingstudents.get(i);
            System.out.println(studID);
            System.out.println("Please press 1 to approve, else 0 to deny approval: ");
            int stat = sc.nextInt();
            sc.nextLine();
            if(stat == 1){
                ao.approveStudentRegistration(studID);
                pendingstudents.remove(i);
                i--;
            }
            System.out.println("Please press 1 to continue, else press 0 to exit: ");
            stat = sc.nextInt();
            sc.nextLine();
            if(stat == 0){
                break;
            }

        }
        return;

    }

    /**
     *
     * Function to view registered students for a particular course
     */

    private void viewCourseStudentList(){
        System.out.println("Please enter Course ID to view Student List");
        String courseID = sc.nextLine();
        ArrayList<String> course_students = ao.viewCourseStudentList(courseID);
        System.out.println("Here is the list of students registered in course: " + courseID);
        for (String st:course_students){
            System.out.println("Student ID: " + st);
        }
        // fetch student list;
        return;

    }

    /**
     *
     * Function to view Grades of all Registered Students for a particular course
     */


    private void viewCourseGrades(){
        System.out.println("Please enter Course ID to view Grades");
        String courseID = sc.nextLine();
        ArrayList<ArrayList<String>> courseGrades = ao.viewCourseGrades(courseID);
        System.out.println("Here is the grade of students registered in course: " + courseID);
        for (ArrayList<String> i: courseGrades){
            System.out.println("Student ID: " + i.get(0) + "Grade: " + i.get(1));
        }
        return;

    }

    /**
     *
     * Function to Generate Grade Card
     */


    public void generateGradeCard() {
        //System.out.println("Please enter semester for which gradecard needs to be generated");
        //String semester= sc.nextLine();
        ao.generateGradeCard();
        System.out.println("Grade Cards generated successfully, now available for students to view!");
    }
}
