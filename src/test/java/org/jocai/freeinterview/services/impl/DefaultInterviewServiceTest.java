package org.jocai.freeinterview.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.jocai.freeinterview.model.Interview;
import org.jocai.freeinterview.repository.InterviewRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Gerardo Martín Roldán on 15/06/17.
 */
public class DefaultInterviewServiceTest {
    @InjectMocks
    private DefaultInterviewService interviewService;

    @Mock
    private InterviewRepository interviewRepositoryMock;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getInteview_SuccessfulOperation_IfEverythingGoesWell() throws Exception {
        Interview interview = new Interview();
        Optional<Interview> optionalInterview = Optional.of(interview);

        when(this.interviewRepositoryMock.findById(1L)).thenReturn(optionalInterview);

        Interview result = this.interviewService.getInteview(1L);

        assertEquals(interview, result);
        verify(this.interviewRepositoryMock, times(1)).findById(1L);
    }

    @Test
    public void getInteview_SuccessfulOperation_IfInterviewDoesNotExists() throws Exception {
        Optional<Interview> optionalInterview = Optional.ofNullable(null);

        when(this.interviewRepositoryMock.findById(1L)).thenReturn(optionalInterview);

        Interview result = this.interviewService.getInteview(1L);

        assertEquals(null, result);
        verify(this.interviewRepositoryMock, times(1)).findById(1L);
    }

    @Test
    public void getInteview_ThrowsIllegalArgumentException_IfIdIsNull() throws Exception {
        try {
            this.interviewService.getInteview(null);
            fail();
        } catch (IllegalArgumentException e) {
            verify(this.interviewRepositoryMock, times(0)).findById(anyLong());
        }
    }

    @Test
    public void createNewInterview_SuccessfulOperation_IfEverythingGoesWell() throws Exception {
        Interview interview = new Interview();

        when(this.interviewRepositoryMock.save(interview)).thenReturn(interview);

        this.interviewService.createNewInterview(interview);

        verify(this.interviewRepositoryMock, times(1)).save(interview);
    }

    @Test
    public void createNewInterview_ThrowsIllegalArgumentException_IfInterviewIsNull() throws Exception {
        try {
            this.interviewService.createNewInterview(null);
            fail();
        } catch (IllegalArgumentException e) {
            verify(this.interviewRepositoryMock, times(0)).save(any(Interview.class));
        }
    }

    @Test
    public void getInteviews_SuccessfulOperation_IfEverythingGoesWell() throws Exception {
        List<Interview> interviewList = Arrays.asList(new Interview(), new Interview());

        when(this.interviewRepositoryMock.findByInterviewer_Id(1L)).thenReturn(interviewList);

        List<Interview> result = this.interviewService.getInterviews(1L);

        assertEquals(interviewList, result);
        verify(this.interviewRepositoryMock, times(1)).findByInterviewer_Id(1L);
    }

    @Test
    public void getInteviews_ThrowsIllegalArgumentException_IfInterviewerIdIsNull() throws Exception {
        try {
            this.interviewService.getInterviews(null);
            fail();
        } catch (IllegalArgumentException e) {
            verify(this.interviewRepositoryMock, times(0)).findByInterviewer_Id(anyLong());
        } catch (Exception e) {
            fail();
        }
    }
}