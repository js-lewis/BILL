package edu.sc.csce740.model;

/**
 * This class represents a Person in the BILL system.
 */
public class Person {
    /**
     * The ID of this Person.
     */
    private String id;

    /**
     * The first name of this Person.
     */
    private String firstName;

    /**
     * This last name of the Person.
     */
    private String lastName;

    /**
     * The default construction for a Person.
     */
    public Person() {
        this.id = "";
        this.firstName = "";
        this.lastName = "";
    }

    /**
     * The constructor for a Person.
     * @param id        the ID of the Person
     * @param firstName the first name of the Person
     * @param lastName  the last name of the Person
     */
    public Person(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Gets the ID of the Person.
     * @return the ID of the Person.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets a new ID for the Person.
     * @param id    the new ID of the Person.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the first name of the Person.
     * @return  the first name of the Person.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets a new first name for the Person.
     * @param firstName the new first name of the Person.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the Person.
     * @return  the last name of the Person.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets a new last name for the Person.
     * @param lastName  the new last name of the Person.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * A String representation of a Person object.
     * @return a String with the Person data.
     */
    @Override
    public String toString() {
        return "id: " + this.getId() + "\n" +
               "firstName: " + this.getFirstName() + "\n" +
               "lastName: " + this.getLastName();
    }
}
