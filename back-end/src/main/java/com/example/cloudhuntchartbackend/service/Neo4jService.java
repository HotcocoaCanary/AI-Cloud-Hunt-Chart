package com.example.cloudhuntchartbackend.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.neo4j.driver.*;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.types.Relationship;
import org.springframework.data.neo4j.core.DatabaseSelectionProvider;
import org.springframework.stereotype.Service;

@Service
public class Neo4jService {

    private final Driver driver;
    private final DatabaseSelectionProvider databaseSelectionProvider;
    private final Gson gson;

    public Neo4jService(Driver driver, DatabaseSelectionProvider databaseSelectionProvider) {
        this.driver = driver;
        this.databaseSelectionProvider = databaseSelectionProvider;
        this.gson = new Gson();
    }

    public JsonObject fetchGraphData() {
        JsonArray nodesJsonArray = new JsonArray();
        JsonArray relationshipsJsonArray = new JsonArray();

        try (Session session = sessionFor(database())) {
            var relationshipsQuery = session.executeRead(tx -> tx.run("MATCH p=(n)-[r]->(m) RETURN n,r,m LIMIT 25").list());

            relationshipsQuery.forEach(record -> {
                Node nodeN = record.get("n").asNode();
                JsonObject nodeNJson = new JsonObject();
                nodeNJson.addProperty("id", nodeN.elementId());
                nodeNJson.add("labels", gson.toJsonTree(nodeN.labels()));
                nodeNJson.add("properties", gson.toJsonTree(nodeN.asMap()));
                nodesJsonArray.add(nodeNJson);
                Relationship relationship = record.get("r").asRelationship();
                JsonObject relationshipJson = new JsonObject();
                relationshipJson.addProperty("id", relationship.elementId());
                relationshipJson.addProperty("type", relationship.type());
                relationshipJson.addProperty("startNode", relationship.startNodeElementId());
                relationshipJson.addProperty("endNode", relationship.endNodeElementId());
                relationshipsJsonArray.add(relationshipJson);
                Node nodeM = record.get("m").asNode();
                JsonObject nodeMJson = new JsonObject();
                nodeMJson.addProperty("id", nodeM.elementId());
                nodeMJson.add("labels", gson.toJsonTree(nodeM.labels()));
                nodeMJson.add("properties", gson.toJsonTree(nodeM.asMap()));
                nodesJsonArray.add(nodeMJson);
            });
        }

        JsonObject result = new JsonObject();
        result.add("nodes",nodesJsonArray);
        result.add("relationships",relationshipsJsonArray);

        return result;
    }

    private Session sessionFor(String database) {
        if (database == null) {
            return driver.session();
        }
        return driver.session(SessionConfig.forDatabase(database));
    }

    private String database() {
        return databaseSelectionProvider.getDatabaseSelection().getValue();
    }

    public JsonObject transformData(){
        // 创建一个 JsonObject 对象
        JsonObject jsonObject = new JsonObject();

        // 创建 results 数组
        JsonArray resultsArray = new JsonArray();

        // 创建一个包含 data 和 columns 数组的对象
        JsonObject resultObject = new JsonObject();
        JsonArray dataArray = new JsonArray();
        JsonArray columnsArray = new JsonArray();

        // 添加一些示例数据到 dataArray 和 columnsArray
        columnsArray.add("user");
        columnsArray.add("entity");

        // 将 dataArray 和 columnsArray 添加到 resultObject
        resultObject.add("data", dataArray);
        resultObject.add("columns", columnsArray);

        // 创建一个包含 graph 对象的数组
        JsonObject graphObject = new JsonObject();
        JsonArray nodesArray = fetchGraphData().getAsJsonArray("nodes");
        JsonArray relationshipsArray = fetchGraphData().getAsJsonArray("relationships");

        // 将 nodesArray 和 relationshipsArray 添加到 graphObject
        graphObject.add("nodes", nodesArray);
        graphObject.add("relationships", relationshipsArray);

        // 将 graphObject 添加到 dataArray
        JsonObject graph= new JsonObject();
        graph.add("graph",graphObject);
        dataArray.add(graph);

        // 将 resultObject 添加到 resultsArray
        resultsArray.add(resultObject);

        // 将 resultsArray 添加到 jsonObject
        jsonObject.add("results", resultsArray);

        // 创建 errors 数组
        JsonArray errorsArray = new JsonArray();

        // 将 errorsArray 添加到 jsonObject
        jsonObject.add("errors", errorsArray);

        // 将 jsonObject 转换为字符串
        return jsonObject;
    }
}
