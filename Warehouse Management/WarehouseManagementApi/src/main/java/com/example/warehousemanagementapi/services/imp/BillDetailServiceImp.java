package com.example.warehousemanagementapi.services.imp;

import com.example.warehousemanagementapi.dtos.BillDetailDTO;
import com.example.warehousemanagementapi.exceptions.NotFoundException;
import com.example.warehousemanagementapi.models.Bill;
import com.example.warehousemanagementapi.models.BillDetail;
import com.example.warehousemanagementapi.models.Product;
import com.example.warehousemanagementapi.models.Receipt;
import com.example.warehousemanagementapi.repositories.BillDetailRepository;
import com.example.warehousemanagementapi.repositories.BillRepository;
import com.example.warehousemanagementapi.repositories.CustomerRepository;
import com.example.warehousemanagementapi.repositories.ProductRepository;
import com.example.warehousemanagementapi.services.IBillDetailService;
import com.example.warehousemanagementapi.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BillDetailServiceImp implements IBillDetailService {
    @Autowired
    private BillDetailRepository billDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BillRepository billRepository;

    @Override
    public BillDetail getBillDetail(Integer billDetailID) {
        Optional<BillDetail> billDetail = billDetailRepository.findById(billDetailID);
        if (!billDetail.isPresent()) {
            throw new NotFoundException("Couldn't find a billDetail with id: " + billDetailID);
        }
        return billDetail.get();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BillDetail createBillDetail(String productID, Integer billID, BillDetailDTO billDetailDTO) {
        Optional<Bill> bill = billRepository.findById(billID);
        if (!bill.isPresent()) {
            throw new NotFoundException("Couldn't find a receipt with id: " + billID);
        }
        Optional<Product> product = productRepository.findById(productID);
        if (!bill.isPresent()) {
            throw new NotFoundException("Couldn't find a product with id: " + productID);
        }

        BillDetail billDetail = new BillDetail();
        billDetail.setBill(bill.get());
        billDetail.setProduct(product.get());
        Convert.fromBillDetailDTOToBillDetail(billDetailDTO, billDetail);
        product.get().setAmount(product.get().getAmount() - billDetailDTO.getAmount());
        return billDetailRepository.save(billDetail);
    }

    @Override
    public BillDetail editBillDetail(Integer billDetailID, BillDetailDTO billDetailDTO) {
        Optional<BillDetail> billDetail = billDetailRepository.findById(billDetailID);
        if (!billDetail.isPresent()) {
            throw new NotFoundException("Couldn't find a billDetail with id: " + billDetailID);
        }
        Convert.fromBillDetailDTOToBillDetail(billDetailDTO, billDetail.get());
        return billDetailRepository.save(billDetail.get());
    }
}
