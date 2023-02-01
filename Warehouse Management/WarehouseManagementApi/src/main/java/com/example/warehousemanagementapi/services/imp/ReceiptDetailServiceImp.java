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

import java.util.List;
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
    public List<ReceiptDetail> getReceiptDetails(Integer temp) {
        return receiptDetailRepository.findByTemp(temp);
    }

    @Override
    public List<ReceiptDetail> edit(Integer temp) {
        List<ReceiptDetail> receiptDetails = receiptDetailRepository.findByTemp(temp);
        Optional<Receipt> receipt = receiptRepository.findById(temp);
        double total = 0;
        for (ReceiptDetail item: receiptDetails) {
            total += item.getAmount()*item.getPrice();
            item.setReceipt(receipt.get());
            receiptDetailRepository.save(item);
        }
        receipt.get().setIntoMoney(total);
        receiptRepository.save(receipt.get());
        return receiptDetails;
    }

    @Override
    public ReceiptDetail getReceiptDetail(Integer receiptDetailID) {
        Optional<ReceiptDetail> receiptDetail = receiptDetailRepository.findById(receiptDetailID);
        if (!receiptDetail.isPresent()) {
            throw new NotFoundException("Couldn't find a receiptDetail with id: " + receiptDetailID);
        }

        return receiptDetail.get();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReceiptDetail createReceiptDetail(String productID, ReceiptDetailDTO receiptDetailDTO) {
        List<Receipt> receipts = receiptRepository.findAll();

        Receipt receipt = receipts.stream().reduce((first, second) -> second).orElse(null);

        Optional<Product> product = productRepository.findById(productID);
        if (!product.isPresent()) {
            throw new NotFoundException("Couldn't find a product with id: " + productID);
        }


        ReceiptDetail receiptDetail = new ReceiptDetail();
        if(receipts.isEmpty()) {
            receiptDetail.setTemp(1);
        }else {
            receiptDetail.setTemp(receipt.getReceiptID()+1);
        }
        receiptDetail.setProduct(product.get());
        Convert.fromReceiptDetailDTOToReceiptDetail(receiptDetailDTO, receiptDetail);
        product.get().setAmount(product.get().getAmount()+receiptDetailDTO.getAmount());
        product.get().setPrice(product.get().getPrice());
//        receipt.setIntoMoney(receipt.getIntoMoney()+receiptDetailDTO.getAmount()*receiptDetailDTO.getPrice());
        return receiptDetailRepository.save(receiptDetail);
    }

    @Override
    public ReceiptDetail editReceiptDetail(Integer receiptDetailID, ReceiptDetailDTO receiptDetailDTO) {
        Optional<ReceiptDetail> receiptDetail = receiptDetailRepository.findById(receiptDetailID);
        if (!receiptDetail.isPresent()) {
            throw new NotFoundException("Couldn't find a receiptDetail with id: " + receiptDetailID);
        }
        Convert.fromReceiptDetailDTOToReceiptDetail(receiptDetailDTO, receiptDetail.get());
        return receiptDetailRepository.save(receiptDetail.get());
    }
}
