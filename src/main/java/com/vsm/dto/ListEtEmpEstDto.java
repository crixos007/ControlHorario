package com.vsm.dto;

import java.io.Serializable;
import java.util.List;

import com.vsm.lib.dto.etime.EtEmpEstDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ListEtEmpEstDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<EtEmpEstDto> EtEmpEsts;
}