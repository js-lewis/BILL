package edu.sc.csce740.model;

import edu.sc.csce740.defines.College;
import edu.sc.csce740.defines.Role;

public class User extends Person{
    private Role role;
    private College college;

    public User() {
    }

    public User(String id, String firstName, String lastName, Role role, College college) {
        super(id, firstName, lastName);
        this.role = role;
        this.college = college;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }
}
