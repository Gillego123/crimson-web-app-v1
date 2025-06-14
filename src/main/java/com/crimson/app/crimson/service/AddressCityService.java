package com.crimson.app.crimson.service;

import com.crimson.app.crimson.dto.AddressCityDto;
import com.crimson.app.crimson.model.AddressRegion;
import com.crimson.app.crimson.repository.AddressCityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressCityService {

    private final AddressCityRepository addressCityRepository;

    public AddressCityService(AddressCityRepository addressCityRepository) {
        this.addressCityRepository = addressCityRepository;
    }

    public List<AddressCityDto> getCityByRegion(AddressRegion region){
        return addressCityRepository.findByRegion(region);
    }
}
