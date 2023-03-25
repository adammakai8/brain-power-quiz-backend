package edu.maszek.brainpowerquiz.exception;

public class ForumCommentCollectionException extends Exception {
    public ForumCommentCollectionException(String message) {
        super(message);
    }
    public static String NotFoundException(String id) {
        return "ForumComment with id " + id + " not found!";
    }
}
