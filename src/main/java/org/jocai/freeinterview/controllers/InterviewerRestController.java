package org.jocai.freeinterview.controllers;

import java.net.URI;
import java.util.List;
import org.jocai.freeinterview.exceptions.FreeInterviewServiceException;
import org.jocai.freeinterview.exceptions.NoResultFoundException;
import org.jocai.freeinterview.model.Interview;
import org.jocai.freeinterview.model.Interviewer;
import org.jocai.freeinterview.services.InterviewService;
import org.jocai.freeinterview.services.InterviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Created by Gerardo Martín Roldán on 16/06/git17.
 */
@RestController
@RequestMapping(value = "/interviewers")
public class InterviewerRestController {
    @Autowired
    private InterviewerService interviewerService;

    @Autowired
    private InterviewService interviewService;

    @GetMapping(value = "/{id}")
    public ResponseEntity getInterviewer(@PathVariable Long id)
            throws NoResultFoundException, FreeInterviewServiceException {
        Interviewer interviewer = this.interviewerService.getInteviewer(id);

        HttpStatus httpStatus
                = interviewer != null ? HttpStatus.OK : HttpStatus.NO_CONTENT;

        return new ResponseEntity(interviewer, httpStatus);
    }

    @GetMapping
    public ResponseEntity getAllInterviewers(Pageable pageable) {
        Page<Interviewer> interviewerPage = this.interviewerService.getAllInterviewers(pageable);

        return ResponseEntity.ok(interviewerPage);
    }

    @GetMapping(value = "/{id}/interviews")
    public ResponseEntity getInterviews(@PathVariable Long id) {
        List<Interview> interviewList = this.interviewService.getInterviews(id);

        HttpStatus httpStatus
                = !CollectionUtils.isEmpty(interviewList) ? HttpStatus.OK : HttpStatus.NO_CONTENT;

        return new ResponseEntity(interviewList, httpStatus);
    }

    @PostMapping
    public ResponseEntity createInterviewer(@RequestBody Interviewer interviewer)
            throws FreeInterviewServiceException {
        Long id = this.interviewerService.save(interviewer);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();

        return ResponseEntity.created(location).build();
    }

}
