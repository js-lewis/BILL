package edu.sc.csce740.model;

//Import required enumerations
import edu.sc.csce740.defines.StudentType;
import edu.sc.csce740.defines.FeeType;
import edu.sc.csce740.defines.Frequency;

//Import for Java classes
import java.math.BigDecimal;

/**
 * This class represents a Fee in the BILL system.
 */
public class Fee {
    /**
     * The type of student that this fee applies to.
     */
    private StudentType studentType;

    /**
     * The type/name or the fee.
     */
    private FeeType feeType;

    /**
     * How often this fee is charged.
     */
    private Frequency frequency;

    /**
     * The amount of the fee.
     */
    private BigDecimal amount;

    /**
     * A note about the fee.
     */
    private String note;

    /**
     * The default constructor for a Fee.
     */
    public Fee() {
        this.studentType = StudentType.ALL;
        this.feeType = FeeType.NONE;
        this.frequency = Frequency.ONE_TIME;
        this.amount = BigDecimal.ZERO;
        this.note = new String();
    }

    /**
     * A constructor for a Fee.
     * @param studentType   the type of student that this fee applies to.
     * @param feeType       the type/identifier of the fee.
     * @param frequency     what time segment is used to apply the fee.
     * @param amount        the amount of the fee.
     * @param note          a description of the fee.
     */
    public Fee(StudentType studentType, FeeType feeType, Frequency frequency, BigDecimal amount, String note) {
        this.studentType = studentType;
        this.feeType = feeType;
        this.frequency = frequency;
        this.amount = amount;
        this.note = note;
    }

    /**
     * Get the type of student that the fee applies to.
     * @return a StudentType that the fee applies to,
     */
    public StudentType getStudentType() {
        return studentType;
    }

    /**
     * Set the type of student that the fee applies to.
     * @param studentType   the type of student the fee applies to.
     */
    public void setStudentType(StudentType studentType) {
        this.studentType = studentType;
    }

    /**
     * Get the type of the fee that this is. This is similar to an identifier for the fee.
     * @return a FeeType with the type of fee this is.
     */
    public FeeType getFeeType() {
        return feeType;
    }

    /**
     * Set the type of fee this is.
     * @param feeType   the type of fee. This is similar to an identifier for the fee.
     */
    public void setFeeType(FeeType feeType) {
        this.feeType = feeType;
    }

    /**
     * Get how often the fee is charged.
     * @return a Frequency with how often the fee will be charged.
     */
    public Frequency getFrequency() {
        return frequency;
    }

    /**
     * Set the time interval for the fee. Some are based on credit hours while others are one-time fees.
     * @param frequency how often the fee is to be charged.
     */
    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    /**
     * Get the amount of the fee.
     * @return  the amount of the fee as a BigDecimal.
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Set the amount of the fee.
     * @param amount    a BigDecimal with the amount of the fee.
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Get a description of the fee.
     * @return  a String describing the fee.
     */
    public String getNote() {
        return note;
    }

    /**
     * Set the fee's description
     * @param note  a String containing the fee's description.
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * A String representation of a Fee object.
     * @return a String with the Fee data.
     */
    @Override
    public String toString() {
        return "feeType: " + getFeeType() + "\n" +
                "studentType: " + getStudentType() + "\n" +
                "frequency: " + getFrequency() + "\n" +
                "amount: " + getAmount() + "\n" +
                "note: " + getNote() + "\n";
    }
}
