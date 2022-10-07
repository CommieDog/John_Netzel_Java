package com.company.monthmathservice;

import com.company.monthmathservice.controllers.MonthController;
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

	@Test
	public void shouldGetMonthByNumberReturnMonthName() throws Exception {
		String expectedVal = "April";

		mockMvc.perform(get("/month/{monthNumber}", 4))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value(expectedVal));
	}
}
