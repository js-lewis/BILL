package edu.sc.csce740;

//Model imports
import edu.sc.csce740.model.Bill;
import edu.sc.csce740.model.StudentRecord;
import edu.sc.csce740.model.User;

//Exception imports
import edu.sc.csce740.exceptions.BillGenerationException;
import edu.sc.csce740.exceptions.BillRetrievalException;
import edu.sc.csce740.exceptions.DataSaveException;
import edu.sc.csce740.exceptions.PaymentException;
import edu.sc.csce740.exceptions.PaymentSaveException;
import edu.sc.csce740.exceptions.RecordDataLoadException;
import edu.sc.csce740.exceptions.UnauthorizedUserException;
import edu.sc.csce740.exceptions.UserDataLoadException;
import edu.sc.csce740.exceptions.UserLoginException;
import edu.sc.csce740.exceptions.UserLogoutException;
import edu.sc.csce740.exceptions.UserRetrievalException;

//Helper imports
import edu.sc.csce740.helpers.UserHelper;

//Java imports
import java.math.BigDecimal;
import java.util.List;

//public class BILL implements BILLIntf {
public class BILL {
    /**
     * A list of users of the system.
     */
    private List<User> users;

    /**
     * The current User.
     */
    private User currentUser;

    /**
     * Constructor to initialize an empty BILL instance.
     *
     */
    public BILL() {
        currentUser = null;
    }

    /**
     * Loads the list of system usernames and permissions.
     *
     * @param usersFile the filename of the users file.
     * @throws Exception for I/O errors.  SEE NOTE IN CLASS HEADER.
     */
    public void loadUsers(String usersFile)
            throws UserDataLoadException {
        UserHelper helper = new UserHelper(usersFile);

        try {
            users = helper.readUsers();
            if( users.isEmpty() ) {
                System.out.println( "no users. this is bad");
            } else {
                System.out.println( "There are " + Integer.toString(users.size()) + " users." );
                helper.printUsers();
            }
        } catch (Exception e) {
            throw new UserDataLoadException();
        }
    }

    /**
     * Loads the list of system transcripts.
     *
     * @param recordsFile the filename of the transcripts file.
     * @throws Exception for I/O errors.  SEE NOTE IN CLASS HEADER.
     */
//    public void loadRecords(String recordsFile)
//            throws RecordDataLoadException {
//
//    }

    /**
     * Sets the user id of the user currently using the system.
     *
     * @param userId the id of the user to log in.
     * @throws Exception if the user id is invalid.  SEE NOTE IN CLASS HEADER.
     */
//    public void logIn(String userId)
//            throws UserLoginException {
//
//    }

    /**
     * Closes the current session, logs the user out, and clears any session data.
     *
     * @throws Exception if the user id is invalid.  SEE NOTE IN CLASS HEADER.
     */
//    public void logOut()
//            throws UserLogoutException {

//    }

    /**
     * Gets the user id of the user currently using the system.
     *
     * @return the user id of the user currently using the system.
     */
//    public String getUser() {
//
//    }

    /**
     * Gets a list of the userIds of the students that an admin can view.
     *
     * @return a list containing the userId of for each student in the
     * college belonging to the current user
     * @throws Exception is the current user is not an admin.
     */
//    public List<String> getStudentIDs()
//            throws UnauthorizedUserException {
//
//    }

    /**
     * Gets the raw student record data for a given userId.
     *
     * @param userId the identifier of the student.
     * @return the student record data.
     * @throws Exception if the form data could not be retrieved. SEE NOTE IN
     *                   CLASS HEADER.
     */
//    public StudentRecord getRecord(String userId)
//            throws UnauthorizedUserException, UserRetrievalException {
//
//    }

    /**
     * Saves a new set of student data to the records data.
     *
     * @param userId    the student ID to overwrite.
     * @param record    the new student record
     * @param permanent a status flag indicating whether (if false) to make a
     *                  temporary edit to the in-memory structure or (if true) a permanent edit.
     * @throws Exception if the transcript data could not be saved or failed
     *                   a validity check.  SEE NOTE IN CLASS HEADER.
     */
//    public void editRecord(String userId, StudentRecord record, Boolean permanent)
//            throws UnauthorizedUserException, DataSaveException {
//
//    }

    /**
     * Generates current bill.
     *
     * @param userId the student to generate the bill for.
     * @throws Exception if the bill could not be generated.
     *                   SEE NOTE IN CLASS HEADER.
     * @returns the student's bill in a data class matching the I/O file.
     */
//    public Bill generateBill(String userId)
//            throws UnauthorizedUserException, BillGenerationException  {
//
//    }

    /**
     * Generates a list of transactions for a chosen period.
     *
     * @param userId     the student to generate the list for.
     * @param startMonth the month of the start date.
     * @param startDay   the day of the start date.
     * @param startYear  the year of the start date.
     * @param endMonth   the month of the end date.
     * @param endDay     the day of the end date.
     * @param endYear    the year of the end date.
     * @throws Exception if the bill could not be generated.
     *                   SEE NOTE IN CLASS HEADER.
     * @returns the student's bill in a data class matching the I/O file.
     */
//    public Bill viewCharges(String userId, int startMonth, int startDay, int startYear,
//                            int endMonth, int endDay, int endYear)
//            throws UnauthorizedUserException, BillRetrievalException {
//
//    }

    /**
     * Makes a payment for the student
     *
     * @param userId the student to make a payment for.
     * @param amount amount to apply to the balance.
     * @param note   a string indicating the reason for the payment
     * @throws Exception if the payment fails a validity check
     *                   or fails to save to file.
     *                   SEE NOTE IN CLASS HEADER.
     */
//    public void applyPayment(String userId, BigDecimal amount, String note)
//            throws UnauthorizedUserException, PaymentException, PaymentSaveException {
//
//    }
}
