package com.vsm.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;

public class RequestHumaneTime implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int numCia;
	private long numEmp;
	private LocalDateTime fechaChecada;
	private String tipoChecada;
	private String foto;
	private int prioridad;

	private String beacon[];
	private sGeolocalizacion geolocalizacion;
	
	SBssids sBssids;
	
	String idTraza;

	@Override
	public String toString() {
		return "RequestHumaneTime [numCia=" + numCia + ", numEmp=" + numEmp + ", fechaChecada=" + fechaChecada
				+ ", tipoChecada=" + tipoChecada + ", foto=" + foto + ", beacon=" + Arrays.toString(beacon)
				+ ", geolocalizacion=" + geolocalizacion + "]";
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

	public int getNumCia() {
		return numCia;
	}

	public void setNumCia(int numCia) {
		this.numCia = numCia;
	}

	public int getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getNumEmp() {
		return numEmp;
	}

	public void setNumEmp(long numEmp) {
		this.numEmp = numEmp;
	}

	public LocalDateTime getFechaChecada() {
		return fechaChecada;
	}

	public void setFechaChecada(LocalDateTime fechaChecada) {
		this.fechaChecada = fechaChecada;
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

	public String[] getBeacon() {
		return beacon;
	}

	public void setBeacon(String[] beacon) {
		this.beacon = beacon;
	}

	public sGeolocalizacion getGeolocalizacion() {
		return geolocalizacion;
	}

	public void setGeolocalizacion(sGeolocalizacion geolocalizacion) {
		this.geolocalizacion = geolocalizacion;
	}

	public class sGeolocalizacion implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private double latitud;
		private double longitud;

		public double getLatitud() {
			return latitud;
		}

		public void setLatitud(double latitud) {
			this.latitud = latitud;
		}

		public double getLongitud() {
			return longitud;
		}

		public void setLongitud(double longitud) {
			this.longitud = longitud;
		}

		@Override
		public String toString() {
			return "sGeolocalizacion [latitud=" + latitud + ", longitud=" + longitud + "]";
		}

	}
}
