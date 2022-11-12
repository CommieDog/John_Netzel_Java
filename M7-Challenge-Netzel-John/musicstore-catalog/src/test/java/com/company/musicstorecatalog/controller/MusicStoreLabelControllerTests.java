package com.company.musicstorecatalog.controller;

import com.company.musicstorecatalog.model.Label;
import com.company.musicstorecatalog.repository.LabelRepository;
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
@WebMvcTest(MusicStoreLabelController.class)
public class MusicStoreLabelControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LabelRepository repository;
    private ObjectMapper mapper = new ObjectMapper();

    // Test model objects here for ease of access
    private Label label1Input, label2Input, label1Output, label2Output;
    private List<Label> allLabels;

    @Before
    public void setUp() {
        label1Input = new Label();
        label1Input.setName("Paul's Records");
        label1Input.setWebsite("www.paulsrecords.com");

        label1Output = new Label();
        label1Output.setLabelId(1);
        label1Output.setName("Paul's Records");
        label1Output.setWebsite("www.paulsrecords.com");

        label2Input = new Label();
        label2Input.setName("Big Daddy Music");
        label2Input.setWebsite("www.bigdaddy.com");

        label2Output = new Label();
        label2Output.setLabelId(1);
        label2Output.setName("Big Daddy Music");
        label2Output.setWebsite("www.bigdaddy.com");

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
        label2Input.setName("Big Momma Music");
        String requestBody = mapper.writeValueAsString(label2Input);

        // Act, Assert
        mockMvc.perform(put("/label/{id}", 1).content(requestBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldFailOnUpdateLabelWithBadId() throws Exception {
        // Arrange
        label2Input.setName("Big Momma Music");
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
