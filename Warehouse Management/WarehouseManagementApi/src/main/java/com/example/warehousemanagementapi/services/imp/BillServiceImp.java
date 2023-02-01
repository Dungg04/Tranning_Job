package com.example.warehousemanagementapi.services.imp;

import com.example.warehousemanagementapi.dtos.BillDTO;
import com.example.warehousemanagementapi.dtos.BillDetailDTO;
import com.example.warehousemanagementapi.exceptions.NotFoundException;
import com.example.warehousemanagementapi.models.Bill;
import com.example.warehousemanagementapi.models.BillDetail;
import com.example.warehousemanagementapi.models.Customer;
import com.example.warehousemanagementapi.repositories.BillDetailRepository;
import com.example.warehousemanagementapi.repositories.BillRepository;
import com.example.warehousemanagementapi.repositories.CustomerRepository;
import com.example.warehousemanagementapi.services.IBillService;
import com.example.warehousemanagementapi.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BillServiceImp implements IBillService {
    @Autowired
    public BillRepository billRepository;

    @Autowired
    public CustomerRepository customerRepository;

    @Autowired
    private BillDetailRepository billDetailRepository;

    @Override
    public List<Bill> getBills() {
        return billRepository.findAll();
    }

    @Override
    public Bill getBill(int id) {
        Optional<Bill> bill = billRepository.findById(id);
        if(!bill.isPresent()) {
            throw new NotFoundException("Couldn't find a bill with id: " + id);
        }
        return bill.get();
    }

    @Override
    @Transactional
    public Bill createBill(String customerID, BillDTO billDTO, Set<BillDetailDTO> billDetailDTOS) {
        Optional<Customer> customer = customerRepository.findById(customerID);
        if(!customer.isPresent()) {
            throw new NotFoundException("Couldn't find a customer with id: " + customerID);
        }
        Bill bill = new Bill();
        bill.setCustomer(customer.get());
        bill.setExportDate(java.time.LocalDate.now());
//        bill.setIntoMoney();
        Convert.fromBillDTTOToBill(billDTO, bill);
        Bill bill1 = billRepository.save(bill);
        for(BillDetailDTO billDetailDTO : billDetailDTOS) {
            BillDetail billDetail = new BillDetail();
            Convert.fromBillDetailDTOToBillDetail(billDetailDTO, billDetail);
            billDetail.setBill(bill1);
            billDetailRepository.save(billDetail);
        }
        return bill1;
    }

    @Override
    public Bill editBill(int id, BillDTO billDTO) {
        Optional<Bill> bill = billRepository.findById(id);
        if(!bill.isPresent()) {
            throw new NotFoundException("Couldn't find a bill with id: " + id);
        }
        Convert.fromBillDTTOToBill(billDTO, bill.get());
        return billRepository.save(bill.get());
    }

//    @Override
//    public String exportReport(String reportFormat) {
//        String path="D:\\Training";
//        return null;
//    }

//    Couldn't  find a customer with id
//    Already exist customer with id:
}
