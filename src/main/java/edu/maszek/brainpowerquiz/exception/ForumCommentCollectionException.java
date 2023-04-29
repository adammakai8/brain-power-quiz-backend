package edu.maszek.brainpowerquiz.exception;

public class ForumCommentCollectionException extends Exception {
    public ForumCommentCollectionException(String message) {
        super(message);
    }
    public static String NotFoundException(String id) {
        return "ForumComment with id " + id + " not found!";
    }
    public static String TooLongAnswer() { return "This comment cannot be saved due to the too long (>250 character) answer!"; }
}
