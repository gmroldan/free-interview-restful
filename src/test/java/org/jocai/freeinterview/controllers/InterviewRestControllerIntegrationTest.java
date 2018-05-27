package org.jocai.freeinterview.controllers;

import org.jocai.freeinterview.Application;
import org.jocai.freeinterview.model.Interview;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Application.class)
@AutoConfigureMockMvc
public class InterviewRestControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllInterviews_ReturnsInterviewsAndOkStatus_IfInterviewExistsUsingDefaultPagination() throws Exception {
        RequestBuilder requestBuilder
                = MockMvcRequestBuilders
                .get("/interviews")
                .accept(MediaType.APPLICATION_JSON_UTF8);

        ResultActions resultActions = this.mockMvc.perform(requestBuilder);

        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.pageable.sort.sorted", is(false)))
                .andExpect(jsonPath("$.pageable.sort.unsorted", is(true)))
                .andExpect(jsonPath("$.pageable.pageSize", is(20)))
                .andExpect(jsonPath("$.numberOfElements", is(10)));
    }

    @Test
    public void getAllInterviews_ReturnsInterviewsAndOkStatus_IfInterviewExistsUsingCustomPagination3() throws Exception {
        RequestBuilder requestBuilder
                = MockMvcRequestBuilders
                .get("/interviews?size=3&page=0")
                .accept(MediaType.APPLICATION_JSON_UTF8);

        ResultActions resultActions = this.mockMvc.perform(requestBuilder);

        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.pageable.sort.sorted", is(false)))
                .andExpect(jsonPath("$.pageable.sort.unsorted", is(true)))
                .andExpect(jsonPath("$.pageable.pageSize", is(3)))
                .andExpect(jsonPath("$.numberOfElements", is(3)));
    }
}
