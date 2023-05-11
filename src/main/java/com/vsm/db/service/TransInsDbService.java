package com.vsm.db.service;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsm.db.entity.HuEmplsAuditEtime;
import com.vsm.db.entity.HuRelojRegTxt;
import com.vsm.db.entity.Pr;
import com.vsm.db.entity.Rn;
import com.vsm.db.repository.HuEmplsAuditEtimeRepository;
import com.vsm.db.repository.HuRelojRegTxtRepository;
import com.vsm.db.repository.PrRepository;
import com.vsm.db.repository.RnRepository;
import com.vsm.util.ConnexionDB;

@Service("dbInsService")
public class TransInsDbService {
	private static final Logger LOG = LogManager.getLogger(TransInsDbService.class.getName());
	
	@Autowired
	private RnRepository rnRepository;
	
	@Autowired
	HuEmplsAuditEtimeRepository huEmpAuditRepository;
	
	@Autowired
	HuRelojRegTxtRepository huRelRegTxtRepository;
	
	@Autowired
	PrRepository prRepository;
	
	@Autowired
	ConnexionDB conn;
	
	public Rn saveRn(Rn in) {
		try {
			Rn out = rnRepository.save(in);
			if (out!=null) {
				return out;
			}else {
				LOG.error("transInsDbService saveHuEmplsAuditEtime error al persistir RN: " + in.toString());
				return null;
			}
		}catch (Exception e) {
			LOG.error("transInsDbService saveRn error trace: " + e.getMessage());
			return null;
		}
	}
	
	@Transactional
	public HuEmplsAuditEtime saveHuEmplsAuditEtime(HuEmplsAuditEtime in) {
		try {
			Optional<HuEmplsAuditEtime> last = huEmpAuditRepository.findFirstByOrderByFechaMovDesc();
			if(last.isPresent()) {
				HuEmplsAuditEtime x = last.get();
				long sec = (x.getId().getId()!=null&&!x.getId().getId().isBlank()?Long.parseLong(x.getId().getId())+1l:1l);
				in.getId().setId(String.valueOf(sec));
			}else {
				in.getId().setId("1");
			}	
			in.setLatitud(in.getLatitud()!=null?in.getLatitud():BigDecimal.ZERO);
			in.setLongitud(in.getLongitud()!=null?in.getLongitud():BigDecimal.ZERO);
			HuEmplsAuditEtime out = huEmpAuditRepository.save(in);
			if (out!=null) {
				return out;
			}else {
				LOG.error("transInsDbService saveHuEmplsAuditEtime error al persistir HU_EMPLS_AUDIT_ETIME: " + in.toString());
				return null;
			}
		}catch (Exception e) {
			LOG.error("transInsDbService saveHuEmplsAuditEtime error trace: " + e.getMessage());
			return null;
		}	
	}
	
	@Transactional
	public Rn saveChecada(Rn in, HuRelojRegTxt huRelojRegTxt) {
		try {
			Rn out = rnRepository.save(in);
			HuRelojRegTxt relRegTxtOut = huRelRegTxtRepository.save(huRelojRegTxt);
			if(out != null && relRegTxtOut != null) {
				return out;
			}else {
				throw new Exception("error al persistir la checada: "  + in.toString());
			}
		}catch (Exception e) {
			LOG.error("transInsDbService saveChecada error trace: " + e.getMessage());
			return null;
		}
	}
	
	@Transactional
	public Pr savePr(Pr in) {
		try {
			Pr out = prRepository.save(in);
			if(out!=null) {
				return out;
			}else {
				LOG.error("transInsDbService saveHuEmplsAuditEtime error al persistir PR: " + in.toString());
				return null;
			}		
		}catch (Exception e) {
			LOG.error("transInsDbService savePr error trace: " + e.getMessage());
			return null;
		}
	}
	
	@Transactional
	public String calculoConceptos(long numCia, long emp, LocalDateTime fecha) throws Exception {
		Connection connection = conn.getConeccion();
		String out = "Ocurrio un error contacte al administrador";
		Date asDate = Date.from(fecha.atZone(ZoneId.systemDefault()).toInstant());
		
		try {			
		    CallableStatement cst = connection.prepareCall("{? = call HUMAN.SP_CC (?,?,?)}");
		    cst.registerOutParameter(1, java.sql.Types.VARCHAR);
		    cst.setLong(2, numCia);
		    cst.setLong(3, emp);
		    cst.setDate(4, new java.sql.Date(((Date) asDate).getTime()));
		    cst.execute();        
		    out = cst.getString(1);
		    LOG.info("Execucion del SP SP_CC: " + out);
		    connection.close();
		} catch (SQLException ex) {
		    System.out.println("Error: " + ex.getMessage());
		    connection.close();
		} finally {
			connection.close();
	    }
		return out;        
    }
}