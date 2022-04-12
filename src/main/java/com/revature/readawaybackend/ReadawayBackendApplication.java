package com.revature.readawaybackend;

import com.revature.readawaybackend.service.GiveawayService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReadawayBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadawayBackendApplication.class, args);
		new GiveawayService().serverRestartScheduleGiveaways();
	}

}
