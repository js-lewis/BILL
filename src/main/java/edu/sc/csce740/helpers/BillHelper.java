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
    private int onlineHours;
    private int totalHours;

    public BillHelper() {
        fees = new Fees();
        try {
            readFees();
            feesLoaded = true;
        } catch (Exception e) {
            feesLoaded = false;
        }
        isFullTime = false;
        onlineHours = 0;
        totalHours = 0;
    }

    protected void reset() {
        isFullTime = false;
        onlineHours = 0;
        totalHours = 0;
    }

    private void readFees()
            throws IOException {
        //Gets the object that the JSON should match.
        Type feeType = new TypeToken<Fees>(){}.getType();
        //Create the GSON object.
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        //Setup the File for IO.
        File file;
        file = new File(feeFile);

        //Read the JSON file into a string and parse the JSON from that String into a Fees object
        fees = gson.fromJson(FileUtils.readFileToString(file, "UTF-8"), feeType);

        //If fees is null here, the file was empty. Create an empty Fee instead.
        if(fees == null) {
            fees = new Fees();
        }
    }

    protected Fees getFees() {
        return fees;
    }

    public Bill retrieveBill(StudentRecord student, Date startDate, Date endDate)
            throws BillRetrievalException {
        Bill returnBill = new Bill(student.getStudent(), student.getCollege(), student.getClassStatus(),
                0.00, new ArrayList<Transaction>()) ;

        if( !feesLoaded || student == null) {
            throw new BillRetrievalException();
        }

        //TODO: Spin through charges and actually pull the correct ones out ...

        return returnBill;
    }

    public Bill generateBill(StudentRecord student)
            throws BillGenerationException {
        Bill returnBill = new Bill(student.getStudent(), student.getCollege(), student.getClassStatus(),
                0.00, new ArrayList<Transaction>()) ;

        if( !feesLoaded || student == null) {
            throw new BillGenerationException();
        }

        //check courses and compute the total number of hours a student it taking.
        processCourses(student);

        //process all of the tuition charges
        returnBill = generateTuitionCharges(student, returnBill);
        returnBill = generateGeneralCharges(student, returnBill);
        returnBill = generateCollegeCharges(student, returnBill);

        //Per Dr. Gay, Bill Generation should not add charges to the Student Record
        System.out.println(returnBill);
        reset();
        return returnBill;
    }

    protected Bill generateTuitionCharges(StudentRecord student, Bill bill)
            throws BillGenerationException {
        List<Fee> tuitionFees = new ArrayList<Fee>();

        switch( student.getClassStatus() ) {
            case FRESHMAN:
            case SOPHOMORE:
            case JUNIOR:
            case SENIOR:
                //if the student is active duty, then use the active duty list
                if( student.isActiveDuty() ){
                    tuitionFees = fees.getActiveDutyTuition();
                } else {
                    //otherwise check for residence and use that list
                    if(student.isResident() || student.isVeteran()) {
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
                if(student.getScholarship() != Scholarship.NONE) {
                    throw new BillGenerationException();
                }

                //check residence and load the correct tuition fees
                if(student.isResident() || student.isVeteran()) {
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
        if(!student.isFreeTuition()) {
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
                            if ((student.isActiveDuty() || student.isVeteran() || student.isResident() )
                                    && fee.getFeeType() == FeeType.TUITION_OVER_17_RES_SS_MIL && totalHours >= OVERTIME_STUDENT_HOURS) {
                                bill.addTransaction(
                                        Transaction.createCharge(BigDecimal.valueOf(totalHours - OVERTIME_STUDENT_HOURS + 1).multiply(fee.getAmount()), fee.getNote()));
                            }
                            if (! (student.isActiveDuty() || student.isVeteran() || student.isResident() )
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
                                            Transaction.createCharge(BigDecimal.valueOf(totalHours-onlineHours).multiply(fee.getAmount()), fee.getNote()));
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
        Date dateToday = new Date(calToday.get(Calendar.MONTH)+1, calToday.get(Calendar.DATE), calToday.get(Calendar.YEAR));

        //Go through each fee and see if it needs to be charged.
        for(Fee fee: fees.getGeneralFees()) {
            switch (fee.getFeeType()) {
                //Charge the technology fee to everyone
                case TECHNOLOGY:
                    if(isFullTime && fee.getStudentType() == StudentType.FULL_TIME) {
                        bill.addTransaction(
                                Transaction.createCharge(fee.getAmount(), fee.getNote()));
                    }
                    if(!isFullTime && fee.getStudentType() == StudentType.PART_TIME) {
                        bill.addTransaction(
                                Transaction.createCharge(BigDecimal.valueOf(totalHours).multiply(fee.getAmount()), fee.getNote()));
                    }
                    break;
                //Charge the capstone fee for the first two years after someone enrolls as a capstone student.
                case CAPSTONE:
                    //If the student enrolled in the capstone program less than two years ago, charge the fee
                    try{
                        if( (dateToday.getYear() - student.getCapstoneEnrolled().getYear() < 2) ||
                            ( dateToday.getYear() == student.getCapstoneEnrolled().getYear() &&
                                    (student.getCapstoneEnrolled().getSemester() == dateToday.getSemeter() ||
                                    student.getCapstoneEnrolled().getSemester() == Semester.SPRING))) {
                            //Add the transaction
                            bill.addTransaction(
                                    Transaction.createCharge(fee.getAmount(), fee.getNote()));
                        }
                    } catch( Exception e ) {
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


                case STUDY_ABROAD:
                    if(student.getStudyAbroad() == StudyAbroad.REGULAR) {
                        bill.addTransaction(
                                Transaction.createCharge(fee.getAmount(), fee.getNote()));
                    }
                    break;
                case COHORT:
                    if(student.getStudyAbroad() == StudyAbroad.COHORT) {
                        bill.addTransaction(
                                Transaction.createCharge(fee.getAmount(), fee.getNote()));
                    }
                    break;
                case HEALTH_CENTER_UNDERGRAD_6_11: //TODO: FIX THIS!!!
                    if(!isFullTime && fee.getStudentType() == StudentType.PART_TIME) {
                        int hoursToCharge = totalHours-8 > 0 ? 3 : totalHours-5;
                        bill.addTransaction(
                                Transaction.createCharge(BigDecimal.valueOf(totalHours).multiply(fee.getAmount()), fee.getNote()));
                    }
                    break;
                case HEALTH_CENTER_GRAD_6_8: //TODO: FIX THIS!!!
                    if(!isFullTime && fee.getStudentType() == StudentType.PART_TIME) {
                        int hoursToCharge = totalHours-8 > 0 ? 3 : totalHours-5;
                        bill.addTransaction(
                                Transaction.createCharge(BigDecimal.valueOf(totalHours).multiply(fee.getAmount()), fee.getNote()));
                    }
                    break;
                case HEALTH_CENTER_GRAD_9_11: //TODO: FIX THIS!!!
                    if(!isFullTime && fee.getStudentType() == StudentType.PART_TIME) {
                        int hoursToCharge = totalHours-11 > 0 ? 3 : totalHours-8;
                        bill.addTransaction(
                                Transaction.createCharge(BigDecimal.valueOf(totalHours).multiply(fee.getAmount()), fee.getNote()));
                    }
                    break;
                case HEALTH_CENTER_GA:
                    if(student.isGradAssistant() && !student.isOutsideInsurance()) {
                        if(isFullTime && fee.getStudentType() == StudentType.FULL_TIME) {
                            bill.addTransaction(
                                    Transaction.createCharge(fee.getAmount(), fee.getNote()));
                        }
                        if(!isFullTime && fee.getStudentType() == StudentType.PART_TIME) {
                            bill.addTransaction(
                                    Transaction.createCharge(BigDecimal.valueOf(totalHours).multiply(fee.getAmount()), fee.getNote()));
                        }
                    }
                    break;
                case INSURANCE:
                    if(!student.isOutsideInsurance()) {
                        bill.addTransaction(
                                Transaction.createCharge(fee.getAmount(), fee.getNote()));
                    }
                    break;

                case INTERNATIONAL_SHORT_TERM:
                    if(student.isInternational()) {
                        if (student.getInternationalStatus() == InternationalStatus.SHORT_TERM) {
                            if (isFullTime && fee.getStudentType() == StudentType.FULL_TIME) {
                                bill.addTransaction(
                                        Transaction.createCharge(fee.getAmount(), fee.getNote()));
                            }
                            if (!isFullTime && fee.getStudentType() == StudentType.PART_TIME) {
                                bill.addTransaction(
                                        Transaction.createCharge(BigDecimal.valueOf(totalHours).multiply(fee.getAmount()), fee.getNote()));
                            }
                        }
                    }
                    break;
                case INTERNATIONAL_SPONSORED:
                    if(student.isInternational()) {
                        if (student.getInternationalStatus() == InternationalStatus.SPONSORED) {
                            if (isFullTime && fee.getStudentType() == StudentType.FULL_TIME) {
                                bill.addTransaction(
                                        Transaction.createCharge(fee.getAmount(), fee.getNote()));
                            }
                            if (!isFullTime && fee.getStudentType() == StudentType.PART_TIME) {
                                bill.addTransaction(
                                        Transaction.createCharge(BigDecimal.valueOf(totalHours).multiply(fee.getAmount()), fee.getNote()));
                            }
                        }
                    }
                    break;

                case MATRICULATION:
                case STUDY_ABROAD_INSURANCE:
                case STUDY_ABROAD_EXCHANGE:
                case NATIONAL_STUDENT_EXCHANGE:
                    break;
                default: System.out.println("Unhandled fee type: " + fee.getFeeType());
                    //TODO: throw exception here?

            }

        }

        return bill;
    }

    protected Bill generateCollegeCharges(StudentRecord student, Bill bill) {

        for(Fee fee: fees.getCollegeFees()) {
            switch (student.getCollege()) {
                case ARTS_AND_SCIENCES:
                    if(student.getCollege() == College.ARTS_AND_SCIENCES) {
                        switch (fee.getFeeType()) {
                            case AAS_AE_LAB:
                                //TODO: Check Courses for this fee?
                                break;
                            case AAS_AH_LAB:
                                //TODO: Check Courses for this fee?
                                break;
                            case AAS_DANCE_LAB:
                                //TODO: Check Courses for this fee?
                                break;
                            case AAS_FIELD:
                                //TODO: Check Courses for this fee?
                                break;
                            case AAS_HS_DRAMA:
                                //TODO: Check Courses for this fee?
                                break;
                            case AAS_LANGUAGE:
                                //TODO: Check Courses for this fee?
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
                            default: System.out.println("Unhandled fee type: " + fee.getFeeType());
                        }
                    }
                    break;
                case ENGINEERING_AND_COMPUTING:
                    if(student.getCollege() == College.ENGINEERING_AND_COMPUTING) {
                        switch (fee.getFeeType()) {
                            case EAC_APOGEE:
                                //TODO: Check Courses for this fee?
                                break;
                            case EAC_CSCE_101_102_LAB:
                                if(searchCourses(student.getCourses(), "CSCE 101")
                                        || searchCourses(student.getCourses(), "CSCE 102") ) {
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
                            default: System.out.println("Unhandled fee type: " + fee.getFeeType());
                        }
                    }
                    break;
                case GRADUATE_SCHOOL:
                default:
                    break;
            }

        }
        //TODO: Add transaction to bill
        return bill;
    }

    protected boolean searchCourses( List<Course> courses, String courseID) {
        for(Course course: courses) {
            if(course.getId().equalsIgnoreCase(courseID)) {
                return true;
            }
        }
        return false;
    }

    protected void processCourses(StudentRecord student) {

        for(Course course: student.getCourses()) {
            //if the course is online, add it to the online hours
            if(course.isOnline()) {
                this.onlineHours += course.getNumCredits();
            }

            //add the hours to the total number of hours
            this.totalHours += course.getNumCredits();

        }

        //figure out if the student is full time or not
        switch(student.getClassStatus()) {
            case FRESHMAN:
            case SOPHOMORE:
            case JUNIOR:
            case SENIOR:
                this.isFullTime = (totalHours >= UNDERGRAD_FULL_TIME_STUDENT_HOURS);
                break;
            case MASTERS:
            case PHD:
                this.isFullTime = (totalHours >= GRAD_FULL_TIME_STUDENT_HOURS);
                break;
            case GRADUATED:
            default:
        }
    }
}
