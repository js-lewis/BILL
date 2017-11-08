package edu.sc.csce740.exceptions;

public class UnauthorizedUserException extends Exception {
    public UnauthorizedUserException() {
    }

    @Override
    public String toString() {
        return "UnauthorizedUserException{}";
    }
}
