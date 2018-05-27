package org.jocai.freeinterview.services;

import org.jocai.freeinterview.exceptions.FreeInterviewServiceException;
import org.jocai.freeinterview.exceptions.NoResultFoundException;
import org.jocai.freeinterview.model.Interviewer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Gerardo Martín Roldán on 16/06/17.
 */
public interface InterviewerService {
    Interviewer getInteviewer(Long id) throws NoResultFoundException, FreeInterviewServiceException;

    Page<Interviewer> getAllInterviewers(Pageable pageable);

    Long save(Interviewer interviewer);
}
