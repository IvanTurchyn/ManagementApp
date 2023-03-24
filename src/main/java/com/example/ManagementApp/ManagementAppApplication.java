package com.example.ManagementApp;

import com.example.ManagementApp.config.WebConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collections;

@Import(WebConfig.class)
@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.example.ManagementApp.repository")
public class ManagementAppApplication {
	@Value("${server.port}")
	private int port;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ManagementAppApplication.class);
		ManagementAppApplication managementAppApplication = new ManagementAppApplication();
		app.setDefaultProperties(Collections.singletonMap("server.port", String.valueOf(managementAppApplication.port)));
		app.run(args);
	}
}
