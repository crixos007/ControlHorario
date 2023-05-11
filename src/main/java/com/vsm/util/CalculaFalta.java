package com.vsm.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vsm.db.entity.HuEmplsAuditEtime;
import com.vsm.db.entity.HuEmplsAuditEtimePK;
import com.vsm.db.entity.Rn;
import com.vsm.db.entity.RnPK;
import com.vsm.db.service.TransInsDbService;
import com.vsm.dto.Geolocalizacion;
import com.vsm.dto.InChecadaEntradaDto;
import com.vsm.dto.InChecadaSalidaDto;
import com.vsm.dto.InRegistraChecadaDto;
import com.vsm.dto.OutChecadaETDto;
import com.vsm.dto.ResponseHumaneTime;

@Service("calculaFaltaService")
/**
 * @Api(tags = "CalculaFalta")
 * @author rcaraveo
 *
 */
public class CalculaFalta {
	private static final Logger LOG = LogManager.getLogger(CalculaFalta.class.getName());
	
	@Autowired
	GetMarcaKardex kardexService;
	
	@Autowired
	GetWekendsOfYear wekendsService;
	
	@Autowired
	TransInsDbService dbInsService;
	
	@Autowired
	RestTemplate template;
	
    @Async("threadPoolTaskExecutor")
    public void calculaFalta(InChecadaEntradaDto in, Rn lastMove) throws InterruptedException {
    	LOG.info(in.getIdTraza() + " Inicia logica para el calculo de faltas");
    	
    	try {
	    	LocalDate ultimaChecada = lastMove.getId().getRnFecha().plusDays(1).toLocalDate();
	    	LocalDate fechaChecada = in.getHoraChecada().toLocalDate();
	    	long anio = Long.parseLong(fechaChecada.format(DateTimeFormatter.ofPattern("yyyy")));
	    	
	    	List<String> kardex = kardexService.consultaRango(in.getNumCia(), in.getNumEmp(), ultimaChecada, fechaChecada);
	    	List<String> weekends = wekendsService.getWekends(in.getNumCia(), in.getNumEmp(), anio);
	    	
	    	while(!ultimaChecada.equals(fechaChecada)) {
	    		String fecha = ultimaChecada.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	    		if(!kardex.contains(fecha) && !weekends.contains(fecha)) {
	    			Rn inputRn = new Rn();
	    			RnPK inputPK = new RnPK();    			
	    			inputPK.setRnCia(in.getNumCia());
	    			inputRn.setRnUsuario("eTime");
	    			inputRn.setRnSts("B");
	    			inputRn.setRnZona(lastMove.getRnZona());
	    			inputRn.setRnArea(lastMove.getRnArea());
	    			inputRn.setRnCentro(lastMove.getRnCentro());
	    			inputRn.setRnLinea(lastMove.getRnLinea());
	    			inputPK.setRnNumEmp(in.getNumEmp());
	    			inputPK.setRnFecha(LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd-MM-yyyy")).atStartOfDay());
	    			inputPK.setRnSecuencia(1);
	    			inputRn.setRnGafete(lastMove.getRnGafete());
	    			inputRn.setRnEstacion("A01");    			
	    			inputRn.setRnChequeo("N");
	    			inputRn.setRnCausaRetardo(" ");
	    			inputRn.setRnComida(BigDecimal.ZERO);    			
	    			inputRn.setRnFecModif(LocalDate.now().atStartOfDay());
	    			inputRn.setRnHoraModif(LocalDateTime.now());
	    			inputRn.setId(inputPK);
	    			Rn guardarRn = dbInsService.saveRn(inputRn);
	    			if(guardarRn == null)LOG.error(in.getIdTraza() + " ocurrio un error al persistir en RN: " + inputPK.toString() + inputRn.toString());
	    			try {    				
	    				String calculo = dbInsService.calculoConceptos(in.getNumCia(), in.getNumEmp(), lastMove.getId().getRnFecha().toLocalDate().atStartOfDay());
	    				LOG.info(in.getIdTraza() + " SP_CC resultado ejecucion: " + calculo);
	    			}catch (Exception e) {			
	    				LOG.error(in.getIdTraza() + " ocurrio un error al ejecuta el calculo de conceptos: " + e.toString());	
	    			}    			
	    		} 	    		
	    		ultimaChecada = ultimaChecada.plusDays(1);    		
	    	}
    	}catch (Exception e) {
    		LOG.error(in.getIdTraza() + " ocurrio un error al ejecutar el calculo de faltas: " + e.toString());
		}
    	
    }
    
    @Async("threadPoolTaskExecutor")
    public void calculaSalida(InChecadaSalidaDto in, Rn lastMove) throws InterruptedException {    	
		try {			
			String calculo = dbInsService.calculoConceptos(in.getNumCia(), in.getNumEmp(), lastMove.getId().getRnFecha().toLocalDate().atStartOfDay());
			LOG.info(in.getIdTraza() + " SP_CC resultado ejecucion: " + calculo);
		}catch (Exception e) {			
			LOG.error(in.getIdTraza() + " ocurrio un error al ejecuta el calculo de conceptos: " + e.toString());
		}  		
    }
    
    @Async("threadPoolTaskExecutor")
    public void registraAuditoriaHu(BigDecimal latitud, BigDecimal longitud, ResponseHumaneTime outHuman, InRegistraChecadaDto in) throws InterruptedException {
		HuEmplsAuditEtime audit = new HuEmplsAuditEtime();
		HuEmplsAuditEtimePK auditPk = new HuEmplsAuditEtimePK();
		try {
			auditPk.setNumCia(Long.parseLong(in.getRoot().getNumeroCompania()));
			auditPk.setNumEmp(Long.parseLong(in.getRoot().getEmpleado()));				
			audit.setCodigoEtime(outHuman.getCodigo());
			audit.setEstacion(outHuman.getEstacion()==null?" ":outHuman.getEstacion());				
			audit.setFechaChecada(in.getRoot().getFechaHoraChecada());
			audit.setFechaMov(LocalDateTime.now());
			audit.setFotografia(in.getRoot().getFoto());
			audit.setStatus("A");
			audit.setTipo(in.getRoot().getTipoChecada());
			audit.setId(auditPk);
			try {
				audit.setLatitud(latitud);
				audit.setLongitud(longitud);					
				audit.setBeacon01(outHuman.getEstOut().getBeacons().get(0)!=null?outHuman.getEstOut().getBeacons().get(0):"");
				audit.setBeacon02(outHuman.getEstOut().getBeacons().get(1)!=null?outHuman.getEstOut().getBeacons().get(1):"");
				audit.setBeacon03(outHuman.getEstOut().getBeacons().get(2)!=null?outHuman.getEstOut().getBeacons().get(2):"");
				audit.setBeacon04(outHuman.getEstOut().getBeacons().get(3)!=null?outHuman.getEstOut().getBeacons().get(3):"");
				audit.setBeacon05(outHuman.getEstOut().getBeacons().get(4)!=null?outHuman.getEstOut().getBeacons().get(4):"");
			}catch (Exception e) {}
			try {
				audit.setEstacion01(outHuman.getEstOut().getEstaciones().get(0)!=null?outHuman.getEstOut().getEstaciones().get(0):"");
				audit.setEstacion02(outHuman.getEstOut().getEstaciones().get(1)!=null?outHuman.getEstOut().getEstaciones().get(1):"");
				audit.setEstacion03(outHuman.getEstOut().getEstaciones().get(2)!=null?outHuman.getEstOut().getEstaciones().get(2):"");
				audit.setEstacion04(outHuman.getEstOut().getEstaciones().get(3)!=null?outHuman.getEstOut().getEstaciones().get(3):"");
				audit.setEstacion05(outHuman.getEstOut().getEstaciones().get(4)!=null?outHuman.getEstOut().getEstaciones().get(4):"");
			}catch (Exception e) {}
			HuEmplsAuditEtime auditSave = dbInsService.saveHuEmplsAuditEtime(audit);
			if(auditSave==null)LOG.error(in.getIdTraza() + " ocurrio un error al persistir en HU_EMPLS_AUDIT_ETIME: " + auditPk.toString() + audit.toString());
		}catch (Exception e) {
			LOG.error(in.getIdTraza() + " ocurrio un error al persistir en HU_EMPLS_AUDIT_ETIME: " + auditPk.toString() + audit.toString());
		}
    }
    
    @Async("threadPoolTaskExecutor")
    public void registraAuditoriaEt(InRegistraChecadaDto in, OutChecadaETDto outHuman, Geolocalizacion geo) throws InterruptedException {
		HuEmplsAuditEtime audit = new HuEmplsAuditEtime();
		HuEmplsAuditEtimePK auditPk = new HuEmplsAuditEtimePK();
		try {
			auditPk.setNumCia(Long.parseLong(in.getRoot().getNumeroCompania()));
			auditPk.setNumEmp(Long.parseLong(in.getRoot().getEmpleado()));				
			audit.setCodigoEtime(outHuman.getCodigo());
			audit.setEstacion(outHuman.getEstacion()==null?" ":outHuman.getEstacion());				
			audit.setFechaChecada(in.getRoot().getFechaHoraChecada());
			audit.setFechaMov(LocalDateTime.now());
			audit.setFotografia(in.getRoot().getFoto());
			audit.setStatus("A");
			audit.setTipo(in.getRoot().getTipoChecada());
			audit.setId(auditPk);
			try {
				audit.setLatitud(geo.getLatitud()!=null?new BigDecimal(geo.getLatitud()):BigDecimal.ZERO);
				audit.setLongitud(geo.getLongitud()!=null?new BigDecimal(geo.getLongitud()):BigDecimal.ZERO);					
				audit.setBeacon01(outHuman.getEstacionesAudit().getBeacons().get(0)!=null?outHuman.getEstacionesAudit().getBeacons().get(0):"");
				audit.setBeacon02(outHuman.getEstacionesAudit().getBeacons().get(1)!=null?outHuman.getEstacionesAudit().getBeacons().get(1):"");
				audit.setBeacon03(outHuman.getEstacionesAudit().getBeacons().get(2)!=null?outHuman.getEstacionesAudit().getBeacons().get(2):"");
				audit.setBeacon04(outHuman.getEstacionesAudit().getBeacons().get(3)!=null?outHuman.getEstacionesAudit().getBeacons().get(3):"");
				audit.setBeacon05(outHuman.getEstacionesAudit().getBeacons().get(4)!=null?outHuman.getEstacionesAudit().getBeacons().get(4):"");
			}catch (Exception e) {}
			try {
				audit.setEstacion01(outHuman.getEstacionesAudit().getEstaciones().get(0)!=null?outHuman.getEstacionesAudit().getEstaciones().get(0):"");
				audit.setEstacion02(outHuman.getEstacionesAudit().getEstaciones().get(1)!=null?outHuman.getEstacionesAudit().getEstaciones().get(1):"");
				audit.setEstacion03(outHuman.getEstacionesAudit().getEstaciones().get(2)!=null?outHuman.getEstacionesAudit().getEstaciones().get(2):"");
				audit.setEstacion04(outHuman.getEstacionesAudit().getEstaciones().get(3)!=null?outHuman.getEstacionesAudit().getEstaciones().get(3):"");
				audit.setEstacion05(outHuman.getEstacionesAudit().getEstaciones().get(4)!=null?outHuman.getEstacionesAudit().getEstaciones().get(4):"");
			}catch (Exception e) {}
			HuEmplsAuditEtime auditSave = dbInsService.saveHuEmplsAuditEtime(audit);
			if(auditSave==null)LOG.error(in.getIdTraza() + " ocurrio un error al persistir en HU_EMPLS_AUDIT_ETIME: " + auditPk.toString() + audit.toString());
		}catch (Exception e) {
			LOG.error(in.getIdTraza() + " ocurrio un error al persistir en HU_EMPLS_AUDIT_ETIME: " + auditPk.toString() + audit.toString());
		}
    }
}