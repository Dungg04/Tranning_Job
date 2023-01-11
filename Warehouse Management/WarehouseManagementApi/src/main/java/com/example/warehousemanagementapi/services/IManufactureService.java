package com.example.warehousemanagementapi.services;

import com.example.warehousemanagementapi.dtos.ManufactureDTO;
import com.example.warehousemanagementapi.models.Manufacture;

import java.util.List;

public interface IManufactureService {
    List<Manufacture> getManufactures();
    Manufacture getManufacture(String manufactureID);
    Manufacture createManufacture(String manufactureID,ManufactureDTO manufactureDTO);
    Manufacture editManufacture(String manufactureID, ManufactureDTO manufactureDTO);
    boolean deleteManufacture(String manufactureID);
}
