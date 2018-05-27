package org.jocai.freeinterview.services;

import java.util.List;

import org.jocai.freeinterview.exceptions.NoResultFoundException;
import org.jocai.freeinterview.model.Interview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Gerardo Martín Roldán on 02/06/17.
 */
public interface InterviewService {
    Interview getInteview(Long id) throws NoResultFoundException;

    Page<Interview> getAllInterviews(Pageable pageable);

    List<Interview> getInterviews(Long interviewerId);

    Long save(Interview interview);
}
