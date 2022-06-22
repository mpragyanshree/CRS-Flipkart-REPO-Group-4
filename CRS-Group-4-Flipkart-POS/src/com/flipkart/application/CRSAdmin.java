package com.flipkart.application;

import java.util.*;
import java.util.concurrent.TimeUnit;
import com.flipkart.bean.Grade;
import com.flipkart.service.AdminImplementation;
public class CRSAdmin {
    private Scanner sc = new Scanner(System.in);
    AdminImplementation aro = new AdminImplementation();
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
    private void addCourse() {

        try{
            String course_name, courseID, courseInstructor;
            int offeredSemester;

            System.out.println("=======================================");
            System.out.println("Kindly enter course details below");
            System.out.println("Course Name: ");
            course_name = sc.nextLine();
            System.out.println("Course ID: ");
            courseID = sc.nextLine();
            System.out.println("Semester: ");
            offeredSemester = sc.nextInt();
            sc.nextLine();
            System.out.println("Instructor for the course: ");
            courseInstructor = sc.nextLine();

//            ao.addCourse(course_name, courseID, offeredSemester, courseInstructor);
            System.out.println("Course added successfully!!! Returning you to main menu");
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void removeCourse() {
        String courseID;
        System.out.println("=======================================");
        System.out.println("Enter course ID for course removal: ");
        courseID = sc.nextLine();
        System.out.println("Course removed successfully!!! Returning you to main menu");


        try {
            TimeUnit.SECONDS.sleep(3);
//            ao.removeCourse(courseID);
            // remove course
        } catch (Exception e) {
//            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }
    private void updateCourse() {

        try{
            String course_name, courseID, courseInstructor;
            int offeredSemester;

            System.out.println("=======================================");
            System.out.println("Kindly enter the course ID for updation");

            System.out.print("Course ID: ");
            courseID = sc.nextLine();
            // search course based on course ID
            System.out.println("Please enter the updated details below");
            System.out.print("Course Name: ");
            course_name = sc.nextLine();
            System.out.print("Semester: ");
            offeredSemester = sc.nextInt();
            sc.nextLine();
            System.out.print("Instructor for the course: ");
            courseInstructor = sc.nextLine();
            // update course details here

//            ao.updateCourse(course_name, courseID, offeredSemester, courseInstructor);
            System.out.println("Course updated successfully!!! Returning you to main menu");
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
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
    private void addProfessor(){

        try {

            String username, name, password, department, designation, contact;
            Integer joiningYear;

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
            System.out.println("Joining Year");
            joiningYear = sc.nextInt();
            sc.nextLine();

            System.out.println("Professor added successfully!!! Returning you to main menu");
            TimeUnit.SECONDS.sleep(3);
            System.out.println("=======================================");


            // add prof details - interface link


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void removeProfessor() {
        System.out.println("Enter Instructor ID :");
        String professorID = sc.nextLine();



        try {
//            ao.removeProfessor(professorID);
//            remove prof based on profID
            System.out.println("Professor removed successfully!!! Returning you to main menu");
            TimeUnit.SECONDS.sleep(3);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void updateProfessor(){

        try {

            String username, name, password, department, designation, contact, profID;
            Integer joiningYear;

            System.out.println("=======================================");
            System.out.println("Kindly enter professor ID for updating details");
            System.out.println("---------------------------------------");
            profID = sc.nextLine();
            //check prof id and fetch details, print details alongside.
            System.out.println("For updating details, please enter new details. To keep the same information, please press enter at respective fields.");
            System.out.println("User Name: ");
            username = sc.nextLine();
            if (username.isEmpty()){
                // fetch existing username
            }
            System.out.println("Password: ");
            password = sc.nextLine();
            if (password.isEmpty()){
                // fetch existing password
            }
            System.out.println("Name: ");
            name = sc.nextLine();
            if (name.isEmpty()){
                // fetch existing name
            }
            System.out.println("Department: ");
            department = sc.nextLine();
            if (department.isEmpty()){
                // fetch existing department
            }
            System.out.println("Designation: ");
            designation = sc.nextLine();
            if (designation.isEmpty()){
                // fetch existing designation
            }
            System.out.println("Contact Number");
            contact = sc.nextLine();
            if (contact.isEmpty()){
                // fetch existing contact
            }
            System.out.println("Joining Year (To keep the exisitng joining year, please enter 0");
            joiningYear = sc.nextInt();
            sc.nextLine();
            if (joiningYear == 0){
                // fetch existing username
            }
            System.out.println("Professor updated successfully!!! Returning you to main menu");
            TimeUnit.SECONDS.sleep(3);
            System.out.println("=======================================");


            // add prof details - interface link


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
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
    private void addAdmin(){

        try {

            String username, name, password, contact;
            Integer joiningYear;

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
            System.out.println("Joining Year");
            joiningYear = sc.nextInt();
            sc.nextLine();
            System.out.println("Admin added successfully!!! Returning you to main menu");
            TimeUnit.SECONDS.sleep(3);
            System.out.println("=======================================");


            // add admin details - interface link


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void removeAdmin() {
        System.out.println("Enter Admin's user ID :");
        String adminID = sc.nextLine();
//        sc.nextLine();

        try {
//            ao.removeAdmin(adminID);
//            remove admin based on userID
            System.out.println("Admin removed successfully!!! Returning you to main menu");
            TimeUnit.SECONDS.sleep(3);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void updateAdmin(){

        try {

            String username, name, password, department, designation, contact, profID;
            Integer joiningYear;

            System.out.println("=======================================");
            System.out.println("Kindly enter admin ID for updating details");
            System.out.println("---------------------------------------");
            profID = sc.nextLine();
            //check prof id and fetch details, print details alongside.
            System.out.println("For updating details, please enter new details. To keep the same information, please press enter at respective fields.");
            System.out.println("User Name: ");
            username = sc.nextLine();
            if (username.isEmpty()){
                // fetch existing username
            }
            System.out.println("Password: ");
            password = sc.nextLine();
            if (password.isEmpty()){
                // fetch existing password
            }
            System.out.println("Name: ");
            name = sc.nextLine();
            if (name.isEmpty()) {
                // fetch existing name
            }
            System.out.println("Contact Number");
            contact = sc.nextLine();
            if (contact.isEmpty()){
                // fetch existing contact
            }
            System.out.println("Joining Year (To keep the exisitng joining year, please enter 0");
            joiningYear = sc.nextInt();
            sc.nextLine();
            if (joiningYear == 0){
                // fetch existing username
            }
            System.out.println("=======================================");
            System.out.println("Admin updated successfully!!! Returning you to main menu");
            TimeUnit.SECONDS.sleep(3);

            // add admin details - interface link


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void enabledisableFeePaymentWindow(){
        System.out.println("=======================================");
        int a = 0 ;
        System.out.println("Enter Semester No. to enable Payment Window : ");
        int sem = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter 0 to disable payment window: ");
        System.out.println("Enter -1 to exit: ");
        a = sc.nextInt();
        sc.nextLine();

        if (a == -1) {
            System.out.println("*********** Exit Successful *************");
        }

        else if (a > 0 && a < 9){
            System.out.println("Payment window is now open!!! Returning you to main menu");

//            ao.enableFeePayment(a);
        }
        else if (a == 0){
            System.out.println("Payment window is now closed!!! Returning you to main menu");
//            ao.disableFeePayment(a);
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
    private void viewAvailableCourses() {
        // dummy setup
        ArrayList<String> alcourse = new ArrayList<>();
        alcourse.add("OS");
        alcourse.add("DBMS");
        alcourse.add("OOPS");
        for (String val : alcourse) {
            System.out.println(val);
        }


    }
    private void approveStudentRegistration() {
        int studentID,semester;
        // fetch pending students data - dummy for now

        ArrayList<String> pendingstudents = new ArrayList<>();

        pendingstudents.add("1801EE62");
        pendingstudents.add("1801EE63");
        pendingstudents.add("1801CS61");
        int i;
        for (i = 0; i<pendingstudents.size(); i++) {
            System.out.println(pendingstudents.get(i));
            System.out.println("Please press 1 to approve, else 0 to deny approval: ");
            int stat = sc.nextInt();
            sc.nextLine();
            if(stat == 1){
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
        // update student approval
        return;



    }
    private void viewCourseStudentList(){
        System.out.println("Please enter Course ID to view Student List");
        String courseID = sc.nextLine();
        // fetch student list;


    }
    private void viewCourseGrades(){
        System.out.println("Please enter Course ID to view Grades");
        String courseID = sc.nextLine();
        HashMap<String, Grade> viewCourseGrades = aro.viewCourseGrades(courseID);
        Iterator itr = viewCourseGrades.entrySet().iterator();
        System.out.println("Viewing Grade for course: " +courseID);
        System.out.println("Student ID        Grade ");
        while(itr.hasNext()){
            Map.Entry ele= (Map.Entry)itr.next();
            Grade individualGrade = (Grade) ele.getValue();
            String studID = (String) ele.getKey();
            System.out.println(studID + "        " +individualGrade.getGrade());
        }
        System.out.println("Enter any key to exit: ");
        String flag = sc.nextLine();


    }

    private void generateGradeCard() {


    }
}
