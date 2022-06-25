package com.flipkart.application;
import java.sql.SQLException;
import java.util.*;

//import com.flipkart.dao.ProfessorDaoOperation;
//import com.flipkart.validator.ProfessorValidator;


import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.GradeNotAddedException;
import com.flipkart.exception.ProfessorCourseRegistrationException;
import com.flipkart.service.ProfessorImplementation;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import com.flipkart.service.ProfessorInterface;
import com.flipkart.bean.Professor;
import com.sun.javaws.progress.PreloaderDelegate;

//import com.flipkart.exception.CourseNotFoundException;
//import com.flipkart.exception.GradeNotAddedException;
//import com.flipkart.exception.ProfessorNotRegisteredException;
//import com.flipkart.business.ProfessorInterface;
//import com.flipkart.business.ProfessorOperation;

public class CRSProfessor {

    public CRSProfessor() {


    }






    private Scanner sc = new Scanner(System.in);
    ProfessorInterface profObj = new ProfessorImplementation();
    private String professorID;

    public static void main(String[] args) {
        CRSProfessor test = new CRSProfessor();
        test.createProfessorMenu("testing");
    }

    // Home page for a Professor Login.
    public void createProfessorMenu(String username) {
        try {

            professorID = username;

            while(true) {

                System.out.println("\n\n==~~=~~=~~=~~=~Professor Panel~=~~=~~=~~=~~==");
                System.out.println("Choose an option : ");
                System.out.println("1 : View registered students");
                System.out.println("2 : Add Grade");
                System.out.println("3 : Show available courses to choose");
                System.out.println("4 : Register for a course");
                System.out.println("5 : Logout");
                System.out.println("=======================================");

                int menuOption = sc.nextInt();
                sc.nextLine();

                switch(menuOption) {
                    case 1 :
                        // View list of enrolled students for a course in a given semester.
                        viewRegisteredStudents(username);
                        break;

                    case 2 :
                        // Add grade for a student in a course.
                        addGrade(username);
                        break;

                    case 3:
                        // Professor opt-in for a course.
                        viewRegisteredCourses(username);
                        break;

                    case 4:
                        registerCourses(username);
                        break;

                    case 5 :
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
    //Function to view registered students
    private void viewRegisteredStudents(String username) throws CourseNotFoundException {

        String courseID;

        System.out.println("Enter course ID: ");
        courseID= sc.nextLine();

        try {
            if(true){ // validation pending
                profObj.viewRegisteredStudents(professorID,courseID);
                createProfessorMenu(username);
            }
            else{
//                logger.error("Invalid Semester");
                System.out.println("invalid course ID");
            }

        }
        catch(Exception e) {
            throw new CourseNotFoundException(courseID);
        }

    }
    //Function to add Grades
    private void addGrade(String username) throws CourseNotFoundException, GradeNotAddedException {


            String courseID,grade;
            String studentID;
            System.out.println("Enter student ID: ");
            studentID = sc.nextLine();
            System.out.println("Enter course ID: ");
            courseID = sc.nextLine();
            System.out.println("Enter Grade: ");
            grade = sc.nextLine();

            try{
            if(true) { // validation pending
                profObj.addGrade(studentID,courseID,grade);
                createProfessorMenu(username);

            }
            else{
//            logger.error("Invalid Grade!!");
                System.out.println("invalid grade");
            }
            }catch(Exception e) {
                throw new GradeNotAddedException(studentID);
            }

    }

    //Function to view all registered courses
    private void viewRegisteredCourses(String username ) {


//        System.out.println("Enter Semester ID: ");
//        semesterID = sc.nextInt();

        profObj.viewRegisteredCourses();
        createProfessorMenu(username);


    }

    //Function for Professor to register for courses to teach
    private void registerCourses(String username) throws ProfessorCourseRegistrationException {
        String courseID,professorID;

        professorID = username;
        System.out.println("Enter course ID: ");
        courseID = sc.nextLine();

        try {
            profObj.registerCourse(professorID, courseID);

        }catch (Exception e)
        {
            throw new ProfessorCourseRegistrationException();
        }
    }

    // Get ID of professor by providing username.
    /*private String getProfessorID(String username){


        ProfessorInterface po = ProfessorImplementation.getInstance();
        try {
            return po.getProfessorID(username);
        } catch (Exception e) {

            System.out.println("error");
        }
        return "";
    }*/
}
