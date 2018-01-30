package com.examplecrud.demo.service;
import com.examplecrud.demo.entity.CommitInstance;
import com.examplecrud.demo.entity.DocKind;
import com.examplecrud.demo.entity.Document;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class TezisService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String loginPath = "http://localhost:8080/app-portal/api/login";
    private static final String commitPath = "http://localhost:8080/app-portal/api/commit";

    public String getConnect(String login, String pass, String loc){
        return restTemplate.getForEntity(loginPath+"?u="+login+"&p="+pass+"&l="+loc,String.class).getBody();
    }

    public Document create(Document document) throws JsonProcessingException {
        String p = commitPath+"?s=" + getConnect("admin","admin","ru");
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");
        document.setDocKind(new DocKind("df$DocKind-3c59f74c-fe98-11e2-9e49-a30cc182d375"));

        CommitInstance commitInstance =new CommitInstance();
        commitInstance.add(document);

        String str = null;
        str = new ObjectMapper().writeValueAsString(commitInstance);

        HttpEntity<JSONObject> request = new HttpEntity(str, headers);

        Document retDoc = restTemplate.postForObject(p, request, Document.class);
        return retDoc;
    }
}