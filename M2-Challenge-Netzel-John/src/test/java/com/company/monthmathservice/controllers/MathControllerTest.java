package com.company.monthmathservice.controllers;

import com.company.monthmathservice.models.MathSolution;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.company.monthmathservice.models.MathSolution.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MathController.class)
public class MathControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldAddTwoNumbers() throws Exception {
        MathSolution request = new MathSolution();
        request.setOperand1(1.0);
        request.setOperand2(2.5);

        String requestBody = mapper.writeValueAsString(request);

        mockMvc.perform(post("/add").content(requestBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operation").value(ADD_OPERATION))
                .andExpect(jsonPath("$.answer").value(3.5));
    }

    @Test
    public void shouldSubtractTwoNumbers() throws Exception {
        MathSolution request = new MathSolution();
        request.setOperand1(1.0);
        request.setOperand2(2.5);

        String requestBody = mapper.writeValueAsString(request);

        mockMvc.perform(post("/subtract").content(requestBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operation").value(SUBTRACT_OPERATION))
                .andExpect(jsonPath("$.answer").value(-1.5));
    }

    @Test
    public void shouldMultiplyTwoNumbers() throws Exception {
        MathSolution request = new MathSolution();
        request.setOperand1(1.0);
        request.setOperand2(2.5);

        String requestBody = mapper.writeValueAsString(request);

        mockMvc.perform(post("/multiply").content(requestBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operation").value(MULTIPLY_OPERATION))
                .andExpect(jsonPath("$.answer").value(2.5));
    }

    @Test
    public void shouldDivideTwoNumbers() throws Exception {
        MathSolution request = new MathSolution();
        request.setOperand1(1.0);
        request.setOperand2(2.5);

        String requestBody = mapper.writeValueAsString(request);

        mockMvc.perform(post("/divide").content(requestBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operation").value(DIVIDE_OPERATION))
                .andExpect(jsonPath("$.answer").value(0.4));
    }

    @Test
    public void shouldFailIfOperand1Undefined() throws Exception {
        MathSolution request = new MathSolution();
        request.setOperand2(10.0);

        String requestBody = mapper.writeValueAsString(request);

        mockMvc.perform(post("/add").content(requestBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldFailIfOperand2Undefined() throws Exception {
        MathSolution request = new MathSolution();
        request.setOperand1(10.0);

        String requestBody = mapper.writeValueAsString(request);

        mockMvc.perform(post("/add").content(requestBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldFailIfDividingByZero() throws Exception {
        MathSolution request = new MathSolution();
        request.setOperand1(10.0);
        request.setOperand2(10.0);

        String requestBody = mapper.writeValueAsString(request);

        mockMvc.perform(post("/divide").content(requestBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }
}