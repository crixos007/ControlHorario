package com.vsm.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CompareResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	boolean res;
	int error;
	double time;
}