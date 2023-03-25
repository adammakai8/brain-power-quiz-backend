package edu.maszek.brainpowerquiz.exception;

public class ForumCollectionException extends Exception {
    public ForumCollectionException(String message) {
        super(message);
    }
    public static String NotFoundException(String id) {
        return "Forum with id " + id + " not found!";
    }
}
