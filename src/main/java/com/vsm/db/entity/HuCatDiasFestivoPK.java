package com.vsm.db.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the HU_CAT_DIAS_FESTIVOS database table.
 * 
 */
@Embeddable
public class HuCatDiasFestivoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="NUM_CIA")
	private long numCia;

	private long clave;

	private LocalDateTime fecha;

	public HuCatDiasFestivoPK() {
	}
	public long getNumCia() {
		return this.numCia;
	}
	public void setNumCia(long numCia) {
		this.numCia = numCia;
	}
	public long getClave() {
		return this.clave;
	}
	public void setClave(long clave) {
		this.clave = clave;
	}
	public LocalDateTime getFecha() {
		return this.fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof HuCatDiasFestivoPK)) {
			return false;
		}
		HuCatDiasFestivoPK castOther = (HuCatDiasFestivoPK)other;
		return 
			(this.numCia == castOther.numCia)
			&& (this.clave == castOther.clave)
			&& this.fecha.equals(castOther.fecha);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.numCia ^ (this.numCia >>> 32)));
		hash = hash * prime + ((int) (this.clave ^ (this.clave >>> 32)));
		hash = hash * prime + this.fecha.hashCode();
		
		return hash;
	}
}