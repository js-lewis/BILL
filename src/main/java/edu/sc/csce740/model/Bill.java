package edu.sc.csce740.model;

//Import required enumerications
import edu.sc.csce740.defines.College;
import edu.sc.csce740.defines.ClassStatus;

//Import help to printing the transaction array
import edu.sc.csce740.helpers.PrintHelper;

//Import to support an Array List for the transactions.
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class represents a Student's Bill in the BILL system.
 */
public class Bill {
    /**
     * The Student whose Bill this is.
     */
    private Student student;

    /**
     * The College that the Student is enrolled in.
     */
    private College college;

    /**
     * The Student's Class Status.
     */
    private ClassStatus classStatus;

    /**
     * The Student's remaining account balance.
     */
    private double balance;

    /**
     * An array of all of the transactions that have been made on the Student's account.
     */
    private List<Transaction> transactions;

    /**
     * The default constructor for a Bill.
     */
    public Bill() {
        this.student = new Student();
        this.college = null;
        this.classStatus = ClassStatus.FRESHMAN;
        balance = 0.0;
        transactions = new ArrayList<Transaction>();
    }

    /**
     * A constructor for a Bill.
     * @param student       the Student the Bill is for.
     * @param college       the College the Student is enrolled in.
     * @param classStatus   the Class Status of the Student.
     * @param balance       the Student's current account balance.
     * @param transactions  the list of transactions that have been made on the Student's account.
     */
    public Bill(Student student, College college, ClassStatus classStatus, double balance, Transaction[] transactions) {
        this.student = student;
        this.college = college;
        this.classStatus = classStatus;
        this.balance = balance;
        this.transactions = new ArrayList<Transaction>(Arrays.asList(transactions));
    }

    /**
     * Gets the Student associated with the Bill.
     * @return the object that represents the Student.
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Sets the Student whose Bill this is.
     * @param student   The student object who this Bill will belong to.
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * Gets the College associate with the Student whose Bill this is.
     * @return the College the Student is enrolled in.
     */
    public College getCollege() {
        return college;
    }

    /**
     * Sets the College the Student is enrolled in.
     * @param college   The College the Student is enrolled in.
     */
    public void setCollege(College college) {
        this.college = college;
    }

    /**
     * Gets the Class Status of the Student.
     * @return the Class Status of the Student.
     */
    public ClassStatus getClassStatus() {
        return classStatus;
    }

    /**
     * Sets the Class Status of the Student
     * @param classStatus   The Class Status of the Student
     */
    public void setClassStatus(ClassStatus classStatus) {
        this.classStatus = classStatus;
    }

    /**
     * Gets the current balance of the Student's acccount.
     * @return a double representation of the Student's account balance.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the Student's account balance.
     * @param balance   The new balance of the Student's account.
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Gets an array of all of the transactions associated with a Student's account.
     * @return an array of Transactions performed on the Student's account.
     */
    public Transaction[] getTransactions() {
        return transactions.toArray(new Transaction[transactions.size()]);
    }

    /**
     * Sets the list of transactions associated with a Student's account.
     * @param transactions  an array of Transactions performed on the Student's account.
     */
    public void setTransactions(Transaction[] transactions) {
        this.transactions = new ArrayList<Transaction>(Arrays.asList(transactions));
    }

    /**
     * Gets an array of all of the transactions associated with a Student's account.
     * @return an array of Transactions performed on the Student's account.
     */
    public List<Transaction> getTransactionList() {
        return transactions;
    }

    /**
     * Adds a transaction to the current transaction array.
     * @param transaction   A new transaction to add to the bill.
     */
    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    /**
     * A String representation of a Bill object.
     * @return a String with the Bill data.
     */
    @Override
    public String toString() {
        return "student: \n" + getStudent() + "\n" +
                "college: " + getCollege() + "\n" +
                "classStatus: " + getClassStatus() + "\n" +
                "balance: " + getBalance() + "\n" +
                "transaction : \n" + PrintHelper.transactionListToString(getTransactions());
    }
}
