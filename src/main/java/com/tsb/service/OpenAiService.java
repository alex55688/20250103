package com.tsb.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tsb.domain.Message;

import okhttp3.OkHttpClient;
import okhttp3.*;

@PropertySource("classpath:application.properties")
@PropertySource("classpath:api-key.properties")
@RequestMapping(path = "/openai")
@RestController
public class OpenAiService {
	
	@Value("${spring.openai.api-key}")
	private String apiKey; 
	
	@Value("${spring.openai.model}")
	private String model;
	
	@Value("${spring.openai.api-url}")
	private String apiUrl;
	
	@PostMapping(path = "/chatGPT", consumes = "application/json")
	public String askChatGPT(@RequestBody String questions) {
		String answer = null;
		
		Gson gson = new Gson();
		Message message = gson.fromJson(questions, Message.class);
		
		JsonObject requestBody = new JsonObject();
		JsonObject userMessage = new JsonObject();
		JsonArray messages = new JsonArray();
		
		requestBody.addProperty("model", model);
		userMessage.addProperty("content", message.getContent());
		userMessage.addProperty("role", "user");
		
		messages.add(userMessage);
		requestBody.add("messages", messages);
		
		
		OkHttpClient client = new OkHttpClient();		
		
		
        MediaType json = MediaType.parse("application/json");
        okhttp3.RequestBody okRequestBody = okhttp3.RequestBody.create(requestBody.toString(), json);

        Request request = new Request.Builder()
        		.url(apiUrl)
        		.post(okRequestBody)
        		.addHeader("Authorization", "Bearer "+apiKey)
        		.build();
        
        try (Response response = client.newCall(request).execute()){
			if(response.isSuccessful()) {
				answer = response.body().string();
				return answer;
			}else {
				return "Error : " +response.code() + "-" + response.message();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return answer;
	}
}
