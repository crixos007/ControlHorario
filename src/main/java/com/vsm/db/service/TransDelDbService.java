package com.vsm.db.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vsm.db.entity.Rn;
import com.vsm.db.repository.RnRepository;

@Service("dbDelService")
public class TransDelDbService {
	private static final Logger LOG = LogManager.getLogger(TransDelDbService.class.getName());

	@Autowired
	private RnRepository rnRepository;
		
	public boolean deleteRn(Rn in) {
		try {
			rnRepository.delete(in);		
			return true;
		}catch (Exception e) {
			LOG.error("TransDelDbService deleteRn error trace: " + e.getMessage());
			return false;
		}
	}	
}