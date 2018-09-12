package org.jocai.freeinterview.controllers.advice;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.jocai.freeinterview.utils.Error;

import java.util.Date;
import java.util.List;

public class ErrorResponseTemplate {

    private String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    private Date timeStamp;

    private String method;
    private String endpoint;
    private List<Error> errors;

    /**
     * Class constructor.
     */
    public ErrorResponseTemplate() {}

    /**
     * Class constructor.
     *
     * @param message
     * @param timeStamp
     * @param method
     * @param endpoint
     * @param errors
     */
    public ErrorResponseTemplate(String message, Date timeStamp, String method, String endpoint, List<Error> errors) {
        this.message = message;
        this.timeStamp = timeStamp;
        this.method = method;
        this.endpoint = endpoint;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }
}
