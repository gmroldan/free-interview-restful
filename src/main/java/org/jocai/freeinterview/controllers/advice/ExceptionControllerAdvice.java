package org.jocai.freeinterview.controllers.advice;

import org.jocai.freeinterview.exceptions.FreeInterviewServiceException;
import org.jocai.freeinterview.exceptions.NoResultFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        List<String> errors = this.handleConstraintViolations(ex);

        ErrorResponseTemplate errorResponseTemplate = new ErrorResponseTemplate();
        errorResponseTemplate.setMessage("The requested resulted in a violation of a defined integrity constraint");
        errorResponseTemplate.setTimeStamp(new Date());
        errorResponseTemplate.setMethod(((ServletWebRequest) request).getRequest().getMethod());
        errorResponseTemplate.setEndpoint(((ServletWebRequest) request).getRequest().getRequestURL().toString());
        errorResponseTemplate.setErrors(errors);

        return new ResponseEntity<>(errorResponseTemplate, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    // TODO: Create a custom exception for data integrity
    private List<String> handleConstraintViolations(final DataIntegrityViolationException ex) {
        final List<String> errors = new ArrayList<>();

        if (ex.getCause() instanceof javax.validation.ConstraintViolationException) {
            javax.validation.ConstraintViolationException cause
                    = (javax.validation.ConstraintViolationException) ex.getCause();

            for (ConstraintViolation<?> violation : cause.getConstraintViolations()) {
                errors.add(violation.getMessage());
            }
        } else {
            org.hibernate.exception.ConstraintViolationException cause
                    = (org.hibernate.exception.ConstraintViolationException) ex.getCause();

            errors.add(cause.getSQLException().getMessage());
        }

        return errors;
    }
}
