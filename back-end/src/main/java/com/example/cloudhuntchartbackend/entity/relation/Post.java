package com.example.cloudhuntchartbackend.entity.relation;

import com.example.cloudhuntchartbackend.entity.InstitutionEntity;
import lombok.Getter;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.util.List;

@RelationshipProperties
public class Post {

    @Getter
    private final List<String> PMID;
    @TargetNode // 相当于@StartNode
    private final InstitutionEntity institution;
    @RelationshipId
    private Long id;

    // 参数1是目标关系实体节点 参数2是关系属性
    //    Roles 参数1：Person实体，演员的出生年和姓名；参数2：演员名字列表（考虑到一个演员可能参演多个角色）
    public Post(List<String> PMID, InstitutionEntity institution) {
        this.PMID = PMID;
        this.institution = institution;
    }
}
