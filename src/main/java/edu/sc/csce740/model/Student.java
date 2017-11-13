package edu.sc.csce740.model;

/**
 * This class represents a Student in the BILL system.
 */
public class Student extends Person {
    /**
     * The phone number of this Student.
     */
    private String phone;

    /**
     * The email address of this Student.
     */
    private String emailAddress;

    /**
     * The street address of this Student.
     */
    private String addressStreet;

    /**
     * The city of this Student.
     */
    private String addressCity;

    /**
     * The two character state code of this Student.
     */
    private String addressState;

    /**
     * The zip code of this Student.
     */
    private String addressPostalCode;

    /**
     * The default constructor for a Student.
     */
    public Student() {
        super();
        this.phone = "";
        this.emailAddress = "";
        this.addressStreet = "";
        this.addressCity = "";
        this.addressState = "";
        this.addressPostalCode = "";
    }

    /**
     * The constructor for a Student.
     * @param id                the ID number of the Student.
     * @param firstName         the first name of the Student.
     * @param lastName          the last name of the Student.
     * @param phone             the phone number of the Student.
     * @param emailAddress      the email address of the Student.
     * @param addressStreet     the street address of the Student.
     * @param addressCity       the city of the Student.
     * @param addressState      the state code of the Student.
     * @param addressPostalCode the zip code of the Student.
     */
    public Student(String id, String firstName, String lastName, String phone, String emailAddress, String addressStreet, String addressCity, String addressState, String addressPostalCode) {
        super(id, firstName, lastName);
        this.phone = phone;
        this.emailAddress = emailAddress;
        this.addressStreet = addressStreet;
        this.addressCity = addressCity;
        this.addressState = addressState;
        this.addressPostalCode = addressPostalCode;
    }

    /**
     * Gets the phone number of the Student.
     * @return the phone number of the Student.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the Student.
     * @param phone the new phone number for the Student.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the email address of the Student.
     * @return the email address of the Student.
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the email address of the Student.
     * @param emailAddress  the new email address for the Student.
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Get the street address of the Student.
     * @return the street address of the Student.
     */
    public String getAddressStreet() {
        return addressStreet;
    }

    /**
     * Sets the street address of the Student.
     * @param addressStreet the new street address for the Student.
     */
    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    /**
     * Gets the city of the Student's addess.
     * @return the city of the Student's address.
     */
    public String getAddressCity() {
        return addressCity;
    }

    /**
     * Sets the city of the Student's address.
     * @param addressCity   the new city for the Student's address.
     */
    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    /**
     * Gets the state of the Student's address.
     * @return the state of the Student's address.
     */
    public String getAddressState() {
        return addressState;
    }

    /**
     * Sets the state of the Student's address.
     * @param addressState  the new state for the Student's address.
     */
    public void setAddressState(String addressState) {
        this.addressState = addressState;
    }

    /**
     * Gets the zip code of the Student's address.
     * @return the zip code of the Student's address.
     */
    public String getAddressPostalCode() {
        return addressPostalCode;
    }

    /**
     * Sets the zip code of the Student's address.
     * @param addressPostalCode the new zip code for the Student's address.
     */
    public void setAddressPostalCode(String addressPostalCode) {
        this.addressPostalCode = addressPostalCode;
    }

    /**
     * A String representation of a Student object.
     * @return a String with the Student data.
     */
    @Override
    public String toString() {
        return super.toString() + "\n" +
               "phone: " + getPhone() + "\n" +
               "emailAddress: " + getEmailAddress() + "\n" +
               "addressStreet: " + getAddressStreet() + "\n" +
               "addressCity: " + getAddressCity() + "\n" +
               "addressState: " + getAddressState() + "\n" +
               "addressPostalCode: " + getAddressPostalCode() + "\n";
    }
}
