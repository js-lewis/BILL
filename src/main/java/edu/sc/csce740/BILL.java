package edu.sc.csce740;

//Enumeration imports
import edu.sc.csce740.defines.College;
import edu.sc.csce740.defines.Role;
import edu.sc.csce740.defines.TransactionType;

//Model imports
import edu.sc.csce740.model.Bill;
import edu.sc.csce740.model.Date;
import edu.sc.csce740.model.StudentRecord;
import edu.sc.csce740.model.Transaction;
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
import java.util.Calendar;
import java.util.List;

//TODO: Implement Interface
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
     */
    public BILL() {
        userHelper = new UserHelper();
        studentHelper = new StudentHelper();
        currentUser = null;
    }


    /**
     * Method for test validation to get the UserHelper that is used by BILL.
     *
     * @return the UserHelper being used by BILL to manipulate User Records.
     */
    protected UserHelper getUserHelper() {
        return userHelper;
    }

    /**
     * Method for test validation to get the StudentHelper that is used by BILL.
     *
     * @return the StudentHelper being used by BILL to manipulate Student Records.
     */
    protected StudentHelper getStudentHelper() {
        return studentHelper;
    }

    /**
     * Mehtod for test validation to get the User who is currently logged into the system.
     *
     * @return the User currently logged in.
     */
    protected User getCurrentUser() {
        return currentUser;
    }

    protected boolean canBeAccessed(StudentRecord student) {

        // The User is editing themselves
        if (currentUser.getId().equalsIgnoreCase(student.getStudent().getId())) {
            return true;
        }
        // The User is an admin and editing a user in their College
        if (currentUser.getCollege() == student.getCollege() && currentUser.getRole() == Role.ADMIN) {
            return true;
        }

        // The User is an admin and editing a grad student
        if (currentUser.getCollege() == College.GRADUATE_SCHOOL) {
            switch (student.getClassStatus()) {
                case MASTERS:
                case PHD:
                    return true;
            }
        }

        return false;
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
            throws UnknownUserException, UserLoginException {

        //If there's a user logged in already, throw an exception
        if (currentUser != null) {
            throw new UserLoginException();
        }

        currentUser = userHelper.findUser(userId);

        //If no user was found, throw an exception
        if (currentUser == null) {
            throw new UnknownUserException();
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
        if (currentUser == null) {
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

        switch (currentUser.getRole()) {
            case STUDENT:
                throw new UnauthorizedUserException();
            case ADMIN:
                switch (currentUser.getCollege()) {
                    case GRADUATE_SCHOOL:
                        studentList = studentHelper.getGraduateStudents();
                        break;
                    case ENGINEERING_AND_COMPUTING:
                    case ARTS_AND_SCIENCES:
                        studentList = studentHelper.getStudentsByCollege(currentUser.getCollege());
                        break;
                }
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
        if (result == null) {
            throw new UserRetrievalException();
        }

        if (canBeAccessed(result)) {
        } else {
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

        if (toChange == null) {
            throw new UnknownUserException();
        }

        if (canBeAccessed(toChange)) {
            //TODO: Do we need to check to only save the parts of a student that the student can modify?
            //TODO: Check to see if this is correct.
            if(currentUser.getRole() == Role.STUDENT) {
                toChange.setStudent(record.getStudent());
            } else {
                studentHelper.removeStudentRecord(toChange);
                studentHelper.addStudentRecord(record);
            }
            if (permanent) {
                try {
                    studentHelper.writeStudentRecords();
                } catch (IOException e) {
                    throw new DataSaveException();
                }
            }
        } else {
            throw new UnauthorizedUserException();
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
    public void applyPayment(String userId, BigDecimal amount, String note)
            throws UnknownUserException, UnauthorizedUserException, PaymentException, PaymentSaveException {
        StudentRecord toChange = studentHelper.findStudentRecord(userId);

        if (toChange == null) {
            throw new UnknownUserException();
        }

        //If the user can modify the student
        if (canBeAccessed(toChange)) {
            //Get today's date
            Calendar today = Calendar.getInstance();
            //Create the payment
            Transaction newPayment = new Transaction( TransactionType.PAYMENT,
                    new Date( today.get(Calendar.MONTH)+1, today.get(Calendar.DATE), today.get(Calendar.YEAR) ),
                    amount,
                    note );

            //If the Transaction amount is negative, the payment cannot be made.
            if(amount.compareTo(BigDecimal.ZERO) == -1) {
                throw new PaymentException();
            }

            //Add the transaction
            toChange.addTransaction(newPayment);

            //Save the record
            try {
                studentHelper.writeStudentRecords();
            } catch (IOException e) {
                throw new PaymentSaveException();
            }

        } else {
            throw new UnauthorizedUserException();
        }
    }
}
