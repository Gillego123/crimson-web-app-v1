package com.crimson.app.crimson.controller;


import com.crimson.app.crimson.model.Investigation;
import com.crimson.app.crimson.repository.InvestigationRepository;
import com.crimson.app.crimson.service.InvestigationServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/investigation/v1")
public class InvestigationController {
    private static final Logger logger =  LoggerFactory.getLogger(ComplaintController.class);


    @Autowired
    InvestigationRepository investigationRepository;

    @Autowired
    InvestigationServiceImp investigationServiceImp;

    //Create new Investigation
    @PostMapping("/createNewInvestigation/{compliantId}/{investigatorId}")
    public ResponseEntity<Investigation> createNewInvestigation(@PathVariable Long compliantId, @PathVariable Long investigatorId ){
        return ResponseEntity.ok(investigationServiceImp.startInvestigation (compliantId, investigatorId));
    }
}
