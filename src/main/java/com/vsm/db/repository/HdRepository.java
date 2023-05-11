package com.vsm.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vsm.db.entity.Hd;
import com.vsm.db.entity.HdPK;

public interface HdRepository  extends JpaRepository<Hd,HdPK>{
}