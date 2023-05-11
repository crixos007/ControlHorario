package com.vsm.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vsm.constant.MensajesConstante;
import com.vsm.constant.ServiciosConstante;
import com.vsm.db.entity.HuCompaniaLicencia;
import com.vsm.db.entity.HuEmpl;
import com.vsm.db.entity.Ta155;
import com.vsm.db.service.TransSelDbService;
import com.vsm.dto.OutEmpleadoDto;
import com.vsm.lib.dto.etime.EtCiaDto;
import com.vsm.lib.dto.etime.EtEmpEstDto;
import com.vsm.lib.dto.etime.EtUsuarioDto;

@Service("consultaEmpleadoService")
/**
 * @Api(tags = "ConsultaEmpleado")
 * @author rcaraveo
 *
 */
public class ConsultaEmpleado {
	private static final Logger LOG = LogManager.getLogger(ConsultaEmpleado.class.getName());
	ResponseEntity<String[]> responseBeacon;
	EtCiaDto ciaEt;
	EtUsuarioDto empEt;
	EtEmpEstDto[] responseEstaciones;
	
	@Autowired
	TransSelDbService dbSelService;
	
	@Autowired
	RestTemplate template;
	
	@Autowired
	Utils utilsService;
	
	@Autowired
	GetFoto fotoService;

	public OutEmpleadoDto getEmpleado (long numCia, long numEmp, String fechaFoto) throws Exception {
		OutEmpleadoDto out = new OutEmpleadoDto();		
		
		/**
		 * VALIDACION DE CAMPOS DE ENTRADA BEGIN
		 */
		if(numCia <= 0) {			
			LOG.error("El campo ciaNom es incorrecto");
			out.setError(true);
			out.setErrorMessage("El campo ciaNom es incorrecto");
			out.setCodigo(MensajesConstante.COMPANIA_NULO);
			return out;
		}		
		if(numEmp <= 0) {			
			LOG.error("El campo numEmp es incorrecto");
			out.setError(true);
			out.setErrorMessage("El campo numEmp es incorrecto");
			out.setCodigo(MensajesConstante.EMPLEADO_NULO);
			return out;
		}			
		
		/**
		 * VALIDAMOS SI LA COMPAÑIA ES DE HUMAN
		 */
		HuCompaniaLicencia responseCia = dbSelService.getHuCiaLicByCia(numCia);
		if(responseCia!=null && responseCia.getNumeroCompania()>0) {			
			//CONSULTA EMPLEADO EN HU_EMPLS
			HuEmpl respEmp = dbSelService.getHuEmplById(numCia, numEmp);
			if(respEmp == null || respEmp.getNombre() == null) {			
				out.setError(true);
				out.setCodigo(MensajesConstante.EMPLEADO_NO_EXISTE);
				out.setErrorMessage("sin coincidencia en HU_EMPLS");
				return out;
			}
			
			//CONSULTA LA BEACON EN HU_EMPLS_EST_CHECADO y TA155									
			List<Ta155> estaciones = dbSelService.getEstaciones(numCia, numEmp);
			if(estaciones!=null) {
				List<Map<String,String>> beacons = new ArrayList<Map<String,String>>();
				for(Ta155 x : estaciones) {
					if(!x.getTa155Beacon().trim().isEmpty()) {
						Map<String,String> beacon = new HashMap<>();
						beacon.put("id", x.getTa155Beacon());
						beacons.add(beacon);
					}
				}
				out.setBeacons(beacons);
			}
			
			//OBTIENE LA FOTO DEL EMPLEADO
			Map<String, String> mapfoto = fotoService.GetFotoHU(numCia, numEmp);
			if(mapfoto != null && !mapfoto.get("foto").isEmpty() && !mapfoto.get("foto").trim().startsWith("File not found")) {
				out.setFechaFoto(mapfoto.get("fechaFoto"));
				if(!fechaFoto.equals(mapfoto.get("fechaFoto"))) {	
					out.setFoto(mapfoto.get("foto"));
				}	
			}
			
			out.setCodigo(MensajesConstante.SUCCES);
			out.setError(false);
			out.setErrorMessage(MensajesConstante.SUCCES);
			out.setNombre(respEmp.getApellPat() + " " + respEmp.getApellMat() + " " + respEmp.getNombre());
			out.setNumeroCompania(String.valueOf(responseCia.getNumeroCompania()));
			return out;
			
		//Valida si la compañia pertenece a ETime
		}else{			
			Map<String, String> getEtCiaParam = new HashMap<>();
			getEtCiaParam.put("cveCia", String.valueOf(numCia));
			String servicio = ServiciosConstante.GET_CIA_ET_BY_NUMBER_SERVICE;
			try {
				ciaEt = template.getForObject(servicio, EtCiaDto.class, getEtCiaParam);
			}catch (Exception e) {
				LOG.error(servicio + " error ET_COMPANIA: " + e.toString());
				out.setError(true);
				out.setCodigo(MensajesConstante.ERROR_EXECUTE_SELECT_CODE);
				out.setErrorMessage(MensajesConstante.ERROR_EXECUTE_SELECT_MSJ);
				return out;
			}
			//Retorna error compania sin coincidencia
			if(ciaEt == null || ciaEt.getCve() == null || ciaEt.getCve().isBlank()) {
				LOG.error("Sin coincidencia para la compania en BD");
				out.setError(true);
				out.setErrorMessage("Sin coincidencia para la compania en BD");
				out.setCodigo(MensajesConstante.COMPANIA_NO_EXISTE);
				return out;
			}
			
			/**
			 * procesa empleado Etime BEGIN
			 */
			servicio = ServiciosConstante.GET_EMP_ET_BY_NUMBER_SERVICE;
			Map<String, Long> getEmpParam = new HashMap<>();
			getEmpParam.put("ciaNum", ciaEt.getIdCia());
			getEmpParam.put("empNum", numEmp);
			try {
				empEt = template.getForObject(servicio, EtUsuarioDto.class, getEmpParam);
			}catch (Exception e) {
				LOG.error(servicio + " error ET_USUARIO: " + e.toString());
				out.setError(true);
				out.setCodigo(MensajesConstante.ERROR_EXECUTE_SELECT_CODE);
				out.setErrorMessage(MensajesConstante.ERROR_EXECUTE_SELECT_MSJ);
				return out;
			}
			//Retorna error empleado sin coincidencia 
			if(empEt == null || empEt.getNombre() == null || empEt.getNombre().isBlank()) {
				LOG.error("Sin coincidencia para el empleado en BD");
				out.setError(true);
				out.setErrorMessage("Sin coincidencia para el empleado en BD");
				out.setCodigo(MensajesConstante.EMPLEADO_NO_EXISTE);
				return out;
			}
			
			servicio = ServiciosConstante.GET_ESTACION_ET_SERVICE;
			Map<String, Long> estacionesVar = new HashMap<>();
			estacionesVar.put("ciaNum", ciaEt.getIdCia());
			estacionesVar.put("empNum", numEmp);
			try {
				responseEstaciones = template.getForObject(servicio, EtEmpEstDto[].class, estacionesVar);
			}catch (Exception e) {
				LOG.error(servicio + " error ET_ESTACION: " + e.toString());
				out.setError(true);
				out.setCodigo(MensajesConstante.ERROR_EXECUTE_SELECT_CODE);
				out.setErrorMessage(MensajesConstante.ERROR_EXECUTE_SELECT_MSJ);
				return out;
			}			
			//Se consulta la estacion
			List<EtEmpEstDto> estaciones = Arrays.asList(responseEstaciones);			
			if(estaciones!=null && estaciones.size()>0) {
				List<Map<String,String>> beacons = new ArrayList<Map<String,String>>();
				for(EtEmpEstDto x : estaciones) {
					if(!x.getEtEstacion().getUuid().trim().isEmpty()) {
						Map<String,String> beacon = new HashMap<>();
						beacon.put("id", x.getEtEstacion().getUuid());
						beacons.add(beacon);
					}
				}
				out.setBeacons(beacons);
			}
			//Se consulta la foto
			Map<String, String> mapfoto = fotoService.GetFotoET(ciaEt.getIdCia(), numEmp);
			if(mapfoto != null && !mapfoto.get("foto").isEmpty() && !mapfoto.get("foto").trim().startsWith("File not found")) {					
				out.setFechaFoto(mapfoto.get("fechaFoto"));
				if(!fechaFoto.equals(mapfoto.get("fechaFoto"))) {	
					out.setFoto(mapfoto.get("foto"));
				}
			}
			
			/**
			 * Retorno exito empleado ET
			 */
			out.setCodigo(MensajesConstante.SUCCES);
			out.setError(false);
			out.setErrorMessage(MensajesConstante.SUCCES);
			out.setNombre(empEt.getApPaterno() + " " + empEt.getApMaterno() + " " + empEt.getNombre());
			out.setNumeroCompania(String.valueOf(ciaEt.getIdCia()));
			return out;
		}
	}
}