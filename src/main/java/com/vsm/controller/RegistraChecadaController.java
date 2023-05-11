package com.vsm.controller;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import com.vsm.constant.MensajesConstante;
import com.vsm.dto.InRegistraChecadaDto;
import com.vsm.dto.OutRegistraChedadaDto;
import com.vsm.dto.OutRootDto;
import com.vsm.service.RegistraChecada;

@CrossOrigin(origins = "*") 
@RestController()
@RequestMapping("/registraChecada")
/**
 * @Api(tags = "HumaneTime Api GateWay")
 * @author rcaraveo
 *
 */
public class RegistraChecadaController {
	private static final Logger LOG = LogManager.getLogger(RegistraChecadaController.class.getName());
	
	@Autowired
	RegistraChecada servicio;
	
	/**
	 * @ApiOperation(value = "Registrar Checada de Human eLand Unificado")
	 * @param in
	 * @return ResponseEntity<OutRegistraChedadaDto>
	 * @throws Exception 
	 */
	@PostMapping(value="/unificado", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OutRegistraChedadaDto> regChqDesvinculado (@RequestBody InRegistraChecadaDto in) {
		//SE CREA EL ID DE LA OPERACION PARA TRAZAR LA MISMA
		Date date = new Date();		
    	String idTraza = (int)(Math.random()*30+1) + in.getRoot().getEmpleado() + in.getRoot().getNumeroCompania() + date.getTime() + "_HC";
		LOG.info(idTraza +" HUMAN-CHECADA-BEGIN FOR: " + in.getRoot().getEmpleado() + " | " + in.getRoot().getNumeroCompania() + " | " + in.getRoot().getFechaHoraChecada());		
		OutRegistraChedadaDto out = new OutRegistraChedadaDto();					
		in.setIdTraza(idTraza);
		
		//LOGICA PARA REALIZAR LAS CHECADAS
		try {	
			try {
				out = servicio.regChqUnificado(in);
			}catch (Exception e) {
				LOG.info(idTraza +" HUMAN-CHECADA-ERROR FOR: " + in.getRoot().getEmpleado() + " | " + in.getRoot().getNumeroCompania() + " | " + in.getRoot().getFechaHoraChecada() + " error: " + e.getMessage());
				throw new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE);
			}
			LOG.info(idTraza +" HUMAN-CHECADA-END FOR: " + in.getRoot().getEmpleado() + " | " + in.getRoot().getNumeroCompania() + " | " + in.getRoot().getFechaHoraChecada());
			return new ResponseEntity<OutRegistraChedadaDto>(out,HttpStatus.OK);
		}
		catch(HttpStatusCodeException exception) {
			LOG.info(idTraza +" HUMAN-CHECADA-ERROR FOR: " + in.getRoot().getEmpleado() + " | " + in.getRoot().getNumeroCompania() + " | " + in.getRoot().getFechaHoraChecada() + " error: " + exception.getMessage());
			HttpHeaders headers=new HttpHeaders();
			OutRootDto rootOut = new OutRootDto();
			headers.add("error", MensajesConstante.DESCRIPCION_ERROR_GENERAL);
			headers.add("errorDesc", exception.getResponseBodyAsString());			
			rootOut.setCodigo(MensajesConstante.ERROR_GENERAL);	
			rootOut.setMensaje(MensajesConstante.DESCRIPCION_ERROR_GENERAL);
			out.setRoot(rootOut);
			return new ResponseEntity<OutRegistraChedadaDto>(out,headers,exception.getStatusCode());
		}		
	}
}