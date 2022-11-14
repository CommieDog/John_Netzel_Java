package com.company.musicstorerecommendations.controller;

import com.company.musicstorerecommendations.model.ArtistRecommendation;
import com.company.musicstorerecommendations.repository.ArtistRecommendationRepository;
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
@WebMvcTest(MusicStoreArtistRecommendationController.class)
public class MusicStoreArtistRecommendationControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArtistRecommendationRepository repository;
    private ObjectMapper mapper = new ObjectMapper();

    // Test model objects here for ease of access
    private ArtistRecommendation artist1Input, artist2Input, artist1Output, artist2Output;
    private List<ArtistRecommendation> allAlbums;

    @Before
    public void setUp() {
        artist1Input = new ArtistRecommendation();
        artist1Input.setArtistId(1);
        artist1Input.setUserId(1);
        artist1Input.setLiked(true);

        artist1Output = new ArtistRecommendation();
        artist1Output.setArtistRecommendationId(1);
        artist1Output.setArtistId(1);
        artist1Output.setUserId(1);
        artist1Output.setLiked(true);

        artist2Input = new ArtistRecommendation();
        artist2Input.setArtistId(2);
        artist2Input.setUserId(2);
        artist2Input.setLiked(false);

        artist2Output = new ArtistRecommendation();
        artist2Output.setArtistRecommendationId(2);
        artist2Output.setArtistId(2);
        artist2Output.setUserId(2);
        artist2Output.setLiked(false);

        allAlbums = Arrays.asList(artist1Output, artist2Output);

        setUpMocks();
    }

    private void setUpMocks() {
        doReturn(artist1Output).when(repository).save(artist1Input);
        doReturn(Optional.of(artist1Output)).when(repository).findById(1);
        doReturn(allAlbums).when(repository).findAll();
    }

    @Test
    public void shouldAddArtistOnPostRequest() throws Exception {
        // Arrange
        String requestBody = mapper.writeValueAsString(artist1Input);
        String responseBody = mapper.writeValueAsString(artist1Output);

        // Act, Assert
        mockMvc.perform(post("/artist").content(requestBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(responseBody));
    }

    @Test
    public void shouldGetArtistByIdOnGetRequest() throws Exception {
        // Arrange
        String responseBody = mapper.writeValueAsString(artist1Output);

        // Act, Assert
        mockMvc.perform(get("/artist/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));
    }

    @Test
    public void shouldFailOnGetArtistWithBadId() throws Exception {
        // Act, Assert
        mockMvc.perform(get("/artist/{id}", -1))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldGetAllArtistsOnGetRequest() throws Exception {
        // Arrange
        String responseBody = mapper.writeValueAsString(allAlbums);

        // Act, Assert
        mockMvc.perform(get("/artist"))
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));
    }

    @Test
    public void shouldUpdateArtistOnPutRequest() throws Exception {
        // Arrange
        artist2Input.setLiked(true);
        String requestBody = mapper.writeValueAsString(artist2Input);

        // Act, Assert
        mockMvc.perform(put("/artist/{id}", 1).content(requestBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldFailOnUpdateArtistWithBadId() throws Exception {
        // Arrange
        artist2Input.setLiked(true);
        String requestBody = mapper.writeValueAsString(artist2Input);

        // Act, Assert
        mockMvc.perform(put("/artist/{id}", -1).content(requestBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldDeleteArtistOnDeleteRequest() throws Exception {
        // Act, Assert
        mockMvc.perform(delete("/artist/{id}", 1))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldFailOnDeleteArtistWithBadId() throws Exception {
        // Act, Assert
        mockMvc.perform(delete("/artist/{id}", -1))
                .andExpect(status().isUnprocessableEntity());
    }
}
