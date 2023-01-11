package com.example.warehousemanagementapi.repositories;

import com.example.warehousemanagementapi.models.Inventory;
import com.example.warehousemanagementapi.models.ReceiptDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptDetailRepository extends JpaRepository<ReceiptDetail, Integer> {
}
