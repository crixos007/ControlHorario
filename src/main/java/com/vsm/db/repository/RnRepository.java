package com.vsm.db.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vsm.db.entity.Rn;
import com.vsm.db.entity.RnPK;

public interface RnRepository extends JpaRepository<Rn, RnPK> {
	@Query( "SELECT r FROM Rn r " +
			"WHERE r.id.rnCia =:numCia " +
			"AND r.id.rnNumEmp =:numEmp " +
			"AND r.rnHorEnt IS NOT NULL " +
			"AND r.rnChequeo = 'N' " +
			"ORDER BY r.id.rnFecha DESC, r.id.rnSecuencia DESC ")
	List<Rn> getLastMov(@Param("numCia")Long numCia, @Param("numEmp")Long numEmp);
	
	@Query( "SELECT r FROM Rn r " +
			"WHERE r.id.rnCia =:numCia " +
			"AND r.id.rnNumEmp =:numEmp " +
			"AND r.id.rnFecha = :date " +			
			"ORDER BY r.id.rnSecuencia DESC ")
	List<Rn> getSecByDate(@Param("numCia")Long numCia, @Param("numEmp")Long numEmp, @Param("date")LocalDateTime date);
	
	@Query( "SELECT r FROM Rn r " +
			"WHERE r.id.rnCia =:numCia " +
			"AND r.id.rnNumEmp =:numEmp " +
			"AND r.id.rnFecha = :date " +
			"AND r.rnHorEnt IS NULL " +
			"AND r.rnHorSal IS NULL ")
	List<Rn> getFalta(@Param("numCia")Long numCia, @Param("numEmp")Long numEmp, @Param("date")LocalDateTime date);
} 