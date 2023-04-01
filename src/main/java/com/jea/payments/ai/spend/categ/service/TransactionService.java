package com.jea.payments.ai.spend.categ.service;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.jea.payments.ai.spend.categ.model.Category;
import com.jea.payments.ai.spend.categ.model.Transaction;
import com.jea.payments.ai.spend.categ.repository.CategoryRepository;
import com.jea.payments.ai.spend.categ.repository.TransactionRepository;

import java.util.List;

@Service
@Data

public class TransactionService {
    private final TransactionRepository transactionRepository;
    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    private final CategoryRepository categoryRepository;
    private final CategoryPredictionService categoryPredictionService;

    public TransactionService(TransactionRepository transactionRepository,
                              CategoryRepository categoryRepository,
                              CategoryPredictionService categoryPredictionService) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
        this.categoryPredictionService = categoryPredictionService;
    }

    public Transaction saveTransaction(Transaction transaction) {
        String predictedCategoryName = categoryPredictionService.predictCategory(transaction.getDescription());
        Category category = categoryRepository.findByName(predictedCategoryName)
                .orElseGet(() -> categoryRepository.save(new Category(predictedCategoryName)));
        transaction.setCategory(category);
        return transactionRepository.save(transaction);
    }
    public Transaction saveAITransaction(Transaction transaction) {
        String predictedCategoryName = categoryPredictionService.predictCategory(transaction.getDescription());

        Category category = categoryRepository.findByName(predictedCategoryName)
                .orElseGet(() -> categoryRepository.save(
                        Category.builder().name(predictedCategoryName).build())
                );

        transaction.setCategory(category);
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        logger.info("Fetching all transactions");
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(Long id) {
        logger.info("Fetching transaction with ID: {}", id);
        return transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found for ID: " + id));
    }



    public void deleteTransaction(Long id) {
        logger.info("Deleting transaction with ID: {}", id);
        transactionRepository.deleteById(id);
    }
    
    
}
