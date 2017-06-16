package org.jocai.freeinterview.services;

import java.util.List;
import org.jocai.freeinterview.model.Interviewer;

/**
 * Created by Gerardo Martín Roldán on 16/06/17.
 */
public interface InterviewerService {
    Interviewer getInteviewer(Long id);

    List<Interviewer> getAllInterviewers();
}
