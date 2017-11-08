package edu.sc.csce740.model;

import edu.sc.csce740.defines.TransactionType;

public class Transaction {
    private TransactionType type;
    private Date transactionDate;
    private double amount;
    private String note;

    public Transaction() {
    }

    public Transaction(TransactionType type, Date transactionDate, double amount, String note) {
        this.type = type;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.note = note;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
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
