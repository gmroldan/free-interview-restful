package org.jocai.freeinterview.services.impl;

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
import static org.mockito.Mockito.doNothing;
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

        when(this.interviewRepositoryMock.findOne(1L)).thenReturn(interview);

        Interview result = this.interviewService.getInteview(1L);

        assertEquals(interview, result);
        verify(this.interviewRepositoryMock, times(1)).findOne(1L);
    }

    @Test
    public void getInteview_ThrowsIllegalArgumentException_IfIdIsNull() throws Exception {
        try {
            this.interviewService.getInteview(null);
            fail();
        } catch (IllegalArgumentException e) {
            verify(this.interviewRepositoryMock, times(0)).findOne(anyLong());
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

}