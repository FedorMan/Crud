package com.examplecrud.demo.serialazer;

import com.examplecrud.demo.entity.Document;
import com.examplecrud.demo.serialazer.JsonDocumentDeserialazer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;


public class JsonDocumentSerialazer extends JsonSerializer<Document> {
    @Override
    public void serialize(Document document, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {

        jsonGenerator.writeStartObject();
        if(document.getId()!=null){
            jsonGenerator.writeStringField("id",JsonDocumentDeserialazer.nameTable+ "-" + document.getId().toString());
        }else{
            jsonGenerator.writeStringField("id","NEW-" + JsonDocumentDeserialazer.nameTable);
        }
        jsonGenerator.writeStringField("theme",document.getName());
        jsonGenerator.writeStringField("number",document.getCode());
        jsonGenerator.writeStringField("comment",document.getDescription());
        jsonGenerator.writeObjectField("docKind",document.getDocKind());
        jsonGenerator.writeEndObject();
    }
}
