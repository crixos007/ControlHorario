package com.vsm.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vsm.db.entity.HuCatToGral;

public interface HuCatToGralRepository extends JpaRepository<HuCatToGral, Integer>{	
	List<HuCatToGral> findByParametro(String parametro);
}