package com.jea.payments.ai.spend.categ.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.jea.payments.ai.spend.categ.service.AiBasedCategoryPredictionService;

@RestController
@RequestMapping("/api/predict/ai-based")
public class AiBasedPredictionController {

    private final AiBasedCategoryPredictionService aiBasedCategoryPredictionService;

    public AiBasedPredictionController(AiBasedCategoryPredictionService aiBasedCategoryPredictionService) {
        this.aiBasedCategoryPredictionService = aiBasedCategoryPredictionService;
    }

    @PostMapping
    public String predictCategory(@RequestBody Map<String, String> request) {
        String description = request.get("description");
        return aiBasedCategoryPredictionService.predictCategory(description);
    }
}
