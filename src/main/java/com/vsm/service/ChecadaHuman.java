package com.vsm.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.vsm.constant.MensajesConstante;
import com.vsm.db.entity.HuCompaniaLicencia;
import com.vsm.db.entity.Mr;
import com.vsm.db.entity.Ta155;
import com.vsm.db.service.TransSelDbService;
import com.vsm.dto.InChecadaEntradaDto;
import com.vsm.dto.InChecadaSalidaDto;
import com.vsm.dto.InRegistraChecadaDto;
import com.vsm.dto.InRootDto;
import com.vsm.dto.OutChecadaEntradaDto;
import com.vsm.dto.OutChecadaSalidaDto;
import com.vsm.dto.ResponseHumaneTime;
import com.vsm.dto.ValidaEstacionHuDto;
import com.vsm.util.CalculaFalta;
import com.vsm.util.GetFoto;
import com.vsm.util.Utils;
import com.vsm.util.ValidaEstacion;

@Service("checadaHuService")
/**
 * @Api(tags = "RegChecadaET")
 * @author rcaraveo
 *
 */
public class ChecadaHuman {
	private static final Logger LOG = LogManager.getLogger(ChecadaHuman.class.getName());
	
	@Autowired
	GetFoto fotoService;
	
	@Autowired
	Utils utilService;
	
	@Autowired
	ValidaEstacion valEstService;
	
	@Autowired
	CalculaFalta faltaService;
	
	@Autowired
	TransSelDbService dbSelService;
	
	@Autowired
	EntradaHu entradaHuService;
	
	@Autowired
	SalidaHu salidaHuService;
	
	public ResponseHumaneTime checadaHuman(@RequestBody InRegistraChecadaDto data) throws Exception {	
		ResponseHumaneTime out = new ResponseHumaneTime();	
		InRootDto in = data.getRoot();
				
		/**
		//CONSULTA LA VALIDES DE LA LICENCIA DE LA CIA 
		Map<String, String> validaLicParam = new HashMap<>();		
		validaLicParam.put("tipoLic", "T");
		validaLicParam.put("ciaNum", String.valueOf(in.getNumCia()));
		String servicio = RegChecadaHumanConstante.VALIDA_LICENCIA_SERVICE;
				
		//validacion de licencia BEGIN
		ValidaLicenciaDto cia = template.getForObject(servicio, ValidaLicenciaDto.class, validaLicParam);
		if(!cia.getCodigo().equalsIgnoreCase(RegChecadaHumanConstante.SUCCES)) {
			LOG.error(in.getIdTraza() + " " + "Codigo error en validar Licencia: " + cia.getCodigo() + " - Parametros: " + validaLicParam.toString());
			out.setCodigo(cia.getCodigo());
			return out;
		}**/
		
		//OBTIENE LA CAOMPANIA Y HU_CAT_CO_GRAL DE BD
		HuCompaniaLicencia ciaLic = dbSelService.getHuCiaLicByCia(Long.parseLong(in.getNumeroCompania()));
		if (ciaLic == null) {
			LOG.error(data.getIdTraza() + " " + "Error al consultar HU_COMPANIA/HU_CAT_CO_GRAL cia: " + in.getNumeroCompania());
			out.setCodigo(MensajesConstante.ERROR_EXECUTE_SELECT_CODE);
			return out;
		}	

		/**
		//CONSULTA EL SALDO DE LA COMPANIA
		LocalDate ld = LocalDate.now();
		String año = String.valueOf(ld.getYear());
		String fechaIni = año + "-01-01";
		String fechaFin = año + "-12-31";
		String fecha = in.getFechaChecada().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		Map<String, String> numChecadasParam = new HashMap<>();		
		numChecadasParam.put("numCia", String.valueOf(in.getNumCia()));
		numChecadasParam.put("fehcaIni", fechaIni);
		numChecadasParam.put("fechaFin", fechaFin);
		numChecadasParam.put("numEmp", String.valueOf(in.getNumEmp()));
		numChecadasParam.put("fecha", fecha);
		servicio = RegChecadaHumanConstante.NUMERO_CHECADAS_SERVICE;
		
		Long checadas = template.getForObject(servicio, Long.class, numChecadasParam);
		if(checadas<0) {
			LOG.error(in.getIdTraza() + " " + "Sin saldo en RN: " + numChecadasParam.toString());
			out.setCodigo(RegChecadaHumanConstante.ERROR_SALDO);
			return out;
		}
		
		BigDecimal saldo = BigDecimal.ZERO;
		for(HuClienteConnectDto x:cia.getHuClienteConnectDto()) {
			saldo  = saldo.add(x.getChecadaDias()!=null?x.getChecadaDias():BigDecimal.ZERO);
		}
		
		if(checadas>saldo.longValue()) {
			LOG.error(in.getIdTraza() + " " + "checadas: " + checadas + "  --  saldo: " + saldo);
			out.setCodigo(RegChecadaHumanConstante.ERROR_SALDO);
			return out;
		}**/
		
		//VALIDACION MAESTRO DE RELOJ
		List<Mr> mrsDto = dbSelService.getActiveMr(Long.parseLong(in.getNumeroCompania()), Long.parseLong(in.getEmpleado()));
		if(mrsDto==null || mrsDto.isEmpty()) {
			LOG.error(data.getIdTraza() + " " + "Sin coincidencia en MR: cia:" + in.getNumeroCompania() + in.getEmpleado());
			out.setCodigo(MensajesConstante.ERROR_MR);
			return out;
		}
		
		//CHECADA HUMAN DE TIPO ENTRADA
		ValidaEstacionHuDto estacion = new ValidaEstacionHuDto();
		if(in.getTipoChecada().equalsIgnoreCase("E")) {
			InChecadaEntradaDto entradaIn = new InChecadaEntradaDto();			
			entradaIn.setHoraChecada(in.getFechaHoraChecada());
			entradaIn.setMrDto(mrsDto.get(0));
			entradaIn.setNumCia(Long.parseLong(in.getNumeroCompania()));
			entradaIn.setNumEmp(Long.parseLong(in.getEmpleado()));
			entradaIn.setTipoChecada(in.getTipoChecada());
			entradaIn.setIdTraza(data.getIdTraza());
			entradaIn.setCiaHu(ciaLic);
			OutChecadaEntradaDto entradaOut = entradaHuService.entrada(entradaIn, in);			
			estacion = entradaOut.getEstOut()!=null?entradaOut.getEstOut():new ValidaEstacionHuDto(new Ta155());
			out.setEstacion(estacion.getTa155().getTa155Descr()!=null?estacion.getTa155().getTa155Descr():"");
			out.setEstOut(estacion);
			if(!entradaOut.getCodigo().equalsIgnoreCase(MensajesConstante.SUCCES)) {				
				out.setCodigo(entradaOut.getCodigo());
				return out;
			}			
		}
		
		//CHECADA HUMAN DE TIPO SALIDA
		if(in.getTipoChecada().equalsIgnoreCase("S")) {
			InChecadaSalidaDto salidaIn = new InChecadaSalidaDto();
			salidaIn.setHoraChecada(in.getFechaHoraChecada());
			salidaIn.setMrDto(mrsDto.get(0));
			salidaIn.setNumCia(Long.parseLong(in.getNumeroCompania()));
			salidaIn.setNumEmp(Long.parseLong(in.getEmpleado()));
			salidaIn.setTipoChecada(in.getTipoChecada());
			salidaIn.setIdTraza(data.getIdTraza());
			salidaIn.setCiaHu(ciaLic);
			OutChecadaSalidaDto salidaOut = salidaHuService.salida(salidaIn, in);
			estacion = salidaOut.getEstOut()!=null?salidaOut.getEstOut():new ValidaEstacionHuDto(new Ta155());
			out.setEstacion(estacion.getTa155().getTa155Descr()!=null?estacion.getTa155().getTa155Descr():"");
			out.setEstOut(estacion);
			if(!salidaOut.getCodigo().equalsIgnoreCase(MensajesConstante.SUCCES)) {				
				out.setCodigo(salidaOut.getCodigo());
				return out;
			}			
			/**try {
				faltaService.calculaSalida(salidaIn, lastMoveByDate);
			} catch (InterruptedException e) {
				LOG.error(in.getIdTraza() + " " + e.getMessage());
			}**/
		}		
		out.setCodigo(MensajesConstante.SUCCES);		
		return out;
	}
}