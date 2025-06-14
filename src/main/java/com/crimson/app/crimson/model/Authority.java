package com.crimson.app.crimson.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "T_AUTHORITY")
public class Authority  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorityId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Maps to the foreign key `user_id`
    private User user;

    @Column(name = "authority_name", nullable = false) // Maps authority name
    private String authorityName;


}
