package com.jea.payments.ai.spend.categ.controller;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.jea.payments.ai.spend.categ.service.RuleBasedCategoryPredictionService;

@RestController
@RequestMapping("/api/predict/rule-based")
public class RuleBasedPredictionController {

    private final RuleBasedCategoryPredictionService ruleBasedCategoryPredictionService;

    public RuleBasedPredictionController(RuleBasedCategoryPredictionService ruleBasedCategoryPredictionService) {
        this.ruleBasedCategoryPredictionService = ruleBasedCategoryPredictionService;
    }

    @PostMapping
    public String predictCategory(@RequestBody Map<String, String> request) {
        String description = request.get("description");
        return ruleBasedCategoryPredictionService.predictCategory(description);
    }
}
