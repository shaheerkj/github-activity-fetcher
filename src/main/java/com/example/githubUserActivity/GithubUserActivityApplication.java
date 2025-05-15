package com.example.githubUserActivity;

import com.example.githubUserActivity.service.Analyzer;
import com.example.githubUserActivity.service.GitHubActivityFetcher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@SpringBootApplication
public class GithubUserActivityApplication implements CommandLineRunner {

	public final GitHubActivityFetcher fetcher;
	private final Analyzer analyzer;

	public GithubUserActivityApplication(GitHubActivityFetcher fetcher, Analyzer analyzer){
		this.analyzer = analyzer;
		this.fetcher = fetcher;
	}
	public static void main(String[] args) {
		SpringApplication.run(GithubUserActivityApplication.class, args);
	}


	@Override
	public void run(String... args){
		if(args.length > 0 && userExists(args[0])){
			String username = args[0];
			analyzer.workWithData(fetcher.fetchData(username));
		}
		else{
			System.out.println("Please provide a username");
		}
	}
	public boolean userExists(String username){
		String url = "https://api.github.com/users/"+username;
		try{
			HttpRequest request = HttpRequest.newBuilder(URI.create(url))
					.GET()
					.build();
			HttpResponse<String> response = HttpClient.newHttpClient()
					.send(request, HttpResponse.BodyHandlers.ofString());
			return response.statusCode() == 200;
		}
		catch (IOException | InterruptedException e){
			e.printStackTrace();
			return false;
		}
	}
}
