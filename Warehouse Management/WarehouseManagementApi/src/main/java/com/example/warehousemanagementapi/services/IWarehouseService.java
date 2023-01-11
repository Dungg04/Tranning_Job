package com.example.warehousemanagementapi.services;

import com.example.warehousemanagementapi.dtos.WarehouseDTO;
import com.example.warehousemanagementapi.models.Warehouse;

import java.util.List;

public interface IWarehouseService {
    List<Warehouse> getWarehouses();
    Warehouse getWarehouse(String warehouseID);
    Warehouse createWarehouse(String warehouseID, WarehouseDTO warehouseDTO);
    Warehouse editWarehouse(String warehouseID, WarehouseDTO warehouseDTO);
}
