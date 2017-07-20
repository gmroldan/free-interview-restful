package org.jocai.freeinterview.exceptions;

/**
 * Created by Gerardo Martín Roldán on 20/07/17.
 */
public class NoResultFoundException extends Exception {

    /**
     * Class constructor.
     *
     * @param message
     */
    public NoResultFoundException(String message) {
        super(message);
    }

    /**
     * Class constructor.
     *
     * @param message
     * @param cause
     */
    public NoResultFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
