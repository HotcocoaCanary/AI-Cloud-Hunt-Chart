package com.example.cloudhuntchartbackend.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Institution实体类，一个neo4j的节点
 */
@Node(labels = "Institution") // 标签名，labels可以缺省
@Data
public class InstitutionEntity {

    @Id
    @GeneratedValue // Id自增
    private Long id;

    private String name;

    @Property("Nation") // 映射到neo4j的属性名
    private String nation;

    // 定义一个关系（来自）
    @Relationship(type = "FROM", direction = Relationship.Direction.INCOMING)
    private List<AuthorEntity> from = new ArrayList<>();
    // 定义一个关系（出自）
    @Relationship(type = "POSTED_IN", direction = Relationship.Direction.INCOMING)
    private List<PMIDEntity> post = new ArrayList<>();

    public InstitutionEntity(String name, String nation) {
        this.name = name;
        this.nation = nation;
        this.id = null;// 生成node时自动生成
    }
}
