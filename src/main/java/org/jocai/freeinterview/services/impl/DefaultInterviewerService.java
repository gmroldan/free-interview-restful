package org.jocai.freeinterview.services.impl;

import java.util.Optional;

import org.jocai.freeinterview.exceptions.FreeInterviewDataIntegrityException;
import org.jocai.freeinterview.exceptions.FreeInterviewServiceException;
import org.jocai.freeinterview.exceptions.NoResultFoundException;
import org.jocai.freeinterview.model.Interviewer;
import org.jocai.freeinterview.repository.InterviewerRepository;
import org.jocai.freeinterview.services.InterviewerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Created by Gerardo Martín Roldán on 16/06/17.
 */
@Service
public class DefaultInterviewerService implements InterviewerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultInterviewerService.class);

    @Autowired
    private InterviewerRepository interviewerRepository;

    @Override
    public Interviewer getInteviewer(final Long id)
            throws NoResultFoundException, FreeInterviewServiceException {
        Assert.notNull(id, "The id cannot be null.");

        LOGGER.info("Searching interviewer with id " + id);

        Optional<Interviewer> optionalInterviewer = null;

        try {
            optionalInterviewer = this.interviewerRepository.findById(id);
        } catch (DataAccessException ex) {
            LOGGER.error("There was an error trying to find the interviewer with id = " + id, ex);
            throw new FreeInterviewServiceException("There was an error trying to find the interviewer with id = " + id, ex);
        }

        return optionalInterviewer.orElseThrow(() -> new NoResultFoundException("There is no interviewer with id = " + id));
    }

    @Override
    public Page<Interviewer> getAllInterviewers(final Pageable pageable) {
        Assert.notNull(pageable, "Pageable cannot be null.");

        LOGGER.info("Searching a page of interviewers. Detail: " + pageable.toString());

        return this.interviewerRepository.findAll(pageable);
    }

    @Override
    public Long save(final Interviewer interviewer) throws FreeInterviewDataIntegrityException {
        Assert.notNull(interviewer, "The interviewer cannot be null.");

        Long id = null;

        try {
            id = this.interviewerRepository.save(interviewer).getId();
        } catch (javax.validation.ConstraintViolationException | DataIntegrityViolationException e) {
            throw new FreeInterviewDataIntegrityException(e);
        }


        return id;
    }
}
