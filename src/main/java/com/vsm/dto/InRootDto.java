package com.vsm.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class InRootDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	String numeroCompania;
	String empleado;
	LocalDateTime fechaHoraChecada;
	String tipoChecada;
	String foto;
	String prioridad;
	
	SGeolocalizacion sGeolocalizacion;
	SBeacons sBeacons;
	SBssids sBssids;
	
	public String getNumeroCompania() {
		return numeroCompania;
	}
	public void setNumeroCompania(String numeroCompania) {
		this.numeroCompania = numeroCompania;
	}
	public String getEmpleado() {
		return empleado;
	}
	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}
	public LocalDateTime getFechaHoraChecada() {
		return fechaHoraChecada;
	}
	public void setFechaHoraChecada(LocalDateTime fechaHoraChecada) {
		this.fechaHoraChecada = fechaHoraChecada;
	}
	public String getTipoChecada() {
		return tipoChecada;
	}
	public void setTipoChecada(String tipoChecada) {
		this.tipoChecada = tipoChecada;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}
	public SGeolocalizacion getsGeolocalizacion() {
		return sGeolocalizacion;
	}
	public void setsGeolocalizacion(SGeolocalizacion sGeolocalizacion) {
		this.sGeolocalizacion = sGeolocalizacion;
	}
	public SBeacons getsBeacons() {
		return sBeacons;
	}
	public void setsBeacons(SBeacons sBeacons) {
		this.sBeacons = sBeacons;
	}

	/**
	 * @return the sBssids
	 */
	public SBssids getsBssids() {
		return sBssids;
	}
	/**
	 * @param sBssids the sBssids to set
	 */
	public void setsBssids(SBssids sBssids) {
		this.sBssids = sBssids;
	}	
}