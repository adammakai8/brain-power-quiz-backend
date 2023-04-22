package edu.maszek.brainpowerquiz.exception;

public class GameCollectionException extends Exception {
    public GameCollectionException(String message) {
        super(message);
    }
    public static String NotFoundException(String id) {
        return "Game with id " + id + " not found!";
    }
    public static String NotFoundByNameException(String name) {
        return "Game with name " + name + " not found!";
    }
}
