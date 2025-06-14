package com.crimson.app.crimson.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "T_COMPLAINT_CATEGORY")
@Getter
@Setter
public class MdlComplaintCategory {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long complaintCategoryId;

    private String category;

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
}
