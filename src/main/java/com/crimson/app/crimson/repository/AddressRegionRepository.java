package com.crimson.app.crimson.repository;

import com.crimson.app.crimson.dto.AddressRegionDto;
import com.crimson.app.crimson.model.AddressRegion;
import com.crimson.app.crimson.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRegionRepository extends JpaRepository<AddressRegion, Long> {

    @Query(value = """
            Select new com.crimson.app.crimson.dto.AddressRegionDto(a.addressRegionId, a.regionName)
                    from AddressRegion a
            """)
    List<AddressRegionDto> getAddressRegion();

    AddressRegion findByAddressRegionId(Long id);

}
