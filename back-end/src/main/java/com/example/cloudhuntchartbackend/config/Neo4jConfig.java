package com.example.cloudhuntchartbackend.config;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Neo4jConfig {
    private static final String URI = "bolt://localhost:7687";
    private static final String USER = "neo4j";
    private static final String PASSWORD = "zwb052116";

    @Bean
    public Driver driver() {
        return GraphDatabase.driver(URI, AuthTokens.basic(USER, PASSWORD));
    }
}