package com.example.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("com.example")
@Configuration
public class Config {

	Logger logger = LoggerFactory.getLogger(Config.class);
	
}
