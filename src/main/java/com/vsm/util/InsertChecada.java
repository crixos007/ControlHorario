package com.vsm.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vsm.db.entity.HuCatCtGral;
import com.vsm.db.entity.HuRelojRegTxt;
import com.vsm.db.entity.HuRelojRegTxtPK;
import com.vsm.db.entity.Rn;
import com.vsm.db.entity.RnPK;
import com.vsm.db.service.TransDelDbService;
import com.vsm.db.service.TransInsDbService;
import com.vsm.db.service.TransSelDbService;
import com.vsm.dto.InChecadaEntradaDto;
import com.vsm.dto.InChecadaSalidaDto;

@Service("insertChecadaService")
/**
 * @Api(tags = "InsertChecada")
 * @author rcaraveo
 *
 */
public class InsertChecada {
	private static final Logger LOG = LogManager.getLogger(InsertChecada.class.getName());

	@Autowired
	RestTemplate template;
	
	@Autowired
	GetMarcaKardex kardexService;
	
	@Autowired
	GetWekendsOfYear wekendsService;
	
	@Autowired
	TransDelDbService dbDelService;
	
	@Autowired
	TransInsDbService dbInsService;

	@Autowired
	TransSelDbService dbSelService;

	/**
	 * @author rcaraveo
	 * @apiNote SERVICIO PARA PERSISTIR LA CHECADA DE ENTRADA DE MANERA ASINCRONA EN LA BD HUMAN19
	 * @param in
	 * @param falta
	 * @param fechaPasada
	 * @param calculoFlag
	 * @param lastMove
	 * @param maxRnSec
	 * @param fechaUltimaChecada
	 * @throws InterruptedException
	 */
    @Async("threadPoolTaskExecutor")
    public void insertaEntradaHu(InChecadaEntradaDto in,Rn falta,String fechaPasada,boolean calculoFlag,Rn lastMove,Long maxRnSec,LocalDateTime fechaUltimaChecada) throws InterruptedException {
    	LOG.info(in.getIdTraza() + " Begin ICEHU-empleado:" + in.getNumEmp() + " cia:" + in.getNumCia() + " fecha:" + in.getHoraChecada() + " Inicia logica para la persistencia de la entrada.");
    	boolean executeFalta = true;
    	boolean executeRn = true;
    	boolean executeCatCoGral = true;
    	int retry = 0;
    	
    	try {
	    	//ELIMINA LA FALTA EN CASO DE EXISTIR
	    	while(executeFalta && retry < 10) {
				if(falta!=null && (falta.getRnHorEnt()==null && falta.getRnHorSal()==null)) {
					boolean deleted = dbDelService.deleteRn(falta);
					if (!deleted) {
						LOG.error(in.getIdTraza() + " ASINC_ERROR-empleado:" + in.getNumEmp() + " cia:" + in.getNumCia() + " fecha:" + in.getHoraChecada() + " | error al eliminar en RN.");
						Thread.sleep(600000);
						retry ++;
						continue;
					}				
					calculoFlag=true;			
				}
				executeFalta=false;
	    	}
			
	    	//PERSISTE LA CHECADA EN RN Y HU_RELOJ_REG_TXT DE MANERA TRANSACCIONAL
	    	while(executeRn && retry < 10) {
	    		//CREA ENTIDAD RN PARA INSERTAR
				Rn inputRn = new Rn();
				RnPK inputPK = new RnPK();
				LocalDateTime hora = in.getHoraChecada().truncatedTo(ChronoUnit.MINUTES);			
				inputPK.setRnCia(in.getNumCia());
				inputRn.setRnChequeo("N");
				inputPK.setRnNumEmp(in.getNumEmp());
				inputPK.setRnFecha(LocalDate.parse(fechaPasada, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay());
				inputRn.setRnFecModif(in.getHoraChecada());
				inputRn.setRnHorEnt(in.getHoraChecada());
				inputRn.setRnGafete(BigDecimal.ZERO);
				inputRn.setRnUsuario("eTime");
				inputRn.setRnSts("A");
				inputRn.setRnCausaRetardo(" ");
				inputRn.setRnComida(BigDecimal.ZERO);
				inputRn.setRnEstacion(in.getEstacion());
				inputRn.setRnHoraModif(hora);
				inputRn.setRnHorEnt(hora);
				inputRn.setRnFecModif(LocalDate.now().atStartOfDay());
				inputRn.setRnHoraModif(LocalDateTime.now());
				inputPK.setRnSecuencia(maxRnSec);
				if(lastMove!=null) {
					inputRn.setRnCentro(lastMove.getRnCentro()!=null?lastMove.getRnCentro():" ");
					inputRn.setRnArea(lastMove.getRnArea()!=null?lastMove.getRnArea():" ");
					inputRn.setRnZona(lastMove.getRnZona()!=null?lastMove.getRnZona():BigDecimal.ZERO);
					inputRn.setRnLinea(lastMove.getRnLinea()!=null?lastMove.getRnLinea():" ");
				}else {
					inputRn.setRnCentro(" ");
					inputRn.setRnArea(" ");
					inputRn.setRnZona(BigDecimal.ZERO);
					inputRn.setRnLinea(" ");
				}
				inputRn.setId(inputPK);
				
				//CREA ENTIDAD HURELOJREGTXT PARA INSERTAR
				HuRelojRegTxt huRelojRegTxtIn = new HuRelojRegTxt();
				HuRelojRegTxtPK huRelojRegTxtPK = new HuRelojRegTxtPK();		
				huRelojRegTxtPK.setNumEmp(in.getNumEmp());
				huRelojRegTxtIn.setUserId("eTime");
				huRelojRegTxtPK.setCia(in.getNumCia());
				huRelojRegTxtIn.setEstacion(in.getEstacion());
				huRelojRegTxtIn.setCveChecada(in.getTipoChecada());
				huRelojRegTxtPK.setHora(in.getHoraChecada().truncatedTo(ChronoUnit.MINUTES));
				huRelojRegTxtIn.setComida(BigDecimal.ZERO);
				huRelojRegTxtIn.setFechaMov(LocalDateTime.now());
				huRelojRegTxtIn.setMotChecada(" ");
				huRelojRegTxtIn.setStatus("B");
				huRelojRegTxtPK.setFecha(LocalDate.parse(fechaPasada, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				huRelojRegTxtIn.setId(huRelojRegTxtPK);
				
				//PERSISTE EN DB
				Rn guardarRn = dbInsService.saveChecada(inputRn, huRelojRegTxtIn);
				if(guardarRn == null) {
					LOG.error(in.getIdTraza() + " ASINC_ERROR-empleado:" + in.getNumEmp() + " cia:" + in.getNumCia() + " fecha:" + in.getHoraChecada() + " | error al persistir la entrada en DB.");
					Thread.sleep(600000);
					retry ++;
					continue;
				}
				executeRn=false;
	    	}    	
	
	    	while(executeCatCoGral  && retry < 10) {
	    		HuCatCtGral catCtGral = dbSelService.getHuCatCtByCia(in.getNumCia());
				if (catCtGral==null) {				
					LOG.error(in.getIdTraza() + " ASINC_ERROR-empleado:" + in.getNumEmp() + " cia:" + in.getNumCia() + " fecha:" + in.getHoraChecada() + "  | error al consultar HU_CAT_CT_GRAL.");			
					Thread.sleep(600000);
					retry ++;
					continue;
				}
				if(catCtGral.getAnalisisFaltas().equalsIgnoreCase("S")) {
					if(fechaUltimaChecada.toLocalDate().isBefore(in.getHoraChecada().toLocalDate())) {
						try {
							this.calculaFalta(in, lastMove);
						} catch (Exception e) {
							LOG.error(in.getIdTraza() + " ASINC_ERROR-empleado:" + in.getNumEmp() + " cia:" + in.getNumCia() + " fecha:" + in.getHoraChecada() + "  | error:" + e.getMessage());
						}	
					}
				}
				executeCatCoGral = false;
	    	}
	    	LOG.info(in.getIdTraza() + "End ICEHU-empleado:" + in.getNumEmp() + " cia:" + in.getNumCia() + " fecha:" + in.getHoraChecada() + " ejecutado correctamente");
    	}catch (Exception e) {
    		LOG.error(in.getIdTraza() + " ASINC_ERROR-empleado:" + in.getNumEmp() + " cia:" + in.getNumCia() + " fecha:" + in.getHoraChecada() + "  | error:" + e.getMessage());
		}
    }
    
    /**
     * @author rcaraveo
	 * @apiNote SERVICIO PARA PERSISTIR LA CHECADA DE ENTRADA DE MANERA ASINCRONA EN LA BD HUMAN19
     * @param in
     * @param fechaPasada
     * @param lastMoveByDate
     * @throws InterruptedException
     */
    @Async("threadPoolTaskExecutor")
    public void insertaSalidaHu(InChecadaSalidaDto in, String fechaPasada, Rn lastMoveByDate) throws InterruptedException {
    	LOG.info(in.getIdTraza() + "Begin ICSHU-empleado:" + in.getNumEmp() + " cia:" + in.getNumCia() + " fecha:" + in.getHoraChecada() + " Inicia logica para la persistencia de la salida. " );
    	boolean executeRn = true;
    	int retry = 0;
    
    	try {
	    	//PERSISTE LA CHECADA EN RN Y HU_RELOJ_REG_TXT DE MANERA TRANSACCIONAL
	    	while(executeRn && retry < 10) {
				//CREA ENTIDAD HURELOJREGTXT PARA INSERTAR
				HuRelojRegTxt huRelojRegTxtIn = new HuRelojRegTxt();
				HuRelojRegTxtPK huRelojRegTxtPK = new HuRelojRegTxtPK();			
				huRelojRegTxtPK.setNumEmp(in.getNumEmp());
				huRelojRegTxtIn.setUserId("eTime");
				huRelojRegTxtPK.setCia(in.getNumCia());
				huRelojRegTxtIn.setEstacion(in.getEstacion());
				huRelojRegTxtIn.setCveChecada(in.getTipoChecada());
				huRelojRegTxtPK.setHora(in.getHoraChecada().truncatedTo(ChronoUnit.MINUTES));
				huRelojRegTxtIn.setComida(BigDecimal.ZERO);
				huRelojRegTxtIn.setFechaMov(LocalDateTime.now());
				huRelojRegTxtIn.setMotChecada(" ");
				huRelojRegTxtIn.setStatus("B");
				huRelojRegTxtPK.setFecha(LocalDate.parse(fechaPasada, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				huRelojRegTxtIn.setId(huRelojRegTxtPK);
				
				//PERSISTE EN DB
				Rn updateResult = dbInsService.saveChecada(lastMoveByDate, huRelojRegTxtIn);
				if(updateResult == null) {
					LOG.error(in.getIdTraza() + " ASINC_ERROR-empleado:" + in.getNumEmp() + " cia:" + in.getNumCia() + " fecha:" + in.getHoraChecada() + " | error al persistir la salida en DB.");
					Thread.sleep(600000);
					retry ++;
					continue;
				}
				executeRn = false;
	    	}
	    	try {
				this.calculaSalida(in, lastMoveByDate);
			} catch (Exception e) {
				LOG.error(in.getIdTraza() + " ASINC_ERROR-empleado:" + in.getNumEmp() + " cia:" + in.getNumCia() + " fecha:" + in.getHoraChecada() + " | error:" + e.getMessage());
			}
			LOG.info(in.getIdTraza() + "End ICSHU-empleado:" + in.getNumEmp() + " cia:" + in.getNumCia() + " fecha:" + in.getHoraChecada() + " ejecutado correctamente");
    	}catch (Exception e) {
    		LOG.error(in.getIdTraza() + " ASINC_ERROR-empleado:" + in.getNumEmp() + " cia:" + in.getNumCia() + " fecha:" + in.getHoraChecada() + " | error:" + e.getMessage());
		}
    }    
    
    /**
     * LOGICA PARA EL CALCULO DE FALTAS
     */    
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
	    			if(guardarRn == null)LOG.error(in.getIdTraza() + " ocurrio un error al persistir en RN:" + inputPK.toString() + inputRn.toString());
	    			try {    				
	    				String calculo = dbInsService.calculoConceptos(in.getNumCia(), in.getNumEmp(), lastMove.getId().getRnFecha().toLocalDate().atStartOfDay());
	    				LOG.info(in.getIdTraza() + " SP_CC resultado ejecucion:" + calculo);
	    			}catch (Exception e) {			
	    				LOG.error(in.getIdTraza() + " ocurrio un error al ejecuta el calculo de conceptos:" + e.toString());	
	    			}    			
	    		} 	    		
	    		ultimaChecada = ultimaChecada.plusDays(1);    		
	    	}
    	}catch (Exception e) {
    		LOG.error(in.getIdTraza() + " ocurrio un error al ejecuta el calculo de faltas:" + e.toString());
		}
    }
    
    public void calculaSalida(InChecadaSalidaDto in, Rn lastMove) throws InterruptedException {   	
		try {			
			String calculo = dbInsService.calculoConceptos(in.getNumCia(), in.getNumEmp(), lastMove.getId().getRnFecha().toLocalDate().atStartOfDay());
			LOG.info(in.getIdTraza() + " SP_CC resultado ejecucion:" + calculo);
		}catch (Exception e) {			
			LOG.error(in.getIdTraza() + " ocurrio un error al ejecuta el calculo de conceptos:" + e.toString());
		}  		
    }
}