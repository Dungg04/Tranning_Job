package com.example.warehousemanagementapi.controller;

import com.example.warehousemanagementapi.dtos.ProductDTO;
import com.example.warehousemanagementapi.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/products")
public class ProductController {
    @Autowired
    private IProductService service;

    @GetMapping
    public ResponseEntity<?> getAllProduct(){
        return ResponseEntity.status(200).body(service.getAllProduct());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id")String id){
        return ResponseEntity.status(200).body(service.getProductByID(id));
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> createProduct(@PathVariable("id")String id, @RequestBody ProductDTO productDTO) {
        return ResponseEntity.status(200).body(service.createProduct(id, productDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editProduct(@PathVariable("id")String id, @RequestBody ProductDTO productDTO) {
        return ResponseEntity.status(200).body(service.editProduct(id, productDTO));
    }
}
