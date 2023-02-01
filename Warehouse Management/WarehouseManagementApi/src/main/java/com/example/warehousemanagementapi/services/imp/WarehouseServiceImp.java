package com.example.warehousemanagementapi.services.imp;

import com.example.warehousemanagementapi.dtos.WarehouseDTO;
import com.example.warehousemanagementapi.exceptions.BadRequestException;
import com.example.warehousemanagementapi.exceptions.NotFoundException;
import com.example.warehousemanagementapi.models.Warehouse;
import com.example.warehousemanagementapi.repositories.WarehouseRepository;
import com.example.warehousemanagementapi.services.IWarehouseService;
import com.example.warehousemanagementapi.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseServiceImp implements IWarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    public List<Warehouse> getWarehouses() {
        return warehouseRepository.findAll();
    }

    @Override
    public Warehouse getWarehouse(String warehouseID) {
        Optional<Warehouse> warehouse = warehouseRepository.findById(warehouseID);
        if(!warehouse.isPresent()) {
            throw new NotFoundException("Couldn't find a warehouse with id: " + warehouseID);
        }
        return warehouse.get();
    }

    @Override
    public Warehouse createWarehouse(String warehouseID, WarehouseDTO warehouseDTO) {
        Optional<Warehouse> warehouse = warehouseRepository.findById(warehouseID);
        if(!warehouse.isPresent()) {
            throw new BadRequestException("Already exist warehouse with id: " + warehouseID);
        }
        Warehouse newWarehouse = new Warehouse();
        Convert.fromWarehouseDTOToWarehouse(warehouseDTO, newWarehouse);
        return warehouseRepository.save(newWarehouse);
    }

    @Override
    public Warehouse editWarehouse(String warehouseID, WarehouseDTO warehouseDTO) {
        Optional<Warehouse> warehouse = warehouseRepository.findById(warehouseID);
        if(!warehouse.isPresent()) {
            throw new NotFoundException("Couldn't find a warehouse with id: " + warehouseID);
        }
        Convert.fromWarehouseDTOToWarehouse(warehouseDTO, warehouse.get());
        return warehouseRepository.save(warehouse.get());
    }
}
