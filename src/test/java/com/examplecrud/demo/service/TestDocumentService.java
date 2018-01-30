package com.examplecrud.demo.service;

import com.examplecrud.demo.entity.Document;
import com.examplecrud.demo.mapper.DocumentMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
public class TestDocumentService {

    @Mock
    DocumentMapper documentMapper;

    @Mock
    TezisService tezisService;

    @InjectMocks
    DocumentService documentService;

    @Test
    public void testFindAll(){
        List<Document> testList = new ArrayList<>();
        testList.add(new Document("test","test","test",null));
        doReturn(testList).when(documentMapper).findAll();
        assertEquals(documentService.findAll(),testList);
        verify(documentMapper,times(1)).findAll();
    }

    @Test
    public void testCreate() throws JsonProcessingException {
        UUID id = UUID.randomUUID();
        Document doc = new Document(id,"testName","testCode","testDescription",null);
        doReturn(doc).when(tezisService).create(doc);
        documentService.create(doc);
        verify(documentMapper,times(1)).create(id,"testName","testCode","testDescription");
        verify(tezisService,times(1)).create(doc);

    }

    @Test
    public void testDelete(){
        UUID id = UUID.randomUUID();
        documentService.delete(id);
        verify(documentMapper,times(1)).delete(id);
    }

    @Test
    public void testRead(){
        UUID id = UUID.randomUUID();
        Document doc = new Document(id,"testName","testCode","testDescription",null);
        doReturn(doc).when(documentMapper).read(id);
        assertEquals(documentService.read(id),doc);
        verify(documentMapper,times(1)).read(id);
    }

    @Test
    public void testUpdate(){
        UUID id = UUID.randomUUID();
        Document doc = new Document(id,"testName","testCode","testDescription",null);
        documentService.update(doc);
        verify(documentMapper,times(1)).update(doc.getId(),doc.getName(),doc.getCode(),doc.getDescription());
    }

}
