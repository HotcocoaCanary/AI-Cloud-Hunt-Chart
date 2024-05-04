package com.example.cloudhuntchartbackend.repository;

import com.example.cloudhuntchartbackend.entity.AuthorEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface AuthorRepository extends Neo4jRepository<AuthorEntity, Long> {
    AuthorEntity findAuthorEntitiesByName(String name);
    AuthorEntity findAuthorEntitiesById(Long id);
}
