package com.revature.readawaybackend;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ReadawayBackendApplication extends SpringBootServletInitializer{
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

  public static void main(String[] args) {
    SpringApplication.run(ReadawayBackendApplication.class, args);
  }

}
