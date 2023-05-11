package com.vsm.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Checada implements Serializable {
	private static final long serialVersionUID = 1L;
	
	String  prioridad;
	String  horaChecada;
	String  tipoChecada;
}