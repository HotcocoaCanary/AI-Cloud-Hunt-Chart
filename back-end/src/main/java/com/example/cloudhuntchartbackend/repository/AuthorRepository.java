package com.example.cloudhuntchartbackend.repository;

import com.example.cloudhuntchartbackend.entity.AuthorEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * 接口
 */
public interface AuthorRepository extends Neo4jRepository<AuthorEntity, Long> {
    AuthorEntity findAuthorEntitiesByName(String name);
}
