package com.company.monthmathservice;

import com.company.monthmathservice.controllers.MonthController;
import com.company.monthmathservice.models.Month;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MonthController.class)
public class MonthControllerTests {
	@Autowired
	private MockMvc mockMvc;
	private ObjectMapper mapper = new ObjectMapper();

	@Test
	public void shouldGetMonthByNumberReturnMonthName() throws Exception {
		String expectedVal = mapper.writeValueAsString(new Month(4));

		mockMvc.perform(get("/month/{monthNumber}", 4))
				.andExpect(status().isOk())
				.andExpect(content().json(expectedVal));
	}

	@Test
	public void shouldFailGetMonthByNumberWithNumberOutOfBounds() throws Exception {
		mockMvc.perform(get("/month/{monthNumber}", 0))
				.andExpect(status().isUnprocessableEntity());
		mockMvc.perform(get("/month/{monthNumber}", 13))
				.andExpect(status().isUnprocessableEntity());
	}

	@Test
	public void shouldGetRandomMonth() throws Exception {
		mockMvc.perform(get("/randomMonth"))
				.andExpect(status().isOk());
	}
}
