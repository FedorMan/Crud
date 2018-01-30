package com.examplecrud.demo.service;


import com.examplecrud.demo.aop.annotation.LogExecutionTime;
import com.examplecrud.demo.entity.Document;
import com.examplecrud.demo.mapper.DocumentMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DocumentService {
    @Autowired
    private DocumentMapper documentMapper;
//    @Autowired
//    private TezisService tezisService;

    @LogExecutionTime
    public List<Document> findAll(){
        return documentMapper.findAll();
    }

    @LogExecutionTime
    public Document create(Document document){
//        try {
//            document = tezisService.create(document);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
        document.setId(UUID.randomUUID());
        documentMapper.create(document.getId(),document.getName(),document.getCode(),document.getDescription());
        return document;
    }

    @LogExecutionTime
    public void delete(UUID id){
        documentMapper.delete(id);
    }

    @LogExecutionTime
    public Document read(UUID id){
        return documentMapper.read(id);
    }

    @LogExecutionTime
    public void update(Document document){
        documentMapper.update(document.getId(),document.getName(),document.getCode(),document.getDescription());
    }

    @LogExecutionTime
    public Document save(Document doc){
        if (documentMapper.read(doc.getId()) != null) {
            update(doc);
        } else {
            create(doc);
        }
        return doc;
    }

    @LogExecutionTime
    public List<Document> findByNameStart(String filterText) {
        return documentMapper.findByNameStart(filterText+"%");
    }
}
