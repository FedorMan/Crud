package com.examplecrud.demo.serialazer;

import com.examplecrud.demo.entity.DocKind;
import com.examplecrud.demo.entity.Document;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.UUID;

public class JsonDocumentDeserialazer extends JsonDeserializer<Document>{

    public static final String nameTable = "df$SimpleDoc";

    @Override
    public Document deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Document document = new Document();

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        if (node.isArray()){
            node = node.get(0);
        }
        if(node.get("theme") != null) document.setName(node.get("theme").asText());
        if(node.get("number") != null) document.setCode(node.get("number").asText());
        if(node.get("comment") != null) document.setDescription(node.get("comment").asText());
        if(node.get("id") != null) {
            if(node.get("id").asText().equals("NEW-"+nameTable)){
                document.setId(null);
            }else if(node.get("id").asText().contains(nameTable)){
                String uuid = node.get("id").asText().substring(nameTable.length()+1,node.get("id").asText().length());
                if (!uuid.equals("")) document.setId(UUID.fromString(uuid));
                else document.setId(null);
            }else{
                document.setId(UUID.fromString(node.get("id").asText()));
            }
        }
        if(node.get("docKind") != null) {
            String s = node.get("docKind").toString();
            document.setDocKind(new ObjectMapper().readValue(s, DocKind.class));
        }
        return document;
    }
}
