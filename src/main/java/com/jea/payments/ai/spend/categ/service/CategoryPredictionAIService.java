package com.jea.payments.ai.spend.categ.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class CategoryPredictionAIService {

    @Value("${spring.ai.openai.api-key}")
    private String openAiApiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String predictCategory(String description) {
        String url = "https://api.openai.com/v1/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + openAiApiKey);
        headers.set("Content-Type", "application/json");

        // Prepare the prompt
        String prompt = String.format("Categorize the following transaction description into a category like Groceries, Utilities, Dining, etc.: %s", description);

        // Create the request body
        Map<String, Object> requestBody = Map.of(
            "model", "text-davinci-003",  // Or use the desired model
            "prompt", prompt,
            "max_tokens", 100,
            "temperature", 0.7
        );

        // Make the request
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);

        // Extract the category from the response
        Map<String, Object> choices = (Map<String, Object>) ((List<Object>) response.getBody().get("choices")).get(0);
        String category = (String) choices.get("text");

        return category.trim();
    }
}
