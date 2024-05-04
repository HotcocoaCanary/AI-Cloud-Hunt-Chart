package com.example.cloudhuntchartbackend.repository;

import com.example.cloudhuntchartbackend.entity.InstitutionEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface InstitutionRepository extends Neo4jRepository<InstitutionEntity, Long> {
    InstitutionEntity findInstitutionEntitiesByName(String name);
}
