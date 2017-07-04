package org.jocai.freeinterview.controllers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.jocai.freeinterview.model.Interviewer;
import org.jocai.freeinterview.services.InterviewerService;
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

import static org.mockito.Mockito.when;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Gerardo Martín Roldán on 16/06/17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = InterviewerController.class, secure = false)
public class InterviewerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InterviewerService interviewerServiceMock;

    @Test
    public void getInterviewer_ReturnsInterviewerAndOkStatus_IfInterviewerExists() throws Exception {
        Interviewer interviewer = new Interviewer(1L, "Homer", "Simpson");

        when(this.interviewerServiceMock.getInteviewer(1L)).thenReturn(interviewer);

        RequestBuilder requestBuilder
                = MockMvcRequestBuilders
                    .get("/interviewers/1")
                    .accept(MediaType.APPLICATION_JSON_UTF8);

        ResultActions resultActions = this.mockMvc.perform(requestBuilder);

        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(interviewer.getId().intValue())))
                .andExpect(jsonPath("$.firstName", is(interviewer.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(interviewer.getLastName())));
    }

    @Test
    public void getInterviewer_ReturnsNoContentStatus_IfInterviewerDoesNotExists() throws Exception {
        when(this.interviewerServiceMock.getInteviewer(1L)).thenReturn(null);

        RequestBuilder requestBuilder
                = MockMvcRequestBuilders
                .get("/interviewers/1")
                .accept(MediaType.APPLICATION_JSON_UTF8);

        ResultActions resultActions = this.mockMvc.perform(requestBuilder);

        resultActions.andExpect(status().isNoContent());
    }

    @Test
    public void getAllInterviews_ReturnsInterviewersAndOkStatus_IfInterviewersExists() throws Exception {
        Interviewer myFirstInterviewer = new Interviewer(1L, "Homer", "Simpson");
        Interviewer mySecondInterviewer = new Interviewer(2L, "Marge", "Simpson");

        List<Interviewer> interviewerList = Arrays.asList(myFirstInterviewer, mySecondInterviewer);

        when(this.interviewerServiceMock.getAllInterviewers()).thenReturn(interviewerList);

        RequestBuilder requestBuilder
                = MockMvcRequestBuilders
                .get("/interviewers")
                .accept(MediaType.APPLICATION_JSON_UTF8);

        ResultActions resultActions = this.mockMvc.perform(requestBuilder);

        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id", is(myFirstInterviewer.getId().intValue())))
                .andExpect(jsonPath("$[0].firstName", is(myFirstInterviewer.getFirstName())))
                .andExpect(jsonPath("$[0].lastName", is(myFirstInterviewer.getLastName())))
                .andExpect(jsonPath("$[1].id", is(mySecondInterviewer.getId().intValue())))
                .andExpect(jsonPath("$[1].firstName", is(mySecondInterviewer.getFirstName())))
                .andExpect(jsonPath("$[1].lastName", is(mySecondInterviewer.getLastName())));
    }

    @Test
    public void getAllInterviews_ReturnsNOContentStatus_IfThereAreNoInterviewers() throws Exception {
        when(this.interviewerServiceMock.getAllInterviewers()).thenReturn(Collections.emptyList());

        RequestBuilder requestBuilder
                = MockMvcRequestBuilders
                .get("/interviewers")
                .accept(MediaType.APPLICATION_JSON_UTF8);

        ResultActions resultActions = this.mockMvc.perform(requestBuilder);

        resultActions.andExpect(status().isNoContent());
    }

}