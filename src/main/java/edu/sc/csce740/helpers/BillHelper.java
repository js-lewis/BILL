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
import java.io.FileNotFoundException;
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
    //Constants
    /**
     * The constant name of the file where the fee information is saved.
     */
    private static String feeFile = "resources/data/fees.json";
    public static String getFeeFile() {
        return BillHelper.feeFile;
    }
    public static void setFeeFile(String feeFile) {
        BillHelper.feeFile = feeFile;
    }

    /**
     * The constant number of credit hours where a student gets charged extra per additional hour.
     */
    private static final int OVERTIME_STUDENT_HOURS = 17;

    /**
     * The constant number of credit hours that must be taken for a graduate student to be considered full time.
     */
    private static final int GRAD_FULL_TIME_STUDENT_HOURS = 6;

    /**
     * The constant number of credit hours that must be taken for an undergraduate student to be considered full time.
     */
    private static final int UNDERGRAD_FULL_TIME_STUDENT_HOURS = 12;

    /**
     * An array of CSCE 101 and 102 labs
     */
    private static final String[] CSCE_100_LABS = {"CSCE 101", "CSCE 102"};

    /**
     * A Constant for a space between prefixes and course numbers.
     */
    private static final String SPACE = " ";

    /**
     * The Arts and Sciences Art Education Courses prefix.
     */
    private static final String AE_LAB_PREFIX = "ART EDUCATION";

    /**
     * The Arts and Sciences Art Education Courses that require a lab fee.
     */
    private static final short[] AE_LAB_COURSES = {101, 260, 520, 530, 535, 540, 541, 555, 560, 595};

    /**
     * The Arts and Sciences Art History Courses prefix.
     */
    private static final String AH_LAB_PREFIX = "ART HISTORY";

    /**
     * The Arts and Sciences Art History Courses that require a lab fee.
     */
    private static final short[] AH_LAB_COURSES = {105, 106, 313, 315, 320, 321, 325,
            326, 327, 330, 335, 337, 340, 341, 342, 345, 346, 350, 365, 366, 370, 390,
            399, 498, 499, 501, 511, 514, 519, 520, 521, 522, 523, 524, 525, 526, 527,
            529, 534, 535, 536, 537, 539, 540, 542, 543, 550, 557, 560, 561, 562, 566,
            569, 590, 720, 725, 730, 735, 737, 769, 790};

    /**
     * The Arts and Sciences Dance Courses prefix.
     */
    private static final String DANCE_PREFIX = "DANCE";

    /**
     * The Arts and Sciences Dance Courses that require a lab fee.
     */
    private static final short[] DANCE_LAB_COURSES = {102, 112, 160, 170, 171, 177,
            178, 202, 203, 204, 212, 278, 302, 303, 307, 312, 360, 378, 385, 402, 403,
            407, 412, 440, 460, 577};

    /**
     * The Arts and Sciences Geology Field Courses that require a lab fee.
     */
    private static final String[] FIELD_LAB_COURSES = {"GEOLOGY 735", "GEOLOGY 750"}; // GEOL

    /**
     * The Arts and Sciences Language Courses.
     */
    private static final String[] LANGUAGE_COURSES = {"ARAB", "CHIN", "FREN", "GERM",
            "ITAL", "JAPA", "LATN", "PORT", "RUSS", "SPAN"};

    /**
     * The Arts and Sciences Marine Science Lab Courses.
     */
    private static final String[] MARINE_SCIENCE_LAB_COURSES = {"MARINE SCIENCE 460"};

    /**
     * The Arts and Sciences Science Lab Courses.
     */
    private static final String[] MATH_LAB_COURSES = {"MATH 141", "MATH 142",
            "MATH 526", "STAT 201"};

    private static final String[] SCIENCE_LAB_COURSES = {"PHYSICS",
            "ASTRONOMY", "BIOLOGY", "CHEMISTRY", "ENVIRONMENT", "GEOLOGY",
            "MARINE SCIENCE"};

    private static final short[] PSYC_LAB_COURSES = {227, 228, 489, 498, 570,
            571, 572, 574, 575, 598, 599, 709, 710, 762};
    private static final String PSYC_PREFIX = "PSYC";

    private static final String[] ANTH_LAB_COURSES = {"ANTHROPOLOGY 161", "ANTHROPOLOGY 391", "ANTHROPOLOGY  561"};

    private static final String[] GEOG_LAB_COURSES = {"GEOGRAPHY 201", "GEOGRAPHY 202"};

    /**
     * The Arts and Sciences Media Courses.
     */
    private static final String[] MEDIA_COURSES = {"MEDIA ART"};

    /**
     * The Arts and Sciences Studio Courses.
     */
    private static final String[] STUDIO_COURSES = {"STUDIO ART"};

    /**
     * The Engineering and Computing Courses prefix.
     */
    private static final String CSCE_PREFIX = "CSCE";

    /**
     * The collection of fees loaded from the JSON file defined in {@link #feeFile}
     */
    private Fees fees;

    /**
     * A boolean to ensure that the fees were loaded. If fees were not loaded, many of these methods will not work.
     */
    private boolean feesLoaded;

    /**
     * A boolean to save if a student whose bill is being computed is full time based on hours.
     */
    private boolean isFullTime;

    /**
     * A boolean to save if a student whose bill is being computed is a graduate student.
     */
    private boolean isGradStudent;

    /**
     * An int to save how many online hours a student is enrolled in.
     */
    private int onlineHours;

    /**
     * An int to save how many total hours a student is enrolled in.
     */
    private int totalHours;

    /**
     * A default constructor. This loads the fees and initializes student information .
     */
    public BillHelper() {
        fees = new Fees();
        try {
            readFees();
            feesLoaded = true;
        } catch (Exception e) {
            feesLoaded = false;
        }
        this.reset();
    }

    /**
     * A method used to reset the stored student information after a BILL has been computed.
     */
    protected void reset() {
        isFullTime = false;
        isGradStudent = false;
        onlineHours = 0;
        totalHours = 0;
    }

    /**
     * Reads the fees in from the JSON fee file.
     *
     * @throws IOException if the file cannot be read.
     */
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
        try {
            fees = gson.fromJson(FileUtils.readFileToString(file, "UTF-8"), feeType);
        }
        // File not found, create an empty fee file
        catch (FileNotFoundException e) {
            fees = new Fees();
        }

        //If fees is null here, the file was empty. Create an empty Fee instead.
        if (fees == null) {
            fees = new Fees();
        }
    }

    /**
     * Get the list of Fees loaded into the system. This is primarily for debugging purposes.
     *
     * @return a Fees object which is a list of fees.
     */
    protected Fees getFees() {
        return fees;
    }

    /**
     * Retrieves a BILL for a certain time period. This method creates a BILL object and then adds any transactions
     * that attached to the student record after the start date and before the end date.
     *
     * @param student   the Student Record whose BILL is being queried.
     * @param startDate the Date to pull transaction after.
     * @param endDate   the Date to pull transactions before.
     * @return a BILL with all of the transactions tht occurred between the startDate and endDate.
     * @throws BillRetrievalException if there's an error retrieving the BILL.
     */
    public Bill retrieveBill(StudentRecord student, Date startDate, Date endDate)
            throws BillRetrievalException {

        //If the fees weren't loaded or the student record isn't set, return an exception
        if (!feesLoaded || student == null) {
            throw new BillRetrievalException();
        }
        
        //Create a base bill with no transactions for the student whose record was passed in.
        Bill returnBill = new Bill(student.getStudent(), student.getCollege(), student.getClassStatus(),
                0.00, new ArrayList<Transaction>());

        //Loop through all of the transactions in the student record and add any Charges that occurred between
        // the dates passed in
        for (Transaction transaction : student.getTransactions()) {
            if (transaction.getType() == TransactionType.CHARGE) {
                if (transaction.getTransactionDate().isBetween(startDate, endDate)) {
                    //Add the transaction to the bill
                    returnBill.addTransaction(transaction);
                    //Add the amount of the transaction to the total
                    returnBill.setBalance(transaction.getAmount()
                            .add(BigDecimal.valueOf(returnBill.getBalance())).doubleValue());
                }
            }
        }

        //return the BILL.
        return returnBill;
    }

    /**
     * Generate a current BILL for this semester.
     *
     * @param student the StudentRecord to use to generate the BILL. NOTE: This does not save the charges to the
     *                StudentRecord!
     * @return a BILL with th current charges for a student.
     * @throws BillGenerationException if the BILL cannot be generated.
     */
    public Bill generateBill(StudentRecord student)
            throws BillGenerationException {
        //If the fees weren't loaded or the student record isn't set, return an exception
        if (!feesLoaded || student == null) {
            throw new BillGenerationException();
        }
        
        //Create a base bill with no transactions for the student whose record was passed in.
        Bill returnBill = new Bill(student.getStudent(), student.getCollege(), student.getClassStatus(),
                0.00, new ArrayList<Transaction>());

        //check courses and compute the total number of hours a student it taking.
        //save this information to some class level variables.
        processCourses(student);

        if (student.getClassStatus() != ClassStatus.GRADUATED) {
            //process all of the tuition charges
            returnBill = generateTuitionCharges(student, returnBill);
            //process all of the general university charges
            returnBill = generateGeneralCharges(student, returnBill);
            //process all of the college/course specific charges
            returnBill = generateCollegeCharges(student, returnBill);
        }

        returnBill = updateBalance(student, returnBill);

        //Per Dr. Gay, Bill Generation should not add charges to the Student Record
        //reset the class level variables.
        reset();
        //return the BILL.
        return returnBill;
    }

    /**
     * Takes in a BILL and adds tuition charges for a student.
     *
     * @param student The student whose information is used to generate the BILL.
     * @param bill    The BILL without these charges.
     * @return The BILL with the tuition charges added.
     */
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
                            //Add regular tuition
                            if (fee.getFeeType() == FeeType.NONE && fee.getStudentType() == StudentType.FULL_TIME ) {
                                bill.addTransaction(
                                        Transaction.createCharge(fee.getAmount(), fee.getNote()));
                            }
                            //Calculate Overtime hours
                            if(totalHours >= OVERTIME_STUDENT_HOURS) {
                                if(fee.getFeeType() == FeeType.TUITION_OVER_17_RES_SS_MIL) {
                                    if(isGradStudent){
                                        if(student.isResident()) {
                                            bill.addTransaction(
                                                    Transaction.createCharge(BigDecimal.valueOf(totalHours - OVERTIME_STUDENT_HOURS + 1).multiply(fee.getAmount()), fee.getNote()));
                                        }
                                    } else {
                                        if (student.isActiveDuty() || student.isVeteran() || student.isResident()) {
                                            bill.addTransaction(
                                                    Transaction.createCharge(BigDecimal.valueOf(totalHours - OVERTIME_STUDENT_HOURS + 1).multiply(fee.getAmount()), fee.getNote()));
                                        }
                                    }
                                }

                                if(fee.getFeeType() == FeeType.TUITION_OVER_17_NON_RES) {
                                    if(isGradStudent){
                                        if(!student.isResident()) {
                                            bill.addTransaction(
                                                    Transaction.createCharge(BigDecimal.valueOf(totalHours - OVERTIME_STUDENT_HOURS + 1).multiply(fee.getAmount()), fee.getNote()));
                                        }
                                    } else {
                                        if (!student.isActiveDuty() && !student.isVeteran() && !student.isResident()) {
                                            bill.addTransaction(
                                                    Transaction.createCharge(BigDecimal.valueOf(totalHours - OVERTIME_STUDENT_HOURS + 1).multiply(fee.getAmount()), fee.getNote()));
                                        }
                                    }
                                }
                            }
                            break;
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
                            break;
                        default:
                    }
                }
            }
        }

        return bill;
    }

    /**
     * Takes in a BILL and adds transactions to it that are applied across all students.
     *
     * @param student The student whose information is used to generate the BILL.
     * @param bill    The BILL without these charges.
     * @return The BILL with the general university charges added.
     */
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
                        if (student.getCapstoneEnrolled() != null) {
                            if ((dateToday.getYear() - student.getCapstoneEnrolled().getYear() < 2) ||
                                    (dateToday.getYear() == student.getCapstoneEnrolled().getYear() &&
                                            (student.getCapstoneEnrolled().getSemester() == dateToday.getSemeter() ||
                                                    student.getCapstoneEnrolled().getSemester() == Semester.SPRING))) {
                                //Add the transaction
                                bill.addTransaction(
                                        Transaction.createCharge(fee.getAmount(), fee.getNote()));
                            }
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
                    if (!isGradStudent && !isFullTime && totalHours >= 6 && totalHours <= 11) {
                        bill.addTransaction(
                                Transaction.createCharge(fee.getAmount(), fee.getNote()));
                    }
                    break;
                //If the student is a grad student but not a GA and taking 6-8 hours, charge this fee
                case HEALTH_CENTER_GRAD_6_8:
                    if (!student.isGradAssistant() && !isFullTime && isGradStudent && totalHours >= 6 && totalHours <= 8) {
                        bill.addTransaction(
                                Transaction.createCharge(fee.getAmount(), fee.getNote()));
                    }
                    break;
                //If the student is a grad student but not a GA and taking 9-11 hours, charge this fee
                case HEALTH_CENTER_GRAD_9_11:
                    if (!student.isGradAssistant() && !isFullTime && isGradStudent && totalHours >= 9 && totalHours <= 11) {
                        bill.addTransaction(
                                Transaction.createCharge(fee.getAmount(), fee.getNote()));
                    }
                    break;
                //If the student is a GA, charge this fee
                case HEALTH_CENTER_GA:
                    if (student.isGradAssistant() && !isFullTime ) {
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

    /**
     * Takes in a BILL and adds transactions to it that are college/course specific.
     *
     * @param student The student whose information is used to generate the BILL.
     * @param bill    The BILL without these charges.
     * @return The BILL with the college specific charges added.
     */
    protected Bill generateCollegeCharges(StudentRecord student, Bill bill) {
        for (Fee fee : fees.getCollegeFees()) {
            switch (fee.getFeeType()) {
                /**
                 * Arts and Sciences (AAS) fees
                 */
                case AAS_AE_LAB: // Art Eductation (AE)
                    // Search current student's courses for AE lab courses, adding charges
                    for (int i = 0; i < BillHelper.AE_LAB_COURSES.length; ++i) {
                        if (searchCourses(student.getCourses(), AE_LAB_PREFIX + SPACE + AE_LAB_COURSES[i]))
                            bill.addTransaction(
                                    Transaction.createCharge(fee.getAmount(), fee.getNote())
                            );
                    }
                    break;
                case AAS_AH_LAB: // Art History (AH)
                    // Search current student's courses for AH lab courses, adding charges
                    for (int i = 0; i < BillHelper.AH_LAB_COURSES.length; ++i) {
                        if (searchCourses(student.getCourses(), AH_LAB_PREFIX + SPACE + AH_LAB_COURSES[i]))
                            bill.addTransaction(
                                    Transaction.createCharge(fee.getAmount(), fee.getNote())
                            );
                    }
                    break;
                case AAS_DANCE_LAB:
                    // Search current student's courses for DANCE lab courses, adding adding charges
                    for (int i = 0; i < BillHelper.DANCE_LAB_COURSES.length; ++i) {
                        if (searchCourses(student.getCourses(), DANCE_PREFIX + SPACE + DANCE_LAB_COURSES[i]))
                            bill.addTransaction(
                                    Transaction.createCharge(fee.getAmount(), fee.getNote())
                            );
                    }
                    break;
                case AAS_FIELD: // Geology Field (FIELD)
                    // Search current student's courses for FIELD lab courses, adding charges
                    for (int i = 0; i < BillHelper.FIELD_LAB_COURSES.length; ++i) {
                        if (
                                searchCourses(student.getCourses(), FIELD_LAB_COURSES[i])
                                )
                            bill.addTransaction(
                                    Transaction.createCharge(fee.getAmount(), fee.getNote())
                            );
                    }
                    break;
                case AAS_HS_DRAMA:
                    //throw new AssertionError("Per GGay, this will not be tested");
                    break;

                case AAS_LANGUAGE:
                    //throw new AssertionError("Per GGay, this will not be tested");
                    break;

                case AAS_MARINE_SCIENCE:
                    // Search current student's courses for MARINE SCIENCE lab courses, adding charges
                    for (int i = 0; i < MARINE_SCIENCE_LAB_COURSES.length; ++i) {
                        if (searchCourses(
                                student.getCourses(), MARINE_SCIENCE_LAB_COURSES[i]
                        ))
                            bill.addTransaction(
                                    Transaction.createCharge(fee.getAmount(), fee.getNote())
                            );
                    }
                    break;
                case AAS_SCIENCE_LAB: // 
                    // Search current student's courses for SCIENCE labs, adding charges
                    for (Course course : student.getCourses()) {
                        boolean exclude = false;
                        // we have two exclusions
                        for (int i = 0; i < MARINE_SCIENCE_LAB_COURSES.length;
                             ++i) {
                            if (MARINE_SCIENCE_LAB_COURSES[i].equals(
                                    course.getId())) {
                                exclude = true;
                                break;
                            }
                        }

                        for (int i = 0; i < BillHelper.FIELD_LAB_COURSES.length;
                             ++i) {
                            if (FIELD_LAB_COURSES[i].equals(course.getId())) {
                                exclude = true;
                                break;
                            }
                        }

                        if (!exclude && isScienceLab(course))
                            bill.addTransaction(
                                    Transaction.createCharge(fee.getAmount(), fee.getNote())
                            );
                    }
                    break;
                case AAS_MEDIA_LAB: // Media Art (MEDIA)
                    // Search current student's courses for MEDIA arts courses, adding charges
                    for (int i = 0; i < MEDIA_COURSES.length; ++i) {
                        List<Course> courses = getDeptCourses(student.getCourses(), MEDIA_COURSES[i]);

                        for (Course course : courses)
                            bill.addTransaction(Transaction.createCharge(fee.getAmount(), fee.getNote()));
                    }
                    break;
                case AAS_STUDIO_LAB: // Studio Art (STUDIO)
                    // Search current student's courses for STUDIO arts course, adding charges
                    for (int i = 0; i < STUDIO_COURSES.length; ++i) {
                        List<Course> courses = getDeptCourses(student.getCourses(), STUDIO_COURSES[i]);

                        for (Course course : courses)
                            bill.addTransaction(Transaction.createCharge(fee.getAmount(), fee.getNote()));
                    }
                    break;
                /**
                 * College of Engineering and Computing (EAC) fees
                 */
                case EAC_APOGEE:
                    // Find all CSCE courses
                    List<Course> courses = getDeptCourses(student.getCourses(), CSCE_PREFIX);
                    // If the coures is online, then it is APOGEE.
                    for (Course course : courses) {
                        if (course.isOnline())
                            bill.addTransaction(Transaction.createCharge(
                                    // fee is per credit hour
                                    new BigDecimal(course.getNumCredits() * fee.getAmount().doubleValue()),
                                    fee.getNote()
                            ));
                    }
                    break;
                case EAC_CSCE_101_102_LAB:
                    // Search current student's courses for 100 labs, adding charges
                    for (int i = 0; i < CSCE_100_LABS.length; ++i) {
                        if (searchCourses(student.getCourses(), CSCE_100_LABS[i]))
                            bill.addTransaction(Transaction.createCharge(fee.getAmount(), fee.getNote()));
                    }
                    break;
                case EAC_EXEC_MASTER:
                    //TODO: Check Courses for this fee?
                    break;

                case EAC_MHIT:
                    //TODO: Check Courses for this fee?
                    break;

                case EAC_PROGRAM: // Full-Time or Part-Time (PROGRAM) Fees:
                    // only apply this fee to students of the EAC college
                    if (student.getCollege() != College.ENGINEERING_AND_COMPUTING)
                        break;

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

    /**
     * Updates the balance based on past history and current charges.
     *
     * @param student the student who the bill is being generated for
     * @param bill    the current bill
     * @return a Bill with the correct balance.
     */
    protected Bill updateBalance(StudentRecord student, Bill bill) {
        //Figure out current balance based on student's past and add a transaction to the bill.
        BigDecimal current = BigDecimal.ZERO;

        for (Transaction transaction : student.getTransactions()) {
            if (transaction.getType() == TransactionType.CHARGE) {
                current = current.add(transaction.getAmount());
            }

            if (transaction.getType() == TransactionType.PAYMENT) {
                current = current.subtract(transaction.getAmount());
            }
        }

        //Add the past balance to the current bill
        if (!current.equals(BigDecimal.ZERO)) {
            bill.addTransaction(Transaction.createCharge(current, "Past Balance"));
        }

        //Loop through all of the Bill transactions and update the current balance.
        bill.setBalance(0.00);
        for (Transaction transaction : bill.getTransactions()) {
            //Should only be charges in the bill but just in case ...
            if (transaction.getType() == TransactionType.CHARGE) {
                bill.setBalance(bill.getBalance() + transaction.getAmount().doubleValue());
            }
        }

        return bill;
    }

    /**
     * Perform a linear inclusion search of course courseID.
     *
     * @param courses  the list of courses to search.
     * @param courseID the courseID being searched for.
     * @return a boolean value indicating whether or not the course was found.
     */
    protected boolean searchCourses(List<Course> courses, String courseID) {
        for (Course course : courses) {
            if (course.getId().equalsIgnoreCase(courseID)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get a List of any courses that begin with a given prefix/department ID.
     *
     * @param courses the list of courses to search
     * @param deptID  the department ID/course prefix to search for.
     * @return a list of courses beginning with string deptId.
     */
    protected List<Course> getDeptCourses(List<Course> courses, String deptID) {
        List<Course> found = new ArrayList<Course>();

        for (Course course : courses) {
            if (course.getId().toUpperCase().startsWith(deptID))
                found.add(course);
        }

        return found;
    }

    /**
     * Method compares course to all possible science lab courses to determine
     * whether it is a science lab.
     */
    protected boolean isScienceLab(Course course) {
        // Check Math courses
        for (int i = 0; i < MATH_LAB_COURSES.length; ++i)
            if (course.getId().equalsIgnoreCase(MATH_LAB_COURSES[i]))
                return true;
        // Check General Sciences
        for (int i = 0; i < SCIENCE_LAB_COURSES.length; ++i)
            if (course.getId().toUpperCase().startsWith(SCIENCE_LAB_COURSES[i].toUpperCase()))
                return true;

        // Check PSYC courses
        for (int i = 0; i < PSYC_LAB_COURSES.length; ++i)
            if (course.getId().equalsIgnoreCase(PSYC_PREFIX + SPACE + PSYC_LAB_COURSES[i]))
                return true;

        // Check ANTH courses
        for (int i = 0; i < ANTH_LAB_COURSES.length; ++i)
            if (course.getId().equalsIgnoreCase(ANTH_LAB_COURSES[i]))
                return true;

        // Check GEOG courses
        for (int i = 0; i < GEOG_LAB_COURSES.length; ++i)
            if (course.getId().equalsIgnoreCase(GEOG_LAB_COURSES[i]))
                return true;

        return false;
    }

    /**
     * Process through the courses a student is taking to set some class level variables used for calculating charges.
     * This function should only be called by others in the BillHelper and these variables must be cleared using the
     * {@link #reset()} method at the end of processing.
     *
     * @param student the student whose information to use.
     */
    protected void processCourses(StudentRecord student) {
        //Calculate the total number of hours and online hours a student is taking.
        for (Course course : student.getCourses()) {
            //if the course is online, add it to the online hours
            if (course.isOnline()) {
                this.onlineHours += course.getNumCredits();
            }

            //add the hours to the total number of hours
            this.totalHours += course.getNumCredits();

        }

        //figure out if the student is full time or not and if they're a graduate student.
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
