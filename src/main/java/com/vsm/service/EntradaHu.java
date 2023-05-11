package com.vsm.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.vsm.constant.MensajesConstante;
import com.vsm.constant.ServiciosConstante;
import com.vsm.db.entity.HuCatCtGral;
import com.vsm.db.entity.HuCatRolDet;
import com.vsm.db.entity.HuRelojRegTxt;
import com.vsm.db.entity.HuRelojRegTxtPK;
import com.vsm.db.entity.Rh;
import com.vsm.db.entity.Rn;
import com.vsm.db.entity.Ta140;
import com.vsm.db.entity.Ta140PK;
import com.vsm.db.service.TransSelDbService;
import com.vsm.dto.CompareRequest;
import com.vsm.dto.CompareResponse;
import com.vsm.dto.InChecadaEntradaDto;
import com.vsm.dto.InRootDto;
import com.vsm.dto.OutChecadaEntradaDto;
import com.vsm.dto.SetFaltaOutDto;
import com.vsm.dto.SetRetardoOutDto;
import com.vsm.dto.ValidaEstacionHuDto;
import com.vsm.util.CalculaFalta;
import com.vsm.util.GetFoto;
import com.vsm.util.InsertChecada;
import com.vsm.util.RetardoFalta;
import com.vsm.util.Utils;
import com.vsm.util.ValidaEstacion;

@Service("entradaHuService")
/**
 * @Api(tags = "RegChecadaET")
 * @author rcaraveo
 *
 */
public class EntradaHu {
	private static final Logger LOG = LogManager.getLogger(EntradaHu.class.getName());
		
	@Autowired
	RestTemplate template;
	
	@Autowired
	CalculaFalta faltaService;
	
	@Autowired
	TransSelDbService dbSelService;
	
	@Autowired
	ChecadaHuman checadaHuService;
	
	@Autowired
	RetardoFalta retardoService;
	
	@Autowired
	ValidaEstacion valEstService;
	
	@Autowired
	GetFoto fotoService;
	
	@Autowired
	Utils utilService;
	
	@Autowired
	InsertChecada insertaService;

	public OutChecadaEntradaDto entrada(InChecadaEntradaDto in, InRootDto req) throws Exception {		
		OutChecadaEntradaDto out = new OutChecadaEntradaDto();
		String fechaPasada = in.getHoraChecada().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String fechaUltima = "";
		LocalDateTime fechaUltimaChecada = LocalDateTime.now();
		boolean calculoFlag = false;
		Long maxRnSec = 1L;
		BigDecimal claveTurno = in.getMrDto().getMrCveTurno()!=null?in.getMrDto().getMrCveTurno():BigDecimal.ZERO;
		BigDecimal claveHorario = in.getMrDto().getMrCveHor()!=null?in.getMrDto().getMrCveHor():BigDecimal.ZERO;
		
		//OBTIENE EL REGISTRO DE HU_RELOJ_REG_TXT
		HuRelojRegTxtPK huRelRegTxtId = new HuRelojRegTxtPK();
		huRelRegTxtId.setCia(in.getNumCia());
		huRelRegTxtId.setFecha(in.getHoraChecada().toLocalDate());
		huRelRegTxtId.setHora(in.getHoraChecada().truncatedTo((ChronoUnit.MINUTES)));
		huRelRegTxtId.setNumEmp(in.getNumEmp());		
		HuRelojRegTxt relojRegTxt = dbSelService.getHuRelRegTxtById(huRelRegTxtId);
		if(relojRegTxt!=null) {
			LOG.error(in.getIdTraza() + " existe registro en HU_RELOJ_REG_TXT parametros: " + huRelRegTxtId.toString());
			out.setCodigo(MensajesConstante.ERROR_CHECADA_DUPLICADA);
			return out;
		}
		
		//OBTIENE EL ULTIMO REGISTRO DE RN		
		Rn lastMove = dbSelService.getRnLastMov(in.getNumCia(), in.getNumEmp());		
		if(lastMove!=null) {
			fechaUltima = lastMove.getId().getRnFecha()!=null?lastMove.getId().getRnFecha().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")):"";
			fechaUltimaChecada = lastMove.getId().getRnFecha()!=null?lastMove.getId().getRnFecha().plusDays(1):LocalDateTime.now();
		}
		Rn lastMoveByDate = dbSelService.getRnLasSecByDate(in.getNumCia(), in.getNumEmp(), in.getHoraChecada().toLocalDate().atStartOfDay());		
		Rn falta = dbSelService.getRnfalta(in.getNumCia(), in.getNumEmp(), in.getHoraChecada().toLocalDate().atStartOfDay());
		
		//VALIDA SI SE PUEDE REALIZAR EL REGISTRO DE LA ENTRADA
		if(falta==null) {
			if(lastMoveByDate!=null) {
				maxRnSec=maxRnSec+lastMoveByDate.getId().getRnSecuencia();
				if(lastMoveByDate.getRnHorSal()==null) {
					LOG.error(in.getIdTraza() + " la ultima fecha no tiene hora de checada");
					out.setCodigo(MensajesConstante.ENTRADA_DUPLICADA);
					return out;
				}
			}				
			if(fechaPasada.equalsIgnoreCase(fechaUltima) && lastMove.getRnHorSal()!=null) {
				if(lastMove.getRnHorSal().isAfter(in.getHoraChecada())) {
					LOG.error(in.getIdTraza() + " la ultima fecha de salida: " + lastMove.getRnHorSal().toString() + " es mayor a la hora de checada");
					out.setCodigo(MensajesConstante.REGISTRO_DUPLICADO);
					return out;
				}
			}			
			if(maxRnSec>1) {
				LocalDateTime horaEntrada = in.getHoraChecada().truncatedTo(ChronoUnit.MINUTES);
				LocalDateTime horaSalida = lastMoveByDate.getRnHorSal().truncatedTo(ChronoUnit.MINUTES);				
				if(horaEntrada.isEqual(horaSalida)) {
					LOG.error(in.getIdTraza() + " la ultima fecha de salida: " + lastMove.getRnHorSal().toString() + " es igual a la hora de checada");
					out.setCodigo(MensajesConstante.REGISTRO_DUPLICADO);
					return out;	
				}
			}
		}
		if(fechaPasada.equalsIgnoreCase(fechaUltima) && !calculoFlag) {
			if(in.getHoraChecada().isBefore(lastMove.getId().getRnFecha())) {
				if(falta!=null) {
					if(falta.getRnHorSal()!=null && falta.getRnHorSal().isBefore(in.getHoraChecada())) {
						LOG.error(in.getIdTraza() + " la ultima fecha de salida: " + lastMove.getRnHorSal().toString() + " es mayor a la hora de checada");
						out.setCodigo(MensajesConstante.REGISTRO_DUPLICADO);
						return out;
					}
					
					if(falta.getRnHorSal()==null) {
						LOG.error(in.getIdTraza() + " la ultima fecha no tiene checada de salida");
						out.setCodigo(MensajesConstante.ENTRADA_DUPLICADA);
						return out;
					}
				}
			}else {
				SetFaltaOutDto setFalta = retardoService.setFalta(in.getNumCia(), in.getNumEmp(), in.getHoraChecada(), lastMove);
				if(setFalta.getCodigo()!=MensajesConstante.SUCCES) {
					LOG.error(in.getIdTraza() + " " + setFalta.getMsj());
					out.setCodigo(setFalta.getCodigo());
					return out;
				}				
			}
		}		
		
		//OBTIENE RH Y TA140 DE DB PARA VALIDAR LA CVE DE HORARIO
		HuCatRolDet huCatRolDet = new HuCatRolDet();
		List<Rh> rhlist = dbSelService.getRhByDate(in.getNumCia(), in.getNumEmp(), in.getHoraChecada().toLocalDate().atStartOfDay());
		if(rhlist!=null && !rhlist.isEmpty()) {
			claveTurno = rhlist.get(0).getRhCveTurno()!=null?rhlist.get(0).getRhCveTurno():BigDecimal.ZERO;
			claveHorario = rhlist.get(0).getRhCveHor()!=null?rhlist.get(0).getRhCveHor():BigDecimal.ZERO;
		}		
		if(claveTurno.equals(BigDecimal.ZERO) && claveHorario.longValue()>0) {		
			List<HuCatRolDet> huCatRolDets = dbSelService.getHuCatRolDet(in.getNumCia(), claveHorario.longValue(), in.getHoraChecada().toLocalDate().atStartOfDay());						
			if(huCatRolDets!=null && !huCatRolDets.isEmpty()) {
				claveTurno = new BigDecimal(huCatRolDets.get(0).getId().getClaveTurno());
				huCatRolDet = huCatRolDets.get(0);
			}			
			if(claveTurno.longValue()<=0){
				out.setCodigo(MensajesConstante.ERROR_CLAVE_TURNO_NULL);
				LOG.error(in.getIdTraza() + " No esta definida la clave del turno");
				return out;
			}
		}
		Ta140PK ta140id = new Ta140PK(); 
		ta140id.setTa140Cia(in.getNumCia());
		ta140id.setTa140CveTurno(claveTurno.longValue());
		Ta140 ta140resp = dbSelService.getTa140ById(ta140id);			
		if(ta140resp==null) {
			LOG.error(in.getIdTraza() + " " + "No se encuentra el turno");
			out.setCodigo(MensajesConstante.ERROR_TURNO_NULL);				
			return out;
		}
		
		//VALIDA ESTACIONES
		String beacon = " ";
		if(req.getsBeacons()!=null && !req.getsBeacons().getBeacons().isEmpty())beacon=req.getsBeacons().getBeacons().get(0);
		String bssid = " ";
		if(req.getsBssids()!=null && !req.getsBssids().getBssid().isEmpty())bssid=req.getsBssids().getBssid().get(0);		
		ValidaEstacionHuDto estacion = valEstService.valEstacion(in.getNumCia(),in.getNumEmp(),req.getPrioridad(),req.getsGeolocalizacion().getLatitud(), 
																 req.getsGeolocalizacion().getLongitud(),beacon,bssid,in.getIdTraza());		
		out.setEstOut(estacion);
		if(estacion.getCodigo()!=MensajesConstante.SUCCES) {
			LOG.error(in.getIdTraza() + " " + "Sin coincidencia con alguna estacion");
			out.setCodigo(estacion.getCodigo());
			return out;
		}
		in.setEstacion(estacion.getTa155().getId().getTa155Estacion()!=null?estacion.getTa155().getId().getTa155Estacion():"");
		
		//VALIDA FOTO
		Map<String, String> mapfoto = fotoService.GetFotoHU(in.getNumCia(),in.getNumEmp());
		String servicio = ServiciosConstante.COMPARA_FOTO_SERVICE;
		CompareRequest reqCompare =  new CompareRequest();
		reqCompare.setCompany(in.getCiaHu().getClaveCompania());
		reqCompare.setEmployeeNo(req.getEmpleado());
		reqCompare.setModel(mapfoto.get("foto"));
		reqCompare.setPhoto(req.getFoto());
		reqCompare.setHash(utilService.encryptPassword(mapfoto.get("foto")));
		CompareResponse res = new CompareResponse();
		res.setRes(false);
		try {
			res = template.postForObject(servicio, reqCompare, CompareResponse.class);
		}catch (Exception e) {
			LOG.error(in.getIdTraza() + " " + "fotoService error trace: " + e.getMessage());
			throw new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE);
		}			
		if(!res.isRes()){	
			LOG.error(in.getIdTraza() + " " + "La imagen no coincide con el modelo");
			out.setCodigo(MensajesConstante.ERROR_COMPARA_FOTO);
			return out;
		}
				
		//VALIDA EL RETARDO
		HuCatCtGral catCtGral = dbSelService.getHuCatCtByCia(in.getNumCia());		
		if(maxRnSec == 1l) {
			SetRetardoOutDto retardoOut = retardoService.setRetardo(in.getNumCia(),in.getNumEmp(),in.getHoraChecada(),lastMoveByDate,in.getMrDto(),maxRnSec,catCtGral,rhlist,huCatRolDet,ta140resp,in.getIdTraza());
			if(retardoOut.getCodigo().equalsIgnoreCase(MensajesConstante.ERROR_RETARDO_FALTA)) {
				out.setCodigo(MensajesConstante.ERROR_RETARDO_FALTA);
				return out;
			}
		}
				
		try {
			insertaService.insertaEntradaHu(in, falta, fechaPasada, calculoFlag, lastMove, maxRnSec, fechaUltimaChecada);
		} catch (InterruptedException e) {
			LOG.error(in.getIdTraza() + " " + "insertService error trace: " + e.getMessage());
			out.setCodigo(MensajesConstante.ERROR_EXECUTE_INSERT_CODE);
			return out;
		}
		out.setCodigo(MensajesConstante.SUCCES);
		return out;
	}
}