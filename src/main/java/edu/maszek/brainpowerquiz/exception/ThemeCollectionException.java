package edu.maszek.brainpowerquiz.exception;

public class ThemeCollectionException extends Exception {
    public ThemeCollectionException(String message) {
        super(message);
    }

    public static String NotFoundException(String id) {
        return "Theme with id " + id + " not found!";
    }

    public static String AlreadyExists() { return "Theme with given id already exist!"; }
}
