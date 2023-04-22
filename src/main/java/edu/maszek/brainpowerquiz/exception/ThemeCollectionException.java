package edu.maszek.brainpowerquiz.exception;

public class ThemeCollectionException extends Exception {
    public ThemeCollectionException(String message) {
        super(message);
    }
    public static String NotFoundException(String id) {
        return "Theme with id " + id + " not found!";
    }
    public static String AlreadyExists(String text) { return "Theme " + text + " already exist!"; }
    public static String NotFoundByTextException(String text) {
        return "Theme with name " + text + " not found!";
    }
}
