package com.crimson.app.crimson.service.imp;

import com.crimson.app.crimson.dto.AddressRegionDto;
import com.crimson.app.crimson.model.AddressRegion;

import java.util.List;

public interface IAddressRegionService {

    List<AddressRegionDto> getAllRegion();
    AddressRegion getByAddressRegionId(Long id);
}
