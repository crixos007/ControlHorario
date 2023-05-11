package com.vsm.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vsm.constant.MensajesConstante;
import com.vsm.db.entity.HuEmpl;
import com.vsm.db.service.TransSelDbService;
import com.vsm.dto.Beacons;
import com.vsm.dto.Bssids;
import com.vsm.dto.Geolocalizacion;
import com.vsm.dto.InChecadaETDto;
import com.vsm.dto.InRegistraChecadaDto;
import com.vsm.dto.OutChecadaETDto;
import com.vsm.dto.OutRegistraChedadaDto;
import com.vsm.dto.OutRootDto;
import com.vsm.dto.ResponseHumaneTime;
import com.vsm.dto.SGeolocalizacion;
import com.vsm.util.CalculaFalta;

@Service("regChecadaService")
/**
 * @Api(tags = "RegChecadaET")
 * @author rcaraveo
 *
 */
public class RegistraChecada {
	private static final Logger LOG = LogManager.getLogger(RegistraChecada.class.getName());
		
	@Autowired
	RestTemplate template;
	
	@Autowired
	CalculaFalta faltaService;
	
	@Autowired
	TransSelDbService dbService;
	
	@Autowired
	ChecadaHuman checadaHuService;
	
	@Autowired
	ChecadaEtime checadaEtService;
	
	public OutRegistraChedadaDto regChqUnificado (InRegistraChecadaDto in) throws Exception {		
		OutRootDto rootOut = new OutRootDto();		
		OutRegistraChedadaDto out = new OutRegistraChedadaDto();
		
		/**
		 * VALIDACION DATOS DE ENTRADA MANDATORIOS
		 */
		if (in == null) {
			rootOut.setCodigo(MensajesConstante.ERROR_ENTRADA_NULL);			
		}		
		if (Integer.parseInt(in.getRoot().getNumeroCompania()) <= 0) {
			LOG.error(in.getIdTraza() + " " + "el campo numCia es mandatorio");
			rootOut.setCodigo(MensajesConstante.COMPANIA_NULA);			
		} else if (Integer.parseInt(in.getRoot().getEmpleado()) <= 0) {
			LOG.error(in.getIdTraza() + " " + "el campo numEmp es mandatorio");
			rootOut.setCodigo(MensajesConstante.EMPLEADO_NULO);
		} else if (in.getRoot().getFechaHoraChecada() == null) {
			LOG.error(in.getIdTraza() + " " + "el campo fechaChecada es mandatorio");
			rootOut.setCodigo(MensajesConstante.FECHA_NULO);
		} else if (!("E".equals(in.getRoot().getTipoChecada()) || "S".equals(in.getRoot().getTipoChecada()))) {
			LOG.error(in.getIdTraza() + " " + "el campo tipoChecada es mandatorio");
			rootOut.setCodigo(MensajesConstante.ERROR_TIPO_ENTRADA);
		} else if (in.getRoot().getFoto() == null) {
			LOG.error(in.getIdTraza() + " " + "el campo foto es mandatorio");
			rootOut.setCodigo(MensajesConstante.ERROR_FOTO);
		} else if (Integer.parseInt(in.getRoot().getPrioridad()) <= 0 || Integer.parseInt(in.getRoot().getPrioridad()) >= 5) {
			LOG.error(in.getIdTraza() + " " + "el campo prioridad es mandatorio");
			rootOut.setCodigo(MensajesConstante.ERROR_PRIORIDAD);
		}		
		if(rootOut.getCodigo()!=null) {
			out.setRoot(rootOut);
			return out;
		}
		if(in.getRoot().getsGeolocalizacion()==null) {
			in.getRoot().setsGeolocalizacion(new SGeolocalizacion("0","0"));
		}
		
		/**
		 * SE VALIDA QUE EL USUARIO PERTENEZCA A HUMAN O ETIME
		 */
		HuEmpl respEmp = dbService.getHuEmplById(Long.parseLong(in.getRoot().getNumeroCompania()), Long.parseLong(in.getRoot().getEmpleado()));
		if(respEmp != null) {	
			BigDecimal latitud = BigDecimal.ZERO;
			try {
				latitud = new BigDecimal(in.getRoot().getsGeolocalizacion().getLatitud());
			}catch (Exception e) {
				LOG.error(in.getIdTraza() + " " + "el campo latitud debe ser double valor: " + in.getRoot().getsGeolocalizacion().getLatitud());
				rootOut.setCodigo(MensajesConstante.ERROR_GENERAL);
			}
			BigDecimal longitud = BigDecimal.ZERO;
			try {					
				longitud = new BigDecimal(in.getRoot().getsGeolocalizacion().getLongitud());
			}catch (Exception e) {
				LOG.error(in.getIdTraza() + " " + "el campo longitud debe ser double valor: " + in.getRoot().getsGeolocalizacion().getLongitud());
				rootOut.setCodigo(MensajesConstante.ERROR_GENERAL);
			}
			ResponseHumaneTime outHuman = checadaHuService.checadaHuman(in);				
			try {					
				faltaService.registraAuditoriaHu(latitud, longitud, outHuman, in);
			} catch (InterruptedException e) {
				LOG.error(in.getIdTraza() + " " + e.getMessage());
			}				
			rootOut.setCodigo(outHuman.getCodigo());
			rootOut.setEstacion(outHuman.getEstacion()==null?" ":outHuman.getEstacion());
		}else {
			InChecadaETDto inEt = new InChecadaETDto();
			Geolocalizacion geo = new Geolocalizacion();
			Beacons beacon = new Beacons();
			Bssids bssid = new Bssids(); 
			
			List<Map<String, String>> beacons = new ArrayList<Map<String,String>>();
			Map<String, String> beac = new HashMap<>();
			try {
				beac.put("beacon", in.getRoot().getsBeacons().getBeacons().get(0));
			}catch (Exception e) {
				beac.put("beacon", "0");
			}
			
			List<Map<String, String>> bssids = new ArrayList<Map<String,String>>();
			Map<String, String> ssid = new HashMap<>();
			try {
				ssid.put("bssid", in.getRoot().getsBssids().getBssid().get(0));
			}catch (Exception e) {
				ssid.put("bssid", "0");
			}
			
			geo.setLatitud(in.getRoot().getsGeolocalizacion().getLatitud());
			geo.setLongitud(in.getRoot().getsGeolocalizacion().getLongitud());
			inEt.setEmpleado(in.getRoot().getEmpleado());
			inEt.setFechaHoraChecada(in.getRoot().getFechaHoraChecada().toString());
			inEt.setFoto(in.getRoot().getFoto());
			inEt.setNumeroCompania(in.getRoot().getNumeroCompania());
			inEt.setPrioridad(in.getRoot().getPrioridad());
			inEt.setTipoChecada(in.getRoot().getTipoChecada());
			
			beacons.add(beac);
			beacon.setBeacons(beacons);
			bssids.add(ssid);
			bssid.setBssids(bssids);
			inEt.setsBeacons(beacon);
			inEt.setsBssids(bssid);
			inEt.setsGeolocalizacion(geo);
			inEt.setIdTraza(in.getIdTraza());
			OutChecadaETDto outHuman = checadaEtService.saveDesvinculado(inEt);
			
			try {
				faltaService.registraAuditoriaEt(in, outHuman, geo);
			} catch (InterruptedException e) {
				LOG.error(in.getIdTraza() + " " + e.getMessage());
			}				
			rootOut.setCodigo(outHuman.getCodigo());
			rootOut.setEstacion(outHuman.getEstacion()==null?" ":outHuman.getEstacion());
		}			
		out.setRoot(rootOut);
		return out;
	}	
}