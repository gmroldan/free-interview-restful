package org.jocai.freeinterview.repository;

import org.jocai.freeinterview.model.Interviewer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Gerardo Martín Roldán on 16/06/17.
 */
public interface InterviewerRepository extends CrudRepository<Interviewer, Long> {
    Page<Interviewer> findAll(Pageable pageable);
}
