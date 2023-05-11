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
import com.vsm.dto.InChecadaSalidaDto;
import com.vsm.dto.InRootDto;
import com.vsm.dto.OutChecadaSalidaDto;
import com.vsm.dto.ValidaEstacionHuDto;
import com.vsm.util.GetFoto;
import com.vsm.util.InsertChecada;
import com.vsm.util.Utils;
import com.vsm.util.ValidaEstacion;

@Service("salidaHuService")
/**
 * @Api(tags = "RegChecadaET")
 * @author rcaraveo
 *
 */
public class SalidaHu {
	private static final Logger LOG = LogManager.getLogger(SalidaHu.class.getName());

	@Autowired
	RestTemplate template;
	
	@Autowired
	TransSelDbService dbSelService;
	
	@Autowired
	ValidaEstacion valEstService;
	
	@Autowired
	GetFoto fotoService;
	
	@Autowired
	Utils utilService;

	@Autowired
	InsertChecada insertaService;
	
	public OutChecadaSalidaDto salida(InChecadaSalidaDto in, InRootDto req) throws Exception {
		OutChecadaSalidaDto out = new OutChecadaSalidaDto();
		String fechaPasada = in.getHoraChecada().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		BigDecimal claveTurno = in.getMrDto().getMrCveTurno();
		BigDecimal claveHorario = in.getMrDto().getMrCveHor();
		
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
		
		//OBTIENE RH Y TA140 DE DB PARA VALIDAR LA CVE DE HORARIO
		Ta140 ta140resp = new Ta140();
		List<Rh> rhlist = dbSelService.getRhByDate(in.getNumCia(), in.getNumEmp(), in.getHoraChecada().toLocalDate().atStartOfDay());
		if(rhlist!=null && !rhlist.isEmpty()) {
			claveTurno = rhlist.get(0).getRhCveTurno()!=null?rhlist.get(0).getRhCveTurno():BigDecimal.ZERO;
			claveHorario = rhlist.get(0).getRhCveHor()!=null?rhlist.get(0).getRhCveHor():BigDecimal.ZERO;
		}		
		if(claveTurno.equals(BigDecimal.ZERO) && claveHorario.longValue()>0) {		
			List<HuCatRolDet> huCatRolDets = dbSelService.getHuCatRolDet(in.getNumCia(), claveHorario.longValue(), in.getHoraChecada().toLocalDate().atStartOfDay());						
			if(huCatRolDets!=null && !huCatRolDets.isEmpty()) {
				claveTurno = new BigDecimal(huCatRolDets.get(0).getId().getClaveTurno());
			}			
			if(claveTurno.longValue()<=0){
				out.setCodigo(MensajesConstante.ERROR_CLAVE_TURNO_NULL);
				LOG.error(in.getIdTraza() + " No esta definida la clave del turno");
				return out;
			}

			Ta140PK ta140id = new Ta140PK(); 
			ta140id.setTa140Cia(in.getNumCia());
			ta140id.setTa140CveTurno(claveTurno.longValue());
			ta140resp = dbSelService.getTa140ById(ta140id);			
			if(ta140resp==null) {
				LOG.error(in.getIdTraza() + " " + "No se encuentra el turno");
				out.setCodigo(MensajesConstante.ERROR_TURNO_NULL);				
				return out;
			}
		}
		String turno = ta140resp.getTa140TipoTurno()!=null?ta140resp.getTa140TipoTurno():" ";
		
		//OBTIENE EL ULTIMO MOVIMIENTO EN RN
		Rn lastMoveByDate = dbSelService.getRnLasSecByDate(in.getNumCia(), in.getNumEmp(), in.getHoraChecada().toLocalDate().atStartOfDay());
		if(turno.startsWith("NOC") || turno.startsWith("24X")) {
			if(lastMoveByDate==null) {
				lastMoveByDate = dbSelService.getRnLasSecByDate(in.getNumCia(), in.getNumEmp(), in.getHoraChecada().toLocalDate().atStartOfDay().minusDays(1));
			}
		}
		
		//VALIDA SI SE PUEDE REALIZAR EL REGISTRO DE SALIDA
		if(lastMoveByDate!=null && (lastMoveByDate.getRnHorEnt()!=null)) {		
			if(lastMoveByDate.getRnHorSal()!=null) {
				out.setCodigo(MensajesConstante.SALIDA_DUPLICADA);
				LOG.error(in.getIdTraza() + " " + "El utimo movimiento es salida");
				return out;
			}			
			if(lastMoveByDate.getRnHorEnt().isAfter(in.getHoraChecada())) {
				out.setCodigo(MensajesConstante.ENTRADA_MAYOR_CHECADA);
				LOG.error(in.getIdTraza() + " " + "La entrada es mayor a la salida: " + lastMoveByDate.getRnHorEnt().toString());
				return out;
			}			
			LocalDateTime hora = in.getHoraChecada().truncatedTo(ChronoUnit.MINUTES);			
			if(lastMoveByDate.getRnHorEnt().isEqual(hora)) {
				out.setCodigo(MensajesConstante.ENTRADA_MAYOR_CHECADA);
				LOG.error(in.getIdTraza() + " " + "La entrada es mayor a la salida: " + lastMoveByDate.getRnHorEnt().toString());
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
			//validacion estaciones END
			
			//validacion foto BEGIN
			Map<String, String> mapfoto = fotoService.GetFotoHU(in.getNumCia(), in.getNumEmp());
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
			//validacion foto END
			
			/**
			 * UPDATE DEL ULTIMO REGISTRO EN LA TABLA RN
			 */
    		lastMoveByDate.setRnHorSal(in.getHoraChecada().truncatedTo(ChronoUnit.MINUTES));
    		lastMoveByDate.setRnFecModif(LocalDateTime.now());
			try {
				insertaService.insertaSalidaHu(in, fechaPasada, lastMoveByDate);
			} catch (InterruptedException e) {
				LOG.error(in.getIdTraza() + " " + "insertService error trace: " + e.getMessage());
				out.setCodigo(MensajesConstante.ERROR_EXECUTE_INSERT_CODE);
				return out;
			}
		}else {
			out.setCodigo(MensajesConstante.SALIDA_DUPLICADA);
			LOG.error(in.getIdTraza() + " " + "No existen checadas");
			return out;
		}		
		out.setCodigo(MensajesConstante.SUCCES);
		return out;
	}
}