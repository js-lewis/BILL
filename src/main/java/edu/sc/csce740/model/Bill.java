package edu.sc.csce740.model;

import edu.sc.csce740.defines.College;
import edu.sc.csce740.defines.ClassStatus;

import edu.sc.csce740.helpers.PrintHelper;

public class Bill {
    private Student student;
    private College college;
    private ClassStatus classStatus;
    private double balance;
    private Transaction[] transactions;

    public Bill() {
    }

    public Bill(Student student, College college, ClassStatus classStatus, double balance, Transaction[] transactions) {
        this.student = student;
        this.college = college;
        this.classStatus = classStatus;
        this.balance = balance;
        this.transactions = transactions;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    public ClassStatus getClassStatus() {
        return classStatus;
    }

    public void setClassStatus(ClassStatus classStatus) {
        this.classStatus = classStatus;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Transaction[] getTransactions() {
        return transactions;
    }

    public void setTransactions(Transaction[] transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "student: \n" + getStudent() + "\n" +
                "college: " + getCollege() + "\n" +
                "classStatus: " + getClassStatus() + "\n" +
                "balance: " + getBalance() + "\n" +
                "transaction : \n" + PrintHelper.transactionListToString(getTransactions());
    }
}
