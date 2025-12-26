package org.example.exception;

public class GameOverException extends Exception {

    public GameOverException(){
        super();
    }
    public GameOverException(String message) {
        super(message);
    }
}
