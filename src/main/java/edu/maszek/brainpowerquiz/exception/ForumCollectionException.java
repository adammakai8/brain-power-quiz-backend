package edu.maszek.brainpowerquiz.exception;

public class ForumCollectionException extends Exception {
    public ForumCollectionException(String message) {
        super(message);
    }
    public static String NotFoundException(String id) {
        return "Forum with id " + id + " not found!";
    }
    public static String TooLongQuestion() { return "This forum cannot be saved due to the too long (>250 character) question!"; }
}
