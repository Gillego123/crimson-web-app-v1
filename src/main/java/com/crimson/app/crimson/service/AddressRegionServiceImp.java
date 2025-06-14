package com.crimson.app.crimson.service;

import com.crimson.app.crimson.dto.AddressRegionDto;
import com.crimson.app.crimson.model.AddressRegion;
import com.crimson.app.crimson.repository.AddressRegionRepository;
import com.crimson.app.crimson.service.imp.IAddressRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressRegionServiceImp implements IAddressRegionService {


    private final AddressRegionRepository addressRegionRepository;

    public AddressRegionServiceImp(AddressRegionRepository addressRegionRepository) {
        this.addressRegionRepository = addressRegionRepository;
    }

    @Override
    public List<AddressRegionDto> getAllRegion() {
        return addressRegionRepository.getAddressRegion();
    }

    @Override
    public AddressRegion getByAddressRegionId(Long id) {
        return addressRegionRepository.findByAddressRegionId(id);
    }
}
