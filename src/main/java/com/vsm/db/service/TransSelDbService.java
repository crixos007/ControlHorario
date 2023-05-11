package com.vsm.db.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.vsm.db.entity.Hd;
import com.vsm.db.entity.HdPK;
import com.vsm.db.entity.HuCatCtGral;
import com.vsm.db.entity.HuCatDiasFestivo;
import com.vsm.db.entity.HuCatRolDet;
import com.vsm.db.entity.HuCatToGral;
import com.vsm.db.entity.HuCompaniaLicencia;
import com.vsm.db.entity.HuEmpl;
import com.vsm.db.entity.HuEmplPK;
import com.vsm.db.entity.HuEmplsAsistencia;
import com.vsm.db.entity.HuRelojRegTxt;
import com.vsm.db.entity.HuRelojRegTxtPK;
import com.vsm.db.entity.HuRutasFileServer;
import com.vsm.db.entity.Mr;
import com.vsm.db.entity.Rh;
import com.vsm.db.entity.Rn;
import com.vsm.db.entity.Ta140;
import com.vsm.db.entity.Ta140PK;
import com.vsm.db.entity.Ta155;
import com.vsm.db.repository.HdRepository;
import com.vsm.db.repository.HuCatCtGralRepository;
import com.vsm.db.repository.HuCatDiasFestivoRepository;
import com.vsm.db.repository.HuCatRolDetRepository;
import com.vsm.db.repository.HuCatToGralRepository;
import com.vsm.db.repository.HuCompaniaLicRepository;
import com.vsm.db.repository.HuEmplRepository;
import com.vsm.db.repository.HuEmplsAsistenciaRepository;
import com.vsm.db.repository.HuRelojRegTxtRepository;
import com.vsm.db.repository.HuRutasFilesServerRepository;
import com.vsm.db.repository.MrRepository;
import com.vsm.db.repository.RhRepository;
import com.vsm.db.repository.RnRepository;
import com.vsm.db.repository.Ta140Repository;
import com.vsm.db.repository.Ta155Repository;

@Service("dbSelService")
public class TransSelDbService {
	private static final Logger LOG = LogManager.getLogger(TransSelDbService.class.getName());
	
	@Autowired
	HuEmplRepository huEmplRepository;
	
	@Autowired
	HuCatToGralRepository huCattoGralRepository;
	
	@Autowired
	HuEmplsAsistenciaRepository huAsisRepository;
	
	@Autowired
	HdRepository hdRepository;
	
	@Autowired
	HuCatDiasFestivoRepository huCatfestRepository;
	
	@Autowired
	HuRutasFilesServerRepository huRutasFSRepository;
	
	@Autowired
	HuCatCtGralRepository huCatCtGralRepository;

	@Autowired
	Ta155Repository ta155Repository;
	
	@Autowired
	HuCompaniaLicRepository huCiaLicRepository;
	
	@Autowired
	MrRepository mrRepository;
	
	@Autowired
	HuRelojRegTxtRepository huRelRegTxtRepository;
	
	@Autowired
	RnRepository rnRepository;
	
	@Autowired
	RhRepository rhRepository;
	
	@Autowired
	HuCatRolDetRepository huCatRolRepository;
	
	@Autowired
	Ta140Repository ta140Repository;
	
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	public HuEmpl getHuEmplById(long numCia, long numEmp) {
		HuEmplPK id = new HuEmplPK();
		id.setNumCia(numCia);
		id.setNumEmp(numEmp);
		try {
			Optional<HuEmpl> out = huEmplRepository.findById(id);
			if(out.isPresent()) {
				return out.get();
			}else {
				LOG.info("transSelDbService getHuEmplById info: sin coincidencia en HU_EMPLS para el numEmp:" + numEmp + " de la numCia:" + numCia);
				return null;
			}
		}catch (Exception e) {
			LOG.error("transSelDbService getHuEmplById error trace:" + e.getMessage());
			return null;
		}
	}
	
	public List<HuCatToGral> getHuCatToGralByParam(String parametro){		
		try {
			List<HuCatToGral> out = huCattoGralRepository.findByParametro(parametro);
			if(out!=null && !out.isEmpty()) {
				return out;
			}else {
				LOG.info("transSelDbService getHuCatToGralByParam info: sin coincidencia en HU_CAT_TO_GRAL para el parametro:" + parametro);
				return null;			
			}
		}catch (Exception e) {
			LOG.error("transSelDbService getHuCatToGralByParam error trace:" + e.getMessage());
			return null;
		}
	}
	
	public List<HuEmplsAsistencia> getHuAsisByDates(long numCia, long numEmp, long fechaIni, long fechaFin) {
		try {
			List<HuEmplsAsistencia> resp = huAsisRepository.getAsistenciaByDates(numCia, numEmp, fechaIni, fechaFin);	
			if(resp!=null && resp.size()>0) {
				return resp;
			}else {
				LOG.info("transSelDbService getHuAsisByDates info: sin coincidencia en HU_EMPLS_ASISTENCIA para el numEmp:" + numEmp + " de la numCia:" + numCia +
						 " fecha inicial:" + fechaIni + "fecha final:" + fechaFin);
				return null;
			}
		}catch (Exception e) {
			LOG.error("transSelDbService getHuAsisByDates error trace:" + e.getMessage());
			return null;
		}
	}
	
	public Hd getHdById(HdPK id) {
		try {
			Optional<Hd> hd = hdRepository.findById(id);		
			if (hd.isPresent()) {
				return hd.get();
			} else {
				LOG.info("transSelDbService getHuAsisByDates info: sin coincidencia en HD parametros:" + id.toString());
				return null;
			}
		}catch (Exception e) {
			LOG.error("transSelDbService getHdById error trace:" + e.getMessage());
			return null;
		}
	}
	
	public List<HuCatDiasFestivo> getDiasFestivos(long numCia, String fechaIni, String fechaFin) {
		try {
			List<HuCatDiasFestivo> festivos = huCatfestRepository.getDiasFestivos(numCia, fechaIni, fechaFin);
			if(festivos!=null && festivos.size()>0) {
				return festivos;
			}else {
				LOG.info("transSelDbService getDiasFestivos info: sin coincidencia en HU_CAT_DIAS_FESTIVOS parametros:" +
						  " cia:" + numCia + "fecha inicial:" + fechaIni + "fecha final:" + fechaFin);
				return null;
			}
		}catch (Exception e) {
			LOG.error("transSelDbService getDiasFestivos error trace:" + e.getMessage());
			return null;
		}
	}
	
	public List<HuRutasFileServer> getHuRutasFSByCve(String claveRuta) {
		try {
			List<HuRutasFileServer> out = huRutasFSRepository.findByClaveRuta(claveRuta);
			if(out!=null && !out.isEmpty()) {
				return out;
			}else {
				LOG.info("transSelDbService getHuRutasFSByCve info: sin coincidencia en HU_RUTAS_FILE_SERVER clave:" + claveRuta);
				return null;
			}
		}catch (Exception e) {
			LOG.error("transSelDbService getHuRutasFSByCve error trace:" + e.getMessage());
			return null;
		}
	}
	
	public HuCatCtGral getHuCatCtByCia(long numCia) {
		try {
			Optional<HuCatCtGral> out = huCatCtGralRepository.findById(numCia);
			if(out.isPresent()) {
				return out.get();
			}else {
				LOG.info("transSelDbService getHuCatCtByCia info: sin coincidencia en HU_CAT_CT_GRAL para la numCia:" + numCia);
				return null;
			}
		}catch (Exception e) {
			LOG.error("transSelDbService getHuCatCtByCia error trace:" + e.getMessage());
			return null;
		}
	}
	
	public List<Ta155> getEstaciones(long numCia, long numEmp) {
		try {
			List<Ta155> estaciones = ta155Repository.getEstaciones(numCia, numEmp);
			if (estaciones != null && estaciones.size() > 0) {
				return estaciones;
			} else {
				LOG.info("transSelDbService getEstaciones info: sin coincidencia en TA155 para la numCia:" + numCia + " emp:" + numEmp);
				return null;
			}
		}catch (Exception e) {
			LOG.error("transSelDbService getEstaciones error trace:" + e.getMessage());
			return null;
		}
	}
	
	public HuCompaniaLicencia getHuCiaLicByCia(long numCia) {
		try {
			Optional<HuCompaniaLicencia> out = huCiaLicRepository.findById(numCia);
			if(out.isPresent()) {
				return out.get();
			}else {
				LOG.info("transSelDbService getHuCiaLicByCia info: sin coincidencia en HU_COMPANIA para la numCia:" + numCia);
				return null;
			}
		}catch (Exception e) {
			LOG.error("transSelDbService getHuCiaLicByCia error trace:" + e.getMessage());
			return null;
		}
	}
	
	public List<Mr> getActiveMr(long numCia, long numEmp) {
		try {
			List<Mr> mrs = mrRepository.getActiveMr(numCia, numEmp);		
			if(mrs!=null && mrs.size()>0) {
				return mrs;
			}else {
				LOG.info("transSelDbService getActiveMr info: sin coincidencia en MR para la numCia:" + numCia + " emp:" + numEmp);
				return null;
			}
		}catch (Exception e) {
			LOG.error("transSelDbService getActiveMr error trace:" + e.getMessage());
			return null;
		}
	}
	
	public HuRelojRegTxt getHuRelRegTxtById(HuRelojRegTxtPK id) {	
		try {
			Optional<HuRelojRegTxt> huRelojRegTxt = huRelRegTxtRepository.findById(id);			
			if(huRelojRegTxt.isPresent()) {
				return huRelojRegTxt.get();
			}else {
				LOG.info("transSelDbService getHuRelRegTxtById info: sin coincidencia en HU_RELOJ_REG_TXT parametros:" + id.toString());
				return null;
			}
		}catch (Exception e) {
			LOG.error("transSelDbService getHuRelRegTxtById error trace:" + e.getMessage());
			return null;
		}
	}
	
	public Rn getRnLastMov(long numCia, long numEmp) {
		try {
			List<Rn> lastMov = rnRepository.getLastMov(numCia,numEmp);			
			if(lastMov!=null && lastMov.size()>0) {
				return lastMov.get(0);
			}else{
				LOG.info("transSelDbService getRnLastMov info: sin coincidencia en RN cia:" + numCia + " emp:" + numEmp);
				return null;
			}
		}catch (Exception e) {
			LOG.error("transSelDbService getRnLastMov error trace:" + e.getMessage());
			return null;
		}
	}
	
	public Rn getRnLasSecByDate(Long numCia, Long numEmp, LocalDateTime date) {
		try {
			List<Rn> lastSec = rnRepository.getSecByDate(numCia,numEmp,date);			
			if(lastSec!=null && lastSec.size()>0) {
				return lastSec.get(0);
			}else{
				LOG.info("transSelDbService getRnLasSecByDate info: sin coincidencia en RN cia:" + numCia + " emp:" + numEmp + " fecha:" + date.toString());
				return null;
			}
		}catch (Exception e) {
			LOG.error("transSelDbService getRnLasSecByDate error trace:" + e.getMessage());
			return null;
		}
	}
	
	public Rn getRnfalta(Long numCia, Long numEmp, LocalDateTime date) {
		try {
			List<Rn> falta = rnRepository.getFalta(numCia,numEmp,date);			
			if(falta!=null && falta.size()>0) {
				return falta.get(0);
			}else{
				LOG.info("transSelDbService getRnLasSecByDate info: sin coincidencia en RN FALTA cia:" + numCia + " emp:" + numEmp + " fecha:" + date.toString());
				return null;
			}
		}catch (Exception e) {
			LOG.error("transSelDbService getRnLasSecByDate error trace:" + e.getMessage());
			return null;
		}
	}
	
	public List<Rh> getRhByDate(Long numCia, Long numEmp, LocalDateTime fehca) {
		try {
			List<Rh> rhs = rhRepository.getRhByDate(numCia, numEmp, fehca);		
			if(rhs!=null && rhs.size()>0) {
				return rhs;
			}else {		
				LOG.info("transSelDbService getRnLasSecByDate info: sin coincidencia en RH cia:" + numCia + " emp:" + numEmp + " fecha:" + fehca.toString());
				return null;
			}
		}catch (Exception e) {
			LOG.error("transSelDbService getRnLasSecByDate error trace:" + e.getMessage());
			return null;
		}			
	}
	
	public List<HuCatRolDet> getHuCatRolDet(Long numCia, Long cveRol, LocalDateTime fechaIni) {
		try {
			List<HuCatRolDet> huCatRolDet = huCatRolRepository.getHuCatRolDet(numCia, cveRol, fechaIni);			
			if(huCatRolDet!=null && huCatRolDet.size()>0) {
				return huCatRolDet;
			}else {		
				LOG.info("transSelDbService getHuCatRolDet info: sin coincidencia en HU_CAT_ROL_DET cia:" + numCia + " cve:" + cveRol + " fecha:" + fechaIni.toString());
				return null;
			}
		}catch (Exception e) {
			LOG.error("transSelDbService getHuCatRolDet error trace:" + e.getMessage());
			return null;
		}
	}
	
	public Ta140 getTa140ById(Ta140PK id) {
		try {
			Optional<Ta140> ta140 = ta140Repository.findById(id);		
			if(ta140.isPresent()) {
				return ta140.get();
			}else {		
				LOG.info("transSelDbService getTa140ById info: sin coincidencia en TA140 parametros:" + id.toString());
				return null;
			}
		}catch (Exception e) {
			LOG.error("transSelDbService getHuCatRolDet error trace:" + e.getMessage());
			return null;
		}
	}
}