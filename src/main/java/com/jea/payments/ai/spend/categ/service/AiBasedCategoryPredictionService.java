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
public class AiBasedCategoryPredictionService {

    @Value("${spring.ai.openai.api-key}")
    private String openAiApiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String predictCategory(String description) {
        String url = "https://api.openai.com/v1/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + openAiApiKey);
        headers.set("Content-Type", "application/json");

        // Prompt preparation
        String prompt = String.format("Categorize this transaction: '%s' into a category like Groceries, Utilities, Dining, etc.", description);

        // Request body
        Map<String, Object> requestBody = Map.of(
            "model", "text-davinci-003",
            "prompt", prompt,
            "max_tokens", 100,
            "temperature", 0.7
        );

        // API call
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);

        // Extract response
        Map<String, Object> choices = (Map<String, Object>) ((List<Object>) response.getBody().get("choices")).get(0);
        String category = (String) choices.get("text");

        return category.trim();
    }
}
