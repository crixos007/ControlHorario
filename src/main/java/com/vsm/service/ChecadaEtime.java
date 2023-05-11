package com.vsm.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.vsm.constant.MensajesConstante;
import com.vsm.constant.ServiciosConstante;
import com.vsm.dto.CompareRequest;
import com.vsm.dto.CompareResponse;
import com.vsm.dto.InChecadaETDto;
import com.vsm.dto.OutChecadaETDto;
import com.vsm.dto.ValidaEstacionDto;
import com.vsm.lib.dto.etime.EtChecadaDto;
import com.vsm.lib.dto.etime.EtChecadaPKDto;
import com.vsm.lib.dto.etime.EtCiaDto;
import com.vsm.lib.dto.etime.EtUsuarioDto;
import com.vsm.lib.dto.human.EtEmplsAuditEtimeDto;
import com.vsm.lib.dto.human.EtEmplsAuditEtimePKDto;
import com.vsm.util.GetFoto;
import com.vsm.util.Utils;
import com.vsm.util.ValidaEstacion;

@Service("checadaEtService")
/**
 * @Api(tags = "RegChecadaET")
 * @author rcaraveo
 *
 */
public class ChecadaEtime {
	private static final Logger LOG = LogManager.getLogger(ChecadaEtime.class.getName());
	EtUsuarioDto emp;
	EtCiaDto cia;
	EtUsuarioDto adm;
	EtChecadaDto checada;
		
	@Autowired
	GetFoto fotoService;
	
	@Autowired
	Utils utilService;
	
	@Autowired
	ValidaEstacion valEstService;
	
	@Autowired
	RestTemplate template;
	
	public OutChecadaETDto saveDesvinculado (@RequestBody InChecadaETDto in) throws Exception {
		OutChecadaETDto out = new OutChecadaETDto();
		int idChecada = 1;
		
		String servicio = ServiciosConstante.GET_CIA_ET_BY_NUMBER_SERVICE;
		Map<String, Long> numCiaParam = new HashMap<>();
		numCiaParam.put("idCia", Long.parseLong(in.getNumeroCompania()));
		try {
			cia = template.getForObject(servicio, EtCiaDto.class, numCiaParam);
		}catch (Exception e) {
			LOG.error(in.getIdTraza() + " " + servicio + " error: " + e.toString());
			out.setError(true);
			out.setCodigo(MensajesConstante.ERROR_EXECUTE_SELECT_CODE);
			out.setErrorMessage(MensajesConstante.ERROR_EXECUTE_SELECT_MSJ);			
			return out;
		}
		
		if(cia!=null) {
			servicio = ServiciosConstante.GET_EMP_ET_BY_NUMBER_SERVICE;
			Map<String, Long> getEmpParam = new HashMap<>();
			getEmpParam.put("ciaNum", cia.getIdCia());
			getEmpParam.put("empNum", Long.parseLong(in.getEmpleado()));
			try {
				emp = template.getForObject(servicio, EtUsuarioDto.class, getEmpParam);
			}catch (Exception e) {
				LOG.error(in.getIdTraza() + " " + servicio + " error: " + e.toString());
				out.setError(true);
				out.setCodigo(MensajesConstante.ERROR_EXECUTE_SELECT_CODE);
				out.setErrorMessage(MensajesConstante.ERROR_EXECUTE_SELECT_MSJ);			
				return out;
			}			
			
			if(emp != null) {				
				servicio = ServiciosConstante.EMPLEADO_ADM_ET_SERVICE;
				Map<String, Long> getAdmParam = new HashMap<>();
				getAdmParam.put("idCia", cia.getIdCia());
				try {
					adm = template.getForObject(servicio, EtUsuarioDto.class, getAdmParam);
				}catch (Exception e) {
					LOG.error(in.getIdTraza() + " " + servicio + " error: " + e.toString());
					out.setError(true);
					out.setCodigo(MensajesConstante.ERROR_EXECUTE_SELECT_CODE);
					out.setErrorMessage(MensajesConstante.ERROR_EXECUTE_SELECT_MSJ);			
					return out;
				}
				
				if(adm != null) {
					servicio = ServiciosConstante.CHECADA_ET_SERVICE;
					Map<String, String> getChecadaParam = new HashMap<>();
					getChecadaParam.put("idCia", in.getNumeroCompania());
					getChecadaParam.put("idEmp", in.getEmpleado());
					getChecadaParam.put("fechaIni", in.getFechaHoraChecada().substring(0, 11) + "00:00:00");
					getChecadaParam.put("fechFin", in.getFechaHoraChecada().substring(0, 16) + ":00");
					try {
						checada = template.getForObject(servicio, EtChecadaDto.class, getChecadaParam);
					}catch (Exception e) {
						LOG.error(in.getIdTraza() + " " + servicio + " error: " + e.toString());
						out.setError(true);
						out.setCodigo(MensajesConstante.ERROR_EXECUTE_SELECT_CODE);
						out.setErrorMessage(MensajesConstante.ERROR_EXECUTE_SELECT_MSJ);			
						return out;
					}
					
					if(checada == null && !in.getTipoChecada().equalsIgnoreCase("E")) { 
						out.setError(true);
						out.setCodigo(MensajesConstante.ENTRADA_NO_EXISTE);
						return out;
					}
					
					if(checada != null && in.getTipoChecada().equalsIgnoreCase("E") 
					   && checada.getId().getTipo().equalsIgnoreCase("E")) { 
						out.setError(true);
						out.setCodigo(MensajesConstante.ENTRADA_DUPLICADA);
						return out;
					}
					
					if(checada != null && in.getTipoChecada().equalsIgnoreCase("S") 
					   && checada.getId().getTipo().equalsIgnoreCase("S")) { 
						out.setError(true);
						out.setCodigo(MensajesConstante.SALIDA_DUPLICADA);
						return out;
					}
										
					if(checada != null) { 
						LocalDateTime fechaIn = LocalDateTime.parse(in.getFechaHoraChecada().replace("T", " ").substring(0, 16), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")); 
						if(fechaIn.isEqual(checada.getId().getFecha().truncatedTo(ChronoUnit.MINUTES))) {
							out.setError(true);
							out.setCodigo(MensajesConstante.REGISTRO_DUPLICADO);
							return out;
						}
					}
					
					if(checada != null) {
						idChecada = (int) checada.getId().getIdChecada() + 1;
					}
					
					ValidaEstacionDto valEst = valEstService.valEstacionEt(in);
					if(valEst.getCodigo()==MensajesConstante.SUCCES) {
						out.setEstacionesAudit(valEst);
						Map<String, String> mapfoto = fotoService.GetFotoET(Long.parseLong(in.getNumeroCompania()), Long.parseLong(in.getEmpleado()));
						servicio = ServiciosConstante.COMPARA_FOTO_SERVICE;
						CompareRequest req =  new CompareRequest();
						req.setCompany(in.getNumeroCompania());
						req.setEmployeeNo(in.getEmpleado());
						req.setModel(mapfoto.get("foto"));
						req.setPhoto(in.getFoto());
						req.setHash(utilService.encryptPassword(mapfoto.get("foto")));
						CompareResponse res = new CompareResponse();
						res.setRes(false);
						try {
							res = template.postForObject(servicio, req, CompareResponse.class);
						}catch (Exception e) {
							LOG.error(in.getIdTraza() + " " + servicio + " error: " + e.toString());
							out.setError(true);
							out.setCodigo(MensajesConstante.ERROR_COMPARA_FOTO);
							return out;
						}							
						
						if(res.isRes()) {
							servicio = ServiciosConstante.REGISTRA_CHECADA_ET_SERVICE;
							EtChecadaDto regChecada = new EtChecadaDto();
							EtChecadaPKDto regChecadaPk = new EtChecadaPKDto();							
							regChecadaPk.setIdChecada(idChecada);
							regChecadaPk.setIdCia(cia.getIdCia());
							regChecadaPk.setIdEmpleado(Long.parseLong(in.getEmpleado()));
							regChecadaPk.setIdEstacion(Long.valueOf(valEst.getIdEstacion()));
							regChecadaPk.setTipo(in.getTipoChecada());
							regChecadaPk.setFecha(LocalDateTime.parse(in.getFechaHoraChecada().substring(0, 16).replace("T", " "), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));							
							regChecada.setEstatus("A");
							regChecada.setId(regChecadaPk);
							
							String guardarChecada = "";
							try {
								guardarChecada = template.postForObject(servicio, regChecada, String.class);
							}catch (Exception e) {
								LOG.error(in.getIdTraza() + " " + servicio + " error: " + e.toString());
								out.setError(true);
								out.setCodigo(MensajesConstante.ERROR_EXECUTE_INSERT_CODE);
								out.setErrorMessage(MensajesConstante.ERROR_EXECUTE_INSERT_MSJ);
								return out;
							}
							
							if(valEst.isEstacionLibre()) {
								servicio = ServiciosConstante.REGISTRA_AUDITORIA_ESTACION_SERVICE;
								EtEmplsAuditEtimeDto regEstacion = new EtEmplsAuditEtimeDto();
								EtEmplsAuditEtimePKDto regEstacionPk = new EtEmplsAuditEtimePKDto();								
								regEstacionPk.setIdCia(cia.getIdCia());
								regEstacionPk.setIdEmp(Long.parseLong(in.getEmpleado()));
								regEstacionPk.setId(String.valueOf(regChecadaPk.getIdChecada()));
								regEstacion.setFechaChecada(LocalDateTime.parse(in.getFechaHoraChecada().substring(0, 16).replace("T", " "), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
								regEstacion.setFechaMov(LocalDateTime.now());
								regEstacion.setLatitud(new BigDecimal(in.getsGeolocalizacion().getLatitud()));
								regEstacion.setLongitud(new BigDecimal(in.getsGeolocalizacion().getLongitud()));
								regEstacion.setStatus("A");
								regEstacion.setTipo(in.getTipoChecada());
								regEstacion.setIdEstacion(valEst.getIdEstacion());
								regEstacion.setId(regEstacionPk);
								
								String guardarEstacion = "";
								try {
									guardarEstacion = template.postForObject(servicio, regEstacion, String.class);
								}catch (Exception e) {
									LOG.error(in.getIdTraza() + " " + servicio + " error: " + e.toString());									
								}
								if(guardarEstacion.equalsIgnoreCase("true")) {
									out.setError(false);
									out.setCodigo(MensajesConstante.SUCCES);
									out.setEstacion(valEst.getIdEstacion());
								}else {
									out.setError(true);
									out.setCodigo(MensajesConstante.ERROR_EXECUTE_INSERT_CODE);
									out.setErrorMessage(MensajesConstante.ERROR_EXECUTE_INSERT_MSJ);
									return out;
								}
							}
							
							if(guardarChecada.equalsIgnoreCase("true")) {
								out.setError(false);
								out.setCodigo(MensajesConstante.SUCCES);
								out.setEstacion(valEst.getNombreEstacion());
							}else {
								out.setError(true);
								out.setCodigo(MensajesConstante.ERROR_EXECUTE_INSERT_CODE);
								out.setErrorMessage(MensajesConstante.ERROR_EXECUTE_INSERT_MSJ);
								return out;
							}
						}else {
							out.setError(true);
							out.setCodigo(MensajesConstante.ERROR_COMPARA_FOTO);							
							return out;
						}
					}else {
						out.setError(true);
						out.setCodigo(valEst.getCodigo());
						return out;
					}
				}else {
					out.setError(true);
					out.setCodigo(MensajesConstante.ADMINISTRADOR_NO_EXISTE);
					return out;
				}
			}else {
				out.setError(true);
				out.setCodigo(MensajesConstante.EMPLEADO_NO_EXISTE);
				return out;
			}
		}else {
			out.setError(true);
			out.setCodigo(MensajesConstante.COMPANIA_NO_EXISTE);
			return out;
		}
		
		return out;
	}
}