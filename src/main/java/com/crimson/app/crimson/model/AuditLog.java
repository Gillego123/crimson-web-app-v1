package com.crimson.app.crimson.model;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_AUDITLOG")
@AllArgsConstructor
@Getter
@Setter
public class AuditLog  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action;
    private String performedBy;
    private String entityType;
    private Long entityId;
    private LocalDateTime timestamp;

    @Column(length = 1000)
    private String details;

    public AuditLog() {
        this.timestamp= LocalDateTime.now();
    }

    public AuditLog(String action, String performedBy, String entityType, Long entityId, String details) {
        this.action = action;
        this.performedBy = performedBy;
        this.entityType = entityType;
        this.entityId = entityId;
        this.details = details;
        this.timestamp = LocalDateTime.now();
    }
}
