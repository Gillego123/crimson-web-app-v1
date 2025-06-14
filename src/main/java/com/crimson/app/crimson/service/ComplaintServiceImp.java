package com.crimson.app.crimson.service;

import com.crimson.app.crimson.common.ComplaintStatus;
import com.crimson.app.crimson.common.InvestigationStatus;
import com.crimson.app.crimson.dto.ComplaintDTO;
import com.crimson.app.crimson.model.Complaint;
import com.crimson.app.crimson.model.MdlComplaintCategory;
import com.crimson.app.crimson.model.User;
import com.crimson.app.crimson.repository.ComplaintCategoryRepository;
import com.crimson.app.crimson.repository.CompliantRepository;
import com.crimson.app.crimson.repository.UserRepository;
import com.crimson.app.crimson.service.imp.IComplaintService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ComplaintServiceImp implements IComplaintService {

    private static final Logger logger =  LoggerFactory.getLogger(ComplaintServiceImp.class);

    @Autowired
    private CompliantRepository compliantRepository;

    @Autowired
    private AuditLogServiceImp auditLogServiceImp;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private ComplaintCategoryRepository complaintCategoryRepository;


    public ComplaintServiceImp(CompliantRepository compliantRepository, AuditLogServiceImp auditLogServiceImp,
                               UserRepository userRepository,ComplaintCategoryRepository complaintCategoryRepository) {
        this.compliantRepository = compliantRepository;
        this.auditLogServiceImp = auditLogServiceImp;
        this.userRepository = userRepository;
        this.complaintCategoryRepository= complaintCategoryRepository;
    }

    @Override
    public Complaint updateComplaint(Long complaintId, Complaint updateComplaintDetails) {
        boolean isComplaintExist = compliantRepository.existsById(complaintId);
        if(!isComplaintExist){
            throw  new RuntimeException("Complaint not found");
        }
        return compliantRepository.save(updateComplaintDetails);
    }

    @Override
    public Complaint fileComplaint(Long userId,ComplaintDTO complaintDTO) {

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));
        MdlComplaintCategory mdlComplaintCategory = complaintCategoryRepository.findById(complaintDTO
                .getComplaintCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));

        Complaint complaint = new Complaint();
        complaint.setCategory(complaintDTO.getCategory());
        complaint.setUser(user);
        complaint.setFiledAT(complaintDTO.getFiledAT());
        complaint.setDescription(complaintDTO.getDescription());
        complaint.setStatus(ComplaintStatus.SUBMITTED);
        complaint.setInvestigationStatus(InvestigationStatus.PENDING_REVIEW);
        complaint.setComplaintCategory(mdlComplaintCategory);

        //Audit Log
        String performedBy = user.getLastName() + " " + user.getFirstName() + " " + user.getMiddleName();
        auditLogServiceImp.logAction("ADDED Complaint", performedBy, "",0L,"");
        return compliantRepository.save(complaint);
    }

    @Override
    public List<Complaint> getComplaintById(Long complaintId) {
        //Audit Log
        auditLogServiceImp.logAction("RETRIEVE Complaint BY ID" + complaintId, "", "",0L,"");
        return compliantRepository.findAllById(Collections.singleton(complaintId));
    }

    @Override
    public List<Complaint> getAllComplaints() {
        //Audit Log
        auditLogServiceImp.logAction("RETRIEVE ALL Complaints", "", "",0L,"");

        return compliantRepository.findAll();
    }

    @Override
    public List<Complaint> getComplaintsByStatus(ComplaintStatus status) {
        //Audit Log
        auditLogServiceImp.logAction("RETRIEVE ALL Complaints BY STATUS", "", "",0L,"");

        return compliantRepository.findByStatus(status);
    }

    @Override
    public List<Complaint> getComplaintsByComplainantId(User user) {
        //Audit Log
        auditLogServiceImp.logAction("RETRIEVE ALL Complaints BY COMPLAINANT", "", "",0L,"");

        return compliantRepository.findByUser(user);
    }

    @Override
    public Complaint updateComplaint(Long complaintId, ComplaintDTO complaintDTO) {

        //Audit Log
        auditLogServiceImp.logAction("UPDATE  Complaints", "", "",0L,"");
        return compliantRepository.findById(complaintId)
                .map(complaint -> {
                    complaint.setCategory(complaintDTO.getCategory());
                    complaint.setDescription(complaintDTO.getDescription());
                    complaint.setStatus(complaintDTO.getStatus());
                    return compliantRepository.save(complaint);
                })
                .orElseThrow(() -> new RuntimeException("Complaint not found with id: " + complaintId));

    }

    @Override
    public Complaint updateComplaintStatus(Long complaintId, String status) {

        //Audit Log
        auditLogServiceImp.logAction("UPDATE  Complaints STATUS", "", "",0L,"");
        return compliantRepository.findById(complaintId)
                .map(complaint -> {
                    complaint.setStatus(ComplaintStatus.valueOf(status.toUpperCase()));
                    return compliantRepository.save(complaint);
                })
                .orElseThrow(() -> new RuntimeException("Complaint not found with id: " + complaintId));
    }

    @Override
    public Complaint assignInvestigator(Long complaintId, Long investigatorId) {

        //Audit Log
        auditLogServiceImp.logAction("ASSIGN INVESTIGATOR to Complaints", "", "",0L,"");

        return compliantRepository.findById(complaintId).map(complaint -> {
            User investigator = userRepository.findById(investigatorId).orElseThrow(() -> new RuntimeException("Investigator not found"));
            complaint.setInvestigator(investigator);
            return compliantRepository.save(complaint);
        }).orElseThrow(() -> new RuntimeException("Complaint not found"));

    }

    @Override
    public void cancelComplaint(Long complaintId, ComplaintDTO reason) {
        //Audit Log
        auditLogServiceImp.logAction("CANCEL  Complaints", "", "",0L,"");

        Complaint complaint = compliantRepository.findById(complaintId).orElseThrow(() -> new RuntimeException("Complaint not found!"));
        complaint.setStatus(ComplaintStatus.CANCELED);
        complaint.setCancellationReason(reason.getCancellationReason());
        compliantRepository.save(complaint);
    }

    @Override
    public void logComplaintAction(Long complaintId, String action, String performedBy, String details) {

    }


}
