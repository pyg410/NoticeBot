package com.ygpark.noticeService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class NoticeTalkApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoticeTalkApplication.class, args);
	}

}
