package com.example.githubUserActivity.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

@Service
public class Analyzer {
    public void workWithData(JsonNode jsonNode){
        if (jsonNode == null || !jsonNode.isArray()) {
            System.out.println("Invalid input data");
            return;
        } else if (jsonNode.isEmpty()) {
            System.out.println("Empty.");
            return;
        }
        JsonNode payload;
        String name = jsonNode.get(0).get("actor").get("login").asText();
        System.out.println("======================================");
        for(JsonNode index:jsonNode){
            payload = index.get("payload");
            switch (index.get("type").asText()){
                case "PushEvent" -> {
                    System.out.println("Pushed " + payload.get("commits").size() + " commit(s) to " + index.get("repo").get("name"));
                }
                case "ForkEvent" -> {
                    System.out.println("User forked " + index.get("repo").get("name"));
                }

                case "CreateEvent" -> {
                    System.out.println("Create Event. Type: " + payload.get("ref_type") + ", Name: " + payload.get("ref") + ", Master: " + payload.get("master_branch"));
                }
                case "WatchEvent" -> {
                    System.out.println("User " + name + " starred the repository " + index.get("repo").get("name"));
                }
                case "CommitCommentEvent" -> {
                    System.out.println("Commit action: " + payload.get("action") + ". Comment: " + payload.get("comment"));
                }
                case "DeleteEvent" -> {
                    System.out.println("Deletion. Type: " + payload.get("ref_type") + ", Name: " + payload.get("ref"));
                }
                case "IssueCommentEvent" -> {
                    System.out.println("User " + name + " " + payload.get("action") + " comment on Issue#" + payload.get("issue").get("number") + " on repository " + index.get("repo").get("name") + ", Comment: " + payload.get("comment").get("body"));
                }
                case "PullRequestEvent" -> {
                    System.out.println("Pull Request -> Action: " + payload.get("action") + ", Number: " + payload.get("number") + ", Title: " + payload.get("pull_request").get("title"));
                }
                case "IssuesEvent" -> {
                    if (payload != null) {
                        String action = payload.has("action") ? payload.get("action").asText() : "N/A";
                        int issueNumber = payload.has("issue") && payload.get("issue").has("number")
                                ? payload.get("issue").get("number").asInt()
                                : -1;
                        String issueTitle = payload.has("issue") && payload.get("issue").has("title")
                                ? payload.get("issue").get("title").asText()
                                : "N/A";

                        System.out.println("Issues Event -> Action: " + action + ", Issue#: " + issueNumber + ", Title: " + issueTitle);
                    } else {
                        System.out.println("No payload data available for IssuesEvent.");
                    }
                }
                case "ReleaseEvent" -> {
                    if (payload != null) {
                        String action = payload.has("action") ? payload.get("action").asText() : "N/A";
                        String tagName = payload.has("release") && payload.get("release").has("tag_name")
                                ? payload.get("release").get("tag_name").asText()
                                : "N/A";
                        String releaseName = payload.has("release") && payload.get("release").has("name")
                                ? payload.get("release").get("name").asText()
                                : "N/A";

                        System.out.println("Release Event -> Action: " + action + ", Tag: " + tagName + ", Name: " + releaseName);
                    } else {
                        System.out.println("No payload data available for ReleaseEvent.");
                    }
                }
                default -> {
                    System.out.println("Event type: " + index.get("type"));
                }

            }
            System.out.println("======================================");

        }
    }
}
