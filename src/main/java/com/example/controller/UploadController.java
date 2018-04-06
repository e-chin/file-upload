package com.example.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.FileDAO;
import com.example.entity.tempFile;

@ControllerAdvice
@RestController
public class UploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	
	@Autowired
	FileDAO fileDAO;

	
	@RequestMapping(value = "/files", method = RequestMethod.POST)
	public String upload(@RequestBody tempFile file, HttpServletResponse response) throws IOException {
		
		logger.info("Uploading file"); 
		
		try {
			fileDAO.upload(file.getFilePath(), file.getFileName());
			response.setStatus(201);
			return "Upload successful.";
		} catch (IOException e) {
			logger.info("Upload failed");
			response.setStatus(400);
			return "Upload failed. Invalid file path.";
		} catch (NullPointerException e) {
			logger.info("Upload failed");
			response.setStatus(400);
			return "Upload failed. File name or file path not provided.";
		}
		
	}

}
