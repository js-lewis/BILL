package edu.sc.csce740.model;

/**
 * This class represents a Course a Student can be enrolled in.
 */
public class Course {
    /**
     * A string with the name of the Course.
     */
    private String name;

    /**
     * A string with the id of the Course.
     */
    private String id;

    /**
     * An integer for the number of credits the class is worth.
     */
    private int numCredits;

    /**
     * A boolean indicating whether or not the class is offered online.
     */
    private boolean online;

    /**
     * The default constructor for a Course.
     */
    public Course() {
        this.name = "";
        this.id = "";
        this.numCredits = 0;
        this.online = false;
    }

    /**
     * The constructor for a Course.
     * @param name          the name of the Course.
     * @param id            the idea of the Course.
     * @param numCredits    the number of credits the Course is worth.
     * @param online        is the Course online?
     */
    public Course(String name, String id, int numCredits, boolean online) {
        this.name = name;
        this.id = id;
        setNumCredits(numCredits);
        this.online = online;
    }

    /**
     * Gets the name of the Course.
     * @return the name of the Course.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the Course.
     * @param name  the new name of the Course.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the ID of the Course.
     * @return the name of the Course.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the Course.
     * @param id    the new ID of the Course
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the number of credits the Course is worth.
     * @return  the number of credits the Course is worth.
     */
    public int getNumCredits() {
        return numCredits;
    }

    /**
     * Sets the number of Credit the Course is worth.
     * @param numCredits
     */
    public void setNumCredits(int numCredits) {
        if( numCredits < 0 ) {
            this.numCredits = 0;
        } else {
            this.numCredits = numCredits;
        }
    }

    /**
     * Returns whether or not the Course is offered online.
     * @return true if the course is available online. False if it is not.
     */
    public boolean isOnline() {
        return online;
    }

    /**
     * Sets the flag that tells if the Course is available online.
     * @param online    the new setting for the online flag.
     */
    public void setOnline(boolean online) {
        this.online = online;
    }

    /**
     * A String representation of a Course object.
     * @return a String with the Course data.
     */
    @Override
    public String toString() {
        return "name: " + getName() + "\n" +
                "id: " + getId() + "\n" +
                "numCredits: " + getNumCredits() + "\n" +
                "online: " + isOnline() + "\n";
    }
}
