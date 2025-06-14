package com.crimson.app.crimson.service.imp;

import com.crimson.app.crimson.common.ComplaintStatus;
import com.crimson.app.crimson.dto.ComplaintDTO;
import com.crimson.app.crimson.model.Complaint;
import com.crimson.app.crimson.model.User;

import java.util.List;

public interface IComplaintService {

    Complaint updateComplaint(Long complaintId, Complaint updateComplaintDetails);

    // Create a new complaint
    Complaint fileComplaint(Long userId, ComplaintDTO complaintDTO);

    // Get complaint details by ID
    List<Complaint> getComplaintById(Long complaintId);

    // Retrieve all complaints
    List<Complaint> getAllComplaints();

    // Retrieve complaints by status (Submitted, In Progress, Resolved, Canceled)
    List<Complaint> getComplaintsByStatus(ComplaintStatus status);

    // Retrieve complaints filed by a specific complainant
    List<Complaint> getComplaintsByComplainantId(User user);


    // Update complaint details (if allowed)
    Complaint  updateComplaint(Long complaintId, ComplaintDTO complaintDTO);

    // Update complaint status (e.g., from Pending to In Progress)
    Complaint updateComplaintStatus(Long complaintId, String status);

    // Assign an investigator to a complaint
    Complaint assignInvestigator(Long complaintId, Long investigatorId);

    // Cancel a complaint (if allowed)
    void cancelComplaint(Long complaintId, ComplaintDTO reason);

    // Log any actions related to complaints
    void logComplaintAction(Long complaintId, String action, String performedBy, String details);


}
