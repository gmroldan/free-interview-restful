package org.jocai.freeinterview.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jocai.freeinterview.Application;
import org.jocai.freeinterview.model.Interviewer;
import org.jocai.freeinterview.utils.Error;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import static org.hamcrest.core.IsNull.notNullValue;
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

    @Test @Ignore // FIXME
    public void getAllInterviews_ReturnsInterviewersAndOkStatus_IfInterviewersExists() throws Exception {
        RequestBuilder requestBuilder
                = MockMvcRequestBuilders
                .get("/interviewers")
                .accept(MediaType.APPLICATION_JSON_UTF8);

        ResultActions resultActions = this.mockMvc.perform(requestBuilder);

        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.pageable.sort.sorted", is(false)))
                .andExpect(jsonPath("$.pageable.sort.unsorted", is(true)))
                .andExpect(jsonPath("$.pageable.pageSize", is(20)))
                .andExpect(jsonPath("$.numberOfElements", is(2)));
    }

    @Test
    public void createInterviewer_CreateNewInterviewer_IfInterviewerDoesNotExist() throws Exception {
        Interviewer interviewer = new Interviewer("Chandler", "Bing");
        ObjectMapper mapper = new ObjectMapper();
        String interviewerJson = mapper.writeValueAsString(interviewer);

        RequestBuilder requestBuilder
                = MockMvcRequestBuilders
                .post("/interviewers")
                .content(interviewerJson)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        ResultActions resultActions = this.mockMvc.perform(requestBuilder);

        resultActions
                .andExpect(status().isCreated())
                .andExpect(redirectedUrl("http://localhost/interviewers/3"));
    }

    @Test
    public void createInterviewer_DoesNotCreateNewInterviewer_IfInterviewerExists() throws Exception {
        Interviewer interviewer = new Interviewer("Danila", "Freeberne");
        ObjectMapper mapper = new ObjectMapper();
        String interviewerJson = mapper.writeValueAsString(interviewer);

        RequestBuilder requestBuilder
                = MockMvcRequestBuilders
                .post("/interviewers")
                .content(interviewerJson)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        ResultActions resultActions = this.mockMvc.perform(requestBuilder);

        resultActions
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("The requested resulted in a violation of a defined integrity constraint")))
                .andExpect(jsonPath("$.timeStamp", notNullValue()))
                .andExpect(jsonPath("$.method", is("POST")))
                .andExpect(jsonPath("$.endpoint", is("http://localhost/interviewers")))
                .andExpect(jsonPath("$.errors[0].code", is(Error.INTERVIEWER_ALREADY_EXISTS.getCode())))
                .andExpect(jsonPath("$.errors[0].description", is(Error.INTERVIEWER_ALREADY_EXISTS.getDescription())))
                .andExpect(jsonPath("$.errors[0].hints", is(Error.INTERVIEWER_ALREADY_EXISTS.getHints())))
                .andExpect(jsonPath("$.errors[0].info", is(Error.INTERVIEWER_ALREADY_EXISTS.getInfo())));
    }

    @Test
    public void createInterviewer_DoesNotCreateNewInterviewer_IfInterviewerHasANullFirstName() throws Exception {
        Interviewer interviewer = new Interviewer(null, "Bing");
        ObjectMapper mapper = new ObjectMapper();
        String interviewerJson = mapper.writeValueAsString(interviewer);

        RequestBuilder requestBuilder
                = MockMvcRequestBuilders
                .post("/interviewers")
                .content(interviewerJson)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        ResultActions resultActions = this.mockMvc.perform(requestBuilder);

        resultActions
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("The requested resulted in a violation of a defined integrity constraint")))
                .andExpect(jsonPath("$.timeStamp", notNullValue()))
                .andExpect(jsonPath("$.method", is("POST")))
                .andExpect(jsonPath("$.endpoint", is("http://localhost/interviewers")))
                .andExpect(jsonPath("$.errors[0].code", is(Error.INTERVIEWER_INVALID_FIRST_NAME.getCode())))
                .andExpect(jsonPath("$.errors[0].description", is(Error.INTERVIEWER_INVALID_FIRST_NAME.getDescription())))
                .andExpect(jsonPath("$.errors[0].hints", is(Error.INTERVIEWER_INVALID_FIRST_NAME.getHints())))
                .andExpect(jsonPath("$.errors[0].info", is(Error.INTERVIEWER_INVALID_FIRST_NAME.getInfo())));
    }

    @Test @Ignore // FIXME - The errors return as an unordered array. Then the test fails randomly.
    public void createInterviewer_DoesNotCreateNewInterviewer_IfInterviewerHasNullFirstNameAndNullLastName() throws Exception {
        Interviewer interviewer = new Interviewer(null, null);
        ObjectMapper mapper = new ObjectMapper();
        String interviewerJson = mapper.writeValueAsString(interviewer);

        RequestBuilder requestBuilder
                = MockMvcRequestBuilders
                .post("/interviewers")
                .content(interviewerJson)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        ResultActions resultActions = this.mockMvc.perform(requestBuilder);

        resultActions
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("The requested resulted in a violation of a defined integrity constraint")))
                .andExpect(jsonPath("$.timeStamp", notNullValue()))
                .andExpect(jsonPath("$.method", is("POST")))
                .andExpect(jsonPath("$.endpoint", is("http://localhost/interviewers")))
                .andExpect(jsonPath("$.errors[0].code", is(Error.INTERVIEWER_INVALID_FIRST_NAME.getCode())))
                .andExpect(jsonPath("$.errors[0].description", is(Error.INTERVIEWER_INVALID_FIRST_NAME.getDescription())))
                .andExpect(jsonPath("$.errors[0].hints", is(Error.INTERVIEWER_INVALID_FIRST_NAME.getHints())))
                .andExpect(jsonPath("$.errors[0].info", is(Error.INTERVIEWER_INVALID_FIRST_NAME.getInfo())))
                .andExpect(jsonPath("$.errors[1].code", is(Error.INTERVIEWER_INVALID_LAST_NAME.getCode())))
                .andExpect(jsonPath("$.errors[1].description", is(Error.INTERVIEWER_INVALID_LAST_NAME.getDescription())))
                .andExpect(jsonPath("$.errors[1].hints", is(Error.INTERVIEWER_INVALID_LAST_NAME.getHints())))
                .andExpect(jsonPath("$.errors[1].info", is(Error.INTERVIEWER_INVALID_LAST_NAME.getInfo())));
    }
}
