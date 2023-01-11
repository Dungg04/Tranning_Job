package com.example.warehousemanagementapi.services.imp;

import com.example.warehousemanagementapi.dtos.ManufactureDTO;
import com.example.warehousemanagementapi.exceptions.BadRequestException;
import com.example.warehousemanagementapi.exceptions.NotFoundException;
import com.example.warehousemanagementapi.models.Manufacture;
import com.example.warehousemanagementapi.repositories.ManufactureRepository;
import com.example.warehousemanagementapi.services.IManufactureService;
import com.example.warehousemanagementapi.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ManufactureServiceImp implements IManufactureService {
    @Autowired
    public ManufactureRepository manufactureRepository;

    @Override
    public List<Manufacture> getManufactures() {
        return manufactureRepository.findAll();
    }

    @Override
    public Manufacture getManufacture(String manufactureID) {
        Optional<Manufacture> manufacture = manufactureRepository.findById(manufactureID);
        if(manufacture.isEmpty()) {
            throw new NotFoundException("Couldn't find a supplier with id: " + manufactureID);
        }
        return manufacture.get();
    }

    @Override
    public Manufacture createManufacture(String manufactureID,ManufactureDTO manufactureDTO) {
        Optional<Manufacture> manufacture = manufactureRepository.findById(manufactureID);
        if(!manufacture.isEmpty()) {
            throw new BadRequestException("Already exist a manufacture with id: " + manufactureID);
        }
        Manufacture newManufacture = new Manufacture();
        newManufacture.setManufactureID(manufactureID);
        Convert.fromManufactureDTOToManufacture(manufactureDTO, newManufacture);
        return manufactureRepository.save(newManufacture);
    }

    @Override
    public Manufacture editManufacture(String manufactureID, ManufactureDTO manufactureDTO) {
        Optional<Manufacture> manufacture = manufactureRepository.findById(manufactureID);
        if(manufacture.isEmpty()) {
            throw new NotFoundException("Couldn't find a supplier with id: " + manufactureID);
        }
        Convert.fromManufactureDTOToManufacture(manufactureDTO, manufacture.get());
        return manufactureRepository.save(manufacture.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteManufacture(String manufactureID) {
        Optional<Manufacture> manufacture = manufactureRepository.findById(manufactureID);
        if(manufacture.isEmpty()) {
            throw new NotFoundException("Couldn't find a supplier with id: " + manufactureID);
        }
        manufactureRepository.delete(manufacture.get());
        return true;
    }
}
