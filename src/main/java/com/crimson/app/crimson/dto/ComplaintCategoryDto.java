package com.crimson.app.crimson.dto;



public class ComplaintCategoryDto {

    private Long complaintCategoryId;
    private String category;

    public ComplaintCategoryDto(Long complaintCategoryId, String category) {
        this.complaintCategoryId = complaintCategoryId;
        this.category = category;
    }

    public Long getComplaintCategoryId() {
        return complaintCategoryId;
    }

    public void setComplaintCategoryId(Long complaintCategoryId) {
        this.complaintCategoryId = complaintCategoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return category;

    }
}
