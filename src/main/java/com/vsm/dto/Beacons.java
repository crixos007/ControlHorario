package com.vsm.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Beacons implements Serializable {
	private static final long serialVersionUID = 1L;
	
	List<Map <String,String>> beacons;
}