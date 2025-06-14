package com.crimson.app.crimson.controller;

import com.crimson.app.crimson.common.ComplaintStatus;
import com.crimson.app.crimson.dto.ComplaintDTO;
import com.crimson.app.crimson.model.Complaint;
import com.crimson.app.crimson.model.User;
import com.crimson.app.crimson.repository.UserRepository;
import com.crimson.app.crimson.service.ComplaintServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complaints/v1")
public class ComplaintController {

    private static final Logger logger =  LoggerFactory.getLogger(ComplaintController.class);

    @Autowired
    private ComplaintServiceImp complaintServiceImp;

    @Autowired
    private UserRepository userRepository;

    //Make a complaint
    @PostMapping("/fileComplaint/{userId}")
    public ResponseEntity<Complaint> fileComplaint(@PathVariable Long userId, @RequestBody ComplaintDTO complaintDto ){
        return ResponseEntity.ok(complaintServiceImp.fileComplaint(userId, complaintDto));
    }

    //GET Complaint by ID
    @GetMapping("/getComplaint/{complaintId}")
    public  ResponseEntity<List<Complaint>> getByComplaintId(@PathVariable Long complaintId){
        return ResponseEntity.ok(complaintServiceImp.getComplaintById(complaintId));
    }

    //Get all complaints
    @GetMapping("/getAllComplaints")
    public ResponseEntity<List<Complaint>> getAllComplaints(){
        return ResponseEntity.ok(complaintServiceImp.getAllComplaints());
    }

    //Get all complaint by status



    //Update Complaint Details(If allowed)
    @PostMapping("/updateComplaint/{userId}")
    public ResponseEntity<Complaint> updateFileComplaint(@PathVariable Long userId, @RequestBody ComplaintDTO complaintDto ){
        return ResponseEntity.ok(complaintServiceImp.updateComplaint(userId, complaintDto));
    }

    //Update Complaint status
    @PutMapping("/update/{complaintId}")
    public ResponseEntity<Complaint> updateComplaintStatus(@PathVariable Long complaintId, @RequestBody Complaint complaintDetails){
        try {
            Complaint complaint = complaintServiceImp.updateComplaint(complaintId, complaintDetails);
            return  ResponseEntity.ok(complaint);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getComplaintByComplainantId/{complainantId}")
    public ResponseEntity<List<Complaint>> getComplaintByComplainantId(@PathVariable Long complainantId){
        User user = userRepository.findById(complainantId).orElseThrow(()-> new RuntimeException("No Complainant found!"));
        return ResponseEntity.ok(complaintServiceImp.getComplaintsByComplainantId(user));

    }

    //find complaint by status
    @GetMapping("/updateStatus")
    public ResponseEntity<List<Complaint>> getByStatus(@RequestParam String status){
        ComplaintStatus complaintStatus = ComplaintStatus.valueOf(status.toUpperCase());
        return  ResponseEntity.ok(complaintServiceImp.getComplaintsByStatus(complaintStatus));
    }

    //Assign Investigator
    @PutMapping("/assignInvestigator/{complaintId}/{investigatorId}")
    public ResponseEntity<String> assignInvestigator(@PathVariable Long complaintId, @PathVariable Long investigatorId){
        try {
            complaintServiceImp.assignInvestigator(complaintId, investigatorId);
            return ResponseEntity.ok("Complaint successfully assign investigator");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //Cancel complaint
    @PutMapping("/cancelComplaint/{complaintId}")
    public ResponseEntity<String> cancelComplaint(@PathVariable Long complaintId, @RequestBody ComplaintDTO reason){
        try {
            complaintServiceImp.cancelComplaint(complaintId, reason);
            return ResponseEntity.ok("Complaint successfully canceled");
        } catch (RuntimeException e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Complaint not found");
        }

    }


    //Search Complaint by status, complainant



}
