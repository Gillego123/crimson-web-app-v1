package com.crimson.app.crimson.repository;

import com.crimson.app.crimson.common.ComplaintStatus;
import com.crimson.app.crimson.model.Complaint;
import com.crimson.app.crimson.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompliantRepository extends JpaRepository<Complaint, Long> {
 //   Complaint findByUser(User user);
//    List<Complaint> findByStatus(String status);
    List<Complaint> findByStatus(ComplaintStatus status);
    List<Complaint> findByUser(User user);
}