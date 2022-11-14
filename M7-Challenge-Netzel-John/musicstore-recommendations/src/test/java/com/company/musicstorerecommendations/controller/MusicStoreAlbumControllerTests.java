package com.company.musicstorerecommendations.controller;

import com.company.musicstorerecommendations.model.AlbumRecommendation;
import com.company.musicstorerecommendations.repository.AlbumRecommendationRepository;
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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MusicStoreAlbumRecommendationController.class)
public class MusicStoreAlbumControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlbumRecommendationRepository repository;
    private ObjectMapper mapper = new ObjectMapper();

    // Test model objects here for ease of access
    private AlbumRecommendation album1Input, album2Input, album1Output, album2Output;
    private List<AlbumRecommendation> allAlbums;

    @Before
    public void setUp() {
        album1Input = new AlbumRecommendation();
        album1Input.setAlbumId(1);
        album1Input.setUserId(1);
        album1Input.setLiked(true);

        album1Output = new AlbumRecommendation();
        album1Output.setAlbumRecommendationId(1);
        album1Output.setAlbumId(1);
        album1Output.setUserId(1);
        album1Output.setLiked(true);

        album2Input = new AlbumRecommendation();
        album2Input.setAlbumId(2);
        album2Input.setUserId(2);
        album2Input.setLiked(false);

        album2Output = new AlbumRecommendation();
        album2Output.setAlbumRecommendationId(2);
        album2Output.setAlbumId(2);
        album2Output.setUserId(2);
        album2Output.setLiked(false);

        allAlbums = Arrays.asList(album1Output, album2Output);

        setUpMocks();
    }

    private void setUpMocks() {
        doReturn(album1Output).when(repository).save(album1Input);
        doReturn(Optional.of(album1Output)).when(repository).findById(1);
        doReturn(allAlbums).when(repository).findAll();
    }

    @Test
    public void shouldAddAlbumOnPostRequest() throws Exception {
        // Arrange
        String requestBody = mapper.writeValueAsString(album1Input);
        String responseBody = mapper.writeValueAsString(album1Output);

        // Act, Assert
        mockMvc.perform(post("/album").content(requestBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(responseBody));
    }

    @Test
    public void shouldGetAlbumByIdOnGetRequest() throws Exception {
        // Arrange
        String responseBody = mapper.writeValueAsString(album1Output);

        // Act, Assert
        mockMvc.perform(get("/album/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));
    }

    @Test
    public void shouldFailOnGetAlbumWithBadId() throws Exception {
        // Act, Assert
        mockMvc.perform(get("/album/{id}", -1))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldGetAllAlbumsOnGetRequest() throws Exception {
        // Arrange
        String responseBody = mapper.writeValueAsString(allAlbums);

        // Act, Assert
        mockMvc.perform(get("/album"))
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));
    }

    @Test
    public void shouldUpdateAlbumOnPutRequest() throws Exception {
        // Arrange
        album2Input.setLiked(true);
        String requestBody = mapper.writeValueAsString(album2Input);

        // Act, Assert
        mockMvc.perform(put("/album/{id}", 1).content(requestBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldFailOnUpdateAlbumWithBadId() throws Exception {
        // Arrange
        album2Input.setLiked(true);
        String requestBody = mapper.writeValueAsString(album2Input);

        // Act, Assert
        mockMvc.perform(put("/album/{id}", -1).content(requestBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldDeleteAlbumOnDeleteRequest() throws Exception {
        // Act, Assert
        mockMvc.perform(delete("/album/{id}", 1))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldFailOnDeleteAlbumWithBadId() throws Exception {
        // Act, Assert
        mockMvc.perform(delete("/album/{id}", -1))
                .andExpect(status().isUnprocessableEntity());
    }
}
