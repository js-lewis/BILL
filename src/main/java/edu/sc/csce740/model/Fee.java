package edu.sc.csce740.model;

import edu.sc.csce740.defines.StudentType;
import edu.sc.csce740.defines.FeeType;
import edu.sc.csce740.defines.Frequency;

public class Fee {
    private StudentType studentType;
    private FeeType feeType;
    private Frequency frequency;
    private double amount;
    private String note;

    public Fee() {
    }

    public Fee(StudentType studentType, FeeType feeType, Frequency frequency, double amount, String note) {
        this.studentType = studentType;
        this.feeType = feeType;
        this.frequency = frequency;
        this.amount = amount;
        this.note = note;
    }

    public StudentType getStudentType() {
        return studentType;
    }

    public void setStudentType(StudentType studentType) {
        this.studentType = studentType;
    }

    public FeeType getFeeType() {
        return feeType;
    }

    public void setFeeType(FeeType feeType) {
        this.feeType = feeType;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
