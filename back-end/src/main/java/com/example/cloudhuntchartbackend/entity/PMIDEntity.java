package com.example.cloudhuntchartbackend.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * PMID实体类，一个neo4j的节点
 */
@Node(labels = "PMID")
@Data
public class PMIDEntity {

    public static final String DATE = "date";
    public static final String PERIODICAL = "periodical";
    public static final String KEYWORD = "keyword";
    public static final String TITLE = "title";

    @Id
    @GeneratedValue
    private Long id;

    private int number;

    @Property("Title")
    private String title;

    @Property("Date")
    private Date date;

    @Property("Keyword")
    private String keyword;

    @Property("Periodical")
    private String periodical;

    // 定义一个关系（编写）
    @Relationship(type = "WORK", direction = Relationship.Direction.INCOMING)
    private List<AuthorEntity> work = new ArrayList<>();

    public PMIDEntity(int number) {
        this.id = null;
        this.number = number;
    }

    public PMIDEntity(int number, String string, String type) {
        if(Objects.equals(type, "periodical")){
            this.id = null;
            this.number = number;
            this.periodical = string;
        }
        if(Objects.equals(type, "keyword")){
            this.id = null;
            this.number = number;
            this.keyword = string;
        }
        if(Objects.equals(type, "title")){
            this.id = null;
            this.number = number;
            this.title = string;
        }
        if(Objects.equals(type, "date")){
            this.id = null;
            this.number = number;
            this.date = new Date(Integer.parseInt(string)/ 10000, (Integer.parseInt(string) % 10000) / 100, Integer.parseInt(string) % 100);
        }
    }
}
