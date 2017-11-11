package edu.sc.csce740.defines;

public class Constants {
    //File Constants
    public static final String USERS_BASE_FILE = "resources/base/users.json";
    public static final String RECORDS_BASE_FILE = "resources/base/students.json";
    public static final String NO_FILE = "resources/base/nonexistent.junk.json";
    public static final String EMPTY_FILE = "resources/base/empty.json";
    public static final String USERS_MOD_FILE = "resources/mod/users.json";
    public static final String RECORDS_MOD_FILE = "resources/mod/students.json";

    //Counts
    public static final int BASE_NUMBER_OF_USERS = 7;
    public static final int BASE_NUMBER_OF_RECORDS = 7;
    public static final int BASE_NUMBER_AAS = 3;
    public static final int BASE_NUMBER_EAC = 4;
    public static final int BASE_NUMBER_GRAD = 3;

    //Users
    public static final String STUDENT_AAS_UNDER = "kmurph";
    public static final String STUDENT_EAC_UNDER = "ggay";
    public static final String STUDENT_AAS_GRAD = "mhunt";
    public static final String STUDENT_EAC_GRAD = "jtrahan";
    public static final String ADMIN_GRAD = "mmatthews";
    public static final String ADMIN_AAS = "jsmith";
    public static final String ADMIN_EAC = "rbob";

    //New User
    public static final String NEW_USER_ID = "ahein";
    public static final String NEW_USER_FIRST_NAME = "Aaron";
    public static final String NEW_USER_LAST_NAME = "Hein";
    public static final Role NEW_USER_ROLE = Role.STUDENT;
    public static final College NEW_USER_COLLEGE = College.ENGINEERING_AND_COMPUTING;

    //Students
    public static final String EAC_FRESHMAN = "ggay";   //full time
    public static final String AAS_SOPHOMORE = "kcook"; //full time
    public static final String AAS_JUNIOR = "kmurph";   //full time
    public static final String EAC_SENIOR = "jwest";    //part time
    public static final String EAC_MASTERS = "jtrahan"; //part time
    public static final String AAS_PHD = "mhunt";       //full time
    public static final String EAC_PHD = "eray";        //full time

    //New Student
    public static final String NEW_STUDENT_ID = "shein";
    public static final String NEW_STUDENT_FIRST_NAME = "Sarah";
    public static final String NEW_STUDENT_LAST_NAME = "Hein";
}