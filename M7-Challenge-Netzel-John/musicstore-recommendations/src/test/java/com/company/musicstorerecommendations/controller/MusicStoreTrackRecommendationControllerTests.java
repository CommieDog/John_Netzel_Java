package com.company.musicstorerecommendations.controller;

import com.company.musicstorerecommendations.model.TrackRecommendation;
import com.company.musicstorerecommendations.repository.TrackRecommendationRepository;
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
@WebMvcTest(MusicStoreTrackRecommendationController.class)
public class MusicStoreTrackRecommendationControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrackRecommendationRepository repository;

    private ObjectMapper mapper = new ObjectMapper();

    // Test model objects here for ease of access
    private TrackRecommendation track1Input, track2Input, track1Output, track2Output;
    private List<TrackRecommendation> allTracks;

    @Before
    public void setUp() {
        track1Input = new TrackRecommendation();
        track1Input.setTrackId(1);
        track1Input.setUserId(1);
        track1Input.setLiked(true);

        track1Output = new TrackRecommendation();
        track1Output.setTrackRecommendationId(1);
        track1Output.setTrackId(1);
        track1Output.setUserId(1);
        track1Output.setLiked(true);

        track2Input = new TrackRecommendation();
        track2Input.setTrackId(2);
        track2Input.setUserId(2);
        track2Input.setLiked(false);

        track2Output = new TrackRecommendation();
        track2Output.setTrackRecommendationId(2);
        track2Output.setTrackId(2);
        track2Output.setUserId(2);
        track2Output.setLiked(false);

        allTracks = Arrays.asList(track1Output, track2Output);

        setUpMocks();
    }

    private void setUpMocks() {
        doReturn(track1Output).when(repository).save(track1Input);
        doReturn(Optional.of(track1Output)).when(repository).findById(1);
        doReturn(allTracks).when(repository).findAll();
    }

    @Test
    public void shouldAddTrackOnPostRequest() throws Exception {
        // Arrange
        String requestBody = mapper.writeValueAsString(track1Input);
        String responseBody = mapper.writeValueAsString(track1Output);

        // Act, Assert
        mockMvc.perform(post("/track").content(requestBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(responseBody));
    }

    @Test
    public void shouldGetTrackByIdOnGetRequest() throws Exception {
        // Arrange
        String responseBody = mapper.writeValueAsString(track1Output);

        // Act, Assert
        mockMvc.perform(get("/track/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));
    }

    @Test
    public void shouldFailOnGetTrackWithBadId() throws Exception {
        // Act, Assert
        mockMvc.perform(get("/track/{id}", -1))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldGetAllTracksOnGetRequest() throws Exception {
        // Arrange
        String responseBody = mapper.writeValueAsString(allTracks);

        // Act, Assert
        mockMvc.perform(get("/track"))
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));
    }

    @Test
    public void shouldUpdateTrackOnPutRequest() throws Exception {
        // Arrange
        track2Input.setLiked(true);
        String requestBody = mapper.writeValueAsString(track2Input);

        // Act, Assert
        mockMvc.perform(put("/track/{id}", 1).content(requestBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldFailOnUpdateTrackWithBadId() throws Exception {
        // Arrange
        track2Input.setLiked(true);
        String requestBody = mapper.writeValueAsString(track2Input);

        // Act, Assert
        mockMvc.perform(put("/track/{id}", -1).content(requestBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldDeleteTrackOnDeleteRequest() throws Exception {
        // Act, Assert
        mockMvc.perform(delete("/track/{id}", 1))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldFailOnDeleteTrackWithBadId() throws Exception {
        // Act, Assert
        mockMvc.perform(delete("/track/{id}", -1))
                .andExpect(status().isUnprocessableEntity());
    }
}
