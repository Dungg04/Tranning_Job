package com.example.warehousemanagementapi.repositories;

import com.example.warehousemanagementapi.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    @Query(value = "select * from PRODUCTS ORDER BY amount asc ", nativeQuery = true)
    List<Product> SortProductsByAmount();

//    @Query(value = "select * from PRODUCTS where productName LIKE %:name%", nativeQuery = true)
//    List<Product> findAllByName(@Param("name") String productName);

    List<Product> findAllByProductName(String productName);
    List<Product> findAllByProductID(String productId);
}
