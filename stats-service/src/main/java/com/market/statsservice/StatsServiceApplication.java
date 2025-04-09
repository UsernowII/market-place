package com.market.statsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication

public class StatsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StatsServiceApplication.class, args);
	}

}
