package org.jocai.freeinterview.controllers;

import java.util.List;
import org.jocai.freeinterview.model.Interviewer;
import org.jocai.freeinterview.services.InterviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Gerardo Martín Roldán on 16/06/git17.
 */
@RestController
@RequestMapping(value = "/interviewers")
public class InterviewerRestController {
    @Autowired
    private InterviewerService interviewerService;

    @GetMapping(value = "/{id}")
    public ResponseEntity getInterviewer(@PathVariable Long id) {
        Interviewer interviewer = this.interviewerService.getInteviewer(id);

        HttpStatus httpStatus
                = interviewer != null ? HttpStatus.OK : HttpStatus.NO_CONTENT;

        return new ResponseEntity(interviewer, httpStatus);
    }

    @GetMapping
    public ResponseEntity getAllInterviewers() {
        List<Interviewer> interviewerList = this.interviewerService.getAllInterviewers();

        HttpStatus httpStatus = !CollectionUtils.isEmpty(interviewerList) ? HttpStatus.OK : HttpStatus.NO_CONTENT;

        return new ResponseEntity(interviewerList, httpStatus);
    }

}
