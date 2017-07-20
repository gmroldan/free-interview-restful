package org.jocai.freeinterview.exceptions;

/**
 * Created by martin on 20/07/17.
 */
public class FreeInterviewServiceException extends Exception {

    /**
     * Class constructor.
     *
     * @param message
     */
    public FreeInterviewServiceException(String message) {
        super(message);
    }

    /**
     * Class constructor.
     *
     * @param message
     * @param cause
     */
    public FreeInterviewServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
