package com.example.ManagementApp;
import com.example.ManagementApp.config.MongoDBConfig;
import com.example.ManagementApp.config.WebConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;

import java.util.Collections;

@Import(WebConfig.class)


@SpringBootApplication
@EnableAutoConfiguration
public class ManagementAppApplication {
	@Value("${server.port}")
	static
	int port;
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(MongoDBConfig.class);
		context.refresh();
		SpringApplication app = new SpringApplication(ManagementAppApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", String.valueOf(port)));
		app.run(args);
	}
}
