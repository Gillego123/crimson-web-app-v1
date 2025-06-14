package com.crimson.app.crimson.repository;

import com.crimson.app.crimson.model.Investigation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestigationRepository extends JpaRepository<Investigation, Long> {


}
