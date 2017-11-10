package edu.sc.csce740.model;

//Import required enumerications
import edu.sc.csce740.defines.College;
import edu.sc.csce740.defines.ClassStatus;
import edu.sc.csce740.defines.InternationalStatus;
import edu.sc.csce740.defines.Scholarship;
import edu.sc.csce740.defines.StudyAbroad;
import edu.sc.csce740.helpers.PrintHelper;

/**
 * This class represents a Student Record in the BILL system.
 */
public class StudentRecord {
    private Student student;
    private College college;
    private Term termBegan;
    private Term capstoneEnrolled;
    private ClassStatus classStatus;
    private boolean gradAssistant;
    private boolean international;
    private InternationalStatus internationalStatus;
    private boolean resident;
    private boolean activeDuty;
    private boolean veteran;
    private boolean freeTuition;
    private Scholarship scholarship;
    private StudyAbroad studyAbroad;
    private boolean nationalStudentExchange;
    private boolean outsideInsurance;
    private Course[] courses;
    private Transaction[] transactions;

    public StudentRecord() {
    }

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
        this.courses = courses;
        this.transactions = transactions;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    public Term getTermBegan() {
        return termBegan;
    }

    public void setTermBegan(Term termBegan) {
        this.termBegan = termBegan;
    }

    public Term getCapstoneEnrolled() {
        return capstoneEnrolled;
    }

    public void setCapstoneEnrolled(Term capstoneEnrolled) {
        this.capstoneEnrolled = capstoneEnrolled;
    }

    public ClassStatus getClassStatus() {
        return classStatus;
    }

    public void setClassStatus(ClassStatus classStatus) {
        this.classStatus = classStatus;
    }

    public boolean isGradAssistant() {
        return gradAssistant;
    }

    public void setGradAssistant(boolean gradAssistant) {
        this.gradAssistant = gradAssistant;
    }

    public boolean isInternational() {
        return international;
    }

    public void setInternational(boolean international) {
        this.international = international;
    }

    public InternationalStatus getInternationalStatus() {
        return internationalStatus;
    }

    public void setInternationalStatus(InternationalStatus internationalStatus) {
        this.internationalStatus = internationalStatus;
    }

    public boolean isResident() {
        return resident;
    }

    public void setResident(boolean resident) {
        this.resident = resident;
    }

    public boolean isActiveDuty() {
        return activeDuty;
    }

    public void setActiveDuty(boolean activeDuty) {
        this.activeDuty = activeDuty;
    }

    public boolean isVeteran() {
        return veteran;
    }

    public void setVeteran(boolean veteran) {
        this.veteran = veteran;
    }

    public boolean isFreeTuition() {
        return freeTuition;
    }

    public void setFreeTuition(boolean freeTuition) {
        this.freeTuition = freeTuition;
    }

    public Scholarship getScholarship() {
        return scholarship;
    }

    public void setScholarship(Scholarship scholarship) {
        this.scholarship = scholarship;
    }

    public StudyAbroad getStudyAbroad() {
        return studyAbroad;
    }

    public void setStudyAbroad(StudyAbroad studyAbroad) {
        this.studyAbroad = studyAbroad;
    }

    public boolean isNationalStudentExchange() {
        return nationalStudentExchange;
    }

    public void setNationalStudentExchange(boolean nationalStudentExchange) {
        this.nationalStudentExchange = nationalStudentExchange;
    }

    public boolean isOutsideInsurance() {
        return outsideInsurance;
    }

    public void setOutsideInsurance(boolean outsideInsurance) {
        this.outsideInsurance = outsideInsurance;
    }

    public Course[] getCourses() {
        return courses;
    }

    public void setCourses(Course[] courses) {
        this.courses = courses;
    }

    public Transaction[] getTransactions() {
        return transactions;
    }

    public void setTransactions(Transaction[] transactions) {
        this.transactions = transactions;
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
