package org.jocai.freeinterview.controllers.advice;

import org.jocai.freeinterview.exceptions.FreeInterviewServiceException;
import org.jocai.freeinterview.exceptions.NoResultFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by Gerardo Martín Roldán on 20/07/17.
 */
@ControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler{

    @ExceptionHandler({NoResultFoundException.class})
    public ResponseEntity handleNoResultException(Exception ex, WebRequest request) {
        return this.handleExceptionInternal(
                ex,
                ex.getMessage(),
                new HttpHeaders(),
                HttpStatus.NO_CONTENT,
                request);
    }

    @ExceptionHandler({FreeInterviewServiceException.class})
    public ResponseEntity handleServiceException(Exception ex, WebRequest request) {
        return this.handleExceptionInternal(
                ex,
                ex.getMessage(),
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                request);
    }
}
