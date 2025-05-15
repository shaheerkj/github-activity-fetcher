package com.example.githubUserActivity.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

@Service
public class GitHubActivityFetcher{

    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;
    private static final String GITHUB_URL = "https://api.github.com/users/";


    public GitHubActivityFetcher(RestTemplate restTemplate, ObjectMapper objectMapper){
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public JsonNode fetchData(String username){
        try{
            ResponseEntity<String> response = restTemplate.getForEntity(GITHUB_URL+username+"/events",String.class);
            String responseBody = response.getBody();
            if(responseBody == null){
                throw new RuntimeException("Empty JSON body");
            }
            return objectMapper.readTree(responseBody);
        }
        catch (Exception e){
            throw new RuntimeException("Error fetching or parsing data: " + e.getMessage());
        }
    }
}