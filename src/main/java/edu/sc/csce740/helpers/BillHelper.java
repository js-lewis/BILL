package edu.sc.csce740.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import edu.sc.csce740.defines.FeeType;
import edu.sc.csce740.defines.StudentType;
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

    public BillHelper() {
        fees = new Fees();
        try {
            readFees();
            feesLoaded = true;
        } catch (Exception e) {
            feesLoaded = false;
        }
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

        System.out.println(student.getStudent().getId());
        returnBill = generateTuitionCharges(student, returnBill);
        returnBill = generateGeneralCharges(student, returnBill);
        returnBill = generateCollegeCharges(student, returnBill);

        //TODO: Add total Charge from Bill to the Student Record
        System.out.println(returnBill);
        return returnBill;
    }

    public Bill generateTuitionCharges(StudentRecord student, Bill bill)
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

        //TODO: Does it make sense to process the courses separately?
        boolean isFullTime = fullTimeStudent(student.getCourseList());

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
                    int hours = getHours(student.getCourseList());
                    switch (student.getScholarship()) {
                        case GENERAL:
                            if (fee.getFeeType() == FeeType.GENERAL && fee.getStudentType() == StudentType.PART_TIME) {
                                bill.addTransaction(
                                        Transaction.createCharge(BigDecimal.valueOf(hours*fee.getAmount()), fee.getNote()));
                            }
                            break;
                        case DEPARTMENTAL:
                            if (fee.getFeeType() == FeeType.DEPARTMENTAL && fee.getStudentType() == StudentType.PART_TIME) {
                                bill.addTransaction(
                                        Transaction.createCharge(BigDecimal.valueOf(hours*fee.getAmount()), fee.getNote()));
                            }
                            break;
                        case ATHLETIC:
                            if (fee.getFeeType() == FeeType.ATHLETIC && fee.getStudentType() == StudentType.PART_TIME) {
                                bill.addTransaction(
                                        Transaction.createCharge(BigDecimal.valueOf(hours*fee.getAmount()), fee.getNote()));
                            }
                            break;
                        case WOODROW:
                            if (fee.getFeeType() == FeeType.WOODROW && fee.getStudentType() == StudentType.PART_TIME) {
                                bill.addTransaction(
                                        Transaction.createCharge(BigDecimal.valueOf(hours*fee.getAmount()), fee.getNote()));
                            }
                            break;
                        case SIMS:
                            if (fee.getFeeType() == FeeType.SIMS && fee.getStudentType() == StudentType.PART_TIME) {
                                bill.addTransaction(
                                        Transaction.createCharge(BigDecimal.valueOf(hours*fee.getAmount()), fee.getNote()));
                            }
                            break;
                        case NONE:
                        default:
                            if (fee.getStudentType() == StudentType.PART_TIME) {
                                bill.addTransaction(
                                        Transaction.createCharge(BigDecimal.valueOf(hours*fee.getAmount()), fee.getNote()));
                            }

                    }
                }
            }
        }

        return bill;
    }

    public Bill generateGeneralCharges(StudentRecord student, Bill bill) {

        //TODO: Add transaction to bill
        return bill;
    }

    public Bill generateCollegeCharges(StudentRecord student, Bill bill) {

        //TODO: Add transaction to bill
        return bill;
    }

    public boolean fullTimeStudent(List<Course> courses) {
        int hours = 0;
        for(Course course: courses) {
            hours += course.getNumCredits();
        }

        return (hours >= 12);
    }

    public int getHours(List<Course> courses) {
        int hours = 0;
        for(Course course: courses) {
            hours += course.getNumCredits();
        }

        return hours;
    }
}
