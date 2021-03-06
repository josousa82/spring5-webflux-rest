package com.training.spring5webfluxrest;

import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

//@EnableReactiveMongoRepositories
@SpringBootApplication
public class Spring5WebfluxRestApplication  {

    public static void main(String[] args) {
        SpringApplication.run(Spring5WebfluxRestApplication.class, args);
    }

}
