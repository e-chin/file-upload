package com.example;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.dao.FileDAO;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class FileUploadApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Autowired
    private MockMvc mockMvc;
	
	@Mock
	FileDAO dao;
	
	@Test(expected = IOException.class)
	public void shouldThrowIOException() throws IOException {
		doThrow(new IOException()).when(dao).upload(null, null);
		dao.upload(null, null);
	}

	
	@Test
	public void shouldReturnErrorMessageAndBadRequest() throws Exception {
		String json = "{}";
		MvcResult result = mockMvc.perform(post("/files").contentType(MediaType.APPLICATION_JSON).content(json))
		.andExpect(status().isBadRequest())
		.andReturn();
		
		String content = result.getResponse().getContentAsString();
		assertEquals("Upload failed. File name or file path not provided.", content);
    }
	
	@Test
	public void shouldReturnErrorMessageAndBadRequest2() throws Exception {
		String json = "{\"filePath\":\"invalidFilePath\",\"fileName\":\"test2\"}";
		MvcResult result = mockMvc.perform(post("/files").contentType(MediaType.APPLICATION_JSON).content(json))
		.andExpect(status().isBadRequest())
		.andReturn();
		
		String content = result.getResponse().getContentAsString();
		assertEquals("Upload failed. Invalid file path.", content);
    }
	
	
	
}
