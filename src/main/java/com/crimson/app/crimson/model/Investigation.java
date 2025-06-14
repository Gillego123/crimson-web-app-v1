package com.crimson.app.crimson.model;

import com.crimson.app.crimson.common.InvestigationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_INVESTIGATION")
@Getter
@Setter
public class Investigation {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long investigationId;

    @ManyToOne
    @JoinColumn(name = "complaint_id", nullable = false)
    private Complaint complaint;

    @ManyToOne
    @JoinColumn(name = "investigator_id", nullable = false)
    private User investigator;

    private String findings;

    @Enumerated(EnumType.STRING)
    private InvestigationStatus investigationStatus = InvestigationStatus.PENDING_REVIEW;

    @ElementCollection
    private List<String> investigationNotes = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime startedAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;

    public Long getInvestigationId() {
        return investigationId;
    }

    public void setInvestigationId(Long investigationId) {
        this.investigationId = investigationId;
    }

    public Complaint getComplaint() {
        return complaint;
    }

    public void setComplaint(Complaint complaint) {
        this.complaint = complaint;
    }

    public User getInvestigator() {
        return investigator;
    }

    public void setInvestigator(User investigator) {
        this.investigator = investigator;
    }

    public String getFindings() {
        return findings;
    }

    public void setFindings(String findings) {
        this.findings = findings;
    }

    public InvestigationStatus getInvestigationStatus() {
        return investigationStatus;
    }

    public void setInvestigationStatus(InvestigationStatus investigationStatus) {
        this.investigationStatus = investigationStatus;
    }

    public List<String> getInvestigationNotes() {
        return investigationNotes;
    }

    public void setInvestigationNotes(List<String> investigationNotes) {
        this.investigationNotes = investigationNotes;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
}
