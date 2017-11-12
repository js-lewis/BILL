package edu.sc.csce740.model;

import java.util.List;
import java.util.ArrayList;

public class Fees {
    private List<Fee> residentUnderGradTuition;
    private List<Fee> nonResidentUnderGradTuition;
    private List<Fee> activeDutyTuition;
    private List<Fee> residentGraduateTuition;
    private List<Fee> nonResidentGraduateTuition;
    private List<Fee> generalFees;
    private List<Fee> collegeFees;

    public Fees() {
        residentUnderGradTuition = new ArrayList<Fee>();
        nonResidentUnderGradTuition = new ArrayList<Fee>();
        activeDutyTuition = new ArrayList<Fee>();
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

    public List<Fee> getNonResidentUnderGradTuition() {
        return nonResidentUnderGradTuition;
    }

    public void setNonResidentUnderGradTuition(List<Fee> nonResidentUnderGradTuition) {
        this.nonResidentUnderGradTuition = nonResidentUnderGradTuition;
    }

    public List<Fee> getActiveDutyTuition() {
        return activeDutyTuition;
    }

    public void setActiveDutyTuition(List<Fee> activeDutyTuition) {
        this.activeDutyTuition = activeDutyTuition;
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
