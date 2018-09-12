package org.jocai.freeinterview.exceptions;

import org.jocai.freeinterview.controllers.advice.ErrorResponseTemplate;
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
    private List<String> getErrors() {
        final RuntimeException ex = (RuntimeException) this.getCause();
        final List<String> errors = new ArrayList<>();

        if (ex instanceof javax.validation.ConstraintViolationException) {
            Set<ConstraintViolation<?>> violationSet
                    = ((javax.validation.ConstraintViolationException) ex).getConstraintViolations();

            for (ConstraintViolation<?> violation : violationSet) {
                errors.add(violation.getMessage());
            }
        } else if (ex instanceof DataIntegrityViolationException) {
            org.hibernate.exception.ConstraintViolationException cause
                    = (org.hibernate.exception.ConstraintViolationException) ex.getCause();

            errors.add(cause.getSQLException().getMessage());
        }

        return errors;
    }
}
