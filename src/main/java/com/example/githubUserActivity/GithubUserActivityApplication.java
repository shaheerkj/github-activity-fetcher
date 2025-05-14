package com.example.githubUserActivity;

import com.example.githubUserActivity.service.Analyzer;
import com.example.githubUserActivity.service.GitHubActivityFetcher;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GithubUserActivityApplication implements CommandLineRunner {

	public final GitHubActivityFetcher fetcher;
	private final ObjectMapper objectMapper;
	private final Analyzer analyzer;

	public GithubUserActivityApplication(GitHubActivityFetcher fetcher, ObjectMapper objectMapper, Analyzer analyzer){
		this.analyzer = analyzer;
		this.fetcher = fetcher;
		this.objectMapper = objectMapper;
	}
	public static void main(String[] args) {
		SpringApplication.run(GithubUserActivityApplication.class, args);
	}


	@Override
	public void run(String... args){

		analyzer.workWithData(fetcher.fetchData("wzc520pyfm"));
	}
}
