package com.jea.payments.ai.spend.categ.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jea.payments.ai.spend.categ.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
