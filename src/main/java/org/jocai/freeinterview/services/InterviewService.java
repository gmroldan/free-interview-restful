package org.jocai.freeinterview.services;

import java.util.List;

import org.jocai.freeinterview.exceptions.NoResultFoundException;
import org.jocai.freeinterview.model.Interview;

/**
 * Created by Gerardo Martín Roldán on 02/06/17.
 */
public interface InterviewService {
    Interview getInteview(Long id) throws NoResultFoundException;

    List<Interview> getAllInterviews();

    List<Interview> getInterviews(Long interviewerId);

    void createNewInterview(Interview interview);
}
