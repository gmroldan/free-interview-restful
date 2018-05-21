package org.jocai.freeinterview.services.impl;

import org.jocai.freeinterview.exceptions.FreeInterviewServiceException;
import org.jocai.freeinterview.exceptions.NoResultFoundException;
import org.jocai.freeinterview.model.Interviewer;
import org.jocai.freeinterview.repository.InterviewerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;


import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Gerardo Martín Roldán on 16/06/17.
 */
public class DefaultInterviewerServiceTest {
    @InjectMocks
    private DefaultInterviewerService interviewerService;

    @Mock
    private InterviewerRepository interviewerRepositoryMock;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getInteviewer_SuccessfulOperation_IfEverythingGoesWell() throws Exception {
        Interviewer interviewer = new Interviewer();
        Optional<Interviewer> optionalInterviewer = Optional.of(interviewer);

        when(this.interviewerRepositoryMock.findById(1L)).thenReturn(optionalInterviewer);

        Interviewer result = this.interviewerService.getInteviewer(1L);

        assertEquals(interviewer, result);
        verify(this.interviewerRepositoryMock, times(1)).findById(1L);
    }

    @Test
    public void getInteviewer_ThrowsIllegalArgumentException_IfIdIsNull() throws Exception {
        try {
            this.interviewerService.getInteviewer(null);
            fail();
        } catch (IllegalArgumentException e) {
            verify(this.interviewerRepositoryMock, times(0)).findById(anyLong());
        }
    }

    @Test
    public void getInteviewer_ThrowsFreeInterviewServiceException_IfDaoThrowsDataAccessException() throws Exception {
        when(this.interviewerRepositoryMock.findById(2L)).thenThrow(new DataAccessException("...") {});

        try {
            this.interviewerService.getInteviewer(2L);
            fail("An exception should be thrown.");
        } catch (FreeInterviewServiceException e) {
            verify(this.interviewerRepositoryMock, times(1)).findById(anyLong());
        } catch (Exception e) {
            fail("The exception should be an instance of FreeInterviewServiceException.");
        }
    }

    @Test
    public void getInteviewer_ThrowsNoResultFoundException_IfDaoReturnsNull() throws Exception {
        Optional<Interviewer> optionalInterviewer = Optional.ofNullable(null);
        when(this.interviewerRepositoryMock.findById(2L)).thenReturn(optionalInterviewer);

        try {
            this.interviewerService.getInteviewer(2L);
            fail("An exception should be thrown.");
        } catch (NoResultFoundException e) {
            verify(this.interviewerRepositoryMock, times(1)).findById(anyLong());
        } catch (Exception e) {
            fail("The exception should be an instance of NoResultFoundException.");
        }
    }

}