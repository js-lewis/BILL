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
     * @param gradAssistant true if the Student is a graduate assistant. False otherwise.
     */
    public void setGradAssistant(boolean gradAssistant) {
        this.gradAssistant = gradAssistant;
    }

    /**
     *
     * @return
     */
    public boolean isInternational() {
        return international;
    }

    /**
     *
     * @param international
     */
    public void setInternational(boolean international) {
        this.international = international;
    }

    /**
     *
     * @return
     */
    public InternationalStatus getInternationalStatus() {
        return internationalStatus;
    }

    /**
     *
     * @param internationalStatus
     */
    public void setInternationalStatus(InternationalStatus internationalStatus) {
        this.internationalStatus = internationalStatus;
    }

    /**
     *
     * @return
     */
    public boolean isResident() {
        return resident;
    }

    /**
     *
     * @param resident
     */
    public void setResident(boolean resident) {
        this.resident = resident;
    }

    /**
     *
     * @return
     */
    public boolean isActiveDuty() {
        return activeDuty;
    }

    /**
     *
     * @param activeDuty
     */
    public void setActiveDuty(boolean activeDuty) {
        this.activeDuty = activeDuty;
    }

    /**
     *
     * @return
     */
    public boolean isVeteran() {
        return veteran;
    }

    /**
     *
     * @param veteran
     */
    public void setVeteran(boolean veteran) {
        this.veteran = veteran;
    }

    /**
     *
     * @return
     */
    public boolean isFreeTuition() {
        return freeTuition;
    }

    /**
     *
     * @param freeTuition
     */
    public void setFreeTuition(boolean freeTuition) {
        this.freeTuition = freeTuition;
    }

    /**
     *
     * @return
     */
    public Scholarship getScholarship() {
        return scholarship;
    }

    /**
     *
     * @param scholarship
     */
    public void setScholarship(Scholarship scholarship) {
        this.scholarship = scholarship;
    }

    /**
     *
     * @return
     */
    public StudyAbroad getStudyAbroad() {
        return studyAbroad;
    }

    /**
     *
     * @param studyAbroad
     */
    public void setStudyAbroad(StudyAbroad studyAbroad) {
        this.studyAbroad = studyAbroad;
    }

    /**
     *
     * @return
     */
    public boolean isNationalStudentExchange() {
        return nationalStudentExchange;
    }

    /**
     *
     * @param nationalStudentExchange
     */
    public void setNationalStudentExchange(boolean nationalStudentExchange) {
        this.nationalStudentExchange = nationalStudentExchange;
    }

    /**
     *
     * @return
     */
    public boolean isOutsideInsurance() {
        return outsideInsurance;
    }

    /**
     *
     * @param outsideInsurance
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
