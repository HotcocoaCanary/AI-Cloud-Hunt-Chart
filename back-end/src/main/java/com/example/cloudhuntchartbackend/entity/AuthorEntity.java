package com.example.cloudhuntchartbackend.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

/**
 * Author实体类
 */
@Node(labels = "Author")
@Data
public class AuthorEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public AuthorEntity(String name) {
        this.id = null;
        this.name = name;
    }
}
