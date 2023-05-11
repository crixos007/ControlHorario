package com.vsm.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vsm.db.entity.HuCatDiasFestivo;
import com.vsm.db.entity.HuCatDiasFestivoPK;

public interface HuCatDiasFestivoRepository extends JpaRepository<HuCatDiasFestivo, HuCatDiasFestivoPK> {
	@Query( "SELECT d FROM  HuCatDiasFestivo d " +
			"WHERE d.id.numCia =:numCia  " +
			"AND d.id.fecha >= TO_DATE(:fechaIni,'dd-mm-yyyy')   " +
			"AND d.id.fecha <= TO_DATE(:fechaFin,'dd-mm-yyyy')  " +
			"AND d.status = 'A'  ")
	public List<HuCatDiasFestivo> getDiasFestivos (Long numCia, String fechaIni, String fechaFin);
}