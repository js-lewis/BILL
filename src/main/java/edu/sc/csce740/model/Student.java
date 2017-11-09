package edu.sc.csce740.model;

public class Student extends Person {
    private String phone;
    private String emailAddress;
    private String addressStreet;
    private String addressCity;
    private String addressState;
    private String addressPostalCode;

    public Student() {
    }

    public Student(String id, String firstName, String lastName, String phone, String emailAddress, String addressStreet, String addressCity, String addressState, String addressPostalCode) {
        super(id, firstName, lastName);
        this.phone = phone;
        this.emailAddress = emailAddress;
        this.addressStreet = addressStreet;
        this.addressCity = addressCity;
        this.addressState = addressState;
        this.addressPostalCode = addressPostalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressState() {
        return addressState;
    }

    public void setAddressState(String addressState) {
        this.addressState = addressState;
    }

    public String getAddressPostalCode() {
        return addressPostalCode;
    }

    public void setAddressPostalCode(String addressPostalCode) {
        this.addressPostalCode = addressPostalCode;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
               "phone: " + getPhone() + "\n" +
               "emailAddress: " + getEmailAddress() + "\n" +
               "addressStreet: " + getAddressStreet() + "\n" +
               "addressCity: " + getAddressCity() + "\n" +
               "addressState: " + getAddressStreet() + "\n" +
               "addressPostalCode: " + getAddressPostalCode() + "\n";
    }
}
