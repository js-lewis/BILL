package edu.sc.csce740.helpers;

import edu.sc.csce740.model.Transaction;
import edu.sc.csce740.model.Course;

public class PrintHelper {
    public static String transactionListToString(Transaction[] transactions) {
        String result = new String();

        if(transactions != null) {
            for (Transaction transaction : transactions) {
                result += transaction.toString() + "\n";
            }
        }

        return result;
    }

    public static String courseListToString(Course[] courses) {
        String result = new String();

        if(courses != null) {
            for (Course course : courses) {
                result += course + "\n";
            }
        }

        return result;
    }
}
