package org.jocai.freeinterview.utils;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Code 5000 - Unknown error
 * Code 6xxx - Any database error such as duplicate keys
 * Code 7xxx - Validation errors
 *
 * @author Gerardo Martín Roldán
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Error {
    UNKNOWN_ERROR(5000, "Unknown error!!!", "Please contact development team wit information on 'how to reproduce this error'. Thank you for your help and support.", "http://developer.freeinterview.com/unknownerror"),
    INTERVIEW_ALREADY_EXISTS(6000, "Interview with the provided 'interviewer' and 'interviewDate' already exists.", "Example: Please use PUT for update instead of POST", "http://developer.freeinterview.com/errors#6000"),
    INTERVIEWER_ALREADY_EXISTS(6001, "Interviewer with the provided 'firstName' and 'lastName' already exists.", "Example: Please use PUT for update instead of POST", "http://developer.freeinterview.com/errors#6001"),
    INTERVIEWER_INVALID_FIRST_NAME(7000, "Interviewer has an invalid 'firstName'. It cannot be null or empty", "Please send an interviewer with a first name.", "http://developer.freeinterview.com/errors#7000"),
    INTERVIEWER_INVALID_LAST_NAME(7001, "Interviewer has an invalid 'lastName'. It cannot be null or empty", "Please send an interviewer with a last name.", "http://developer.freeinterview.com/errors#7001"),
    ;

    private final int code;
    private final String description;
    private final String hints;
    private final String info;

    Error(int code, String description, String hints, String info) {
        this.code = code;
        this.description = description;
        this.hints = hints;
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getHints() {
        return hints;
    }

    public String getInfo() {
        return info;
    }
}
