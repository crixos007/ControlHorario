package com.vsm.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vsm.db.entity.HuRutasFileServer;

public interface HuRutasFilesServerRepository extends JpaRepository<HuRutasFileServer, String>{
	List<HuRutasFileServer> findByClaveRuta(String claveRuta);	
}