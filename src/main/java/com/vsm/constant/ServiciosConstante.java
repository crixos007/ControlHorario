package com.vsm.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("SvcConstantes")
public class ServiciosConstante {
	public static String VERSION_DEV = "version en desarrollo - 0.0.4";
	
	public static String GET_CIA_ET_BY_NUMBER_SERVICE; 
	@Value("${GET_CIA_ET_BY_NUMBER}")
    public void GET_CIA_ET_BY_NUMBER(String GET_CIA_ET_BY_NUMBER) {
		GET_CIA_ET_BY_NUMBER_SERVICE = GET_CIA_ET_BY_NUMBER;
    }

	public static String GET_ESTACION_ET_SERVICE; 
	@Value("${GET_ESTACION_ET}")
    public void GET_ESTACION_ET(String GET_ESTACION_ET) {
		GET_ESTACION_ET_SERVICE = GET_ESTACION_ET;
    }
	
	public static String GET_EMP_ET_BY_NUMBER_SERVICE; 
	@Value("${GET_EMP_ET_BY_NUMBER}")
    public void GET_EMP_ET_BY_NUMBER(String GET_EMP_ET_BY_NUMBER) {
		GET_EMP_ET_BY_NUMBER_SERVICE = GET_EMP_ET_BY_NUMBER;
    }
	
	public static String EMPLEADO_ADM_ET_SERVICE;
	@Value("${GET_ADM_ET_BY_CIA}")
    public void GET_ADM_ET_BY_CIA(String GET_ADM_ET_BY_CIA) {
		EMPLEADO_ADM_ET_SERVICE = GET_ADM_ET_BY_CIA;
    }	
	
	public static String CHECADA_ET_SERVICE;
	@Value("${GET_CHECADA_ET_BY_DATE}")
    public void GET_CHECADA_ET_BY_DATE(String GET_CHECADA_ET_BY_DATE) {
		CHECADA_ET_SERVICE = GET_CHECADA_ET_BY_DATE;
    }
	
	public static String COMPARA_FOTO_SERVICE;
	@Value("${COMPARA_FOTO_REST_SERVICE}")
    public void COMPARA_FOTO_REST_SERVICE(String COMPARA_FOTO_REST_SERVICE) {
		COMPARA_FOTO_SERVICE = COMPARA_FOTO_REST_SERVICE;
    }
	
	public static String REGISTRA_CHECADA_ET_SERVICE;
	@Value("${REG_CHECADA_ET_BY_DATE}")
    public void REG_CHECADA_ET_BY_DATE(String REG_CHECADA_ET_BY_DATE) {
		REGISTRA_CHECADA_ET_SERVICE = REG_CHECADA_ET_BY_DATE;
    }	
	
	public static String REGISTRA_AUDITORIA_ESTACION_SERVICE;
	@Value("${REGISTRA_AUDITORIA_ESTACION}")
    public void REGISTRA_AUDITORIA_ESTACION(String REGISTRA_AUDITORIA_ESTACION) {
		REGISTRA_AUDITORIA_ESTACION_SERVICE = REGISTRA_AUDITORIA_ESTACION;
    }	
	
	public static String UNIDAD_MEDIDA_ET_SERVICE;
	@Value("${GET_UNIDAD_MEDIDA_ET}")
    public void GET_UNIDAD_MEDIDA_ET(String GET_UNIDAD_MEDIDA_ET) {
		UNIDAD_MEDIDA_ET_SERVICE = GET_UNIDAD_MEDIDA_ET;
    }	
	
	public static String GET_PARAMETRO_ET_SERVICE;
	@Value("${GET_PARAMETRO_ET}")
	public void GET_PARAMETRO_ET(String GET_PARAMETRO_ET) {
		GET_PARAMETRO_ET_SERVICE = GET_PARAMETRO_ET;
    }
}