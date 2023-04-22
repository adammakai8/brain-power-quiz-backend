package edu.maszek.brainpowerquiz.exception;

public class UserCollectionException extends Exception {
    public UserCollectionException(String message) {
        super(message);
    }
    public static String NotFoundException(String id) {
        return "User with id " + id + " not found!";
    }
    public static String AlreadyExists(String name) { return "User " + name + " already exist!"; }
}
