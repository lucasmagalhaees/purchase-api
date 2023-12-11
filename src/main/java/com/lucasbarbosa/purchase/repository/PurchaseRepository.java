package com.lucasbarbosa.purchase.repository;

import com.lucasbarbosa.purchase.model.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, String> {}
