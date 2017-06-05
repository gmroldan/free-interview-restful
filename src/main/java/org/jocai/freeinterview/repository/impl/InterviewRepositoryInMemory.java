package org.jocai.freeinterview.repository.impl;

import org.jocai.freeinterview.model.Interview;
import org.jocai.freeinterview.model.Interviewer;
import org.jocai.freeinterview.repository.InterviewRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by martin on 02/06/17.
 */
@Repository
public class InterviewRepositoryInMemory implements InterviewRepository {
    private static final Map<Long, Interview> INTERVIEW_MAP = new HashMap<>();
    private static long counter = 1L;

    static {
        Interview myInterview = new Interview();
        myInterview.setId(counter++);
        myInterview.setInterviewer(new Interviewer("Juan Perez"));
        myInterview.setDate(new Date());

        INTERVIEW_MAP.put(myInterview.getId(), myInterview);
    }

    @Override
    public Interview findById(final Long id) {
        return INTERVIEW_MAP.get(id);
    }

    @Override
    public void insert(final Interview interview) {
        interview.setId(counter++);
        INTERVIEW_MAP.put(interview.getId(), interview);
    }
}
