package com.flipkart.application;
import java.sql.SQLException;
import java.util.*;

//import com.flipkart.dao.ProfessorDaoOperation;
//import com.flipkart.validator.ProfessorValidator;


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

            this.professorID = getProfessorID(username);

            while(true) {

                System.out.println("\n\n==~~=~~=~~=~~=~Professor Panel~=~~=~~=~~=~~==");
                System.out.println("Choose an option : ");
                System.out.println("1 : View registered students");
                System.out.println("2 : Add Grade");

                System.out.println("3 : Show Registered courses");
                System.out.println("4 : Register for a course");
                System.out.println("5 : Logout");
                System.out.println("=======================================");

                int menuOption = sc.nextInt();
                sc.nextLine();

                switch(menuOption) {
                    case 1 :
                        // View list of enrolled students for a course in a given semester.
                        viewRegisteredStudents();
                        break;

                    case 2 :
                        // Add grade for a student in a course.
                        addGrade();
                        break;

                    case 3:
                        // Professor opt-in for a course.
                        viewRegisteredCourses();
                        break;

                    case 4:
                        registerCourses();

                    case 5 :
                        return ;


                    default:
                        System.out.println("Invalid input");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void viewRegisteredStudents(){

        String courseID;
        int semesterID;

        System.out.println("Enter course ID: ");
        courseID= sc.nextLine();
        System.out.println("Enter semester ID: ");
        semesterID = sc.nextInt();

        try {
            if(true){
                profObj.viewRegisteredStudents();
            }
            else{
//                logger.error("Invalid Semester");
                System.out.println("invalid sem");
            }

        }
        catch(Exception e) {
            System.out.println("course not found");
        }

    }

    private void registerCourses() {
        Integer semesterID;
        String courseID;
        System.out.println("Enter course ID: ");
        courseID = sc.nextLine();
        System.out.println("Enter Semester ID: ");
        semesterID = sc.nextInt();
        sc.nextLine();
        profObj.registerCourse(professorID,courseID, semesterID);
    }

    private void viewRegisteredCourses() {

        Integer semesterID;
        System.out.println("Enter Semester ID: ");
        semesterID = sc.nextInt();

        profObj.viewRegisteredCourses(semesterID);


    }

    private void addGrade(){

        String courseID,grade;
        Integer studentID,semesterID;
        System.out.println("Enter student ID: ");
        studentID = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Semester ID: ");
        semesterID = sc.nextInt();
        System.out.println("Enter course ID: ");
        sc.nextLine();
        courseID = sc.nextLine();
        System.out.println("Enter Grade: ");
        grade = sc.nextLine();
        sc.nextLine();
        if(true) {
            profObj.addGrade(studentID,courseID,grade,semesterID);
        }
        else{
//            logger.error("Invalid Grade!!");
            System.out.println("invalid grade");
        }

    }



    // Get ID of professor by providing username.
    private String getProfessorID(String username){

        ProfessorInterface pf = new ProfessorImplementation();
        try {
            for (Professor s : pf) {
                if(s.getName() == username)
                {
                    return s.getUserId();
                }

            }
            return null;

        } catch (Exception e) {

            System.out.println("faltu error");
        }
        return "";
    }
}
