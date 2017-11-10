package edu.sc.csce740;

//Model imports
import edu.sc.csce740.defines.Role;
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
import edu.sc.csce740.exceptions.UnknownUserException;
import edu.sc.csce740.exceptions.UserDataLoadException;
import edu.sc.csce740.exceptions.UserLoginException;
import edu.sc.csce740.exceptions.UserLogoutException;
import edu.sc.csce740.exceptions.UserRetrievalException;

//Helper imports
import edu.sc.csce740.helpers.UserHelper;
import edu.sc.csce740.helpers.StudentHelper;
import edu.sc.csce740.helpers.BillHelper;

//Java imports
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

//public class BILL implements BILLIntf {
public class BILL {
    /**
     * A helper to access the Users of the system.
     */
    private UserHelper userHelper;

    /**
     * A helper to access the Student Record data of the system.
     */
    private StudentHelper studentHelper;

    /**
     * The current User.
     */
    private User currentUser;

    /**
     * Constructor to initialize an empty BILL instance.
     *
     */
    public BILL() {
        userHelper = new UserHelper();
        studentHelper = new StudentHelper();
        currentUser = null;
    }


    protected UserHelper getUserHelper() {
        return userHelper;
    }

    protected void setUserHelper(UserHelper userHelper) {
        this.userHelper = userHelper;
    }

    protected StudentHelper getStudentHelper() {
        return studentHelper;
    }

    protected void setStudentHelper(StudentHelper studentHelper) {
        this.studentHelper = studentHelper;
    }

    /**
     * Loads the list of system usernames and permissions.
     *
     * @param usersFile the filename of the users file.
     * @throws Exception for I/O errors.  SEE NOTE IN CLASS HEADER.
     */
    public void loadUsers(String usersFile)
            throws UserDataLoadException {
        //Set the filename to load the users from
        userHelper.setFileName(usersFile);

        //Load the users
        try {
            userHelper.readUsers();
        //If an IO Exception occurs, throw an exception specific to this function
        } catch (IOException e) {
            throw new UserDataLoadException();
        }
    }

    /**
     * Loads the list of system transcripts.
     *
     * @param recordsFile the filename of the transcripts file.
     * @throws Exception for I/O errors.  SEE NOTE IN CLASS HEADER.
     */
    public void loadRecords(String recordsFile)
            throws RecordDataLoadException {

        studentHelper.setFileName(recordsFile);

        //Load the Student Records
        try {
            studentHelper.readStudentRecords();
        //If an IO Exception occurs, throw an exception specific to this function
        } catch (IOException e) {
            throw new RecordDataLoadException();
        }
    }

    /**
     * Sets the user id of the user currently using the system.
     *
     * @param userId the id of the user to log in.
     * @throws Exception if the user id is invalid.  SEE NOTE IN CLASS HEADER.
     */
    public void logIn(String userId)
            throws UserLoginException {
        //If there's a user logged in already, throw an exception
        if( currentUser != null ) {
            throw new UserLoginException();
        }

        currentUser = userHelper.findUser(userId);

        //If no user was found, throw an exception
        if(currentUser == null) {
            throw new UserLoginException();
        }
    }

    /**
     * Closes the current session, logs the user out, and clears any session data.
     *
     * @throws Exception if no one is logged in.  SEE NOTE IN CLASS HEADER.
     */
     public void logOut()
            throws UserLogoutException {

         //If no user was logged in, throw an exception
         if( currentUser == null ) {
             throw new UserLogoutException();
         } else {
             currentUser = null;
         }

    }

    /**
     * Gets the user id of the user currently using the system.
     *
     * @return the user id of the user currently using the system.
     */
    public String getUser() {
        return currentUser.getId();
    }

    /**
     * Gets a list of the userIds of the students that an admin can view.
     *
     * @return a list containing the userId of for each student in the
     * college belonging to the current user
     * @throws Exception is the current user is not an admin.
     */
    public List<String> getStudentIDs()
            throws UnauthorizedUserException {
        List<String> studentList = null;

        switch(currentUser.getRole()) {
            case STUDENT: throw new UnauthorizedUserException();
            case ADMIN:
                studentList = studentHelper.getStudentsByCollege(currentUser.getCollege().toString());
                break;
            case GRADUATE_PROGRAM_COORDINATOR:
                studentList = studentHelper.getGraduateStudents();
                break;
        }

        return studentList;
    }

    /**
     * Gets the raw student record data for a given userId.
     *
     * @param userId the identifier of the student.
     * @return the student record data.
     * @throws Exception if the form data could not be retrieved. SEE NOTE IN
     *                   CLASS HEADER.
     */
    public StudentRecord getRecord(String userId)
            throws UnauthorizedUserException, UserRetrievalException {
        StudentRecord result = studentHelper.findStudentRecord(userId);

        //If no user was found, throw an exception
        if(result == null) {
            throw new UserRetrievalException();
        }

        if( result.getCollege() != currentUser.getCollege() ) {
            throw new UnauthorizedUserException();
        }

        return result;
    }

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
    public void editRecord(String userId, StudentRecord record, Boolean permanent)
            throws UnauthorizedUserException, UnknownUserException, DataSaveException {
        StudentRecord toChange = studentHelper.findStudentRecord(userId);

        if( toChange == null ) {
            throw new UnknownUserException();
        }

        if( toChange.getCollege() != currentUser.getCollege() ) {
            throw new UnauthorizedUserException();
        }

        studentHelper.removeStudentRecord(toChange);
        studentHelper.addStudentRecord(record);

        if( permanent ) {
            try{
                studentHelper.writeStudentRecords();
            } catch (IOException e) {
                throw new DataSaveException();
            }
        }
    }

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
