package com.jea.payments.ai.spend.categ.model;


import java.util.List;

import com.jea.payments.ai.spend.categ.repository.CategoryRepository;
import com.jea.payments.ai.spend.categ.repository.TransactionRepository;
import com.jea.payments.ai.spend.categ.service.CategoryPredictionService;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Builder

@AllArgsConstructor
@RequiredArgsConstructor
public class Category {
    // Constructor with only name parameter
    public Category(String name) {
        this.name = name;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Transaction> transactions;
}
