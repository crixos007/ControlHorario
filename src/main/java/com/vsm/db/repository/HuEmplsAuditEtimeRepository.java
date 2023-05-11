package com.vsm.db.repository;
 
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vsm.db.entity.HuEmplsAuditEtime;
import com.vsm.db.entity.HuEmplsAuditEtimePK;

public interface HuEmplsAuditEtimeRepository extends JpaRepository<HuEmplsAuditEtime, HuEmplsAuditEtimePK>{
	Optional<HuEmplsAuditEtime> findFirstByOrderByFechaMovDesc();	
}