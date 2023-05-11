package com.vsm.dto;

import java.io.Serializable;

public class InChecadaETDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	String numeroCompania;
	String empleado;
	String fechaHoraChecada;
	String tipoChecada;
	String foto;
	String prioridad;
	
	Geolocalizacion sGeolocalizacion;
	Beacons sBeacons;
	Bssids sBssids;
	
	String idTraza;
	
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
	public String getFechaHoraChecada() {
		return fechaHoraChecada;
	}
	public void setFechaHoraChecada(String fechaHoraChecada) {
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
	public Geolocalizacion getsGeolocalizacion() {
		return sGeolocalizacion;
	}
	public void setsGeolocalizacion(Geolocalizacion sGeolocalizacion) {
		this.sGeolocalizacion = sGeolocalizacion;
	}
	public Beacons getsBeacons() {
		return sBeacons;
	}
	public void setsBeacons(Beacons sBeacons) {
		this.sBeacons = sBeacons;
	}
	
	/**
	 * @return the sBssids
	 */
	public Bssids getsBssids() {
		return sBssids;
	}
	/**
	 * @param sBssids the sBssids to set
	 */
	public void setsBssids(Bssids sBssids) {
		this.sBssids = sBssids;
	}
	/**
	 * @return the idTraza
	 */
	public String getIdTraza() {
		return idTraza;
	}
	/**
	 * @param idTraza the idTraza to set
	 */
	public void setIdTraza(String idTraza) {
		this.idTraza = idTraza;
	}	
}