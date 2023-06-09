package com.vsm.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CompareRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	String company;
	String employeeNo;
	String hash;
	String model;
	String photo;
}