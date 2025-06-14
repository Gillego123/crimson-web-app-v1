package com.crimson.app.crimson.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "T_EVIDENCE")
public class Evidence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long evidenceId;

    @ManyToOne
    @JoinColumn(name = "complaintId")
    private Complaint complaint;

    private  String filePath;
    private String fileType;

    @CreationTimestamp
    private Timestamp uploadedAt;

}
