package com.vsm.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SBssids implements Serializable {
	private static final long serialVersionUID = 1L;
	
	List<String> bssid;
}