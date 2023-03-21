package edu.maszek.brainpowerquiz.exception;

public class QuestionCollectionException extends Exception {
    public QuestionCollectionException(String message) {
        super(message);
    }
    public static String NotFoundException(String id) {
        return "Question with id " + id + " not found!";
    }
    public static String AlreadyExists() {
        return "Question with given id already exist!";
    }
    public static String MultipleCorrectAnswerNotAccepted() { return "Only one answer can be true!"; }

}
