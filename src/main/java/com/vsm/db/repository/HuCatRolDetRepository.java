package com.vsm.db.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vsm.db.entity.HuCatRolDet;
import com.vsm.db.entity.HuCatRolDetPK;

public interface HuCatRolDetRepository extends JpaRepository<HuCatRolDet, HuCatRolDetPK> {
	@Query( "SELECT hcr FROM HuCatRolDet hcr " +
			"WHERE hcr.id.numCia = :numCia " +
			"AND hcr.id.claveRol = :cveRol " +
			"AND hcr.fechaFin = (SELECT MAX(hcr2.fechaFin) " +
			"				     FROM HuCatRolDet hcr2 " +
			"				     WHERE hcr.id.numCia = hcr2.id.numCia " +
			"				     AND hcr.id.claveRol = hcr2.id.claveRol " +
			"				     AND hcr2.id.fechaIni = :fechaIni) ")
	List<HuCatRolDet> getHuCatRolDet(@Param("numCia")Long numCia, @Param("cveRol")Long cveRol,  @Param("fechaIni")LocalDateTime fechaIni);
}