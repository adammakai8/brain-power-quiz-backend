package edu.maszek.brainpowerquiz.exception;

public class AuthenticationException extends Exception {
    public AuthenticationException(String message) {
        super(message);
    }
    public static String AlreadyExists(String name) { return "User " + name + " already exist!"; }
}
