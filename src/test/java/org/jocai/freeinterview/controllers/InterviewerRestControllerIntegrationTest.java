package org.jocai.freeinterview.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jocai.freeinterview.Application;
import org.jocai.freeinterview.model.Interviewer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Application.class)
@AutoConfigureMockMvc
public class InterviewerRestControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getInterviewer_ReturnsInterviewerAndOkStatus_IfInterviewerExists() throws Exception {
        RequestBuilder requestBuilder
                = MockMvcRequestBuilders
                .get("/interviewers/1")
                .accept(MediaType.APPLICATION_JSON_UTF8);

        ResultActions resultActions = this.mockMvc.perform(requestBuilder);

        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("Danila")))
                .andExpect(jsonPath("$.lastName", is("Freeberne")));
    }

    @Test
    public void getInterviewer_ReturnsNoContentStatus_IfInterviewerDoesNotExists() throws Exception {
        RequestBuilder requestBuilder
                = MockMvcRequestBuilders
                .get("/interviewers/100")
                .accept(MediaType.APPLICATION_JSON_UTF8);

        ResultActions resultActions = this.mockMvc.perform(requestBuilder);

        resultActions.andExpect(status().isNoContent());
    }

    @Test
    public void getAllInterviews_ReturnsInterviewersAndOkStatus_IfInterviewersExists() throws Exception {
        RequestBuilder requestBuilder
                = MockMvcRequestBuilders
                .get("/interviewers")
                .accept(MediaType.APPLICATION_JSON_UTF8);

        ResultActions resultActions = this.mockMvc.perform(requestBuilder);

        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstName", is("Danila")))
                .andExpect(jsonPath("$[0].lastName", is("Freeberne")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].firstName", is("Maurizia")))
                .andExpect(jsonPath("$[1].lastName", is("Chifney")));
    }
}
