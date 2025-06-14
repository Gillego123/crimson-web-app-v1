package com.crimson.app.crimson.repository;

import com.crimson.app.crimson.dto.AddressCityDto;
import com.crimson.app.crimson.model.AddressCityMunicipality;
import com.crimson.app.crimson.model.AddressRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressCityRepository extends JpaRepository<AddressCityMunicipality, Long> {
    List<AddressCityDto> findByRegion(AddressRegion addressRegion);
}
