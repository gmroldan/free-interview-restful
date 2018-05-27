package org.jocai.freeinterview.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jocai.freeinterview.model.Interview;
import org.jocai.freeinterview.model.Interviewer;
import org.jocai.freeinterview.services.InterviewService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Gerardo Martín Roldán on 05/06/17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = InterviewRestController.class, secure = false)
public class InterviewRestControllerTest {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InterviewService interviewServiceMock;

    @Test
    public void getInterview_ReturnsInterviewAndOkStatus_IfInterviewExists() throws Exception {
        Interview myInterview = new Interview();
        myInterview.setId((long) 33);
        myInterview.setInterviewDate(DATE_FORMAT.parse("2018-02-01 21:45 AM UTC"));

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
                .andExpect(jsonPath("$.interviewDate", is(DATE_FORMAT.format(myInterview.getInterviewDate()))));
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
    public void createInterviewer_Ok() throws Exception {
        Interviewer interviewer = new Interviewer(27L, "Chandler", "Bing");
        Interview interview = new Interview(interviewer, new Date());
        ObjectMapper mapper = new ObjectMapper();
        String interviewJson = mapper.writeValueAsString(interview);

        when(this.interviewServiceMock.save(Mockito.any(Interview.class))).thenReturn(101L);

        RequestBuilder requestBuilder
                = MockMvcRequestBuilders
                .post("/interviews")
                .content(interviewJson)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        ResultActions resultActions = this.mockMvc.perform(requestBuilder);

        resultActions
                .andExpect(status().isCreated())
                .andExpect(redirectedUrl("http://localhost/interviews/101"));
    }
}