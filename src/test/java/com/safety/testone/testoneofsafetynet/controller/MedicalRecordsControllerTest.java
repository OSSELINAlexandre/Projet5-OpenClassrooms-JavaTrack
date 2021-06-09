package com.safety.testone.testoneofsafetynet.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class MedicalRecordsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testGetMedicalRecords() throws Exception {

		mockMvc.perform(get("/medicalrecords")).andExpect(status().isOk());
	}

}