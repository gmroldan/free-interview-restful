package org.jocai.freeinterview.services.impl;

import org.jocai.freeinterview.model.Interviewer;
import org.jocai.freeinterview.repository.InterviewerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


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

        when(this.interviewerRepositoryMock.findOne(1L)).thenReturn(interviewer);

        Interviewer result = this.interviewerService.getInteviewer(1L);

        assertEquals(interviewer, result);
        verify(this.interviewerRepositoryMock, times(1)).findOne(1L);
    }

    @Test
    public void getInteviewer_ThrowsIllegalArgumentException_IfIdIsNull() throws Exception {
        try {
            this.interviewerService.getInteviewer(null);
            fail();
        } catch (IllegalArgumentException e) {
            verify(this.interviewerRepositoryMock, times(0)).findOne(anyLong());
        }
    }

}