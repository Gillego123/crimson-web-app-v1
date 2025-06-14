package com.crimson.app.crimson.repository;

import com.crimson.app.crimson.model.Complaint;
import com.crimson.app.crimson.model.Evidence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvidenceRepository extends JpaRepository<Evidence, Long> {
    List<Evidence> findByComplaint(Complaint complaint);
}