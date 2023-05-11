package com.vsm.util;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vsm.constant.MensajesConstante;
import com.vsm.db.entity.HuCatCtGral;
import com.vsm.db.entity.HuCatRolDet;
import com.vsm.db.entity.Mr;
import com.vsm.db.entity.Pr;
import com.vsm.db.entity.PrPK;
import com.vsm.db.entity.Rh;
import com.vsm.db.entity.Rn;
import com.vsm.db.entity.Ta140;
import com.vsm.db.service.TransInsDbService;
import com.vsm.db.service.TransSelDbService;
import com.vsm.dto.SetFaltaOutDto;
import com.vsm.dto.SetRetardoOutDto;

@Service("retardoService")
/**
 * @Api(tags = "Retardo")
 * @author rcaraveo
 *
 */
public class RetardoFalta {
	private static final Logger LOG = LogManager.getLogger(RetardoFalta.class.getName());
	
	@Autowired
	TransSelDbService dbSelService;
	
	@Autowired
	TransInsDbService dbInsService;

	public SetRetardoOutDto setRetardo(Long numCia,Long numEmp,LocalDateTime horaChecada,Rn lastMove,Mr mr,Long maxRnSec,HuCatCtGral huCatCtGral,List<Rh> rhlist,
									   HuCatRolDet huCatRolDet,Ta140 ta140,String idTraza) throws Exception {
		SetRetardoOutDto out = new SetRetardoOutDto();
		BigDecimal claveTurno = mr.getMrCveTurno();		
		String fechaSinHora = horaChecada.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		horaChecada = horaChecada.truncatedTo(ChronoUnit.MINUTES);
				
		//VALIDA QUE EXISTA TURNO
		if(mr.getMrCveTurno().equals(BigDecimal.ZERO) && mr.getMrCveHor().longValue()>0) {
			if(huCatRolDet!=null) {
				claveTurno = new BigDecimal(huCatRolDet.getId().getClaveTurno());
			}			
			if(claveTurno.longValue()<=0){
				LOG.error(idTraza + " No se encuentra el turno");
				out.setCodigo(MensajesConstante.ERROR_CLAVE_TURNO_NULL);
				out.setMsj("No esta definida la clave del turno");
				return out;
			}
		}				
		if(ta140==null) {
			LOG.error(idTraza + " Sin coincidencia en TA140");
			out.setCodigo(MensajesConstante.ERROR_TURNO_NULL);
			out.setMsj("No se encuentra el turno");
			return out;
		}		
		int diaSemana = horaChecada.getDayOfWeek().getValue();
		String entradaToleranciaStr = "";
		String replace = horaChecada.toString().substring(0,10) + " ";
		
		//VALIDA LA TOLERANCIA POR DIA DE LA SEMANA
		switch (diaSemana) {
			case 1:
				entradaToleranciaStr = ta140.getTa140HoraE1Lun().plusMinutes(ta140.getTa140MinTol().longValue()).toString();
				entradaToleranciaStr = entradaToleranciaStr.replace("1900-01-01T", replace);
				break;
			case 2:				
				entradaToleranciaStr = ta140.getTa140HoraE1Mar().plusMinutes(ta140.getTa140MinTol().longValue()).toString();
				entradaToleranciaStr = entradaToleranciaStr.replace("1900-01-01T", replace);
				break;	
			case 3:
				entradaToleranciaStr = ta140.getTa140HoraE1Mie().plusMinutes(ta140.getTa140MinTol().longValue()).toString();
				entradaToleranciaStr = entradaToleranciaStr.replace("1900-01-01T", replace);
				break;
			case 4: 
				entradaToleranciaStr = ta140.getTa140HoraE1Jue().plusMinutes(ta140.getTa140MinTol().longValue()).toString();
				entradaToleranciaStr = entradaToleranciaStr.replace("1900-01-01T", replace);
				break;
			case 5: 
				entradaToleranciaStr = ta140.getTa140HoraE1Vie().plusMinutes(ta140.getTa140MinTol().longValue()).toString();
				entradaToleranciaStr = entradaToleranciaStr.replace("1900-01-01T", replace);
				break;
			case 6: 
				entradaToleranciaStr = ta140.getTa140HoraE1Sab().plusMinutes(ta140.getTa140MinTol().longValue()).toString();
				entradaToleranciaStr = entradaToleranciaStr.replace("1900-01-01T", replace);
				break;
			case 7: 
				entradaToleranciaStr = ta140.getTa140HoraE1Dom().plusMinutes(ta140.getTa140MinTol().longValue()).toString();
				entradaToleranciaStr = entradaToleranciaStr.replace("1900-01-01T", replace);
				break;
		}		
		if(horaChecada.isAfter(LocalDateTime.parse(entradaToleranciaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))) {
			long minutesRet = Duration.between(LocalDateTime.parse(entradaToleranciaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), horaChecada).toMinutes();
			
			//CREA OBJETO PR
			Pr regPr = new Pr();
			PrPK prPK = new PrPK();
			prPK.setPrCia(numCia);
			regPr.setPrConcepto(BigDecimal.ZERO);
			prPK.setPrFecha(LocalDate.parse(fechaSinHora, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay());
			regPr.setPrFechaCap(LocalDate.now().atStartOfDay());
			regPr.setPrHoraCap(LocalDateTime.now());
			regPr.setPrHrsPer1(BigDecimal.ZERO);
			regPr.setPrHrsPer2(BigDecimal.ZERO);
			regPr.setPrMinRetardo(new BigDecimal(minutesRet));
			prPK.setPrNumEmp(numEmp);
			regPr.setPrObservacion(" ");
			regPr.setPrPermHhFin(horaChecada);
			prPK.setPrPermHhIni(LocalDateTime.parse(entradaToleranciaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
			regPr.setPrSts("A");
			regPr.setPrUsuario("Etime");	
			regPr.setId(prPK);
			Pr guardarPr = dbInsService.savePr(regPr);
			if(guardarPr==null) {
				LOG.error(idTraza + " No se pudo persistir el retardo en PR");
				out.setCodigo(MensajesConstante.ERROR_GENERAL);
				out.setMsj("Error al inserter Pr");
				return out;
			}
			
			//VALIDA SI SE DEBE PERMITIR CHECAR CON RETARDO
			if(huCatCtGral.getChecarRetardo().equalsIgnoreCase("N")) {
				if(minutesRet > huCatCtGral.getMinChecAntesEntrada().longValue()) {
					LOG.error(idTraza + " Minutos mayor a lo permitido en HU_CAT_CT_GRAL: " + minutesRet);
					out.setCodigo(MensajesConstante.ERROR_RETARDO_FALTA);
					out.setMsj("Minutos mayor a lo permitido en huCatCtGral");
					return out;
				}
			}
		}else {
			out.setCodigo(MensajesConstante.SUCCES_RETARDO);
			return out;
		}
		out.setCodigo(MensajesConstante.SUCCES_RETARDO);
		return out;
	}
	
	
	public SetFaltaOutDto setFalta(Long numCia, Long numEmp, LocalDateTime horaChecada, Rn lastMove) throws Exception {
		SetFaltaOutDto out = new SetFaltaOutDto();		
		if(lastMove==null) {
			out.setCodigo(MensajesConstante.ERROR_RN_NO_EXISTE);
			return out;
		}		
		if(horaChecada.isAfter(lastMove.getId().getRnFecha())) {
			horaChecada.getYear();
			lastMove.getId().getRnFecha().getYear();			
			out.setCodigo(MensajesConstante.SUCCES);
			return out;			
		}else if(horaChecada.isAfter(lastMove.getId().getRnFecha())) {
			out.setCodigo(MensajesConstante.REGISTRO_DUPLICADO);
			return out;
		}else {
			out.setCodigo(MensajesConstante.SUCCES);
			return out;
		}
	}
}