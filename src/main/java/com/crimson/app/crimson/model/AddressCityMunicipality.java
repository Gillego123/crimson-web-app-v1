package com.crimson.app.crimson.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "T_ADDRESS_CITY")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddressCityMunicipality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressCityMunicipalityId;

    private String cityName;

    @ManyToOne
    @JoinColumn(name = "addressRegionId", nullable = false) // Maps to the foreign key `user_id`
    private AddressRegion region;

}
