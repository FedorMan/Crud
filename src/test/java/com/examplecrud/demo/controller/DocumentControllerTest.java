package com.examplecrud.demo.controller;

import com.examplecrud.demo.DemoApplication;
import com.examplecrud.demo.entity.Document;
import com.examplecrud.demo.service.DocumentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import javax.sql.DataSource;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@WebAppConfiguration
@SqlGroup(
        {@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:setupData.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:dropData.sql")}
)
public class DocumentControllerTest {

    private MockMvc mockMvc;

    protected MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("UTF-8"));

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Autowired
    protected DataSource dataSource;

    @Autowired
    protected DocumentService documentService;

    @Before
    public void before() throws SQLException {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @After
    public void after(){

    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/documents/").contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));
    }

    @Test
    public void testCreate() throws Exception {
        Document doc = new Document("IntegrationTest","iii","asfaf",null);
        String json = mockMvc.perform(post("/documents/")
                .contentType(contentType)
                .content(new ObjectMapper().writeValueAsString(doc)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json("{\n" +
                        "  \"theme\":\"IntegrationTest\",\n" +
                        "  \"number\":\"iii\",\n" +
                        "  \"comment\":\"asfaf\"\n" +
                        "}",false))
                .andReturn().getResponse().getContentAsString();
        doc = new ObjectMapper().readValue(json,Document.class);
        documentService.delete(doc.getId());
    }

    @Test
    public void testRead() throws Exception {
        mockMvc.perform(get("/documents/{id}","cba88e8c-0284-11e8-ba89-0ed5f89f718a")
                .contentType(contentType)
                .param("id","cba88e8c-0284-11e8-ba89-0ed5f89f718b"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json("{\n" +
                        "  \"id\":\"df$SimpleDoc-cba88e8c-0284-11e8-ba89-0ed5f89f718a\", \n" +
                        "  \"theme\":\"integration Test name\",\n" +
                        "  \"number\":\"integration Test code\",\n" +
                        "  \"comment\":\"integration test description\"\n" +
                        "}",false));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/documents/{id}","fc575a06-04b7-11e8-ba89-0ed5f89f718b")
                .contentType(contentType)
                .param("id","fc575a06-04b7-11e8-ba89-0ed5f89f718b"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json(new ObjectMapper().writeValueAsString(UUID.fromString("fc575a06-04b7-11e8-ba89-0ed5f89f718b"))));

    }

    @Test
    public void testUpdate() throws Exception {
        Document doc = new Document(UUID.fromString("cba88e8c-0284-11e8-ba89-0ed5f89f718a"),"IntegrationTest","iii","asfaf",null);
        mockMvc.perform(put("/documents/")
                .contentType(contentType)
                .content(new ObjectMapper().writeValueAsString(doc)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json("{\n" +
                        "  \"id\":\"df$SimpleDoc-cba88e8c-0284-11e8-ba89-0ed5f89f718a\", \n" +
                        "  \"theme\":\"IntegrationTest\",\n" +
                        "  \"number\":\"iii\",\n" +
                        "  \"comment\":\"asfaf\"\n" +
                        "}",false));

    }

}
