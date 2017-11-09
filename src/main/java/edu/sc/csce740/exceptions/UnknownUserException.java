package edu.sc.csce740.exceptions;

public class UnknownUserException extends Exception {
    public UnknownUserException() {
    }

    @Override
    public String toString() {
        return "UnknownUserException{}";
    }
}
