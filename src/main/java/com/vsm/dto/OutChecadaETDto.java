package com.vsm.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OutChecadaETDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	boolean isError;
	String  errorMessage;
	
	String codigo;
	String estacion;
	
	ValidaEstacionDto estacionesAudit;
}