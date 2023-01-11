package com.example.warehousemanagementapi.repositories;

import com.example.warehousemanagementapi.models.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {
    List<Receipt> findAllByInputDay(Date inputDay);

}
