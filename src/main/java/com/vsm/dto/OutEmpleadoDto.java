package com.vsm.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OutEmpleadoDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	boolean isError;
	String  errorMessage;
	
	String codigo;
	String nombre;
	String numeroCompania;	
	String foto;
	String fechaFoto;	
	List<Map<String,String>> beacons;
	
	String token;
	String user;
}