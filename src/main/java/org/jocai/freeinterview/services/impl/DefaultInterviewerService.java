package org.jocai.freeinterview.services.impl;

import java.util.List;
import org.jocai.freeinterview.model.Interviewer;
import org.jocai.freeinterview.repository.InterviewerRepository;
import org.jocai.freeinterview.services.InterviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Created by Gerardo Martín Roldán on 16/06/17.
 */
@Service
public class DefaultInterviewerService implements InterviewerService {
    @Autowired
    private InterviewerRepository interviewerRepository;

    @Override
    public Interviewer getInteviewer(final Long id) {
        Assert.notNull(id, "The id cannot be null.");
        return this.interviewerRepository.findOne(id);
    }

    @Override
    public List<Interviewer> getAllInterviewers() {
        return this.interviewerRepository.findAll();
    }
}
