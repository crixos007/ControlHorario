package com.vsm.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vsm.constant.MensajesConstante;
import com.vsm.constant.ServiciosConstante;
import com.vsm.db.entity.Ta155;
import com.vsm.db.service.TransSelDbService;
import com.vsm.dto.InChecadaETDto;
import com.vsm.dto.ValidaEstacionDto;
import com.vsm.dto.ValidaEstacionHuDto;
import com.vsm.lib.dto.etime.EtEmpEstDto;
import com.vsm.lib.dto.etime.EtUnidadMedidaDto;

@Service("validaEstacionService")
public class ValidaEstacion {
	private static final Logger LOG = LogManager.getLogger(ValidaEstacion.class.getName());
	EtEmpEstDto[] estacion;
	EtUnidadMedidaDto uMed;
	
	@Autowired
	RestTemplate template;
	
	@Autowired
	TransSelDbService dbSelService;
		
	public ValidaEstacionHuDto valEstacion(long numCia,long numEmp,String prioridad,String latitud,String longitud,String beacon,String bssid, String idTraza) throws Exception {
		ValidaEstacionHuDto out = new ValidaEstacionHuDto();
		List<Ta155> estaciones = dbSelService.getEstaciones(numCia, numEmp);
		if(estaciones==null || estaciones.isEmpty()) {
			LOG.error("Sin coincidencia en Ta155 para la cia:" + numCia + " emp:" + numEmp);
			out.setCodigo(MensajesConstante.ESTACION_NO_EXISTE);
			return out;
		}	
		
		latitud  = latitud==null?"0":latitud;
		longitud = longitud==null?"0":longitud;
		if (latitud.equalsIgnoreCase("0")&&longitud.equalsIgnoreCase("0")) {
			LOG.error("La localizacion es nula o igual a 0 para la cia:" + numCia + " emp:" + numEmp);
			out.setCodigo(MensajesConstante.FECHA_NULO);
			return out;
		}
		
		List<String> beacons = new ArrayList<String>();
		List<String> estacionesOut = new ArrayList<String>();		
		Ta155 est = new Ta155();
		boolean found = false;
		double  metros = 0;
		LOG.info(idTraza + " Valida estacion traza for: " + numCia + " | " + numEmp + " -prioridad:" + prioridad + " -latitud:" + latitud + " -longitud:" + longitud + " -beacon:" + beacon + " -bssid:" + bssid);				
		if(estaciones.size()>0) {									
			for(Ta155 x : estaciones) {
				if(x.getTa155Aut()!=null && x.getTa155Aut().equalsIgnoreCase("S")) {
					est = x;
					found = true;
				}				
				if(bssid!=null && !bssid.isBlank()) {
					if(x.getTa155Bssid()!=null && x.getTa155Bssid().equals(bssid)) {
						est = x;
						found = true;
					}
				}				
				try{     
			        double radioTierra = 6371;//en kilómetros
			        double Latitud01  =x.getTa155CoordX()!=null?x.getTa155CoordX().doubleValue():0;   
			        double Latitud02  =Double.parseDouble(latitud);   
			        double Longitud01 =x.getTa155CoordY()!=null?x.getTa155CoordY().doubleValue():0;    
			        double Longitud02 =Double.parseDouble(longitud);
			        double dLat = Math.toRadians(Latitud02 - Latitud01);      
			        double dLng = Math.toRadians(Longitud02 - Longitud01);
			        double sindLat = Math.sin(dLat / 2);      
			        double sindLng = Math.sin(dLng / 2);      
			        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2) * Math.cos(Math.toRadians(Latitud01)) * Math.cos(Math.toRadians(Latitud02)); 
			        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));      
			        double distancia = radioTierra * va2;			        
			        metros = 1000 * distancia;			     
				}catch (Exception ex){
					LOG.error(idTraza + " Error al validar la estacion: " + ex.toString());
					out.setCodigo(MensajesConstante.ERROR_GENERAL);
					return out;           
				}				
				if(x.getTa155Tipo().equalsIgnoreCase("B")) {
					beacons.add(x.getTa155Beacon());
				}
				if(x.getTa155Tipo().equalsIgnoreCase("G") || x.getTa155Tipo().equalsIgnoreCase("P")) {
					String desc = x.getTa155Descr()!=null && !x.getTa155Descr().isBlank()? x.getTa155Descr(): " ";
					String zona = x.getTa155Zona()!=null?x.getTa155Zona().toString():" ";
					String xCor = x.getTa155CoordX()!=null?x.getTa155CoordX().toString():" ";
					String yCor = x.getTa155CoordY()!=null?x.getTa155CoordY().toString():" "; 
					String beac = x.getTa155Beacon()!=null && !x.getTa155Beacon().isBlank()?x.getTa155Beacon():" ";
					estacionesOut.add(desc + "|" + zona + "|" + xCor + "," + yCor + "|" + metros + "|" + beac);
				}
				if(prioridad.equalsIgnoreCase("2")||prioridad.equalsIgnoreCase("3")) {
					double rango = x.getTa155Radio()!=null?x.getTa155Radio().doubleValue():0;
					if(rango>=metros) {
						est = x;
						found = true;
					}
				}				
				if(prioridad.equalsIgnoreCase("1")||prioridad.equalsIgnoreCase("3")) {
					if(x.getTa155Beacon()!=null && !x.getTa155Beacon().isBlank()?x.getTa155Beacon().equalsIgnoreCase(beacon):false) {
						est = x;
						found = true;
					}
				}
			}						
			out.setBeacons(beacons);
			out.setEstaciones(estacionesOut);
			out.setTa155(est);
			if(found) {
				out.setCodigo(MensajesConstante.SUCCES);				
				return out;
			}
		}else {
			out.setCodigo(MensajesConstante.ESTACION_NO_EXISTE);
			return out;
		}		
		if(prioridad.equalsIgnoreCase("1")) {
			out.setCodigo(MensajesConstante.P1_MATCH_NO_EXISTE);
		}		
		if(prioridad.equalsIgnoreCase("2")) {
			out.setCodigo(MensajesConstante.P2_MATCH_NO_EXISTE);
		}		
		if(prioridad.equalsIgnoreCase("3")) {
			out.setCodigo(MensajesConstante.P3_MATCH_NO_EXISTE);
		}		
		return out;		
	}
	
	public ValidaEstacionDto valEstacionEt (InChecadaETDto in) throws Exception {
		ValidaEstacionDto out = new ValidaEstacionDto();
		String servicio = ServiciosConstante.GET_ESTACION_ET_SERVICE;
		Map<String, Long> estacionesVar = new HashMap<>();
		estacionesVar.put("ciaNum", Long.parseLong(in.getNumeroCompania()));
		estacionesVar.put("empNum", Long.parseLong(in.getEmpleado()));
		out.setEstacionLibre(false);
		double  metros = 0;
		boolean found = false;
		
		try {
			estacion = template.getForObject(servicio, EtEmpEstDto[].class, estacionesVar);
		}catch (Exception e) {
			LOG.error(in.getIdTraza() + " " + servicio + " error: " + e.toString());			
			out.setCodigo(MensajesConstante.ERROR_EXECUTE_SELECT_CODE);						
			return out;
		}
		List<String> beacons = new ArrayList<String>();
		List<String> estacionesOut = new ArrayList<String>();
		List<EtEmpEstDto> estaciones = Arrays.asList(estacion);
		
		if(estaciones!=null&&estaciones.size()>0) {
			int diaSemana = LocalDateTime.parse(in.getFechaHoraChecada().substring(0, 16) + ":00").getDayOfWeek().getValue();
			List <EtEmpEstDto> responseList = estaciones;							
			for(EtEmpEstDto x : responseList) {
				switch (diaSemana) {
				case 1:
					String ho = x.getEtEstacion().getLunesHo()!=null?x.getEtEstacion().getLunesHo():"N";
					String ssid = x.getEtEstacion().getBssid()!=null?x.getEtEstacion().getBssid():"Not Found";
					if(ho.equalsIgnoreCase("S")) {
						if(in.getsBssids().getBssids().get(0).get("bssid").equalsIgnoreCase(ssid)) {
							out.setCodigo(MensajesConstante.SUCCES);
							out.setIdEstacion(String.valueOf(x.getEtEstacion().getIdEstacion()));
							out.setNombreEstacion(x.getEtEstacion().getNombre());
							out.setEstacionLibre(true);
							out.setEstacionChecada(x);
							found = true;
						}
					}
					break;			
				case 2:
					ho = x.getEtEstacion().getMartesHo()!=null?x.getEtEstacion().getMartesHo():"N";
					ssid = x.getEtEstacion().getBssid()!=null?x.getEtEstacion().getBssid():"Not Found";
					if(ho.equalsIgnoreCase("S")) {
						if(in.getsBssids().getBssids().get(0).get("bssid").equalsIgnoreCase(ssid)) {
							out.setCodigo(MensajesConstante.SUCCES);
							out.setIdEstacion(String.valueOf(x.getEtEstacion().getIdEstacion()));
							out.setNombreEstacion(x.getEtEstacion().getNombre());
							out.setEstacionLibre(true);
							out.setEstacionChecada(x);
							found = true;
						}
					}
					break;
				case 3:
					ho = x.getEtEstacion().getMiercolesHo()!=null?x.getEtEstacion().getMiercolesHo():"N";
					ssid = x.getEtEstacion().getBssid()!=null?x.getEtEstacion().getBssid():"Not Found";
					if(ho.equalsIgnoreCase("S")) {
						if(in.getsBssids().getBssids().get(0).get("bssid").equalsIgnoreCase(ssid)) {
							out.setCodigo(MensajesConstante.SUCCES);
							out.setIdEstacion(String.valueOf(x.getEtEstacion().getIdEstacion()));
							out.setNombreEstacion(x.getEtEstacion().getNombre());
							out.setEstacionLibre(true);
							out.setEstacionChecada(x);
							found = true;
						}
					}
					break;
				case 4:
					ho = x.getEtEstacion().getJuevesHo()!=null?x.getEtEstacion().getJuevesHo():"N";
					ssid = x.getEtEstacion().getBssid()!=null?x.getEtEstacion().getBssid():"Not Found";
					if(ho.equalsIgnoreCase("S")) {
						if(in.getsBssids().getBssids().get(0).get("bssid").equalsIgnoreCase(ssid)) {
							out.setCodigo(MensajesConstante.SUCCES);
							out.setIdEstacion(String.valueOf(x.getEtEstacion().getIdEstacion()));
							out.setNombreEstacion(x.getEtEstacion().getNombre());
							out.setEstacionLibre(true);
							out.setEstacionChecada(x);
							found = true;
						}
					}
					break;
				case 5:
					ho = x.getEtEstacion().getViernesHo()!=null?x.getEtEstacion().getViernesHo():"N";
					ssid = x.getEtEstacion().getBssid()!=null?x.getEtEstacion().getBssid():"Not Found";
					if(ho.equalsIgnoreCase("S")) {
						if(in.getsBssids().getBssids().get(0).get("bssid").equalsIgnoreCase(ssid)) {
							out.setCodigo(MensajesConstante.SUCCES);
							out.setIdEstacion(String.valueOf(x.getEtEstacion().getIdEstacion()));
							out.setNombreEstacion(x.getEtEstacion().getNombre());
							out.setEstacionLibre(true);
							out.setEstacionChecada(x);
							found = true;
						}
					}
					break;
				case 6:
					ho = x.getEtEstacion().getSabadoHo()!=null?x.getEtEstacion().getSabadoHo():"N";
					ssid = x.getEtEstacion().getBssid()!=null?x.getEtEstacion().getBssid():"Not Found";
					if(ho.equalsIgnoreCase("S")) {
						if(in.getsBssids().getBssids().get(0).get("bssid").equalsIgnoreCase(ssid)) {
							out.setCodigo(MensajesConstante.SUCCES);
							out.setIdEstacion(String.valueOf(x.getEtEstacion().getIdEstacion()));
							out.setNombreEstacion(x.getEtEstacion().getNombre());
							out.setEstacionLibre(true);
							out.setEstacionChecada(x);
							found = true;
						}
					}
					break;
				case 7:
					ho = x.getEtEstacion().getDomingoHo()!=null?x.getEtEstacion().getDomingoHo():"N";
					ssid = x.getEtEstacion().getBssid()!=null?x.getEtEstacion().getBssid():"Not Found";
					if(ho.equalsIgnoreCase("S")) {
						if(in.getsBssids().getBssids().get(0).get("bssid").equalsIgnoreCase(ssid)) {
							out.setCodigo(MensajesConstante.SUCCES);
							out.setIdEstacion(String.valueOf(x.getEtEstacion().getIdEstacion()));
							out.setNombreEstacion(x.getEtEstacion().getNombre());
							out.setEstacionLibre(true);
							out.setEstacionChecada(x);
							found = true;
						}
					}
					break;
				}
				
				String ssid = x.getEtEstacion().getBssid()!=null?x.getEtEstacion().getBssid():"Not Found";
				if(x.getEtEstacion().getEstacionLibre()!=null) {
					if(x.getEtEstacion().getEstacionLibre().equalsIgnoreCase("S")) {
						out.setCodigo(MensajesConstante.SUCCES);
						out.setIdEstacion(String.valueOf(x.getEtEstacion().getIdEstacion()));
						out.setNombreEstacion(x.getEtEstacion().getNombre());
						out.setEstacionLibre(true);
						out.setEstacionChecada(x);
						found = true;
					}
				}
				
				if(in.getsBssids().getBssids().get(0).get("bssid").equalsIgnoreCase(ssid)) {
					out.setCodigo(MensajesConstante.SUCCES);
					out.setIdEstacion(String.valueOf(x.getEtEstacion().getIdEstacion()));
					out.setNombreEstacion(x.getEtEstacion().getNombre());
					out.setEstacionLibre(true);
					out.setEstacionChecada(x);
					found = true;
				}
				
				try{     
			        double radioTierra = 6371;//en kilómetros
			        double  Latitud01  =x.getEtEstacion().getLatitud().doubleValue();   
			        double  Latitud02  =Double.parseDouble(in.getsGeolocalizacion().getLatitud());   
			        double  Longitud01 =x.getEtEstacion().getLongitud().doubleValue();    
			        double  Longitud02 =Double.parseDouble(in.getsGeolocalizacion().getLongitud());
			        double dLat = Math.toRadians(Latitud02 - Latitud01);      
			        double dLng = Math.toRadians(Longitud02 - Longitud01);
			        double sindLat = Math.sin(dLat / 2);      
			        double sindLng = Math.sin(dLng / 2);      
			        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2) * Math.cos(Math.toRadians(Latitud01)) * Math.cos(Math.toRadians(Latitud02)); 
			        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));      
			        double distancia = radioTierra * va2;      
			        
			        metros = 1000 * distancia;        
			     
				}catch ( Exception ex ){           
					out.setCodigo(MensajesConstante.ERROR_GENERAL);
					return out;           
				}
				
				if(x.getEtEstacion().getUuid()!=null && !x.getEtEstacion().getUuid().isBlank()) {
					beacons.add(x.getEtEstacion().getUuid());
				}
				if(x.getEtEstacion().getLatitud()!=null && x.getEtEstacion().getLatitud().intValue()>0) {					
					String desc = x.getEtEstacion().getNombre()!=null && !x.getEtEstacion().getNombre().isBlank()? x.getEtEstacion().getNombre(): " ";
					String zona = x.getEtEstacion().getIdZona()!=null?x.getEtEstacion().getIdZona().toString():" ";
					String xCor = x.getEtEstacion().getLatitud()!=null?x.getEtEstacion().getLatitud().toString():" ";
					String yCor = x.getEtEstacion().getLongitud()!=null?x.getEtEstacion().getLongitud().toString():" "; 
					String beac = x.getEtEstacion().getUuid()!=null && !x.getEtEstacion().getUuid().isBlank()?x.getEtEstacion().getUuid():" ";
					estacionesOut.add(desc + "|" + zona + "|" + xCor + "," + yCor + "|" + metros + "|" + beac);
				}
				
				servicio = ServiciosConstante.UNIDAD_MEDIDA_ET_SERVICE;
				Map<String, String> uMedidaVar = new HashMap<>();
				uMedidaVar.put("cveUnidad",x.getEtEstacion().getCveUnidad());
				try {
					uMed = template.getForObject(servicio, EtUnidadMedidaDto.class, uMedidaVar);
				}catch (Exception e) {
					LOG.error(in.getIdTraza() + " " + servicio + " error: " + e.toString());
					out.setCodigo(MensajesConstante.ERROR_EXECUTE_SELECT_CODE);						
					return out;
				}
				
				if(in.getPrioridad().equalsIgnoreCase("2")||in.getPrioridad().equalsIgnoreCase("3")) {
					if(uMed!=null) {
						double rango = uMed.getValorMetros().doubleValue() * x.getEtEstacion().getRango().doubleValue();
						if(rango>=metros) {
							out.setCodigo(MensajesConstante.SUCCES);
							out.setIdEstacion(String.valueOf(x.getEtEstacion().getIdEstacion()));
							out.setNombreEstacion(x.getEtEstacion().getNombre());
							out.setEstacionChecada(x);
							found = true;
						}
					}else {
						out.setCodigo(MensajesConstante.UNIDAD_MEDIDA_NO_EXISTE);
						return out;
					}
				}
				
				if(in.getPrioridad().equalsIgnoreCase("1")||in.getPrioridad().equalsIgnoreCase("3")||in.getPrioridad().equalsIgnoreCase("2")) {
					String beaconIn = in.getsBeacons().getBeacons().get(0).get("beacon");						
					if(x.getEtEstacion().getEstacionLibre()!=null) {
						if(x.getEtEstacion().getEstacionLibre().equalsIgnoreCase("S")) {
							out.setCodigo(MensajesConstante.SUCCES);
							out.setIdEstacion(String.valueOf(x.getEtEstacion().getIdEstacion()));
							out.setNombreEstacion(x.getEtEstacion().getNombre());
							out.setEstacionLibre(true);
							out.setEstacionChecada(x);
							found = true;
						}
					}
					
					if(x.getEtEstacion().getUuid()!=null?x.getEtEstacion().getUuid().equalsIgnoreCase(beaconIn):false) {
						out.setCodigo(MensajesConstante.SUCCES);
						out.setIdEstacion(String.valueOf(x.getEtEstacion().getIdEstacion()));
						out.setNombreEstacion(x.getEtEstacion().getNombre());
						out.setEstacionChecada(x);
						found = true;
					}						
				}				
				
				if(found) {
					out.setCodigo(MensajesConstante.SUCCES);
					return out;
				}
			}			
		}else {
			out.setCodigo(MensajesConstante.ESTACION_NO_EXISTE);
			return out;
		}
		
		if(in.getPrioridad().equalsIgnoreCase("1")) {
			out.setCodigo(MensajesConstante.P1_MATCH_NO_EXISTE);
		}
		
		if(in.getPrioridad().equalsIgnoreCase("2")) {
			out.setCodigo(MensajesConstante.P2_MATCH_NO_EXISTE);
		}
		
		if(in.getPrioridad().equalsIgnoreCase("3")) {
			out.setCodigo(MensajesConstante.P3_MATCH_NO_EXISTE);
		}		
		return out;
	}
}