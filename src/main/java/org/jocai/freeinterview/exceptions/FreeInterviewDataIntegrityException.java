package org.jocai.freeinterview.exceptions;

import org.jocai.freeinterview.controllers.advice.ErrorResponseTemplate;
import org.jocai.freeinterview.utils.Error;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class FreeInterviewDataIntegrityException extends RuntimeException {

    /**
     * Class constructor.
     *
     * @param e
     */
    public FreeInterviewDataIntegrityException(RuntimeException e) {
        super(e);
    }

    /**
     * Returns the error response for the current exception.
     *
     * @param request
     * @return
     */
    public ErrorResponseTemplate buildErrorResponse(final WebRequest request) {
        ErrorResponseTemplate errorResponseTemplate = new ErrorResponseTemplate();
        errorResponseTemplate.setMessage("The requested resulted in a violation of a defined integrity constraint");
        errorResponseTemplate.setTimeStamp(new Date());
        errorResponseTemplate.setMethod(((ServletWebRequest) request).getRequest().getMethod());
        errorResponseTemplate.setEndpoint(((ServletWebRequest) request).getRequest().getRequestURL().toString());
        errorResponseTemplate.setErrors(this.getErrors());

        return errorResponseTemplate;
    }

    /**
     * Returns a list with all data violations.
     *
     * @return
     */
    private List<Error> getErrors() {
        final RuntimeException ex = (RuntimeException) this.getCause();
        final List<Error> errors = new ArrayList<>();

        if (ex instanceof javax.validation.ConstraintViolationException) {
            Set<ConstraintViolation<?>> violationSet
                    = ((javax.validation.ConstraintViolationException) ex).getConstraintViolations();

            for (ConstraintViolation<?> violation : violationSet) {
                if ("First name cannot be null.".equals(violation.getMessage())) {
                    errors.add(Error.INTERVIEWER_INVALID_FIRST_NAME);
                } else if ("Last name cannot be null.".equals(violation.getMessage())) {
                    errors.add(Error.INTERVIEWER_INVALID_LAST_NAME);
                } else {
                    errors.add(Error.UNKNOWN_ERROR);
                }
            }
        } else if (ex instanceof DataIntegrityViolationException) {
            org.hibernate.exception.ConstraintViolationException cause
                    = (org.hibernate.exception.ConstraintViolationException) ex.getCause();

            if ("INTERVIEWER_UC".equals(cause.getConstraintName())) {
                errors.add(Error.INTERVIEWER_ALREADY_EXISTS);
            } else {
                errors.add(Error.UNKNOWN_ERROR);
            }
        }

        return errors;
    }
}
