package com.vsm.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vsm.db.entity.Mr;
import com.vsm.db.entity.MrPK;

public interface MrRepository extends JpaRepository<Mr, MrPK> {
	@Query("select m from  Mr m " +
	       "WHERE ((m.id.mrCia = :numCia AND m.id.mrNumEmp = :numEmp) " +
	       "        AND (m.id.mrFechaApli =  (SELECT MAX(r.id.mrFechaApli) " +
	       "					              FROM Mr r " +
	       "					              WHERE r.id.mrCia = m.id.mrCia " +
	       "					              AND r.id.mrNumEmp = m.id.mrNumEmp " +
	       "					              AND r.mrSts = 'A'))  " + 
	       "        AND (m.mrSts = 'A')   " +
	       "       ) ")
	public List<Mr> getActiveMr(long numCia, long numEmp);
}