package com.vsm.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SGeolocalizacion implements Serializable {
	private static final long serialVersionUID = 1L;
	
	String latitud;
	String longitud;
	
	public SGeolocalizacion(String latitud, String longitud) {
		super();
		this.latitud = latitud;
		this.longitud = longitud;
	}
}