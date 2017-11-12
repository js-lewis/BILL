package edu.sc.csce740.model;

import java.util.List;
import java.util.ArrayList;

public class Fees {
    private List<Fee> residentUnderGradTuition;
    private List<Fee> nonResidentTUnderGraduation;
    private List<Fee> activeDuty;
    private List<Fee> residentGraduateTuition;
    private List<Fee> nonResidentGraduateTuition;
    private List<Fee> generalFees;
    private List<Fee> collegeFees;

    public Fees() {
        residentUnderGradTuition = new ArrayList<Fee>();
        nonResidentTUnderGraduation = new ArrayList<Fee>();
        activeDuty = new ArrayList<Fee>();
        residentGraduateTuition = new ArrayList<Fee>();
        nonResidentGraduateTuition = new ArrayList<Fee>();
        generalFees = new ArrayList<Fee>();
        collegeFees = new ArrayList<Fee>();
    }

    public List<Fee> getResidentUnderGradTuition() {
        return residentUnderGradTuition;
    }

    public void setResidentUnderGradTuition(List<Fee> residentUnderGradTuition) {
        this.residentUnderGradTuition = residentUnderGradTuition;
    }

    public List<Fee> getNonResidentTUnderGraduation() {
        return nonResidentTUnderGraduation;
    }

    public void setNonResidentTUnderGraduation(List<Fee> nonResidentTUnderGraduation) {
        this.nonResidentTUnderGraduation = nonResidentTUnderGraduation;
    }

    public List<Fee> getActiveDuty() {
        return activeDuty;
    }

    public void setActiveDuty(List<Fee> activeDuty) {
        this.activeDuty = activeDuty;
    }

    public List<Fee> getResidentGraduateTuition() {
        return residentGraduateTuition;
    }

    public void setResidentGraduateTuition(List<Fee> residentGraduateTuition) {
        this.residentGraduateTuition = residentGraduateTuition;
    }

    public List<Fee> getNonResidentGraduateTuition() {
        return nonResidentGraduateTuition;
    }

    public void setNonResidentGraduateTuition(List<Fee> nonResidentGraduateTuition) {
        this.nonResidentGraduateTuition = nonResidentGraduateTuition;
    }

    public List<Fee> getGeneralFees() {
        return generalFees;
    }

    public void setGeneralFees(List<Fee> generalFees) {
        this.generalFees = generalFees;
    }

    public List<Fee> getCollegeFees() {
        return collegeFees;
    }

    public void setCollegeFees(List<Fee> collegeFees) {
        this.collegeFees = collegeFees;
    }
}
