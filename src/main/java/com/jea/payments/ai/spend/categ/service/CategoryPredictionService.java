package com.jea.payments.ai.spend.categ.service;

import org.springframework.stereotype.Service;

@Service
public class CategoryPredictionService {

    public String predictCategory(String description) {
        if (description.toLowerCase().contains("grocery")) {
            return "Groceries";
        } else if (description.toLowerCase().contains("electricity") || description.contains("water")) {
            return "Utilities";
        } else if (description.toLowerCase().contains("restaurant")) {
            return "Dining";
        }
        return "Other";
    }
}
