package com.example.cloudhuntchartbackend.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class Neo4jDataFormat {

    public String transformData(String data){
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
        JsonArray nodesArray = transformNodes(data);
        JsonArray relationshipsArray = transformRelationship(data);

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
        return jsonObject.toString();
    }
    private JsonArray transformNodes(String data) {
        List<JsonObject> relationshipList = new ArrayList<>();
//        Set<JsonObject> addedObjects = new HashSet<>();

        JsonParser jsonParser=new JsonParser();
        JsonElement jsonElement = jsonParser.parse(data);

        if (jsonElement.isJsonArray()) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            jsonArray.forEach(jsonItem -> {
                if (jsonItem.isJsonObject()) {
                    JsonObject jsonObject = jsonItem.getAsJsonObject();
                    if (jsonObject.has("p") && jsonObject.get("p").isJsonObject()) {
                        JsonObject pObject = jsonObject.get("p").getAsJsonObject();
                        if (pObject.has("start") && pObject.get("start").isJsonObject() && pObject.has("end") && pObject.get("end").isJsonObject()) {
                            JsonObject startObject = pObject.get("start").getAsJsonObject();
                            JsonObject endObject = pObject.get("end").getAsJsonObject();
                            startObject.addProperty("id", startObject.get("elementId").getAsString());
                            startObject.remove("identity");
                            startObject.remove("elementId");
                            endObject.addProperty("id", endObject.get("elementId").getAsString());
                            endObject.remove("identity");
                            endObject.remove("elementId");
//                            if (!addedObjects.contains(startObject) && !addedObjects.contains(endObject)) {
//                                relationshipList.add(startObject);
//                                relationshipList.add(endObject);
//                                addedObjects.add(startObject);
//                                addedObjects.add(endObject);
//                            }
                            relationshipList.add(startObject);
                            relationshipList.add(endObject);
                        }
                    }
                }
            });
        }
        JsonArray jsonArray = new JsonArray();
        relationshipList.forEach(jsonArray::add);
        return jsonArray;
    }
    private JsonArray transformRelationship(String data) {
        List<JsonObject> relationshipList = new ArrayList<>();

        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(data);

        if (jsonElement.isJsonArray()) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for (JsonElement jsonItem : jsonArray) {
                if (jsonItem.isJsonObject()) {
                    JsonObject jsonObject = jsonItem.getAsJsonObject();
                    if (jsonObject.has("p") && jsonObject.get("p").isJsonObject()) {
                        JsonObject pObject = jsonObject.get("p").getAsJsonObject();
                        if (pObject.has("segments")) {
                            JsonArray segmentsArray = pObject.get("segments").getAsJsonArray();
                            for (JsonElement segment : segmentsArray) {
                                if (segment.isJsonObject()) {
                                    JsonObject segmentObject = segment.getAsJsonObject();
                                    if (segmentObject.has("relationship")) {
                                        JsonObject relationshipObject = segmentObject.get("relationship").getAsJsonObject();
                                        // Rename the elementId property to id
                                        if (relationshipObject.has("elementId")) {
                                            relationshipObject.addProperty("id", relationshipObject.get("elementId").getAsString());
                                            relationshipObject.remove("elementId");
                                        }
                                        // Rename the startNodeElementId property to startNode
                                        if (relationshipObject.has("startNodeElementId")) {
                                            relationshipObject.addProperty("startNode", relationshipObject.get("startNodeElementId").getAsString());
                                            relationshipObject.remove("startNodeElementId");
                                        }
                                        // Rename the endNodeElementId property to endNode
                                        if (relationshipObject.has("endNodeElementId")) {
                                            relationshipObject.addProperty("endNode", relationshipObject.get("endNodeElementId").getAsString());
                                            relationshipObject.remove("endNodeElementId");
                                        }
                                        // Remove unnecessary properties
                                        relationshipObject.remove("identity");
                                        relationshipObject.remove("start");
                                        relationshipObject.remove("end");
                                        relationshipList.add(relationshipObject);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        JsonArray jsonArray = new JsonArray();
        for (JsonObject jsonObject : relationshipList) {
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

}