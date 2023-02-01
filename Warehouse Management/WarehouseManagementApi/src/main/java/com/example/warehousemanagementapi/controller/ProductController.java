package com.example.warehousemanagementapi.controller;

import com.example.warehousemanagementapi.dtos.ProductDTO;
import com.example.warehousemanagementapi.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@CrossOrigin
@RequestMapping("v1/api/products")
public class ProductController {
    @Autowired
    private IProductService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> getAllProduct(){
        return ResponseEntity.status(200).body(service.getAllProduct());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getProductById(@PathVariable("id")String id){
        return ResponseEntity.status(200).body(service.getProductByID(id));
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createProduct(@PathVariable("id")String id, @RequestBody @Valid ProductDTO productDTO) {
        return ResponseEntity.status(200).body(service.createProduct(id, productDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> editProduct(@PathVariable("id")String id, @RequestBody @Valid ProductDTO productDTO) {
        return ResponseEntity.status(200).body(service.editProduct(id, productDTO));
    }

    @GetMapping("/getReceipt/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> getReceiptsByProduct(@PathVariable("id")String id) {
        return ResponseEntity.status(200).body(service.getAllReceiptByProduct(id));
    }

    @GetMapping("/getBill/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> getBillByProduct(@PathVariable("id")String id) {
        return ResponseEntity.status(200).body(service.getAllBillByProduct(id));
    }

    @GetMapping("/sortByAmount")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> sortByAmount(){
        return ResponseEntity.status(200).body(service.getProductsSortByAmount());
    }

    @GetMapping("/getByName/{productName}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> getByName(@PathVariable("productName")String productName){
        return ResponseEntity.status(200).body(service.getProductsByName(productName));
    }

    @GetMapping("find/{startP}/{endP}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> getById(@PathVariable("startP")Double startP, @PathVariable("endP")Double endP){
        return ResponseEntity.status(200).body(service.getByPrice(startP, endP));
    }
}
