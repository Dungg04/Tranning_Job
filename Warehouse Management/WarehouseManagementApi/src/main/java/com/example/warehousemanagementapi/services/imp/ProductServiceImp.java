package com.example.warehousemanagementapi.services.imp;

import com.example.warehousemanagementapi.dtos.ProductDTO;
import com.example.warehousemanagementapi.exceptions.NotFoundException;
import com.example.warehousemanagementapi.models.BillDetail;
import com.example.warehousemanagementapi.models.Product;
import com.example.warehousemanagementapi.models.ReceiptDetail;
import com.example.warehousemanagementapi.repositories.ProductRepository;
import com.example.warehousemanagementapi.services.IProductService;
import com.example.warehousemanagementapi.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductByID(String productID) {
        Optional<Product> product = productRepository.findById(productID);
        if (!product.isPresent()) {
            throw new NotFoundException("Couldn't find a product with id: " + productID);
        }

        return product.get();
    }

    @Override
    public Product createProduct(String productID, ProductDTO productDTO) {
        Optional<Product> product = productRepository.findById(productID);
        if (product.isPresent()) {
            throw new NotFoundException("Already exist product with id: " + productID);
        }
        Product newProduct = new Product();
        newProduct.setProductID(productID);
        Convert.fromProductDTOToProduct(productDTO, newProduct);

        return productRepository.save(newProduct);
    }

    @Override
    public Product editProduct(String productID, ProductDTO productDTO) {
        Optional<Product> product = productRepository.findById(productID);
        if (!product.isPresent()) {
            throw new NotFoundException("Couldn't find a product with id: " + productID);
        }
        Convert.fromProductDTOToProduct(productDTO, product.get());

        return productRepository.save(product.get());
    }

    @Override
    public List<ReceiptDetail> getAllReceiptByProduct(String productID) {
        Optional<Product> product = productRepository.findById(productID);
        if (!product.isPresent()) {
            throw new NotFoundException("Couldn't find a product with id: " + productID);
        }
        return product.get().getReceiptDetails();
    }

    @Override
    public List<BillDetail> getAllBillByProduct(String productID) {
        Optional<Product> product = productRepository.findById(productID);
        if (!product.isPresent()) {
            throw new NotFoundException("Couldn't find a product with id: " + productID);
        }
        return product.get().getBillDetails();
    }

    @Override
    public List<Product> getProductsSortByAmount() {
        return productRepository.SortProductsByAmount();
    }

    @Override
    public List<Product> getProductsByName(String productName) {
        List<Product> products = productRepository.findAllByProductName(productName);
        if (products.isEmpty()) {
            throw new NotFoundException("Couldn't find a product with name: " + productName);
        }

        return products;
    }

    @Override
    public List<Product> getByPrice(Double startP, Double endP) {
        List<Product> products = productRepository.findAll();
        List<Product> productList = new ArrayList<>();
        for(Product item: products) {
            if (item.getPrice()>=startP && item.getPrice()<=endP) {
                productList.add(item);
            }
        }
        return productList;
    }


}
