package org.jocai.freeinterview.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jocai.freeinterview.exceptions.FreeInterviewServiceException;
import org.jocai.freeinterview.exceptions.NoResultFoundException;
import org.jocai.freeinterview.model.Interview;
import org.jocai.freeinterview.model.Interviewer;
import org.jocai.freeinterview.services.InterviewService;
import org.jocai.freeinterview.services.InterviewerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Gerardo Martín Roldán on 16/06/17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = InterviewerRestController.class, secure = false)
public class InterviewerRestControllerTest {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InterviewerService interviewerServiceMock;

    @MockBean
    private InterviewService interviewServiceMock;

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
        when(this.interviewerServiceMock.getInteviewer(1L)).thenThrow(NoResultFoundException.class);

        RequestBuilder requestBuilder
                = MockMvcRequestBuilders
                .get("/interviewers/1")
                .accept(MediaType.APPLICATION_JSON_UTF8);

        ResultActions resultActions = this.mockMvc.perform(requestBuilder);

        resultActions.andExpect(status().isNoContent());
    }

    @Test
    public void getInterviewer_ReturnsInternalServerError_IfServiceThrowsFreeInterviewServiceException()
            throws Exception {
        when(this.interviewerServiceMock.getInteviewer(1L)).thenThrow(FreeInterviewServiceException.class);

        RequestBuilder requestBuilder
                = MockMvcRequestBuilders
                .get("/interviewers/1")
                .accept(MediaType.APPLICATION_JSON_UTF8);

        ResultActions resultActions = this.mockMvc.perform(requestBuilder);

        resultActions.andExpect(status().isInternalServerError());
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

    @Test
    public void getInterviews_ReturnsInterviewsAndOkStatus_IfTheGivenInterviewerHasInterviews()
            throws Exception {
        Interviewer myFirstInterviewer = new Interviewer(3L, "Homer", "Simpson");
        Interview myFirstInterview = new Interview(4L, myFirstInterviewer, DATE_FORMAT.parse("2018-02-01 21:45 AM UTC"));
        Interview mySecondInterview = new Interview(8L, myFirstInterviewer, DATE_FORMAT.parse("2018-02-10 16:45 AM UTC"));

        List<Interview> interviewList = Arrays.asList(myFirstInterview, mySecondInterview);

        when(this.interviewServiceMock.getInterviews(3L)).thenReturn(interviewList);

        RequestBuilder requestBuilder
                = MockMvcRequestBuilders
                    .get("/interviewers/3/interviews")
                    .accept(MediaType.APPLICATION_JSON_UTF8);

        ResultActions resultActions = this.mockMvc.perform(requestBuilder);

        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id", is(myFirstInterview.getId().intValue())))
                .andExpect(jsonPath("$[0].interviewer.id", is(myFirstInterview.getInterviewer().getId().intValue())))
                .andExpect(jsonPath("$[0].interviewer.firstName", is(myFirstInterview.getInterviewer().getFirstName())))
                .andExpect(jsonPath("$[0].interviewer.lastName", is(myFirstInterview.getInterviewer().getLastName())))
                .andExpect(jsonPath("[0].interviewDate", is(DATE_FORMAT.format(myFirstInterview.getInterviewDate().getTime()))))
                .andExpect(jsonPath("$[1].id", is(mySecondInterview.getId().intValue())))
                .andExpect(jsonPath("$[1].interviewer.id", is(myFirstInterview.getInterviewer().getId().intValue())))
                .andExpect(jsonPath("$[1].interviewer.firstName", is(myFirstInterview.getInterviewer().getFirstName())))
                .andExpect(jsonPath("$[1].interviewer.lastName", is(myFirstInterview.getInterviewer().getLastName())))
                .andExpect(jsonPath("[1].interviewDate", is(DATE_FORMAT.format(mySecondInterview.getInterviewDate()))));
    }

    @Test
    public void getInterviews_ReturnsNoContentStatus_IfInterviewerHasNoInterview() throws Exception {
        when(this.interviewServiceMock.getInterviews(3L)).thenReturn(Collections.EMPTY_LIST);

        RequestBuilder requestBuilder
                = MockMvcRequestBuilders
                .get("/interviewers/3/interviews")
                .accept(MediaType.APPLICATION_JSON_UTF8);

        ResultActions resultActions = this.mockMvc.perform(requestBuilder);

        resultActions.andExpect(status().isNoContent());
    }

    @Test
    public void createInterviewer_Ok() throws Exception {
        Interviewer interviewer = new Interviewer("Chandler", "Bing");
        ObjectMapper mapper = new ObjectMapper();
        String interviewerJson = mapper.writeValueAsString(interviewer);

        when(this.interviewerServiceMock.save(Mockito.any(Interviewer.class))).thenReturn(40L);

        RequestBuilder requestBuilder
                = MockMvcRequestBuilders
                    .post("/interviewers")
                    .content(interviewerJson)
                    .accept(MediaType.APPLICATION_JSON_UTF8)
                    .contentType(MediaType.APPLICATION_JSON_VALUE);

        ResultActions resultActions = this.mockMvc.perform(requestBuilder);

        resultActions
                .andExpect(status().isCreated())
                .andExpect(redirectedUrl("http://localhost/interviewers/40"));
    }
}