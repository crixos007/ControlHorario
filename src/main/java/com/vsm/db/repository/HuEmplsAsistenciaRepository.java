package com.vsm.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vsm.db.entity.HuEmplsAsistencia;
import com.vsm.db.entity.HuEmplsAsistenciaPK;

public interface HuEmplsAsistenciaRepository extends JpaRepository<HuEmplsAsistencia, HuEmplsAsistenciaPK> {
	@Query( "SELECT a from HuEmplsAsistencia a " +
			"WHERE a.id.asCia = :numCia " +
			"AND a.id.asNumEmp = :numEmpl " +
			"AND a.id.asAaProc >= :anioIni " +
			"AND a.id.asAaProc <= :anioFin " +
			"ORDER BY a.id.asNumEmp ASC, a.id.asAaProc ASC ")
	List<HuEmplsAsistencia> getAsistenciaByDates(@Param("numCia")long numCia,@Param("numEmpl")long numEmpl,@Param("anioIni")long anioIni,@Param("anioFin")long anioFin);
}