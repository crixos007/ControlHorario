package com.vsm.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.vsm.db.entity.HuEmpl;
import com.vsm.db.entity.HuEmplPK;

@EnableJpaRepositories
public interface HuEmplRepository extends JpaRepository<HuEmpl, HuEmplPK> {
}