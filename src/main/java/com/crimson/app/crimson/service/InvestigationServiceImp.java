package com.crimson.app.crimson.service;

import com.crimson.app.crimson.common.ComplaintStatus;
import com.crimson.app.crimson.common.InvestigationStatus;
import com.crimson.app.crimson.model.Complaint;
import com.crimson.app.crimson.model.Investigation;
import com.crimson.app.crimson.model.User;
import com.crimson.app.crimson.repository.CompliantRepository;
import com.crimson.app.crimson.repository.InvestigationRepository;
import com.crimson.app.crimson.repository.UserRepository;
import com.crimson.app.crimson.service.imp.IInvestigationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InvestigationServiceImp implements IInvestigationService {

    @Autowired
    InvestigationRepository investigationRepository;

    @Autowired
    CompliantRepository compliantRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public Investigation startInvestigation(Long complaintId, Long investigatorId) {

        Complaint complaint =compliantRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Compliant not foud with ID:" + complaintId));

        User user = userRepository.findById(investigatorId)
                .orElseThrow(() -> new RuntimeException("Investigator not found with ID: "+ investigatorId));

        if(!complaint.getStatus().toString().equalsIgnoreCase("SUBMITTED") &&
                !complaint.getStatus().toString().equalsIgnoreCase("CANCELED") ){
            throw new RuntimeException("Investigation can only be started for new and pending complaints ");
        }

        if(!complaint.getInvestigationStatus().toString().equals( "PENDING_REVIEW")){
            throw new RuntimeException("Investigation already exists for this complaint.");
        }

        Investigation investigation = new Investigation();
        investigation.setComplaint(complaint);
        investigation.setInvestigator(user);
        investigation.setStartedAt(LocalDateTime.now());
        investigation.setInvestigationStatus(InvestigationStatus.IN_PROGRESS);

        //Update Complaint Details
        complaint.setInvestigationStatus(InvestigationStatus.IN_PROGRESS);
        complaint.setStatus(ComplaintStatus.UNDER_INVESTIGATION);
        complaint.setInvestigator(user);

        return investigationRepository.save(investigation);
    }

    @Override
    @Transactional
    public void assignInvestigator(Long complaintId, Long investigatorId) {
        // Fetch Investigation
        Investigation investigation = investigationRepository.findById(investigatorId)
                .orElseThrow(() -> new RuntimeException("Investigation not found with ID: " + investigatorId));
        // Fetch User (Investigator)
        User newInvestigator = userRepository.findById(investigatorId)
                .orElseThrow(() -> new RuntimeException("Investigator not found with ID: " + investigatorId));
        // Validate Investigation Status
        if (!InvestigationStatus.IN_PROGRESS.equals(investigation.getInvestigationStatus())) {
            throw new RuntimeException("Investigator reassignment is only allowed for investigations in progress.");
        }
        // Update Investigator in Investigation
        investigation.setInvestigator(newInvestigator);
        // Save and Return Updated Investigation
        investigationRepository.save(investigation);
    }

    @Override
    public void updateInvestigationStatus(Long complaintId, String status) {

        Complaint complaint = compliantRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found with ID: " + complaintId));
        if (complaint.getInvestigationStatus().toString().equals(status) ) {
            throw new IllegalStateException("Complaint is already in status: " + status);
        }
        complaint.setInvestigationStatus(InvestigationStatus.valueOf(status.toUpperCase()));
        compliantRepository.save(complaint);

    }

    @Override
    public void updateInvestigationProgress(Long investigationId, String progressDetails) {

        Investigation investigation = investigationRepository.findById(investigationId)
                .orElseThrow(() -> new RuntimeException("Investigation not found with ID: " + investigationId));

        if (investigation.getInvestigationStatus() != InvestigationStatus.IN_PROGRESS) {
            throw new IllegalStateException("Cannot update progress for a completed or closed investigation.");
        }

        // Append new progress update
        String existingProgress = investigation.getFindings() != null ? investigation.getFindings() : "";
        investigation.setFindings(existingProgress + "\n" + LocalDateTime.now() + " - " + progressDetails);

        investigationRepository.save(investigation);
    }

    @Override
    public void addInvestigationNotes(Long complaintId, String investigatorId, String notes) {
    }

    @Override
    public void addEvidence(Long complaintId, String evidenceDetails, byte[] evidenceFile) {
    }

    @Override
    public void resolveInvestigation(Long complaintId, String resolutionDetails) {
    }

    @Override
    public List<Investigation> getInvestigationsByInvestigator(String investigatorId) {
        return List.of();
    }

    @Override
    public Investigation getInvestigationDetails(Long complaintId) {
        return null;
    }

    @Override
    public void logInvestigationAction(Long complaintId, String action, String investigatorId, String details) {
    }

}
