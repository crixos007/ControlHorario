package com.vsm.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.vsm.db.entity.HuCompaniaLicencia;
import com.vsm.db.entity.Mr;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InChecadaSalidaDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	long numCia;
	long numEmp;
	LocalDateTime horaChecada;
	String tipoChecada;
	String Estacion;
	Mr mrDto;
	
	String idTraza; 
	HuCompaniaLicencia ciaHu;
}