package ru.sbrf.javacourse.exeption.example.basic;

/**
 * Thrown when system can not find requested item.
 */
public class ItemNotFoundException extends Exception {

    private static final long serialVersionUID = -2586414477138775105L;

    public ItemNotFoundException() {
    }

    public ItemNotFoundException(String message) {
        super(message);
    }

    public ItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ItemNotFoundException(Throwable cause) {
        super(cause);
    }

    public ItemNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
