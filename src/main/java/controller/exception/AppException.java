package main.java.controller.exception;

/**
 * Класс, выводящий сообщение об ошибке.
 */
public class AppException extends RuntimeException {
    public AppException(String message) {
        super(message);
    }
}
