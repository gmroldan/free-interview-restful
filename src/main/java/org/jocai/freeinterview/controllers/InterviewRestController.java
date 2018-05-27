package org.jocai.freeinterview.controllers;

import java.net.URI;

import org.jocai.freeinterview.exceptions.NoResultFoundException;
import org.jocai.freeinterview.model.Interview;
import org.jocai.freeinterview.services.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


/**
 * Created by Gerardo Martín Roldán on 02/06/17.
 */
@RestController
@RequestMapping(value = "/interviews")
public class InterviewRestController {
    @Autowired
    private InterviewService interviewService;

    @GetMapping(value = "/{id}")
    public ResponseEntity getInterview(@PathVariable Long id) throws NoResultFoundException {
        Interview interview = this.interviewService.getInteview(id);
        HttpStatus httpStatus
                = interview != null ? HttpStatus.OK : HttpStatus.NO_CONTENT;

        return new ResponseEntity(interview, httpStatus);
    }

    @GetMapping
    public ResponseEntity getAllInterviews(Pageable pageable) {
        Page<Interview> interviewPage = this.interviewService.getAllInterviews(pageable);

        return ResponseEntity.ok(interviewPage);
    }

    @PostMapping
    public ResponseEntity createNewInterview(@RequestBody Interview interview) {
        Long interviewId = this.interviewService.save(interview);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(interviewId).toUri();

        return ResponseEntity.created(location).build();
    }
}
