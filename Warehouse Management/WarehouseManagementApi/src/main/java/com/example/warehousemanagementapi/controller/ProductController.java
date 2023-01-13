package com.example.warehousemanagementapi.controller;

import com.example.warehousemanagementapi.dtos.ProductDTO;
import com.example.warehousemanagementapi.services.IProductService;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
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

    @GetMapping("/getReceipt/{id}")
    public ResponseEntity<?> getReceiptsByProduct(@PathVariable("id")String id) {
        return ResponseEntity.status(200).body(service.getAllReceiptByProduct(id));
    }

    @GetMapping("/getBill/{id}")
    public ResponseEntity<?> getBillByProduct(@PathVariable("id")String id) {
        return ResponseEntity.status(200).body(service.getAllBillByProduct(id));
    }

    @GetMapping("/sortByAmount")
    public ResponseEntity<?> sortByAmount(){
        return ResponseEntity.status(200).body(service.getProductsSortByAmount());
    }

    @GetMapping("/getByName/{productName}")
    public ResponseEntity<?> getByName(@PathVariable("productName")String productName){
        return ResponseEntity.status(200).body(service.getProductsByName(productName));
    }

    @GetMapping("/getById/{productID}")
    public ResponseEntity<?> getById(@PathVariable("productID")String productID){
        return ResponseEntity.status(200).body(service.getProductByID(productID));
    }
}
