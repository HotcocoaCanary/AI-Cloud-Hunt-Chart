package com.example.cloudhuntchartbackend.repository;

import com.example.cloudhuntchartbackend.entity.PMIDEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * 接口
 */
public interface PMIDRepository extends Neo4jRepository<PMIDEntity, Long> {
    PMIDEntity findPMIDEntityByNumber(int number);
}
