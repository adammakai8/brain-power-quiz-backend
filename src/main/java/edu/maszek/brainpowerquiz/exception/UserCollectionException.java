package edu.maszek.brainpowerquiz.exception;

public class UserCollectionException extends Exception {
    public UserCollectionException(String message) {
        super(message);
    }
    public static String NotFoundException(String name) {
        return "User with name " + name + " not found!";
    }
    public static String AlreadyExists() {
        return "User with given name already exist!";
    }
    public static String LoginFailed() {
        return "Invalid username or password!";
    }
}
