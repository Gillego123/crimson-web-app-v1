package com.crimson.app.crimson.model;


import com.crimson.app.crimson.common.ComplaintCategory;
import com.crimson.app.crimson.common.ComplaintStatus;
import com.crimson.app.crimson.common.InvestigationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_COMPLAINT")
@Getter
@Setter
public class Complaint {

    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE, generator = "complaint_seq")
    @SequenceGenerator(name = "complaint_seq",sequenceName = "complaint_seq",allocationSize = 1)
    private Long complaintId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "investigator_id")
    private User investigator;

    @ManyToOne
    @JoinColumn(name = "complaintCategoryId")
    private MdlComplaintCategory complaintCategory;


    private String description;
    private String cancellationReason;

    @Enumerated(EnumType.STRING)
    private ComplaintCategory category;

    @Enumerated(EnumType.STRING)
    private ComplaintStatus status = ComplaintStatus.SUBMITTED;

    @Enumerated(EnumType.STRING)
    private InvestigationStatus investigationStatus = InvestigationStatus.PENDING_REVIEW;

    private LocalDateTime filedAT;

    private LocalDateTime updatedAT;

    public Long getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Long complaintId) {
        this.complaintId = complaintId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getInvestigator() {
        return investigator;
    }

    public void setInvestigator(User investigator) {
        this.investigator = investigator;
    }

    public MdlComplaintCategory getComplaintCategory() {
        return complaintCategory;
    }

    public void setComplaintCategory(MdlComplaintCategory complaintCategory) {
        this.complaintCategory = complaintCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    public ComplaintCategory getCategory() {
        return category;
    }

    public void setCategory(ComplaintCategory category) {
        this.category = category;
    }

    public ComplaintStatus getStatus() {
        return status;
    }

    public void setStatus(ComplaintStatus status) {
        this.status = status;
    }

    public InvestigationStatus getInvestigationStatus() {
        return investigationStatus;
    }

    public void setInvestigationStatus(InvestigationStatus investigationStatus) {
        this.investigationStatus = investigationStatus;
    }

    public LocalDateTime getFiledAT() {
        return filedAT;
    }

    public void setFiledAT(LocalDateTime filedAT) {
        this.filedAT = filedAT;
    }

    public LocalDateTime getUpdatedAT() {
        return updatedAT;
    }

    public void setUpdatedAT(LocalDateTime updatedAT) {
        this.updatedAT = updatedAT;
    }
}
