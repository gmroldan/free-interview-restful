package org.jocai.freeinterview.services;

import org.jocai.freeinterview.model.Interview;

/**
 * Created by martin on 02/06/17.
 */
public interface InterviewService {
    Interview getInteview(Long id);

    void createNewInterview(Interview interview);
}
