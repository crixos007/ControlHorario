package com.vsm.db.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the HU_EMPLS_EST_CHECADO database table.
 * 
 */
@Embeddable
public class HuEmplsEstChecadoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="NUM_CIA")
	private long numCia;

	@Column(name="NUM_EMP")
	private long numEmp;

	private String estacion;

	private long gafete;

	public HuEmplsEstChecadoPK() {
	}
	public long getNumCia() {
		return this.numCia;
	}
	public void setNumCia(long numCia) {
		this.numCia = numCia;
	}
	public long getNumEmp() {
		return this.numEmp;
	}
	public void setNumEmp(long numEmp) {
		this.numEmp = numEmp;
	}
	public String getEstacion() {
		return this.estacion;
	}
	public void setEstacion(String estacion) {
		this.estacion = estacion;
	}
	public long getGafete() {
		return this.gafete;
	}
	public void setGafete(long gafete) {
		this.gafete = gafete;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof HuEmplsEstChecadoPK)) {
			return false;
		}
		HuEmplsEstChecadoPK castOther = (HuEmplsEstChecadoPK)other;
		return 
			(this.numCia == castOther.numCia)
			&& (this.numEmp == castOther.numEmp)
			&& this.estacion.equals(castOther.estacion)
			&& (this.gafete == castOther.gafete);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.numCia ^ (this.numCia >>> 32)));
		hash = hash * prime + ((int) (this.numEmp ^ (this.numEmp >>> 32)));
		hash = hash * prime + this.estacion.hashCode();
		hash = hash * prime + ((int) (this.gafete ^ (this.gafete >>> 32)));
		
		return hash;
	}
}