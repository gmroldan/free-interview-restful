package org.jocai.freeinterview.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.jocai.freeinterview.model.Interview;
import org.jocai.freeinterview.services.InterviewService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Gerardo Martín Roldán on 05/06/17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = InterviewController.class, secure = false)
public class InterviewControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InterviewService interviewServiceMock;

    @Before
    public void setUp() throws Exception {}

    @Test
    public void getInterview_ReturnsInterviewAndOkStatus_IfInterviewExists() throws Exception {
        Interview myInterview = new Interview();
        myInterview.setId((long) 33);
        myInterview.setDate(new Date());

        when(this.interviewServiceMock.getInteview(33L)).thenReturn(myInterview);

        RequestBuilder requestBuilder
                = MockMvcRequestBuilders
                    .get("/interviews/33")
                    .accept(MediaType.APPLICATION_JSON_UTF8);

        ResultActions resultActions = this.mockMvc.perform(requestBuilder);

        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(myInterview.getId().intValue())))
                .andExpect(jsonPath("$.interviewer", is(myInterview.getInterviewer())))
                .andExpect(jsonPath("$.date", is(myInterview.getDate().getTime())));
    }

    @Test
    public void getInterview_ReturnsInterviewAndNOContentStatus_IfInterviewDoesNotExist() throws Exception {
        when(this.interviewServiceMock.getInteview(33L)).thenReturn(null);

        RequestBuilder requestBuilder
                = MockMvcRequestBuilders
                .get("/interviews/33")
                .accept(MediaType.APPLICATION_JSON_UTF8);

        ResultActions resultActions = this.mockMvc.perform(requestBuilder);

        resultActions.andExpect(status().isNoContent());
    }

    @Test
    public void getAllInterviews_ReturnsInterviewsAndOkStatus_IfInterviewExists() throws Exception {
        Interview myFirstInterview = new Interview();
        myFirstInterview.setId((long) 33);
        myFirstInterview.setDate(new Date());

        Interview mySecondInterview = new Interview();
        mySecondInterview.setId((long) 34);
        mySecondInterview.setDate(new Date());

        List<Interview> interviewList = Arrays.asList(myFirstInterview, mySecondInterview);

        when(this.interviewServiceMock.getAllInterviews()).thenReturn(interviewList);

        RequestBuilder requestBuilder
                = MockMvcRequestBuilders
                .get("/interviews")
                .accept(MediaType.APPLICATION_JSON_UTF8);

        ResultActions resultActions = this.mockMvc.perform(requestBuilder);

        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id", is(myFirstInterview.getId().intValue())))
                .andExpect(jsonPath("$[0].interviewer", is(myFirstInterview.getInterviewer())))
                .andExpect(jsonPath("$[0].date", is(myFirstInterview.getDate().getTime())))
                .andExpect(jsonPath("$[1].id", is(mySecondInterview.getId().intValue())))
                .andExpect(jsonPath("$[1].interviewer", is(mySecondInterview.getInterviewer())))
                .andExpect(jsonPath("$[1].date", is(mySecondInterview.getDate().getTime())));
    }

    @Test
    public void getAllInterviews_ReturnsNOContentStatus_IfThereAreNoInterviews() throws Exception {
        when(this.interviewServiceMock.getAllInterviews()).thenReturn(Collections.emptyList());

        RequestBuilder requestBuilder
                = MockMvcRequestBuilders
                .get("/interviews")
                .accept(MediaType.APPLICATION_JSON_UTF8);

        ResultActions resultActions = this.mockMvc.perform(requestBuilder);

        resultActions.andExpect(status().isNoContent());
    }

}