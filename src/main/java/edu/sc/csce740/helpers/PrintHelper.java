package edu.sc.csce740.helpers;

//Model imports
import edu.sc.csce740.model.Transaction;
import edu.sc.csce740.model.Course;
import edu.sc.csce740.model.Fee;

//Java List import
import java.util.List;

/**
 * This class contains some helper functions that are useful for converting various lists of objects into Strings for
 * printing and debugging purposes.
 */
public class PrintHelper {
    /**
     * This method converts a List of Transactions into a String for printing etc.
     * @param transactions  a List<Transaction>
     * @return a String for printing etc.
     */
    public static String transactionListToString(List<Transaction> transactions) {
        String result = new String();

        //iterate through the list to build the String to return.
        if(transactions != null) {
            for (Transaction transaction : transactions) {
                result += transaction.toString() + "\n";
            }
        }

        //return the completed String.
        return result;
    }

    /**
     * This method converts a List of Courses into a String for printing etc.
     * @param courses  a List<Course>
     * @return a String for printing etc.
     */
    public static String courseListToString(List<Course> courses) {
        String result = new String();

        //iterate through the list to build the String to return.
        if(courses != null) {
            for (Course course : courses) {
                result += course + "\n";
            }
        }

        //return the completed String.
        return result;
    }

    /**
     * This method converts a List of Fees into a String for printing etc.
     * @param fees  a List<Fee>
     * @return a String for printing etc.
     */
    public static String feeListToString(List<Fee> fees) {
        String result = new String();

        //iterate through the list to build the String to return.
        if(fees != null) {
            for (Fee fee : fees) {
                result += fee + "\n";
            }
        }

        //return the completed String.
        return result;
    }
}
