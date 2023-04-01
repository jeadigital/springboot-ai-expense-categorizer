package com.jea.payments.ai.spend.categ.service;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jea.payments.ai.spend.categ.model.Category;
import com.jea.payments.ai.spend.categ.repository.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    public List<Category> getAllCategories() {
        logger.info("Fetching all categories");
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        logger.info("Fetching category with ID: {}", id);
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + id));
    }

    public Category createCategory(Category category) {
        logger.info("Creating new category: {}", category.getName());
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        logger.info("Deleting category with ID: {}", id);
        categoryRepository.deleteById(id);
    }
}
