package com.company.musicstorerecommendations.controller;

import com.company.musicstorerecommendations.model.LabelRecommendation;
import com.company.musicstorerecommendations.repository.LabelRecommendationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MusicStoreLabelRecommendationController.class)
public class MusicStoreLabelRecommendationControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LabelRecommendationRepository repository;
    private ObjectMapper mapper = new ObjectMapper();

    // Test model objects here for ease of access
    private LabelRecommendation label1Input, label2Input, label1Output, label2Output;
    private List<LabelRecommendation> allLabels;

    @Before
    public void setUp() {
        label1Input = new LabelRecommendation();
        label1Input.setLabelId(1);
        label1Input.setUserId(1);
        label1Input.setLiked(true);

        label1Output = new LabelRecommendation();
        label1Output.setLabelRecommendationId(1);
        label1Output.setLabelId(1);
        label1Output.setUserId(1);
        label1Output.setLiked(true);

        label2Input = new LabelRecommendation();
        label2Input.setLabelId(2);
        label2Input.setUserId(2);
        label2Input.setLiked(false);

        label2Output = new LabelRecommendation();
        label2Output.setLabelRecommendationId(2);
        label2Output.setLabelId(2);
        label2Output.setUserId(2);
        label2Output.setLiked(false);

        allLabels = Arrays.asList(label1Output, label2Output);

        setUpMocks();
    }

    private void setUpMocks() {
        doReturn(label1Output).when(repository).save(label1Input);
        doReturn(Optional.of(label1Output)).when(repository).findById(1);
        doReturn(allLabels).when(repository).findAll();
    }

    @Test
    public void shouldAddLabelOnPostRequest() throws Exception {
        // Arrange
        String requestBody = mapper.writeValueAsString(label1Input);
        String responseBody = mapper.writeValueAsString(label1Output);

        // Act, Assert
        mockMvc.perform(post("/label").content(requestBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(responseBody));
    }

    @Test
    public void shouldGetLabelByIdOnGetRequest() throws Exception {
        // Arrange
        String responseBody = mapper.writeValueAsString(label1Output);

        // Act, Assert
        mockMvc.perform(get("/label/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));
    }

    @Test
    public void shouldFailOnGetLabelWithBadId() throws Exception {
        // Act, Assert
        mockMvc.perform(get("/label/{id}", -1))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldGetAllLabelsOnGetRequest() throws Exception {
        // Arrange
        String responseBody = mapper.writeValueAsString(allLabels);

        // Act, Assert
        mockMvc.perform(get("/label"))
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));
    }

    @Test
    public void shouldUpdateLabelOnPutRequest() throws Exception {
        // Arrange
        label2Input.setLiked(true);
        String requestBody = mapper.writeValueAsString(label2Input);

        // Act, Assert
        mockMvc.perform(put("/label/{id}", 1).content(requestBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldFailOnUpdateLabelWithBadId() throws Exception {
        // Arrange
        label2Input.setLiked(true);
        String requestBody = mapper.writeValueAsString(label2Input);

        // Act, Assert
        mockMvc.perform(put("/label/{id}", -1).content(requestBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldDeleteLabelOnDeleteRequest() throws Exception {
        // Act, Assert
        mockMvc.perform(delete("/label/{id}", 1))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldFailOnDeleteLabelWithBadId() throws Exception {
        // Act, Assert
        mockMvc.perform(delete("/label/{id}", -1))
                .andExpect(status().isUnprocessableEntity());
    }
}
