package org.jocai.freeinterview.repository;

import org.jocai.freeinterview.model.Interview;

/**
 * Created by martin on 02/06/17.
 */
public interface InterviewRepository {
    Interview findById(Long id);

    void insert(Interview interview);
}
