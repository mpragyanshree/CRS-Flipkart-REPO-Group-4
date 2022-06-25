package com.flipkart.constant;

public class SQLQueries {

    //AdminDao Queries
    public static final String PAYMENT_STATUS_STUDENT = "SELECT studentid from payment where studentid = ?";
    public static final String MAKE_PAYMENT = "INSERT INTO payment(studentid, amount, paymenttime, paymentstatus, paymentmode, paymentid, notificationsent, notificationid) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String GET_PASSWORD = "select password from user where userid = ?";
    //public static final String DELETE_COURSE_QUERY = "delete from Course where courseCode = ?";
    // Get all course details.
    public static final String REGISTRATION_GET_ALL_COURSES = "SELECT * FROM coursecatalog";


    public static final String UPDATE_ADMIN = "update user set name=?,password=?,contactno=?,joiningdate=?,useraddress=? where userid=?";
    public static final String UPDATE_PROFESSOR = "update user set name=?,password=?,joiningdate=?,useraddress=?,contactno=? where userid=?";
    public static final String UPDATE_PROFESSOR1 = "update professor set department=?,designation=? where professorid=?";
    public static final String REMOVE_ADMIN = "delete from user where userid = ?";
    public static final String REMOVE_COURSE = "delete from course where coursecode = ?";
    public static final String UPDATE_COURSE = "update course set coursecode=?,coursename=?,instructorid=?,numberofseats=? where courseid=?";
    public static final String UPDATE_REG_STATUS = "update student set registrationstatus=? where studentid=?";
    // view pending students for approval
    public static final String VIEW_PENDING_STUDENT_APPROVAL = "select studentid from student where isapproved = 0 and registrationstatus = 1";//check
    // approve student registration
    public static final String APPROVE_STUDENT_QUERY = "update student set isapproved = 1 where studentId = ?";
    // generate gradecard
    public static final String GENERATE_GRADE_CARD = "update deadline set gradecardgenerationwindow = ? where semester = ?";
    // view available courses
    public static final String ADMIN_VIEW_AVAILABLE_COURSES = "select coursecode, coursename, instructorid, numberofseats from coursecatalog where numberofseats >= 1";
    // view student details for all the courses
    public static final String ADMIN_GET_REGISTERED_STUDENTS = "select studentid from registeredcourses where coursecode = ?";
    // view student wise grades
    public static final String ADMIN_GET_COURSE_GRADES = "select studentid, grade from registeredcourses  where coursecode = ?";
    /*public static final String ADD_USER_QUERY = "insert into User(userId, name, password, role, gender, address, country) values (?, ?, ?, ?, ?, ?, ?)";
    public static final String ADD_PROFESSOR_QUERY = "insert into Professor(userId, department, designation) values (?, ?, ?)";
    public static final String ASSIGN_COURSE_QUERY = "update Course set professorId = ? where courseCode = ?";
    public static final String VIEW_COURSE_QUERY = "select courseCode, courseName, professorId from Course where catalogId = ?";
    public static final String VIEW_PROFESSOR_QUERY = "select userId, name, gender, department, designation, address, country from Professor natural join User";*/

    public static final String ADD_USER="insert into user (userid,name,password,joiningdate,role,useraddress,contactno) values (?,?,?,?,?,?,?)";
    public static final String ADD_STUDENT="insert into student (studentid,department,isapproved,registrationstatus) values (?,?,?,?)";
    public static final String ADD_PROFESSOR="insert into professor (professorid,department,designation) values (?,?,?)";
    public static final String ADD_COURSE = "insert into coursecatalog (coursecode,coursename,numberofseats) values (?,?,?)";
    public static final String ADMIN_REMOVE_PROFESSOR_FROM_PROFESSOR_TABLE = "DELETE FROM professor WHERE professorid=?";
    public static final String REMOVE_PROFESSOR_FROM_CATALOG = "update coursecatalog set instructorid = ? where intructorid = ?";
    public static final String ADMIN_REMOVE_PROFESSOR_FROM_USER_TABLE = "DELETE FROM USER WHERE userid=?";
    public static final String UPDATE_PASSWORD="update user set password=? where userid = ? ";
    /*public static final String VERIFY_CREDENTIALS="select password from user where userId = ?";
    public static final String GET_ROLE="select role from user where userId = ? ";
    public static final String IS_APPROVED="select isApproved from student where studentId = ? ";
    public static final String GET_STUDENT_ID="select studentId from student where userId = ? ";
    public static final String GET_MAX_STUDENT_ID = "SELECT MAX(studentid) FROM student";
    */
    /*

    public static final String GET_PROF_NAME = "select name from user where userId = ?";
    public static final String GET_MAX_COURSE_ID = "SELECT MAX(coursecode) FROM coursecatalog";
    */
    public static final String GET_MAX_TRANSACTION_ID = "SELECT MAX(paymentid) FROM payment";
    public static final String GET_MAX_NOTIFICATION_ID = "SELECT MAX(notificationid) FROM payment";
    // enable disable fee payment
    public static final String CHANGE_PAYMENT_WINDOW_STATUS = "UPDATE deadline SET paymentwindow= ? WHERE semester = ?";
    // Student Queries
    public static final String VIEW_REGISTERED_COURSES=" select * from course inner join registeredcourse on course.courseCode = registeredcourse.courseCode where registeredcourse.studentId = ?";
    public static final String VIEW_AVAILABLE_COURSES=" select * from course where courseCode not in  (select courseCode  from registeredcourse where studentId = ?) and course.isOffered = ? and seats > 0";
    public static final String CHECK_COURSE_AVAILABILITY=" select coursecode from registeredcourse where studentId = ? ";
    public static final String DECREMENT_COURSE_SEATS="update course set seats = seats-1 where courseCode = ? ";
    //public static final String ADD_COURSE="insert into coursecatalog(coursecode,coursename,instructorid,numberofseats) values(?,?,?,?)";
    /*public static final String DROP_COURSE_QUERY = "delete from registeredcourse where courseCode = ? AND studentId = ?;";
    public static final String INCREMENT_SEAT_QUERY  = "update course set seats = seats + 1 where  courseCode = ?;";
    public static final String CALCULATE_FEES  = "select sum(courseFee) from course where courseCode in (select courseCode from registeredcourse where studentId = ?);";
    public static final String VIEW_GRADE = "select course.courseCode,course.courseName,registeredcourse.grade from course inner join registeredcourse on course.courseCode = registeredcourse.courseCode where registeredcourse.studentId = ?;";
    public static final String GET_SEATS = "select seats from course where courseCode = ?;";
    public static final String INSERT_PAYMENT = "insert into payment(studentId,modeofPayment,referenceId,amount) values(?,?,?,?);";
    public static final String INSERT_NOTIFICATION = "insert into notification(studentId,type,referenceId) values(?,?,?);";
    public static final String GET_NOTIFICATION = "select * from notification where referenceId = ?;";*/

    public static final String GET_PROF_ID="select professorId from professor where userId = ?";
    public static final String GET_COURSES="select * from course where professorId=?";
    public static final String GET_COURSES_STUDENT = "select * from registeredcourses where studentid = ?";
    /*

    public static final String GET_REGISTRATION_STATUS=" select isRegistered from student where studentId = ? ";
    public static final String SET_REGISTRATION_STATUS="update student set isRegistered = true  where studentId=?";
    */
    public static final String ADD_GRADE="update registeredcourses set grade=? where coursecode=? and studentid=?";
    public static final String GET_REGISTERED_STUDENTS="select * from registeredcourses natural join student where coursecode=? AND professorid=?";
    public static final String AVAILABLE_COURSES_PROFESSOR="select * from coursecatalog where instructorid is NULL";
    public static final String REGISTER_COURSE_PROFESSOR="update coursecatalog set instructorid =? where coursecode =?";
    public static final String IS_AVAILABLE_COURSE_PROFESSOR="select * from coursecatalog where coursecode = ? and instructorid is NULL";
    public static final String CHECK_REGISTRATION="select * from student natural join registeredcourses  where studentid=? and coursecode=?";
    //public static final String NUMBER_OF_REGISTERED_COURSES=" select studentId from registeredcourse where studentId = ? ";
    //public static final String IS_REGISTERED=" select courseCode from registeredcourse where courseCode=? and studentId=? ";
    public static final String GET_GRADECARD = "select coursecode, grade FROM registeredcourses where studentid=?";
    // check if grade card is available
    public static final String CHECK_GRADECARD = "select * FROM deadline where semester = ?";
    //-----------
    public static final String VERIFY_PAYMENT = "SELECT studentid from payment where studentid = ?";
    public static final String REGISTRATION_FINISH_REG = "SELECT * FROM registeredcourses WHERE studentid=?";
    public static final String REGISTRATION_GET_COURSES = "SELECT * FROM coursecatalog WHERE coursecode = ?";
    public static final String REGISTRATION_DROP_COURSE = "DELETE FROM registeredcourses WHERE studentid = ? AND coursecode = ?";
    public static final String REGISTRATION_COURSE_EXISTS = "SELECT COUNT(1) FROM registeredcourses WHERE studentid = ? AND coursecode = ? ";
    public static final String REGISTRATION_UPDATE_SEATS = "UPDATE coursecatalog SET numberofseats = ? WHERE  coursecode = ? ";
    public static String GET_COURSE_BY_ID = "select * from coursecatalog where coursecode = ?";
    public static String GET_SEM_BY_STUDENT = "SELECT semester_id FROM registered_courses WHERE student_id = ?";
    public static final String SEM_PAYMENT_WINDOW = "SELECT is_open FROM paymentwindow WHERE semester_id = ?";
    public static final String REGISTRATION_ADD_COURSE = "INSERT INTO registeredcourses(studentid,coursecode,isprimary,professorid,grade) VALUES (?,?,?,?,?)";

    //----
    public static final String GET_REGISTERED_COURSES = "select coursecode FROM registeredcourses where studentID = ?";


}