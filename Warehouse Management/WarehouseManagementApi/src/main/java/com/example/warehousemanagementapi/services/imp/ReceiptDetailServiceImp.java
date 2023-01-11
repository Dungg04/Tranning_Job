package com.example.warehousemanagementapi.services.imp;

import com.example.warehousemanagementapi.dtos.ReceiptDetailDTO;
import com.example.warehousemanagementapi.exceptions.NotFoundException;
import com.example.warehousemanagementapi.models.Product;
import com.example.warehousemanagementapi.models.Receipt;
import com.example.warehousemanagementapi.models.ReceiptDetail;
import com.example.warehousemanagementapi.repositories.ProductRepository;
import com.example.warehousemanagementapi.repositories.ReceiptDetailRepository;
import com.example.warehousemanagementapi.repositories.ReceiptRepository;
import com.example.warehousemanagementapi.services.IReceiptDetailService;
import com.example.warehousemanagementapi.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ReceiptDetailServiceImp implements IReceiptDetailService {
    @Autowired
    private ReceiptDetailRepository receiptDetailRepository;

    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ReceiptDetail getReceiptDetail(Integer receiptDetailID) {
        Optional<ReceiptDetail> receiptDetail = receiptDetailRepository.findById(receiptDetailID);
        if (receiptDetail.isEmpty()) {
            throw new NotFoundException("Couldn't find a receiptDetail with id: " + receiptDetailID);
        }

        return receiptDetail.get();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReceiptDetail createReceiptDetail(Integer receiptID, String productID, ReceiptDetailDTO receiptDetailDTO) {
        Optional<Receipt> receipt = receiptRepository.findById(receiptID);
        if (receipt.isEmpty()) {
            throw new NotFoundException("Couldn't find a receipt with id: " + receiptID);
        }
        Optional<Product> product = productRepository.findById(productID);
        if (product.isEmpty()) {
            throw new NotFoundException("Couldn't find a product with id: " + productID);
        }
//        if (product.get().getAmount() <= 0) {
//            throw new NotFoundException("Quantity is not enough");
//        }

        ReceiptDetail receiptDetail = new ReceiptDetail();
        receiptDetail.setReceipt(receipt.get());
        receiptDetail.setProduct(product.get());
        Convert.fromReceiptDetailDTOToReceiptDetail(receiptDetailDTO, receiptDetail);
        product.get().setAmount(product.get().getAmount()+receiptDetailDTO.getAmount());
        return receiptDetailRepository.save(receiptDetail);
    }

    @Override
    public ReceiptDetail editReceiptDetail(Integer receiptDetailID, ReceiptDetailDTO receiptDetailDTO) {
        Optional<ReceiptDetail> receiptDetail = receiptDetailRepository.findById(receiptDetailID);
        if (receiptDetail.isEmpty()) {
            throw new NotFoundException("Couldn't find a receiptDetail with id: " + receiptDetailID);
        }
        Convert.fromReceiptDetailDTOToReceiptDetail(receiptDetailDTO, receiptDetail.get());
        return receiptDetailRepository.save(receiptDetail.get());
    }
}
