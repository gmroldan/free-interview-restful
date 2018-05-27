package org.jocai.freeinterview.repository;

import java.util.List;
import org.jocai.freeinterview.model.Interview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Gerardo Martín Roldán on 02/06/17.
 */
public interface InterviewRepository extends CrudRepository<Interview, Long> {
    Page<Interview> findAll(Pageable pageable);

    List<Interview> findByInterviewer_Id(Long interviewerId);
}
