package edu.sc.csce740.model;

//Import models
import edu.sc.csce740.helpers.PrintHelper;

//Import List components
import java.util.List;
import java.util.ArrayList;

/**
 * This class represents a group of all of the Fees in the BILL system.
 */
public class Fees {
    /**
     * Tuition Fees for undergraduate residents of SC.
     */
    private List<Fee> residentUnderGradTuition;

    /**
     * Tuition Fees for undergraduate non-residents of SC.
     */
    private List<Fee> nonResidentUnderGradTuition;

    /**
     * Tuition Fees for military members on Active Duty.
     */
    private List<Fee> activeDutyTuition;

    /**
     * Tuition Fees for graduate residents of SC.
     */
    private List<Fee> residentGraduateTuition;

    /**
     * Tuition Fees for graduate non-residents of SC.
     */
    private List<Fee> nonResidentGraduateTuition;

    /**
     * General Fees that are applicable to all students.
     */
    private List<Fee> generalFees;

    /**
     * Fees that are applicable only to certain classes or colleges.
     */
    private List<Fee> collegeFees;

    /**
     * Default constructor for a group of Fees.
     */
    public Fees() {
        residentUnderGradTuition = new ArrayList<Fee>();
        nonResidentUnderGradTuition = new ArrayList<Fee>();
        activeDutyTuition = new ArrayList<Fee>();
        residentGraduateTuition = new ArrayList<Fee>();
        nonResidentGraduateTuition = new ArrayList<Fee>();
        generalFees = new ArrayList<Fee>();
        collegeFees = new ArrayList<Fee>();
    }

    /**
     * Get a List of fees associated with resident undergrad tuition.
     * @return a List of fees
     */
    public List<Fee> getResidentUnderGradTuition() {
        return residentUnderGradTuition;
    }

    /**
     * Set a List of fees pertaining to resident undergrad tuition.
     * @param residentUnderGradTuition  a list of fees.
     */
    public void setResidentUnderGradTuition(List<Fee> residentUnderGradTuition) {
        this.residentUnderGradTuition = residentUnderGradTuition;
    }

    /**
     * Get a List of fees associated with non-resident undergrad tuition.
     * @return a List of fees
     */
    public List<Fee> getNonResidentUnderGradTuition() {
        return nonResidentUnderGradTuition;
    }

    /**
     * Set a List of fees pertaining to non-resident undergrad tuition.
     * @param nonResidentUnderGradTuition  a list of fees.
     */
    public void setNonResidentUnderGradTuition(List<Fee> nonResidentUnderGradTuition) {
        this.nonResidentUnderGradTuition = nonResidentUnderGradTuition;
    }

    /**
     * Get a List of fees associated with tuition for active duty military members.
     * @return a List of fees
     */
    public List<Fee> getActiveDutyTuition() {
        return activeDutyTuition;
    }

    /**
     * Set a List of fees pertaining to active duty tuition.
     * @param activeDutyTuition  a list of fees.
     */
    public void setActiveDutyTuition(List<Fee> activeDutyTuition) {
        this.activeDutyTuition = activeDutyTuition;
    }

    /**
     * Get a List of fees associated with resident graduate tuition.
     * @return a List of fees
     */
    public List<Fee> getResidentGraduateTuition() {
        return residentGraduateTuition;
    }

    /**
     * Set a List of fees pertaining to resident graduate tuition.
     * @param residentGraduateTuition  a list of fees.
     */
    public void setResidentGraduateTuition(List<Fee> residentGraduateTuition) {
        this.residentGraduateTuition = residentGraduateTuition;
    }

    /**
     * Get a List of fees associated with non-resident graduate tuition.
     * @return a List of fees
     */
    public List<Fee> getNonResidentGraduateTuition() {
        return nonResidentGraduateTuition;
    }

    /**
     * Set a List of fees pertaining to non-resident graduate tuition.
     * @param nonResidentGraduateTuition  a list of fees.
     */
    public void setNonResidentGraduateTuition(List<Fee> nonResidentGraduateTuition) {
        this.nonResidentGraduateTuition = nonResidentGraduateTuition;
    }

    /**
     * Get a List of fees applicable to all students.
     * @return a List of fees
     */
    public List<Fee> getGeneralFees() {
        return generalFees;
    }

    /**
     * Set a List of fees applicable to all students.
     * @param generalFees  a list of fees.
     */
    public void setGeneralFees(List<Fee> generalFees) {
        this.generalFees = generalFees;
    }

    /**
     * Get a List of fees associated with various colleges.
     * @return a List of fees
     */
    public List<Fee> getCollegeFees() {
        return collegeFees;
    }

    /**
     * Set a List of fees applicable to all students that attend specific colleges/classes.
     * @param collegeFees  a list of fees.
     */
    public void setCollegeFees(List<Fee> collegeFees) {
        this.collegeFees = collegeFees;
    }

    /**
     * A String representation of a Fees object.
     * @return a String with the Fees data.
     */
    @Override
    public String toString() {
        //TODO: Implement to String for all fees.
        return "residentUnderGradTuition" + PrintHelper.feeListToString(this.residentUnderGradTuition) + "\n" +
                "nonResidentUnderGradTuition" + PrintHelper.feeListToString(this.nonResidentUnderGradTuition) + "\n" +
                "residentGraduateTuition" + PrintHelper.feeListToString(this.residentGraduateTuition) + "\n" +
                "nonResidentGraduateTuition" + PrintHelper.feeListToString(this.nonResidentGraduateTuition) + "\n" +
                "activeDutyTuition" + PrintHelper.feeListToString(this.activeDutyTuition) + "\n" +
                "generalFees" + PrintHelper.feeListToString(this.generalFees) + "\n" +
                "collegeFees" + PrintHelper.feeListToString(this.collegeFees) + "\n";
    }
}
