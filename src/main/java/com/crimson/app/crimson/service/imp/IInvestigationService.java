package com.crimson.app.crimson.service.imp;

import com.crimson.app.crimson.model.Investigation;
import java.util.List;

public interface IInvestigationService {


    // Create a new investigation for a complaint
    Investigation startInvestigation(Long complaintId, Long investigatorId);

    // Assign an investigator to a complaint (this could be during case creation or re-assignment)
    void assignInvestigator(Long complaintId, Long investigatorId);

    // Update the investigation status (e.g., from "Pending" to "In Progress")
    void updateInvestigationStatus(Long complaintId, String status);

    // Update the progress of an investigation
    void updateInvestigationProgress(Long complaintId, String progressDetails);

    // Add notes to the investigation (for internal communication)
    void addInvestigationNotes(Long complaintId, String investigatorId, String notes);

    // Add evidence to an investigation
    void addEvidence(Long complaintId, String evidenceDetails, byte[] evidenceFile);

    // Complete the investigation and set status to Resolved/Closed
    void resolveInvestigation(Long complaintId, String resolutionDetails);

    // Retrieve all investigations for a particular investigator
    List<Investigation> getInvestigationsByInvestigator(String investigatorId);

    // Get the details of a specific investigation
    Investigation getInvestigationDetails(Long complaintId);

    // Log any audit actions for investigation activities
    void logInvestigationAction(Long complaintId, String action, String investigatorId, String details);

}
