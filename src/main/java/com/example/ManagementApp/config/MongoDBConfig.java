package com.example.ManagementApp.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.MongoTemplate;


@Configuration
@PropertySource("application.properties")
public class MongoDBConfig {

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;
    @Value("${spring.data.mongodb.database}")
    private String database;

    @Value("${server.base-url}")
    public String baseUrl;
    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(mongoUri);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), database);
    }


}
