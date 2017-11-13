package edu.sc.csce740.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import edu.sc.csce740.defines.*;
import edu.sc.csce740.exceptions.BillGenerationException;

import edu.sc.csce740.model.Bill;
import edu.sc.csce740.model.Course;
import edu.sc.csce740.model.Fee;
import edu.sc.csce740.model.Fees;
import edu.sc.csce740.model.StudentRecord;
import edu.sc.csce740.model.Transaction;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

//TODO: Reset files in mod directory before tests

public class BillHelper {
    private static String feeFile = "resources/data/fees.json";
    private boolean feesLoaded;
    private Fees fees;
    private boolean isFullTime;
    private boolean onlineClasses;
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
        onlineClasses = false;
        totalHours = 0;
    }

    protected void reset() {
        isFullTime = false;
        onlineClasses = false;
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

    public Bill generateBill(StudentRecord student)
            throws BillGenerationException {
        Bill returnBill = new Bill(student.getStudent(), student.getCollege(), student.getClassStatus(),
                0.00, new ArrayList<Transaction>()) ;

        if( !feesLoaded || student == null) {
            throw new BillGenerationException();
        }

        processCourses(student);
        returnBill = generateTuitionCharges(student, returnBill);
        returnBill = generateGeneralCharges(student, returnBill);
        returnBill = generateCollegeCharges(student, returnBill);

        //TODO: Add total Charge from Bill to the Student Record
        System.out.println(returnBill);
        reset();
        return returnBill;
    }

    protected Bill generateTuitionCharges(StudentRecord student, Bill bill)
            throws BillGenerationException {
        List<Fee> tuitionFees = new ArrayList<Fee>();
        //if the student is active duty, then use the active duty list
        switch( student.getClassStatus() ) {
            case FRESHMAN:
            case SOPHOMORE:
            case JUNIOR:
            case SENIOR:
                if( student.isActiveDuty() ){
                    tuitionFees = fees.getActiveDutyTuition();
                } else {
                    if(student.isResident()) {
                        tuitionFees = fees.getResidentUnderGradTuition();
                    } else {
                        tuitionFees = fees.getNonResidentUnderGradTuition();
                    }
                }
                break;
            case MASTERS:
            case PHD:
                if(student.isResident()) {
                    tuitionFees = fees.getResidentGraduateTuition();
                } else {
                    tuitionFees = fees.getNonResidentGraduateTuition();
                }
                break;
                //TODO: Check to see if a graduated student should generate no bill or an exception?
            case GRADUATED: throw new BillGenerationException();
        }

        if(!student.isFreeTuition()) {
            for (Fee fee : tuitionFees) {
                if (isFullTime) {
                    switch (student.getScholarship()) {
                        case GENERAL:
                            if (fee.getFeeType() == FeeType.GENERAL && fee.getStudentType() == StudentType.FULL_TIME) {
                                bill.addTransaction(
                                        Transaction.createCharge(BigDecimal.valueOf(fee.getAmount()), fee.getNote()));
                            }
                            break;
                        case DEPARTMENTAL:
                            if (fee.getFeeType() == FeeType.DEPARTMENTAL && fee.getStudentType() == StudentType.FULL_TIME) {
                                bill.addTransaction(
                                        Transaction.createCharge(BigDecimal.valueOf(fee.getAmount()), fee.getNote()));
                            }
                            break;
                        case ATHLETIC:
                            if (fee.getFeeType() == FeeType.ATHLETIC && fee.getStudentType() == StudentType.FULL_TIME) {
                                bill.addTransaction(
                                        Transaction.createCharge(BigDecimal.valueOf(fee.getAmount()), fee.getNote()));
                            }
                            break;
                        case WOODROW:
                            if (fee.getFeeType() == FeeType.WOODROW && fee.getStudentType() == StudentType.FULL_TIME) {
                                bill.addTransaction(
                                        Transaction.createCharge(BigDecimal.valueOf(fee.getAmount()), fee.getNote()));
                            }
                            break;
                        case SIMS:
                            if (fee.getFeeType() == FeeType.SIMS && fee.getStudentType() == StudentType.FULL_TIME) {
                                bill.addTransaction(
                                        Transaction.createCharge(BigDecimal.valueOf(fee.getAmount()), fee.getNote()));
                            }
                            break;
                        case NONE:
                        default:
                            if (fee.getStudentType() == StudentType.FULL_TIME) {
                                bill.addTransaction(
                                        Transaction.createCharge(BigDecimal.valueOf(fee.getAmount()), fee.getNote()));
                            }

                    }
                } else {
                    switch (student.getScholarship()) {
                        case GENERAL:
                            if (fee.getFeeType() == FeeType.GENERAL && fee.getStudentType() == StudentType.PART_TIME) {
                                bill.addTransaction(
                                        Transaction.createCharge(BigDecimal.valueOf(totalHours*fee.getAmount()), fee.getNote()));
                            }
                            break;
                        case DEPARTMENTAL:
                            if (fee.getFeeType() == FeeType.DEPARTMENTAL && fee.getStudentType() == StudentType.PART_TIME) {
                                bill.addTransaction(
                                        Transaction.createCharge(BigDecimal.valueOf(totalHours*fee.getAmount()), fee.getNote()));
                            }
                            break;
                        case ATHLETIC:
                            if (fee.getFeeType() == FeeType.ATHLETIC && fee.getStudentType() == StudentType.PART_TIME) {
                                bill.addTransaction(
                                        Transaction.createCharge(BigDecimal.valueOf(totalHours*fee.getAmount()), fee.getNote()));
                            }
                            break;
                        case WOODROW:
                            if (fee.getFeeType() == FeeType.WOODROW && fee.getStudentType() == StudentType.PART_TIME) {
                                bill.addTransaction(
                                        Transaction.createCharge(BigDecimal.valueOf(totalHours*fee.getAmount()), fee.getNote()));
                            }
                            break;
                        case SIMS:
                            if (fee.getFeeType() == FeeType.SIMS && fee.getStudentType() == StudentType.PART_TIME) {
                                bill.addTransaction(
                                        Transaction.createCharge(BigDecimal.valueOf(totalHours*fee.getAmount()), fee.getNote()));
                            }
                            break;
                        case NONE:
                        default:
                            if (fee.getStudentType() == StudentType.PART_TIME) {
                                bill.addTransaction(
                                        Transaction.createCharge(BigDecimal.valueOf(totalHours*fee.getAmount()), fee.getNote()));
                            }

                    }
                }
            }
        }

        return bill;
    }

    protected Bill generateGeneralCharges(StudentRecord student, Bill bill) {

        for(Fee fee: fees.getGeneralFees()) {
            switch (fee.getFeeType()) {
                case CAPSTONE:
                    //TODO: Is Capstone only the semester you enrolled - i.e. one time?
                    break;
                case STUDY_ABROAD:
                    if(student.getStudyAbroad() == StudyAbroad.REGULAR) {
                        bill.addTransaction(
                                Transaction.createCharge(BigDecimal.valueOf(fee.getAmount()), fee.getNote()));
                    }
                    break;
                case COHORT:
                    if(student.getStudyAbroad() == StudyAbroad.COHORT) {
                        bill.addTransaction(
                                Transaction.createCharge(BigDecimal.valueOf(fee.getAmount()), fee.getNote()));
                    }
                    break;
                case HEALTH_CENTER_UNDERGRAD_6_11: //TODO: FIX THIS!!!
                    if(!isFullTime && fee.getStudentType() == StudentType.PART_TIME) {
                        int hoursToCharge = totalHours-8 > 0 ? 3 : totalHours-5;
                        bill.addTransaction(
                                Transaction.createCharge(BigDecimal.valueOf(totalHours*fee.getAmount()), fee.getNote()));
                    }
                    break;
                case HEALTH_CENTER_GRAD_6_8: //TODO: FIX THIS!!!
                    if(!isFullTime && fee.getStudentType() == StudentType.PART_TIME) {
                        int hoursToCharge = totalHours-8 > 0 ? 3 : totalHours-5;
                        bill.addTransaction(
                                Transaction.createCharge(BigDecimal.valueOf(totalHours*fee.getAmount()), fee.getNote()));
                    }
                    break;
                case HEALTH_CENTER_GRAD_9_11: //TODO: FIX THIS!!!
                    if(!isFullTime && fee.getStudentType() == StudentType.PART_TIME) {
                        int hoursToCharge = totalHours-11 > 0 ? 3 : totalHours-8;
                        bill.addTransaction(
                                Transaction.createCharge(BigDecimal.valueOf(totalHours*fee.getAmount()), fee.getNote()));
                    }
                    break;
                case HEALTH_CENTER_GA:
                    if(student.isGradAssistant() && !student.isOutsideInsurance()) {
                        if(isFullTime && fee.getStudentType() == StudentType.FULL_TIME) {
                            bill.addTransaction(
                                    Transaction.createCharge(BigDecimal.valueOf(fee.getAmount()), fee.getNote()));
                        }
                        if(!isFullTime && fee.getStudentType() == StudentType.PART_TIME) {
                            bill.addTransaction(
                                    Transaction.createCharge(BigDecimal.valueOf(totalHours*fee.getAmount()), fee.getNote()));
                        }
                    }
                    break;
                case INSURANCE:
                    if(!student.isOutsideInsurance()) {
                        bill.addTransaction(
                                Transaction.createCharge(BigDecimal.valueOf(fee.getAmount()), fee.getNote()));
                    }
                    break;
                case INTERNATIONAL:
                    //TODO: Check for one time international fee
                    break;
                case INTERNATIONAL_SHORT_TERM:
                    if(student.getInternationalStatus() == InternationalStatus.SHORT_TERM) {
                        if(isFullTime && fee.getStudentType() == StudentType.FULL_TIME) {
                            bill.addTransaction(
                                    Transaction.createCharge(BigDecimal.valueOf(fee.getAmount()), fee.getNote()));
                        }
                        if(!isFullTime && fee.getStudentType() == StudentType.PART_TIME) {
                            bill.addTransaction(
                                    Transaction.createCharge(BigDecimal.valueOf(totalHours*fee.getAmount()), fee.getNote()));
                        }
                    }
                    break;
                case INTERNATIONAL_SPONSORED:
                    if(student.getInternationalStatus() == InternationalStatus.SPONSORED) {
                        if(isFullTime && fee.getStudentType() == StudentType.FULL_TIME) {
                            bill.addTransaction(
                                    Transaction.createCharge(BigDecimal.valueOf(fee.getAmount()), fee.getNote()));
                        }
                        if(!isFullTime && fee.getStudentType() == StudentType.PART_TIME) {
                            bill.addTransaction(
                                    Transaction.createCharge(BigDecimal.valueOf(totalHours*fee.getAmount()), fee.getNote()));
                        }
                    }
                    break;
                case TECHNOLOGY:
                    if(isFullTime && fee.getStudentType() == StudentType.FULL_TIME) {
                        bill.addTransaction(
                                Transaction.createCharge(BigDecimal.valueOf(fee.getAmount()), fee.getNote()));
                    }
                    if(!isFullTime && fee.getStudentType() == StudentType.PART_TIME) {
                        bill.addTransaction(
                                Transaction.createCharge(BigDecimal.valueOf(totalHours*fee.getAmount()), fee.getNote()));
                    }
                    break;
                default: //TODO: throw exception here?

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
                            default:
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
                                if(searchCourses(student.getCourseList(), "CSCE 101")
                                        || searchCourses(student.getCourseList(), "CSCE 102") ) {
                                    bill.addTransaction(
                                            Transaction.createCharge(BigDecimal.valueOf(fee.getAmount()), fee.getNote()));
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
                                            Transaction.createCharge(BigDecimal.valueOf(fee.getAmount()), fee.getNote()));
                                }
                                if (!isFullTime && fee.getStudentType() == StudentType.PART_TIME) {
                                    bill.addTransaction(
                                            Transaction.createCharge(BigDecimal.valueOf(totalHours * fee.getAmount()), fee.getNote()));
                                }
                                break;
                            case EAC_SYS_DESIGN:
                                //TODO: Check Courses for this fee?
                                break;
                            default:
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

        for(Course course: student.getCourseList()) {
            this.totalHours += course.getNumCredits();
            if(course.isOnline()) {
                this.onlineClasses = true;
            }
        }

        switch(student.getClassStatus()) {
            case FRESHMAN:
            case SOPHOMORE:
            case JUNIOR:
            case SENIOR:
                this.isFullTime = (totalHours >= 12);
                break;
            case MASTERS:
            case PHD:
                this.isFullTime = (totalHours >= 6);
                break;
            case GRADUATED:
            default:
        }
    }
}
