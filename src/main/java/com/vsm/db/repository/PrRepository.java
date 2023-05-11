package com.vsm.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vsm.db.entity.Pr;
import com.vsm.db.entity.PrPK;

public interface PrRepository extends JpaRepository<Pr, PrPK> {
}