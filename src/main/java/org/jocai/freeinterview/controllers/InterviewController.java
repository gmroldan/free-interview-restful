package org.jocai.freeinterview.controllers;

import org.jocai.freeinterview.model.Interview;
import org.jocai.freeinterview.services.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Created by martin on 02/06/17.
 */
@RestController
@RequestMapping(value = "/interviews")
public class InterviewController {
    @Autowired
    private InterviewService interviewService;

    @RequestMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    public ResponseEntity getTestInterview(@RequestParam Long id) {
        Interview interview = this.interviewService.getInteview(id);
        HttpStatus httpStatus
                = interview != null ? HttpStatus.OK : HttpStatus.NO_CONTENT;

        return new ResponseEntity(interview, httpStatus);
    }

    @RequestMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    public ResponseEntity createNewInterview(@RequestBody Interview interview) {
        this.interviewService.createNewInterview(interview);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
