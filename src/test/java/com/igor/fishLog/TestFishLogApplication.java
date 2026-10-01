package com.igor.fishLog;

import org.springframework.boot.SpringApplication;

public class TestFishLogApplication {

	public static void main(String[] args) {
		SpringApplication.from(FishLogApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
