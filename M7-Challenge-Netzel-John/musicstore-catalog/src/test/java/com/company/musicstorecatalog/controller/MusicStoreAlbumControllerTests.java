package com.company.musicstorecatalog.controller;

import com.company.musicstorecatalog.model.Album;
import com.company.musicstorecatalog.repository.AlbumRepository;
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
import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@WebMvcTest(MusicStoreAlbumController.class)
public class MusicStoreAlbumControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlbumRepository repository;
    private ObjectMapper mapper = new ObjectMapper();

    // Test model objects here for ease of access
    private Album album1Input, album2Input, album1Output, album2Output;
    private List<Album> allAlbums;

    @Before
    public void setUp() {
        album1Input = new Album();
        album1Input.setTitle("Album 1");
        album1Input.setArtistId(1);
        album1Input.setReleaseDate(LocalDate.of(2022, 1, 1));
        album1Input.setLabelId(1);
        album1Input.setListPrice(new BigDecimal("9.99"));

        album1Output = new Album();
        album1Output.setAlbumId(1);
        album1Output.setTitle("Album 1");
        album1Output.setArtistId(1);
        album1Output.setReleaseDate(LocalDate.of(2022, 1, 1));
        album1Output.setLabelId(1);
        album1Output.setListPrice(new BigDecimal("9.99"));

        album2Input = new Album();
        album2Input.setTitle("Album 2");
        album2Input.setArtistId(2);
        album2Input.setReleaseDate(LocalDate.of(2022, 1, 2));
        album2Input.setLabelId(1);
        album2Input.setListPrice(new BigDecimal("19.99"));

        album2Output = new Album();
        album2Output.setAlbumId(2);
        album2Output.setTitle("Album 2");
        album2Output.setArtistId(2);
        album2Output.setReleaseDate(LocalDate.of(2022, 1, 2));
        album2Output.setLabelId(1);
        album2Output.setListPrice(new BigDecimal("19.99"));

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
        album2Input.setTitle("Love: The Album");
        album2Input.setReleaseDate(LocalDate.of(2022, 11, 10));
        String requestBody = mapper.writeValueAsString(album2Input);

        // Act, Assert
        mockMvc.perform(put("/album/{id}", 1).content(requestBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldFailOnUpdateAlbumWithBadId() throws Exception {
        // Arrange
        album2Input.setTitle("Love: The Album");
        album2Input.setReleaseDate(LocalDate.of(2022, 11, 10));
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
