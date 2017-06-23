package org.jocai.freeinterview.services.impl;

import java.util.List;
import org.jocai.freeinterview.model.Interview;
import org.jocai.freeinterview.repository.InterviewRepository;
import org.jocai.freeinterview.services.InterviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Created by Gerardo Martín Roldán on 02/06/17.
 */
@Service
public class DefaultInterviewService implements InterviewService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultInterviewService.class);

    @Autowired
    private InterviewRepository interviewRepository;

    @Override
    public Interview getInteview(final Long id) {
        Assert.notNull(id, "The id cannot be null.");

        LOGGER.info("Searching interview with id " + id);

        return this.interviewRepository.findOne(id);
    }

    @Override
    public List<Interview> getAllInterviews() {
        LOGGER.info("Searching all the interviews");
        return this.interviewRepository.findAll();
    }

    @Override
    public void createNewInterview(final Interview interview) {
        Assert.notNull(interview, "The interview cannot be null.");

        LOGGER.info("Creating a new interview");

        this.interviewRepository.save(interview);
    }
}
