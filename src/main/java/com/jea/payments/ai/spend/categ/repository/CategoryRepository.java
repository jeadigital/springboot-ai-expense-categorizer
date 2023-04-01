package com.jea.payments.ai.spend.categ.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jea.payments.ai.spend.categ.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	   Optional<Category> findByName(String name);
}
