package com.company.musicstorecatalog.controller;

import com.company.musicstorecatalog.model.Artist;
import com.company.musicstorecatalog.repository.ArtistRepository;
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
@WebMvcTest(MusicStoreArtistController.class)
public class MusicStoreArtistControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArtistRepository repository;
    private ObjectMapper mapper = new ObjectMapper();

    // Test model objects here for ease of access
    private Artist artist1Input, artist2Input, artist1Output, artist2Output;
    private List<Artist> allAlbums;

    @Before
    public void setUp() {
        artist1Input = new Artist();
        artist1Input.setName("Jonny Boy");
        artist1Input.setInstagram("JonnyBoy");
        artist1Input.setTwitter("@jonnyboy");

        artist1Output = new Artist();
        artist1Output.setArtistId(1);
        artist1Output.setName("Jonny Boy");
        artist1Output.setInstagram("JonnyBoy");
        artist1Output.setTwitter("@jonnyboy");

        artist2Input = new Artist();
        artist2Input.setName("The Artist Formerly Known As Jonny Boy");
        artist2Input.setInstagram("NotJonnyBoy");
        artist2Input.setTwitter("@notjonnyboy");

        artist2Output = new Artist();
        artist2Output.setArtistId(2);
        artist2Output.setName("The Artist Formerly Known As Jonny Boy");
        artist2Output.setInstagram("NotJonnyBoy");
        artist2Output.setTwitter("@notjonnyboy");

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
        artist2Input.setName("Jhonny-Boi");
        String requestBody = mapper.writeValueAsString(artist2Input);

        // Act, Assert
        mockMvc.perform(put("/artist/{id}", 1).content(requestBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldFailOnUpdateArtistWithBadId() throws Exception {
        // Arrange
        artist2Input.setName("Jhonny-Boi");
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
