package com.vsm.db.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the PR database table.
 * 
 */
@Embeddable
public class PrPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="PR_CIA")
	private long prCia;

	@Column(name="PR_NUM_EMP")
	private long prNumEmp;

	@Column(name="PR_FECHA")
	private LocalDateTime prFecha;

	@Column(name="PR_PERM_HH_INI")
	private LocalDateTime prPermHhIni;

	public PrPK() {
	}
	public long getPrCia() {
		return this.prCia;
	}
	public void setPrCia(long prCia) {
		this.prCia = prCia;
	}
	public long getPrNumEmp() {
		return this.prNumEmp;
	}
	public void setPrNumEmp(long prNumEmp) {
		this.prNumEmp = prNumEmp;
	}
	public LocalDateTime getPrFecha() {
		return this.prFecha;
	}
	public void setPrFecha(LocalDateTime prFecha) {
		this.prFecha = prFecha;
	}
	public LocalDateTime getPrPermHhIni() {
		return this.prPermHhIni;
	}
	public void setPrPermHhIni(LocalDateTime prPermHhIni) {
		this.prPermHhIni = prPermHhIni;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PrPK)) {
			return false;
		}
		PrPK castOther = (PrPK)other;
		return 
			(this.prCia == castOther.prCia)
			&& (this.prNumEmp == castOther.prNumEmp)
			&& this.prFecha.equals(castOther.prFecha)
			&& this.prPermHhIni.equals(castOther.prPermHhIni);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.prCia ^ (this.prCia >>> 32)));
		hash = hash * prime + ((int) (this.prNumEmp ^ (this.prNumEmp >>> 32)));
		hash = hash * prime + this.prFecha.hashCode();
		hash = hash * prime + this.prPermHhIni.hashCode();
		
		return hash;
	}
	
	@Override
	public String toString() {
		return "PrPK [prCia=" + prCia + ", prNumEmp=" + prNumEmp + ", prFecha=" + prFecha + ", prPermHhIni="
				+ prPermHhIni + "]";
	}
}