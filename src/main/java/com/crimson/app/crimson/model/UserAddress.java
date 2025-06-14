package com.crimson.app.crimson.model;

import jakarta.persistence.*;
import lombok.*;
import org.atmosphere.config.service.Get;

@Entity
@Table(name = "T_USER_ADDRESS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    private String streetRoomDriveAddress;
    private String barangayAddress;
    private String provinceAddress;
    private String cityAddress;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Maps to the foreign key `user_id`
    private User user;

}
