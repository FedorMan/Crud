package com.examplecrud.demo.entity;

import java.io.Serializable;

public class DocKind implements Serializable{
    private String id;

    public DocKind(String id) {
        this.id = id;
    }

    public DocKind() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DocKind{" +
                "id='" + id + '\'' +
                '}';
    }
}
