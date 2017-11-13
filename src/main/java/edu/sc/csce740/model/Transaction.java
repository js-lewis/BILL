package edu.sc.csce740.model;

//Import required enumerications
import edu.sc.csce740.defines.TransactionType;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * This class represents a Transaction in the BILL system.
 */
public class Transaction {
    /**
     * The type of the Transaction.
     */
    private TransactionType type;

    /**
     * The Date the Transaction took place.
     */
    private Date transactionDate;

    /**
     * The amount of the Transaction.
     */
    private BigDecimal amount;

    /**
     * A note with details about the Transaction.
     */
    private String note;

    /**
     * The default constructor for a Transaction.
     */
    public Transaction() {
        this.type = TransactionType.PAYMENT;
        this.transactionDate = new Date();
        this.amount = BigDecimal.ZERO;
        this.note = "";
    }

    /**
     * The constructor for a Transaction.
     * @param type              the type of the Transaction.
     * @param transactionDate   the Date of the Transaction.
     * @param amount            the positive amount of the Transaction.
     * @param note              a note that describes the Transaction.
     */
    public Transaction(TransactionType type, Date transactionDate, BigDecimal amount, String note) {
        this.type = type;
        this.transactionDate = transactionDate;
        setAmount(amount);
        this.note = note;
    }

    /**
     * Gets the type of the Transaction.
     * @return the type of the Transaction.
     */
    public TransactionType getType() {
        return type;
    }

    /**
     * Sets the type of the Transaction.
     * @param type  the new type of the Transaction.
     */
    public void setType(TransactionType type) {
        this.type = type;
    }

    /**
     * Gets the Date of the Transaction.
     * @return the Date of the Transaction.
     */
    public Date getTransactionDate() {
        return transactionDate;
    }

    /**
     * Sets the Date of the Transaction.
     * @param transactionDate   the new Date of the Transaction.
     */
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    /**
     * Gets the amount of the Transaction.
     * @return the amount of the Transaction as a positive number.
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the amount of the transaction as a positive number. If the number is negative, the absolute value will be
     * used.
     * @param amount    the positive amount of the Transaction.
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount.abs();
    }

    /**
     * Gets a note about the Transaction.
     * @return  A note about the Transaction.
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets a note about the Transaction.
     * @param note  The new note to add to the Transaction.
     */
    public void setNote(String note) {
        this.note = note;
    }

    public static Transaction createPayment(BigDecimal amount, String note) {
        //Get today's date
        Calendar today = Calendar.getInstance();
        //Create the payment
        Transaction newPayment = new Transaction( TransactionType.PAYMENT,
                new Date( today.get(Calendar.MONTH)+1, today.get(Calendar.DATE), today.get(Calendar.YEAR) ),
                amount,
                note );

        return newPayment;
    }

    public static Transaction createCharge(BigDecimal amount, String note) {
        //Get today's date
        Calendar today = Calendar.getInstance();
        //Create the payment
        Transaction newCharge = new Transaction( TransactionType.CHARGE,
                new Date( today.get(Calendar.MONTH)+1, today.get(Calendar.DATE), today.get(Calendar.YEAR) ),
                amount,
                note );

        return newCharge;
    }

    /**
     * A String representation of a Transaction object.
     * @return a String with the Transaction data.
     */
    @Override
    public String toString() {
        return "type: " + getType() + "\n" +
                "transactionDate: " + getTransactionDate() + "\n" +
                "amount: " + getAmount() + "\n" +
                "note: " + getNote() + "\n";
    }
}
