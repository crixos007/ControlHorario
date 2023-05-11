package com.vsm.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vsm.db.entity.Ta155;
import com.vsm.db.entity.Ta155PK;

public interface Ta155Repository extends JpaRepository<Ta155, Ta155PK> {
	@Query( "SELECT t FROM Ta155 t " +
			"WHERE t.id.ta155Cia = :numCia " +
			"AND t.id.ta155Estacion IN (SELECT h.id.estacion FROM HuEmplsEstChecado h " +
			"                           WHERE h.id.numCia = t.id.ta155Cia " +
			"                           AND h.id.numEmp = :numEmp " +
			"                           AND h.status = 'A') ")
	List<Ta155> getEstaciones(@Param("numCia")Long numCia, @Param("numEmp")Long numEmp);
}