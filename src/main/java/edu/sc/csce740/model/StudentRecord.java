package edu.sc.csce740.model;

//Import required enumerications
import edu.sc.csce740.defines.College;
import edu.sc.csce740.defines.ClassStatus;
import edu.sc.csce740.defines.InternationalStatus;
import edu.sc.csce740.defines.Scholarship;
import edu.sc.csce740.defines.StudyAbroad;
import edu.sc.csce740.helpers.PrintHelper;

//Import to support an Array List for the courses and transactions.
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class represents a Student Record in the BILL system.
 */
public class StudentRecord {
    /**
     * The Student whose record this is.
     */
    private Student student;

    /**
     * The College the Student attends.
     */
    private College college;

    /**
     * The Term that the Student began taking classes.
     */
    private Term termBegan;

    /**
     * The Term that the Student began their capstone project.
     */
    private Term capstoneEnrolled;

    /**
     * The Student's class status.
     */
    private ClassStatus classStatus;

    /**
     * Is the Student a graduate or teaching assistant?
     */
    private boolean gradAssistant;

    /**
     * Is the Student an international student?
     */
    private boolean international;

    /**
     * The Student's International Status.
     */
    private InternationalStatus internationalStatus;

    /**
     * Is the Student a resident of the state?
     */
    private boolean resident;

    /**
     * Is the Student on active duty?
     */
    private boolean activeDuty;

    /**
     * Is the Student a veteran?
     */
    private boolean veteran;

    /**
     * Does the Student qualify for free tuition?
     */
    private boolean freeTuition;

    /**
     * The Scholarship the Student was been awarded.
     */
    private Scholarship scholarship;

    /**
     * The Student's Study Abroad status.
     */
    private StudyAbroad studyAbroad;

    /**
     * Is the Student a part of the National Student Exchange?
     */
    private boolean nationalStudentExchange;

    /**
     * Does the Student have insurance outside the school?
     */
    private boolean outsideInsurance;

    /**
     * A list of the courses the Student has enrolled in.
     */
    private List<Course> courses;

    /**
     * A list of the transaction on the Student's account.
     */
    private List<Transaction> transactions;

    /**
     * The default constructor for a Student Record.
     */
    public StudentRecord() {
        this.student = new Student();
        this.college = null;
        this.termBegan = new Term();
        this.capstoneEnrolled = new Term();
        this.classStatus = null;
        this.gradAssistant = false;
        this.international = false;
        this.internationalStatus = InternationalStatus.NONE;
        this.resident = false;
        this.activeDuty = false;
        this.veteran = false;
        this.freeTuition = false;
        this.scholarship = Scholarship.NONE;
        this.studyAbroad = StudyAbroad.NONE;
        this.nationalStudentExchange = false;
        this.outsideInsurance = false;
        this.courses = new ArrayList<Course>();
        this.transactions = new ArrayList<Transaction>();
    }

    /**
     * A constructor for a Student Record
     * @param student                   the Student this record is for.
     * @param college                   the College the Student attends.
     * @param termBegan                 the Date the Student enrolled.
     * @param capstoneEnrolled          the Date the Student started their capstone project.
     * @param classStatus               the Class Status of the Student.
     * @param gradAssistant             is the Student a graudate assistant?
     * @param international             is the Student an international student?
     * @param internationalStatus       the Student's international status.
     * @param resident                  is the Student a resident of SC?
     * @param activeDuty                is the Student on active duty?
     * @param veteran                   is the Student a veteran?
     * @param freeTuition               does the Student receive free tuition?
     * @param scholarship               the Scholarship the Student is receiving.
     * @param studyAbroad               the Student's Study Abroad status.
     * @param nationalStudentExchange   it the Student part of the national student exchange?
     * @param outsideInsurance          does the Student have insurance outside the school?
     * @param courses                   courses the Student has enrolled in
     * @param transactions              transactions made on the Student's account.
     */
    public StudentRecord(Student student, College college, Term termBegan, Term capstoneEnrolled, ClassStatus classStatus, boolean gradAssistant, boolean international, InternationalStatus internationalStatus, boolean resident, boolean activeDuty, boolean veteran, boolean freeTuition, Scholarship scholarship, StudyAbroad studyAbroad, boolean nationalStudentExchange, boolean outsideInsurance, Course[] courses, Transaction[] transactions) {
        this.student = student;
        this.college = college;
        this.termBegan = termBegan;
        this.capstoneEnrolled = capstoneEnrolled;
        this.classStatus = classStatus;
        this.gradAssistant = gradAssistant;
        this.international = international;
        this.internationalStatus = internationalStatus;
        this.resident = resident;
        this.activeDuty = activeDuty;
        this.veteran = veteran;
        this.freeTuition = freeTuition;
        this.scholarship = scholarship;
        this.studyAbroad = studyAbroad;
        this.nationalStudentExchange = nationalStudentExchange;
        this.outsideInsurance = outsideInsurance;
        this.courses = new ArrayList<Course>(Arrays.asList(courses));
        this.transactions = new ArrayList<Transaction>(Arrays.asList(transactions));
    }

    /**
     * Gets the Student whose records this is.
     * @return a Student object whose record this is.
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Sets the Student associated with Student Record.
     * @param student   the Student object associated with this record.
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * Gets the College the Student attends.
     * @return  the college the Student is enrolled in.
     */
    public College getCollege() {
        return college;
    }

    /**
     * Sets the College the Student attends.
     * @param college   a College the Student attends.
     */
    public void setCollege(College college) {
        this.college = college;
    }

    /**
     * Gets the Date that the Student enrolled in classes.
     * @return the Date the Student started taking classes.
     */
    public Term getTermBegan() {
        return termBegan;
    }

    /**
     * Sets the Date that the Student enrolled in classes.
     * @param termBegan the Date the Student started taking classes.
     */
    public void setTermBegan(Term termBegan) {
        this.termBegan = termBegan;
    }

    /**
     * Gets the Date that the Student started their capstone project.
     * @return the Date the Student started their capstone project.
     */
    public Term getCapstoneEnrolled() {
        return capstoneEnrolled;
    }

    /**
     * Sets the Date that the Student started their capstone project.
     * @param capstoneEnrolled  the Date the Student started their capstone project.
     */
    public void setCapstoneEnrolled(Term capstoneEnrolled) {
        this.capstoneEnrolled = capstoneEnrolled;
    }

    /**
     * Gets the Class Status of the Student.
     * @return the Class Status of the Student.
     */
    public ClassStatus getClassStatus() {
        return classStatus;
    }

    /**
     * Sets the Class Status of the Student.
     * @param classStatus   the new Class Status of the Student.
     */
    public void setClassStatus(ClassStatus classStatus) {
        this.classStatus = classStatus;
    }

    /**
     * Indicates if the Student is a graduate assistant.
     * @return  true is the Student is a graduate assistant. False otherwise.
     */
    public boolean isGradAssistant() {
        return gradAssistant;
    }

    /**
     * Sets the Student's graduate assistant status.
     * @param gradAssistant set to true if the Student is a graduate assistant. False otherwise.
     */
    public void setGradAssistant(boolean gradAssistant) {
        this.gradAssistant = gradAssistant;
    }

    /**
     * Indicates if the Student is an international student.
     * @return  true if the Student is an international student. False otherwise.
     */
    public boolean isInternational() {
        return international;
    }

    /**
     * Sets the Student's international status.
     * @param international set to true if the Student is an international student. False otherwise.
     */
    public void setInternational(boolean international) {
        this.international = international;
    }

    /**
     * Gets an international student's status. Only applicable if isInternational is true.
     * @return  the status of an international student.
     */
    public InternationalStatus getInternationalStatus() {
        return internationalStatus;
    }

    /**
     * Sets an international student's status.
     * @param internationalStatus   the new status of an international student.
     */
    public void setInternationalStatus(InternationalStatus internationalStatus) {
        this.internationalStatus = internationalStatus;
    }

    /**
     * Indicates if the Student is an in-state resident.
     * @return  true if the Student lives in-state. False if the Student lives out-of-state.
     */
    public boolean isResident() {
        return resident;
    }

    /**
     * Sets the Student's resident status.
     * @param resident  set to true if the Student is in-state. False if the Student is out-of-state.
     */
    public void setResident(boolean resident) {
        this.resident = resident;
    }

    /**
     * Indicates if the Student is active duty military.
     * @return  true if the Student is on active duty. False if the Student is not.
     */
    public boolean isActiveDuty() {
        return activeDuty;
    }

    /**
     * Sets the Student's active duty flag.
     * @param activeDuty    set to true if the Student is on active duty. False otherwise.
     */
    public void setActiveDuty(boolean activeDuty) {
        this.activeDuty = activeDuty;
    }

    /**
     * Indicates if the Student is a military veteran.
     * @return  true if the Student is a military veteran. False if the Student is not.
     */
    public boolean isVeteran() {
        return veteran;
    }

    /**
     * Sets the Student's veteran flag.
     * @param veteran   set to true if the Student is a military veteran. False otherwise.
     */
    public void setVeteran(boolean veteran) {
        this.veteran = veteran;
    }

    /**
     * Indicated the Student receives free tuition.
     * @return  true if the Student receives free tuition. False otherwise.
     */
    public boolean isFreeTuition() {
        return freeTuition;
    }

    /**
     * Sets the Student's free tuition flag.
     * @param freeTuition   set to true if the Student receives free tuition or does not need to pay tuition.
     */
    public void setFreeTuition(boolean freeTuition) {
        this.freeTuition = freeTuition;
    }

    /**
     * Gets the Scholarship that the student has been awarded.
     * @return  the Scholarship that the Student has been awarded.
     */
    public Scholarship getScholarship() {
        return scholarship;
    }

    /**
     * Sets the Scholarship that the student has been awarded.
     * @param scholarship   that the student has earned.
     */
    public void setScholarship(Scholarship scholarship) {
        this.scholarship = scholarship;
    }

    /**
     * Gets the Study Abroad program the Student is in if any.
     * @return the study abroad program the Student is participating in.
     */
    public StudyAbroad getStudyAbroad() {
        return studyAbroad;
    }

    /**
     * Sets the Study Abroad program the Student is participating in.
     * @param studyAbroad   set to the Student's Study Abroad program.
     */
    public void setStudyAbroad(StudyAbroad studyAbroad) {
        this.studyAbroad = studyAbroad;
    }

    /**
     * Indicates the Student is part of the national student exchange.
     * @return true if the Student is part of the national student exchange. False otherwise.
     */
    public boolean isNationalStudentExchange() {
        return nationalStudentExchange;
    }

    /**
     * Sets the National Student Exchange flag for a student.
     * @param nationalStudentExchange   set to true if the Student is part of the National Student Exchange.
     */
    public void setNationalStudentExchange(boolean nationalStudentExchange) {
        this.nationalStudentExchange = nationalStudentExchange;
    }

    /**
     * Indicates a Student has outside Insurance.
     * @return  true if the Student has insurance outside the school.
     */
    public boolean isOutsideInsurance() {
        return outsideInsurance;
    }

    /**
     * Sets the Student's insurance flag.
     * @param outsideInsurance  set to true if the Student has insurance outside the school. False if they need
     *                          insurance through the school.
     */
    public void setOutsideInsurance(boolean outsideInsurance) {
        this.outsideInsurance = outsideInsurance;
    }

    /**
     * Gets an array of all of the courses associated with a Student's account.
     * @return an array of Courses the Student has enrolled in.
     */
    public Course[] getCourses() {
        return courses.toArray(new Course[courses.size()]);
    }

    /**
     * Sets the list of courses the Student has enrolled in.
     * @param courses   an array of Courses the Student has enrolled in.
     */
    public void setCourses(Course[] courses) {
        this.courses = new ArrayList<Course>(Arrays.asList(courses));
    }

    /**
     * Get a list of all of the Courses a Student's has enrolled in.
     * @return a list of Courses performed on the Student's account.
     */
    public List<Course> getCourseList() {
        return courses;
    }

    /**
     * Add a course to the current Courses array.
     * @param course    A new Course to add to the Student's Record.
     */
    public void addCourse(Course course) {
        this.courses.add(course);
    }

    /**
     * Gets an array of all of the transactions associated with a Student's account.
     * @return an array of Transactions performed on the Student's account.
     */
    public Transaction[] getTransactions() {
        return transactions.toArray(new Transaction[transactions.size()]);
    }

    /**
     * Sets the list of transactions associated with a Student's account.
     * @param transactions  an array of Transactions performed on the Student's account.
     */
    public void setTransactions(Transaction[] transactions) {
        this.transactions = new ArrayList<Transaction>(Arrays.asList(transactions));
    }

    /**
     * Get a list of all of the transactions associated with a Student's account.
     * @return a list of Transactions performed on the Student's account.
     */
    public List<Transaction> getTransactionList() {
        return transactions;
    }

    /**
     * Add a transaction to the current transaction array.
     * @param transaction   A new transaction to add to the bill.
     */
    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    /**
     * A String representation of a Student Record object.
     * @return a String with the Student Record data.
     */
    @Override
    public String toString() {
        return "student: \n" + getStudent() + "\n" +
                "college: " + getCollege() + "\n" +
                "termBegan: \n" + getTermBegan() + "\n" +
                "capstoneEnrolled: \n" + getCapstoneEnrolled() + "\n" +
                "classStatus: " + getClassStatus() + "\n" +
                "gradAssistant: " + isGradAssistant() + "\n" +
                "international: " + isInternational() + "\n" +
                "internationalStatus: " + getInternationalStatus() + "\n" +
                "resident: " + isResident() + "\n" +
                "activeDuty: " + isActiveDuty() + "\n" +
                "veteran: " + isVeteran() + "\n" +
                "freeTuition: " + isFreeTuition() + "\n" +
                "scholarship: " + getScholarship() + "\n" +
                "studyAbroad: " + getStudyAbroad() + "\n" +
                "nationalStudentExchange: " + isNationalStudentExchange() + "\n" +
                "outsideInsurance: " + isOutsideInsurance() + "\n" +
                "courses: \n " + PrintHelper.courseListToString(getCourses()) + "\n" +
                "transactions: \n" + PrintHelper.transactionListToString(getTransactions()) + "\n";
    }
}
