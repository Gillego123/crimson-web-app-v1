package com.crimson.app.crimson.dto;

import com.crimson.app.crimson.common.ComplaintCategory;
import com.crimson.app.crimson.common.ComplaintStatus;
import com.crimson.app.crimson.common.InvestigationStatus;
import com.crimson.app.crimson.model.User;

import java.time.LocalDateTime;

public class ComplaintDTO {

    private Long complaintId;
    private User user;
    private ComplaintCategory category;
    private String description;
    private String cancellationReason;
    private ComplaintStatus status;
    private InvestigationStatus investigationStatus;
    private LocalDateTime filedAT;
    private LocalDateTime updatedAT;
    private Long complaintCategoryId;

    public ComplaintDTO() {
    }



    public ComplaintDTO(Long complaintId, User user, ComplaintCategory category, String description, String cancellationReason,
                        ComplaintStatus status, InvestigationStatus investigationStatus, LocalDateTime filedAT,
                        LocalDateTime updatedAT, Long complaintCategoryId) {
        this.complaintId = complaintId;
        this.user = user;
        this.category = category;
        this.description = description;
        this.cancellationReason = cancellationReason;
        this.status = status;
        this.investigationStatus = investigationStatus;
        this.filedAT = filedAT;
        this.updatedAT = updatedAT;
        this.complaintCategoryId= complaintCategoryId;
    }

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

    public ComplaintCategory getCategory() {
        return category;
    }

    public void setCategory(ComplaintCategory category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
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

    public Long getComplaintCategoryId() {
        return complaintCategoryId;
    }

    public void setComplaintCategoryId(Long complaintCategoryId) {
        this.complaintCategoryId = complaintCategoryId;
    }
}
