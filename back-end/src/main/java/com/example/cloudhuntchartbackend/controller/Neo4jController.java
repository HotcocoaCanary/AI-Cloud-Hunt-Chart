package com.example.cloudhuntchartbackend.controller;

import com.example.cloudhuntchartbackend.service.Neo4jService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Neo4jController {

    private final Neo4jService neo4jService;

    @Autowired
    public Neo4jController(Neo4jService neo4jService) {
        this.neo4jService = neo4jService;
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/query")
    public String executeQuery() {
        neo4jService.transformData();
        return neo4jService.transformData().toString();
    }
}