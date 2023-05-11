package com.vsm.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.vsm.constant.MensajesConstante;
import com.vsm.constant.ServiciosConstante;
import com.vsm.db.entity.HuCatToGral;
import com.vsm.db.entity.HuEmpl;
import com.vsm.db.service.TransSelDbService;
import com.vsm.dto.OutEmpleadoDto;
import com.vsm.lib.dto.checada.UserAutDto;
import com.vsm.lib.dto.etime.EtUsuarioDto;
import com.vsm.util.ConsultaEmpleado;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@CrossOrigin(origins = "*")
@RestController()
@RequestMapping("/Autenticacion")
/**
 * @Api(tags = "Autenticacion")
 * @author rcaraveo
 *
 */
public class UserController {
	private static final Logger LOG = LogManager.getLogger(UserController.class.getName());
	EtUsuarioDto respEmpEt;
	
	@Autowired
	RestTemplate template;
	
	@Autowired
	ConsultaEmpleado empleadoService;
	
	@Autowired
	TransSelDbService dbService;
	
	@GetMapping("VersionDes")
	public String getDesVersion() {
		LOG.info("getDesVersion para Aplicativo Pandora en desarrollo: " + ServiciosConstante.VERSION_DEV);
		return "getDesVersion para Aplicativo Pandora: " + ServiciosConstante.VERSION_DEV;
	}
	
	@PostMapping("empleado")
	public OutEmpleadoDto login(@RequestParam("user") String username, @RequestParam("cia") String cia) {
		OutEmpleadoDto out = new OutEmpleadoDto();
		String token = getJWTToken(username, cia);
		if(!token.startsWith("Bearer")){
			out.setError(true);
			out.setErrorMessage(token);
			return out;
		}
		UserAutDto user = new UserAutDto();
		user.setUser(username);
		user.setToken(token);		
		try {
			out = empleadoService.getEmpleado(Long.parseLong(cia), Long.parseLong(username), "1");
		}catch (Exception e) {
			LOG.error(e.getMessage());
			out.setError(true);
			out.setErrorMessage(MensajesConstante.DESCRIPCION_ERROR_GENERAL);
			return out;
		}
		out.setToken(token);
		return out;
	}
	
	@PostMapping("user")
	public OutEmpleadoDto token(@RequestParam("user") String username, @RequestParam("cia") String cia) {
		OutEmpleadoDto out = new OutEmpleadoDto();
		String token = getJWTToken(username, cia);
		if(!token.startsWith("Bearer")){
			out.setError(true);
			out.setErrorMessage(token);
			return out;
		}
		out.setToken(token);
		out.setUser(username);
		return out;
	}

	private String getJWTToken(String numEmp, String numCia) {
		String servicio = ServiciosConstante.GET_EMP_ET_BY_NUMBER_SERVICE;
		String username = "";	
		HuEmpl respEmp = dbService.getHuEmplById(Long.parseLong(numCia), Long.parseLong(numEmp));
		if(respEmp == null) {			
			Map<String, Long> getEmpEtParam = new HashMap<>();
			getEmpEtParam.put("ciaNum", Long.parseLong(numCia));
			getEmpEtParam.put("empNum", Long.parseLong(numEmp));
			try {
				respEmpEt = template.getForObject(servicio, EtUsuarioDto.class, getEmpEtParam);
			}catch (Exception e) {
				return e.getMessage();
			}
			if(respEmpEt != null) {
				username = String.valueOf(respEmpEt.getNombre());
			}else {
				return(MensajesConstante.EMPLEADO_NO_EXISTE);
			}
		}else {
			username = respEmp.getNombre();
		}
		
		//CREA EL TOKEN
		long tokenExp = 0; 
		List<HuCatToGral> listGral = dbService.getHuCatToGralByParam("TOKEN_EXPIRATION");
		for (int i = 0; i < listGral.size(); i++) {
			tokenExp = listGral.get(i).getValorNumerico()!=null?listGral.get(i).getValorNumerico().longValue():5000;
		}		
		String secretKey = "ChecadaUni";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");		
		String token = Jwts.builder().setId("venturssotfJWT")
									 .setSubject(username)
									 .claim("authorities", grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
									 .setIssuedAt(new Date(System.currentTimeMillis()))
									 .setExpiration(new Date(System.currentTimeMillis() + tokenExp))
									 .signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
									 .compact();
		return "Bearer " + token;
	}
}