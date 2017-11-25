package edu.sc.csce740.helpers;

//GSON imports

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

//Enumerations
import edu.sc.csce740.defines.*;

//Import the new Exception
import edu.sc.csce740.exceptions.BillGenerationException;
import edu.sc.csce740.exceptions.BillRetrievalException;

//Model imports
import edu.sc.csce740.model.Bill;
import edu.sc.csce740.model.Course;
import edu.sc.csce740.model.Date;
import edu.sc.csce740.model.Fee;
import edu.sc.csce740.model.Fees;
import edu.sc.csce740.model.StudentRecord;
import edu.sc.csce740.model.Transaction;

//Apache IO import
import org.apache.commons.io.FileUtils;

//General Java imports
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;

//TODO: Reset files in mod directory before tests

/**
 * This class contains the business logic for generating a Bill in the BILL system. This includes business logic
 * pertaining to when a fee is charged.
 */
public class BillHelper {
    private static String feeFile = "resources/data/fees.json";
    private static int OVERTIME_STUDENT_HOURS = 17;
    private static int GRAD_FULL_TIME_STUDENT_HOURS = 6;
    private static int UNDERGRAD_FULL_TIME_STUDENT_HOURS = 12;
    private boolean feesLoaded;
    private Fees fees;
    private boolean isFullTime;
    private boolean isGradStudent;
    private int onlineHours;
    private int totalHours;

    private static short[] AE_LAB_COURSES = {101, 260, 520, 530, 535, 540, 541, 555, 560, 595};

    private static short[] AH_LAB_COURSES = {105, 106, 313, 315, 320, 321, 325,
            326, 327, 330, 335, 337, 340, 341, 342, 345, 346, 350, 365, 366, 370, 390,
            399, 498, 499, 501, 511, 514, 519, 520, 521, 522, 523, 524, 525, 526, 527,
            529, 534, 535, 536, 537, 539, 540, 542, 543, 550, 557, 560, 561, 562, 566,
            569, 590, 720, 725, 730, 735, 737, 769, 790};

    private static short[] DANCE_LAB_COURSES = {102, 112, 160, 170, 171, 177,
            178, 202, 203, 204, 212, 278, 302, 303, 307, 312, 360, 378, 385, 402, 403,
            407, 412, 440, 460, 577};

    private static short[] FIELD_LAB_COURSES = {735, 750}; // GEOL

    private static String[] LANGUAGE_COURSES = {"ARAB", "CHIN", "FREN", "GERM",
            "ITAL", "JAPA", "LATN", "PORT", "RUSS", "SPAN"};

    private static short[] MARINE_SCIENCE = {460};

    private static String[] MATH_LAB = {"MATH 141", "MATH 142", "MATH 526", "STAT 201"};

    private static String[] MEDIA_COURSES = {"MART"};

    private static String[] STUDIO_LAB = {"ARTS"};

    public BillHelper() {
        fees = new Fees();
        try {
            readFees();
            feesLoaded = true;
        } catch (Exception e) {
            feesLoaded = false;
        }
        isFullTime = false;
        isGradStudent = false;
        onlineHours = 0;
        totalHours = 0;
    }

    protected void reset() {
        isFullTime = false;
        isGradStudent = false;
        onlineHours = 0;
        totalHours = 0;
    }

    private void readFees()
            throws IOException {
        //Gets the object that the JSON should match.
        Type feeType = new TypeToken<Fees>() {
        }.getType();
        //Create the GSON object.
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        //Setup the File for IO.
        File file;
        file = new File(feeFile);

        //Read the JSON file into a string and parse the JSON from that String into a Fees object
        fees = gson.fromJson(FileUtils.readFileToString(file, "UTF-8"), feeType);

        //If fees is null here, the file was empty. Create an empty Fee instead.
        if (fees == null) {
            fees = new Fees();
        }
    }

    protected Fees getFees() {
        return fees;
    }

    public Bill retrieveBill(StudentRecord student, Date startDate, Date endDate)
            throws BillRetrievalException {
        Bill returnBill = new Bill(student.getStudent(), student.getCollege(), student.getClassStatus(),
                0.00, new ArrayList<Transaction>());

        if (!feesLoaded || student == null) {
            throw new BillRetrievalException();
        }

        //TODO: Spin through charges and actually pull the correct ones out ...

        return returnBill;
    }

    public Bill generateBill(StudentRecord student)
            throws BillGenerationException {
        Bill returnBill = new Bill(student.getStudent(), student.getCollege(), student.getClassStatus(),
                0.00, new ArrayList<Transaction>());

        if (!feesLoaded || student == null) {
            throw new BillGenerationException();
        }

        //check courses and compute the total number of hours a student it taking.
        processCourses(student);

        //process all of the tuition charges
        returnBill = generateTuitionCharges(student, returnBill);
        returnBill = generateGeneralCharges(student, returnBill);
        returnBill = generateCollegeCharges(student, returnBill);

        //Per Dr. Gay, Bill Generation should not add charges to the Student Record
        //TODO: Remove print
        System.out.println(returnBill);
        reset();
        return returnBill;
    }

    protected Bill generateTuitionCharges(StudentRecord student, Bill bill)
            throws BillGenerationException {
        List<Fee> tuitionFees = new ArrayList<>();

        switch (student.getClassStatus()) {
            case FRESHMAN:
            case SOPHOMORE:
            case JUNIOR:
            case SENIOR:
                //if the student is active duty, then use the active duty list
                if (student.isActiveDuty()) {
                    tuitionFees = fees.getActiveDutyTuition();
                } else {
                    //otherwise check for residence and use that list
                    if (student.isResident() || student.isVeteran()) {
                        tuitionFees = fees.getResidentUnderGradTuition();
                    } else {
                        tuitionFees = fees.getNonResidentUnderGradTuition();
                    }
                }
                break;
            case MASTERS:
            case PHD:
                //Check here and if a Graduate Student has a Scholarship, throw an exception because there's a problem
                // with the data. Scholarships are only for tuition modification.
                if (student.getScholarship() != Scholarship.NONE) {
                    throw new BillGenerationException();
                }

                //check residence and load the correct tuition fees
                if (student.isResident() || student.isVeteran()) {
                    tuitionFees = fees.getResidentGraduateTuition();
                } else {
                    tuitionFees = fees.getNonResidentGraduateTuition();
                }
                break;
            case GRADUATED:
                //Graduated student should have no tuition in a bill.
                break;
        }

        //only need to calculate tuition if the student is not being given free tuition
        if (!student.isFreeTuition()) {
            //process each tuition fee one at a time
            for (Fee fee : tuitionFees) {
                //if they're a full time student, add the correct tuition based on scholarship/modifiers and then check
                // to see if they're an overtime student taking more than 17 hours and add that fee too.
                if (isFullTime) {
                    switch (student.getScholarship()) {
                        case GENERAL:
                            if (fee.getFeeType() == FeeType.GENERAL && fee.getStudentType() == StudentType.FULL_TIME) {
                                bill.addTransaction(
                                        Transaction.createCharge(fee.getAmount(), fee.getNote()));
                            }
                            if (fee.getFeeType() == FeeType.TUITION_OVER_17_RES_SS_MIL && totalHours >= OVERTIME_STUDENT_HOURS) {
                                bill.addTransaction(
                                        Transaction.createCharge(BigDecimal.valueOf(totalHours - OVERTIME_STUDENT_HOURS + 1).multiply(fee.getAmount()), fee.getNote()));
                            }
                            break;
                        case DEPARTMENTAL:
                            if (fee.getFeeType() == FeeType.DEPARTMENTAL && fee.getStudentType() == StudentType.FULL_TIME) {
                                bill.addTransaction(
                                        Transaction.createCharge(fee.getAmount(), fee.getNote()));
                            }
                            if (fee.getFeeType() == FeeType.TUITION_OVER_17_RES_SS_MIL && totalHours >= OVERTIME_STUDENT_HOURS) {
                                bill.addTransaction(
                                        Transaction.createCharge(BigDecimal.valueOf(totalHours - OVERTIME_STUDENT_HOURS + 1).multiply(fee.getAmount()), fee.getNote()));
                            }
                            break;
                        case ATHLETIC:
                            if (fee.getFeeType() == FeeType.ATHLETIC && fee.getStudentType() == StudentType.FULL_TIME) {
                                bill.addTransaction(
                                        Transaction.createCharge(fee.getAmount(), fee.getNote()));
                            }
                            if (fee.getFeeType() == FeeType.TUITION_OVER_17_RES_SS_MIL && totalHours >= OVERTIME_STUDENT_HOURS) {
                                bill.addTransaction(
                                        Transaction.createCharge(BigDecimal.valueOf(totalHours - OVERTIME_STUDENT_HOURS + 1).multiply(fee.getAmount()), fee.getNote()));
                            }
                            break;
                        case WOODROW:
                            if (fee.getFeeType() == FeeType.WOODROW && fee.getStudentType() == StudentType.FULL_TIME) {
                                bill.addTransaction(
                                        Transaction.createCharge(fee.getAmount(), fee.getNote()));
                            }
                            if (fee.getFeeType() == FeeType.TUITION_OVER_17_RES_SS_MIL && totalHours >= OVERTIME_STUDENT_HOURS) {
                                bill.addTransaction(
                                        Transaction.createCharge(BigDecimal.valueOf(totalHours - OVERTIME_STUDENT_HOURS + 1).multiply(fee.getAmount()), fee.getNote()));
                            }
                            break;
                        case SIMS:
                            if (fee.getFeeType() == FeeType.SIMS && fee.getStudentType() == StudentType.FULL_TIME) {
                                bill.addTransaction(
                                        Transaction.createCharge(fee.getAmount(), fee.getNote()));
                            }
                            if (fee.getFeeType() == FeeType.TUITION_OVER_17_RES_SS_MIL && totalHours >= OVERTIME_STUDENT_HOURS) {
                                bill.addTransaction(
                                        Transaction.createCharge(BigDecimal.valueOf(totalHours - OVERTIME_STUDENT_HOURS + 1).multiply(fee.getAmount()), fee.getNote()));
                            }
                            break;
                        case NONE:
                            if (fee.getStudentType() == StudentType.FULL_TIME) {
                                bill.addTransaction(
                                        Transaction.createCharge(fee.getAmount(), fee.getNote()));
                            }
                            if ((student.isActiveDuty() || student.isVeteran() || student.isResident())
                                    && fee.getFeeType() == FeeType.TUITION_OVER_17_RES_SS_MIL && totalHours >= OVERTIME_STUDENT_HOURS) {
                                bill.addTransaction(
                                        Transaction.createCharge(BigDecimal.valueOf(totalHours - OVERTIME_STUDENT_HOURS + 1).multiply(fee.getAmount()), fee.getNote()));
                            }
                            if (!(student.isActiveDuty() || student.isVeteran() || student.isResident())
                                    && fee.getFeeType() == FeeType.TUITION_OVER_17_NON_RES && totalHours >= OVERTIME_STUDENT_HOURS) {
                                bill.addTransaction(
                                        Transaction.createCharge(BigDecimal.valueOf(totalHours - OVERTIME_STUDENT_HOURS + 1).multiply(fee.getAmount()), fee.getNote()));
                            }
                        default:
                    }
                } else {
                    //if they're a part time student, add the correct tuition based on scholarship/modifiers by hour.
                    switch (student.getScholarship()) {
                        case GENERAL:
                            if (fee.getFeeType() == FeeType.GENERAL && fee.getStudentType() == StudentType.PART_TIME) {
                                bill.addTransaction(
                                        Transaction.createCharge(BigDecimal.valueOf(totalHours).multiply(fee.getAmount()), fee.getNote()));
                            }
                            break;
                        case DEPARTMENTAL:
                            if (fee.getFeeType() == FeeType.DEPARTMENTAL && fee.getStudentType() == StudentType.PART_TIME) {
                                bill.addTransaction(
                                        Transaction.createCharge(BigDecimal.valueOf(totalHours).multiply(fee.getAmount()), fee.getNote()));
                            }
                            break;
                        case ATHLETIC:
                            if (fee.getFeeType() == FeeType.ATHLETIC && fee.getStudentType() == StudentType.PART_TIME) {
                                bill.addTransaction(
                                        Transaction.createCharge(BigDecimal.valueOf(totalHours).multiply(fee.getAmount()), fee.getNote()));
                            }
                            break;
                        case WOODROW:
                            if (fee.getFeeType() == FeeType.WOODROW && fee.getStudentType() == StudentType.PART_TIME) {
                                bill.addTransaction(
                                        Transaction.createCharge(BigDecimal.valueOf(totalHours).multiply(fee.getAmount()), fee.getNote()));
                            }
                            break;
                        case SIMS:
                            if (fee.getFeeType() == FeeType.SIMS && fee.getStudentType() == StudentType.PART_TIME) {
                                bill.addTransaction(
                                        Transaction.createCharge(BigDecimal.valueOf(totalHours).multiply(fee.getAmount()), fee.getNote()));
                            }
                            break;
                        case NONE:
                            if (fee.getStudentType() == StudentType.PART_TIME) {
                                if (fee.getFeeType() == FeeType.ONLINE) {
                                    bill.addTransaction(
                                            Transaction.createCharge(BigDecimal.valueOf(onlineHours).multiply(fee.getAmount()), fee.getNote()));
                                } else {
                                    bill.addTransaction(
                                            Transaction.createCharge(BigDecimal.valueOf(totalHours - onlineHours).multiply(fee.getAmount()), fee.getNote()));
                                }
                            }
                        default:
                    }
                }
            }
        }

        return bill;
    }

    protected Bill generateGeneralCharges(StudentRecord student, Bill bill) {
        //Get today's date
        Calendar calToday = Calendar.getInstance();
        //Create a new Date
        Date dateToday = new Date(calToday.get(Calendar.MONTH) + 1, calToday.get(Calendar.DATE), calToday.get(Calendar.YEAR));

        //Go through each fee and see if it needs to be charged.
        for (Fee fee : fees.getGeneralFees()) {
            switch (fee.getFeeType()) {
                //Charge the technology fee to everyone
                case TECHNOLOGY:
                    if (isFullTime && fee.getStudentType() == StudentType.FULL_TIME) {
                        bill.addTransaction(
                                Transaction.createCharge(fee.getAmount(), fee.getNote()));
                    }
                    if (!isFullTime && fee.getStudentType() == StudentType.PART_TIME) {
                        bill.addTransaction(
                                Transaction.createCharge(BigDecimal.valueOf(totalHours).multiply(fee.getAmount()), fee.getNote()));
                    }
                    break;
                //Charge the capstone fee for the first two years after someone enrolls as a capstone student.
                case CAPSTONE:
                    //If the student enrolled in the capstone program less than two years ago, charge the fee
                    try {
                        if ((dateToday.getYear() - student.getCapstoneEnrolled().getYear() < 2) ||
                                (dateToday.getYear() == student.getCapstoneEnrolled().getYear() &&
                                        (student.getCapstoneEnrolled().getSemester() == dateToday.getSemeter() ||
                                                student.getCapstoneEnrolled().getSemester() == Semester.SPRING))) {
                            //Add the transaction
                            bill.addTransaction(
                                    Transaction.createCharge(fee.getAmount(), fee.getNote()));
                        }
                    } catch (Exception e) {
                        //TODO: Remove this? Should never get here
                        System.out.println(e);
                    }

                    break;
                //If this is the International student's first semester, charge the fee
                case INTERNATIONAL:
                    try {
                        if (student.isInternational() &&
                                student.getTermBegan().getSemester() == dateToday.getSemeter() &&
                                student.getTermBegan().getYear() == dateToday.getYear()) {
                            //Add the transaction
                            bill.addTransaction(
                                    Transaction.createCharge(fee.getAmount(), fee.getNote()));
                        }
                    } catch (Exception e) {
                        //TODO: Remove this? Should never get here
                        System.out.println(e);
                    }
                    break;
                //If this is a Short Term International student, charge the short term fee
                case INTERNATIONAL_SHORT_TERM:
                    if (student.isInternational()) {
                        if (student.getInternationalStatus() == InternationalStatus.SHORT_TERM) {
                            bill.addTransaction(
                                    Transaction.createCharge(fee.getAmount(), fee.getNote()));
                        }
                    }
                    break;
                //If this is a Sponsored International student, charge the sponsored fee
                case INTERNATIONAL_SPONSORED:
                    if (student.isInternational()) {
                        if (student.getInternationalStatus() == InternationalStatus.SPONSORED) {
                            bill.addTransaction(
                                    Transaction.createCharge(fee.getAmount(), fee.getNote()));
                        }
                    }
                    break;
                //If Study abroad is "regular", charge the regular study abroad fee
                case STUDY_ABROAD:
                    if (student.getStudyAbroad() == StudyAbroad.REGULAR) {
                        bill.addTransaction(
                                Transaction.createCharge(fee.getAmount(), fee.getNote()));
                    }
                    break;
                //If Study abroad is "cohort", charge the regular study abroad fee
                case COHORT:
                    if (student.getStudyAbroad() == StudyAbroad.COHORT) {
                        bill.addTransaction(
                                Transaction.createCharge(fee.getAmount(), fee.getNote()));
                    }
                    break;
                //If National Student Exchange is set, charge the national student exchange
                case NATIONAL_STUDENT_EXCHANGE:
                    if (student.isNationalStudentExchange()) {
                        bill.addTransaction(
                                Transaction.createCharge(fee.getAmount(), fee.getNote()));
                    }
                    break;
                //If Study abroad is anything other than none, charge this non-refundable deposit
                case STUDY_ABROAD_EXCHANGE:
                    if (student.getStudyAbroad() != StudyAbroad.NONE) {
                        bill.addTransaction(
                                Transaction.createCharge(fee.getAmount(), fee.getNote()));
                    }
                    break;
                //If Study abroad is anything other than none, charge for study abroad insurance
                case STUDY_ABROAD_INSURANCE:
                    if (student.getStudyAbroad() != StudyAbroad.NONE) {
                        bill.addTransaction(
                                Transaction.createCharge(fee.getAmount(), fee.getNote()));
                    }
                    break;
                //If the student it an undergraduate and they're taking between 6 and 11 hours, charge this fee
                case HEALTH_CENTER_UNDERGRAD_6_11:
                    if (!isGradStudent && totalHours >= 6 && totalHours <= 11) {
                        bill.addTransaction(
                                Transaction.createCharge(fee.getAmount(), fee.getNote()));
                    }
                    break;
                //If the student is a grad student but not a GA and taking 6-8 hours, charge this fee
                case HEALTH_CENTER_GRAD_6_8:
                    if (!student.isGradAssistant() && isGradStudent && totalHours >= 6 && totalHours <= 8) {
                        bill.addTransaction(
                                Transaction.createCharge(fee.getAmount(), fee.getNote()));
                    }
                    break;
                //If the student is a grad student but not a GA and taking 9-11 hours, charge this fee
                case HEALTH_CENTER_GRAD_9_11:
                    if (!student.isGradAssistant() && isGradStudent && totalHours >= 9 && totalHours <= 11) {
                        bill.addTransaction(
                                Transaction.createCharge(fee.getAmount(), fee.getNote()));
                    }
                    break;
                //If the student is a GA, charge this fee
                case HEALTH_CENTER_GA:
                    if (student.isGradAssistant()) {
                        bill.addTransaction(
                                Transaction.createCharge(fee.getAmount(), fee.getNote()));
                    }
                    break;
                //If the student doesn't have outside insurance, charge for insurance
                case INSURANCE:
                    if (!student.isOutsideInsurance()) {
                        bill.addTransaction(
                                Transaction.createCharge(fee.getAmount(), fee.getNote()));
                    }
                    break;
                //If the student just started this semester, charge the matriculation fee
                case MATRICULATION:
                    try {
                        if (student.getTermBegan().getSemester() == dateToday.getSemeter() &&
                                student.getTermBegan().getYear() == dateToday.getYear()) {
                            bill.addTransaction(
                                    Transaction.createCharge(fee.getAmount(), fee.getNote()));
                        }
                    } catch (Exception e) {
                        //TODO: Remove this? Should never get here
                        System.out.println(e);
                    }
                    break;
                default:
                    System.out.println("Unhandled fee type in generateGeneralCharges: " + fee.getFeeType());
            }

        }

        return bill;
    }

    protected Bill generateCollegeCharges(StudentRecord student, Bill bill) {
        for (Fee fee : fees.getCollegeFees()) {
            switch (fee.getFeeType()) {
                case AAS_AE_LAB:
                    for (int i = 0; i < BillHelper.AE_LAB_COURSES.length; ++i) {
                        if (searchCourses(student.getCourses(), "ARTE " + AE_LAB_COURSES[i]))
                            bill.addTransaction(
                                    Transaction.createCharge(fee.getAmount(), fee.getNote())
                            );
                    }
                    break;
                case AAS_AH_LAB:
                    for (int i = 0; i < BillHelper.AH_LAB_COURSES.length; ++i) {
                        if (searchCourses(student.getCourses(), "ARTH " + AH_LAB_COURSES[i]))
                            bill.addTransaction(
                                    Transaction.createCharge(fee.getAmount(), fee.getNote())
                            );
                    }
                    break;
                case AAS_DANCE_LAB:
                    for (int i = 0; i < BillHelper.DANCE_LAB_COURSES.length; ++i) {
                        if (
                                searchCourses(student.getCourses(), "DANC " + DANCE_LAB_COURSES[i])
                                )
                            bill.addTransaction(
                                    Transaction.createCharge(fee.getAmount(), fee.getNote())
                            );
                    }
                    break;

                case AAS_FIELD:
                    for (int i = 0; i < BillHelper.FIELD_LAB_COURSES.length; ++i) {
                        if (
                                searchCourses(student.getCourses(), "GEOL " + FIELD_LAB_COURSES[i])
                                )
                            bill.addTransaction(
                                    Transaction.createCharge(fee.getAmount(), fee.getNote())
                            );
                    }
                    break;

                //case AAS_HS_DRAMA:
                //    TODO: Determine what we should do about this

                case AAS_LANGUAGE:
                    //TODO: Ensure method is correct
                    for (int i = 0; i < BillHelper.LANGUAGE_COURSES.length; ++i) {
                        Course crse = getDeptCourse(
                                student.getCourses(),
                                BillHelper.LANGUAGE_COURSES[i]
                        );
                        if (crse != null && crse.getNumCredits() > 3)
                            bill.addTransaction(
                                    Transaction.createCharge(fee.getAmount(), fee.getNote())
                            );
                        //System.out.println("Lang: " + LANGUAGE_COURSES[i]);
                        //System.out.println("Course: " + crse);
                    }
                    break;
                case AAS_MARINE_SCIENCE:
                    //TODO: Check Courses for this fee?
                    break;
                case AAS_MATH_LAB:
                    //TODO: Check Courses for this fee?
                    break;
                case AAS_MEDIA_LAB:
                    //TODO: Check Courses for this fee?
                    break;
                case AAS_STUDIO_LAB:
                    //TODO: Check Courses for this fee?
                    break;
                case EAC_APOGEE:
                    //TODO: Check Courses for this fee?
                    break;
                case EAC_CSCE_101_102_LAB:
                    if (searchCourses(student.getCourses(), "CSCE 101")
                            || searchCourses(student.getCourses(), "CSCE 102")) {
                        bill.addTransaction(
                                Transaction.createCharge(fee.getAmount(), fee.getNote()));
                    }
                    break;
                case EAC_EXEC_MASTER:
                    //TODO: Check Courses for this fee?
                    break;
                case EAC_MHIT:
                    //TODO: Check Courses for this fee?
                    break;
                case EAC_PROGRAM:
                    if (isFullTime && fee.getStudentType() == StudentType.FULL_TIME) {
                        bill.addTransaction(
                                Transaction.createCharge(fee.getAmount(), fee.getNote()));
                    }
                    if (!isFullTime && fee.getStudentType() == StudentType.PART_TIME) {
                        bill.addTransaction(
                                Transaction.createCharge(BigDecimal.valueOf(totalHours).multiply(fee.getAmount()), fee.getNote()));
                    }
                    break;
                case EAC_SYS_DESIGN:
                    //TODO: Check Courses for this fee?
                    break;
                default:
                    System.out.println("Unhandled fee type in generateCollegeCharges: " + fee.getFeeType());
            }
        }

        return bill;
    }

    protected boolean searchCourses(List<Course> courses, String courseID) {
        for (Course course : courses) {
            if (course.getId().equalsIgnoreCase(courseID)) {
                return true;
            }
        }
        return false;
    }

    protected Course getDeptCourse(List<Course> courses, String deptID) {
        for (Course course : courses) {
            if (course.getId().toUpperCase().startsWith(deptID)) {
                return course;
            }
        }
        return null;
    }

    protected void processCourses(StudentRecord student) {

        for (Course course : student.getCourses()) {
            //if the course is online, add it to the online hours
            if (course.isOnline()) {
                this.onlineHours += course.getNumCredits();
            }

            //add the hours to the total number of hours
            this.totalHours += course.getNumCredits();

        }

        //figure out if the student is full time or not
        switch (student.getClassStatus()) {
            case FRESHMAN:
            case SOPHOMORE:
            case JUNIOR:
            case SENIOR:
                this.isFullTime = (totalHours >= UNDERGRAD_FULL_TIME_STUDENT_HOURS);
                this.isGradStudent = false;
                break;
            case MASTERS:
            case PHD:
                this.isFullTime = (totalHours >= GRAD_FULL_TIME_STUDENT_HOURS);
                this.isGradStudent = true;
                break;
            case GRADUATED:
            default:
        }
    }
}
