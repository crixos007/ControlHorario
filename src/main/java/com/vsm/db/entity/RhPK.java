package com.vsm.db.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the RH database table.
 * 
 */
@Embeddable
public class RhPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="RH_CIA")
	private long rhCia;

	@Column(name="RH_NUM_EMP")
	private long rhNumEmp;

	@Column(name="RH_FEC_INI")
	private LocalDateTime rhFecIni;

	public RhPK() {
	}
	public long getRhCia() {
		return this.rhCia;
	}
	public void setRhCia(long rhCia) {
		this.rhCia = rhCia;
	}
	public long getRhNumEmp() {
		return this.rhNumEmp;
	}
	public void setRhNumEmp(long rhNumEmp) {
		this.rhNumEmp = rhNumEmp;
	}
	public LocalDateTime getRhFecIni() {
		return this.rhFecIni;
	}
	public void setRhFecIni(LocalDateTime rhFecIni) {
		this.rhFecIni = rhFecIni;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RhPK)) {
			return false;
		}
		RhPK castOther = (RhPK)other;
		return 
			(this.rhCia == castOther.rhCia)
			&& (this.rhNumEmp == castOther.rhNumEmp)
			&& this.rhFecIni.equals(castOther.rhFecIni);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.rhCia ^ (this.rhCia >>> 32)));
		hash = hash * prime + ((int) (this.rhNumEmp ^ (this.rhNumEmp >>> 32)));
		hash = hash * prime + this.rhFecIni.hashCode();
		
		return hash;
	}
}