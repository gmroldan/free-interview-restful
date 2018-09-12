package org.jocai.freeinterview.utils;

public enum Error {
    UNKNOWN_ERROR(5000, "Unknown error!!!", "Please contact development team wit information on 'how to reproduce this error'. Thank you for your help and support.", "http://developer.freeinterview.com/unknownerror"),
    INTERVIEW_ALREADY_EXISTS(6001, "Interview with the provided 'interviewer' and 'interviewDate' already exists.", "Please use PUT for update instead of POST", "http://developer.freeinterview.com/errors#6001"),
    INTERVIEWER_ALREADY_EXISTS(6002, "Interviewer with the provided 'firstName' and 'lastName' already exists.", "Please use PUT for update instead of POST", "http://developer.freeinterview.com/errors#6002"),
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
}
