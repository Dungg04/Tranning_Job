package com.example.warehousemanagementapi.repositories;

import com.example.warehousemanagementapi.models.Manufacture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufactureRepository extends JpaRepository<Manufacture, String> {
}
