package org.jocai.freeinterview.repository;

import java.util.List;
import org.jocai.freeinterview.model.Interviewer;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Gerardo Martín Roldán on 16/06/17.
 */
public interface InterviewerRepository extends CrudRepository<Interviewer, Long> {
    @Override
    List<Interviewer> findAll();
}
