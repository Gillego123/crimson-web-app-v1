package com.crimson.app.crimson.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "T_ADDRESS_REGION")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddressRegion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressRegionId;

    private String regionName;


}
