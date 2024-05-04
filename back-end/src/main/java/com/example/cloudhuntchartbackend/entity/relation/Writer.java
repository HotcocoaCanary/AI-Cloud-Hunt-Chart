package com.example.cloudhuntchartbackend.entity.relation;

import com.example.cloudhuntchartbackend.entity.PMIDEntity;
import lombok.Getter;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.util.List;

@RelationshipProperties
public class Writer {

    @Getter
    private final List<String> author;
    @TargetNode // 相当于@StartNode
    private final PMIDEntity pmid;
    @RelationshipId
    private Long id;

    public Writer(List<String> author, PMIDEntity pmid) {
        this.author = author;
        this.pmid = pmid;
    }
}
