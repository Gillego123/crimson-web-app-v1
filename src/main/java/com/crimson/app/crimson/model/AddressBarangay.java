package com.crimson.app.crimson.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "T_ADDRESS_BARANGAY")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddressBarangay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressBarangayID;

    private String barangayName;

    @ManyToOne
    @JoinColumn(name = "addressCityMunicipalityId", nullable = false) // Maps to the foreign key `user_id`
    private AddressCityMunicipality city;
}
