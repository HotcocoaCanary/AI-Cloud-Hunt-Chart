package com.example.cloudhuntchartbackend.entity.relation;

import com.example.cloudhuntchartbackend.entity.InstitutionEntity;
import lombok.Getter;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.util.List;

@RelationshipProperties
public class From {

    @Getter
    private final List<String> author;
    @TargetNode // 相当于@StartNode
    private final InstitutionEntity institution;
    @RelationshipId
    private Long id;

    public From(List<String> author, InstitutionEntity institution) {
        this.author = author;
        this.institution = institution;
    }
}
