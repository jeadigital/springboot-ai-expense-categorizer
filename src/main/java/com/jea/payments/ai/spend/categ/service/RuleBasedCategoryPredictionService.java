package com.jea.payments.ai.spend.categ.service;

import org.springframework.stereotype.Service;

@Service
public class RuleBasedCategoryPredictionService {

    public String predictCategory(String description) {
        if (description.toLowerCase().contains("grocery") || description.toLowerCase().contains("walmart")) {
            return "Groceries";
        } else if (description.toLowerCase().contains("restaurant") || description.toLowerCase().contains("dinner")) {
            return "Dining";
        } else if (description.toLowerCase().contains("uber") || description.toLowerCase().contains("lyft")) {
            return "Transport";
        } else if (description.toLowerCase().contains("electricity") || description.toLowerCase().contains("water")) {
            return "Utilities";
        }
        return "Other";
    }
}
