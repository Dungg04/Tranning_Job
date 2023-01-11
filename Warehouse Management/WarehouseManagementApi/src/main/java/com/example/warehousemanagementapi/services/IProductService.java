package com.example.warehousemanagementapi.services;

import com.example.warehousemanagementapi.dtos.ProductDTO;
import com.example.warehousemanagementapi.models.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProduct();
    Product getProductByID(String productID);
    Product createProduct(String productID, ProductDTO productDTO);
    Product editProduct(String productID, ProductDTO productDTO);
}
