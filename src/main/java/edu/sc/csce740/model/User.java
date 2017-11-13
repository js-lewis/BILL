package edu.sc.csce740.model;

//Import required enumerations
import edu.sc.csce740.defines.College;
import edu.sc.csce740.defines.Role;

/**
 * This class represents a User in the BILL system.
 */
public class User extends Person{
    /**
     * The role of a BILL User.
     */
    private Role role;

    /**
     * The college associated with a BILL User.
     */
    private College college;

    /**
     * The default constructor for a User.
     */
    public User() {
        super();
        role = Role.STUDENT;
        college = College.ENGINEERING_AND_COMPUTING;
    }

    /**
     * The constructor for a User.
     * @param id        the ID number of the User.
     * @param firstName the first name of the User.
     * @param lastName  the last name of the User.
     * @param role      the Role of the User.
     * @param college   the College the User is associated with.
     */
    public User(String id, String firstName, String lastName, Role role, College college) {
        super(id, firstName, lastName);
        this.role = role;
        this.college = college;
    }

    /**
     * Gets the Role of the User.
     * @return the Role of the User.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the Role of the User.
     * @param role  the new Role of the User.
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Gets the College the User is associated with.
     * @return the User's College.
     */
    public College getCollege() {
        return college;
    }

    /**
     * Sets the User's College.
     * @param college   the new College the User is attending.
     */
    public void setCollege(College college) {
        this.college = college;
    }

    /**
     * A String representation of a User.
     * @return a String with the User data.
     */
    @Override
    public String toString() {
        return super.toString() + "\n" +
               "role: " + getRole() + "\n" +
               "college: " + getCollege() + "\n";
    }
}
