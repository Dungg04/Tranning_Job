package com.example.warehousemanagementapi.repositories;

import com.example.warehousemanagementapi.models.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail, Integer> {
}
