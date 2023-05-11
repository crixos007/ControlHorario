package com.vsm.dto;

import java.io.Serializable;
import java.util.List;

import com.vsm.db.entity.Ta155;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ValidaEstacionHuDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	String codigo;
	Ta155 ta155;
	List<String> beacons;
	List<String> estaciones;
		
	public ValidaEstacionHuDto(Ta155 ta155) {
		super();
		this.ta155 = ta155;
	}	
}