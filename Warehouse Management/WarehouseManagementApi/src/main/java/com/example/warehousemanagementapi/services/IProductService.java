package com.example.warehousemanagementapi.services;

import com.example.warehousemanagementapi.dtos.ProductDTO;
import com.example.warehousemanagementapi.models.BillDetail;
import com.example.warehousemanagementapi.models.Product;
import com.example.warehousemanagementapi.models.ReceiptDetail;

import java.util.List;

public interface IProductService {
    List<Product> getAllProduct();
    Product getProductByID(String productID);
    Product createProduct(String productID, ProductDTO productDTO);
    Product editProduct(String productID, ProductDTO productDTO);
    List<ReceiptDetail> getAllReceiptByProduct(String productID);
    List<BillDetail> getAllBillByProduct(String productID);
    List<Product> getProductsSortByAmount();
    List<Product> getProductsByName(String productName);
    List<Product> getByPrice(Double startP, Double endP);
}
