package com.examplecrud.demo.entity;


import com.examplecrud.demo.serialazer.JsonDocumentDeserialazer;
import com.examplecrud.demo.serialazer.JsonDocumentSerialazer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.ibatis.annotations.Param;


import java.io.Serializable;
import java.util.UUID;

@JsonSerialize(using = JsonDocumentSerialazer.class)
@JsonDeserialize(using = JsonDocumentDeserialazer.class)
public class Document implements Serializable{
    private UUID id;
    private String name;
    private String code;
    private String description;
    private DocKind docKind;

    public Document(UUID id, String name, String code, String description, DocKind docKind) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
        this.docKind = docKind;
    }

    public Document(String name, String code, String description, DocKind docKind) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.docKind = docKind;
    }

    public Document() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DocKind getDocKind() {
        return docKind;
    }

    public void setDocKind(DocKind docKind) {
        this.docKind = docKind;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", docKind=" + docKind +
                '}';
    }
}
