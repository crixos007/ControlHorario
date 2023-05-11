package com.vsm.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InRegistraChecadaDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	String idTraza;
	InRootDto root;
}