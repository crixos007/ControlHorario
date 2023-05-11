package com.vsm.db.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vsm.db.entity.Rh;
import com.vsm.db.entity.RhPK;

public interface RhRepository extends JpaRepository<Rh, RhPK> {
	@Query( "SELECT r FROM Rh r " +
			"WHERE ((id.rhCia = :numCia AND id.rhNumEmp = :numEmp) " +
			"		AND " +
			"	   (id.rhFecIni <= :fehca AND rhFecFin >=:fehca) " +
			"	   ) ")
	List<Rh> getRhByDate(@Param("numCia")Long numCia, @Param("numEmp")Long numEmp,  @Param("fehca")LocalDateTime fehca);
}