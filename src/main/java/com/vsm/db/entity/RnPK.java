package com.vsm.db.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the RN database table.
 * 
 */
@Embeddable
public class RnPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="RN_CIA")
	private long rnCia;

	@Column(name="RN_NUM_EMP")
	private long rnNumEmp;

	@Column(name="RN_FECHA")
	private LocalDateTime rnFecha;

	@Column(name="RN_SECUENCIA")
	private long rnSecuencia;

	public RnPK() {
	}
	public long getRnCia() {
		return this.rnCia;
	}
	public void setRnCia(long rnCia) {
		this.rnCia = rnCia;
	}
	public long getRnNumEmp() {
		return this.rnNumEmp;
	}
	public void setRnNumEmp(long rnNumEmp) {
		this.rnNumEmp = rnNumEmp;
	}
	public LocalDateTime getRnFecha() {
		return this.rnFecha;
	}
	public void setRnFecha(LocalDateTime rnFecha) {
		this.rnFecha = rnFecha;
	}
	public long getRnSecuencia() {
		return this.rnSecuencia;
	}
	public void setRnSecuencia(long rnSecuencia) {
		this.rnSecuencia = rnSecuencia;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RnPK)) {
			return false;
		}
		RnPK castOther = (RnPK)other;
		return 
			(this.rnCia == castOther.rnCia)
			&& (this.rnNumEmp == castOther.rnNumEmp)
			&& this.rnFecha.equals(castOther.rnFecha)
			&& (this.rnSecuencia == castOther.rnSecuencia);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.rnCia ^ (this.rnCia >>> 32)));
		hash = hash * prime + ((int) (this.rnNumEmp ^ (this.rnNumEmp >>> 32)));
		hash = hash * prime + this.rnFecha.hashCode();
		hash = hash * prime + ((int) (this.rnSecuencia ^ (this.rnSecuencia >>> 32)));
		
		return hash;
	}
	
	@Override
	public String toString() {
		return "RnPK [rnCia=" + rnCia + ", rnNumEmp=" + rnNumEmp + ", rnFecha=" + rnFecha + ", rnSecuencia="
				+ rnSecuencia + "]";
	}
}