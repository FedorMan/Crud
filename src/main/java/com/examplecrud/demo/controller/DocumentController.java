package com.examplecrud.demo.controller;

import com.examplecrud.demo.entity.Document;
import com.examplecrud.demo.service.DocumentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequestMapping(path = "/documents")
@Controller
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @GetMapping()
    @ResponseBody
    public List<Document> getAll(){
        return documentService.findAll();
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    public Document read(@PathVariable("id") String id){
        UUID uuidId = UUID.fromString(id);
        Document doc= documentService.read(uuidId);
        return doc;
    }

    @PostMapping(consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Document create(@RequestBody String entity){
        Document document = null;
        try {
            document = new ObjectMapper().readValue(entity,Document.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return documentService.create(document);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public UUID delete(@PathVariable String id){
        UUID uuidId = UUID.fromString(id);
        documentService.delete(uuidId);
        return uuidId;
    }

    @PutMapping()
    @ResponseBody
    public Document update(@RequestBody String entity){
        Document document = null;
        try {
            document = new ObjectMapper().readValue(entity,Document.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        documentService.update(document);
        return document;
    }

}
