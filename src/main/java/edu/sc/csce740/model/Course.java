package edu.sc.csce740.model;

public class Course {
    private String name;
    private String id;
    private int numCredits;
    private boolean online;

    public Course() {
    }

    public Course(String name, String id, int numCredits, boolean online) {
        this.name = name;
        this.id = id;
        this.numCredits = numCredits;
        this.online = online;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumCredits() {
        return numCredits;
    }

    public void setNumCredits(int numCredits) {
        this.numCredits = numCredits;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    @Override
    public String toString() {
        return "name: " + getName() + "\n" +
                "id: " + getId() + "\n" +
                "numCredits: " + getNumCredits() + "\n" +
                "online: " + isOnline() + "\n";
    }
}
