package com.example.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class FileDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(FileDAO.class);
	
	// path value is initialize in application.properties
	@Value("${upload.path}")
	private String path;
	
	public void upload (String filePath, String fileName) throws IOException  {
		
		
		if (!filePath.isEmpty()) {
			
			File f = new File(filePath);
			
			if(fileName.isEmpty() || fileName == null) {
				fileName = f.getName();
			}
			
			Path src = f.toPath();

            Files.copy(src, Paths.get(path + fileName),StandardCopyOption.REPLACE_EXISTING);
            
            logger.info("Upload successful");


        } else {
        	
        	logger.info("Upload failed");
        	         
        }
	}

}
