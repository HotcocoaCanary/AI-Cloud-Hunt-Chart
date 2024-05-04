package com.example.cloudhuntchartbackend.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

/**
 * Author实体类，一个neo4j的节点
 */
@Node(labels = "Author") // 标签名，labels可以缺省
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
