package com.vsm.db.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the MR database table.
 * 
 */
@Embeddable
public class MrPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="MR_CIA")
	private long mrCia;

	@Column(name="MR_NUM_EMP")
	private long mrNumEmp;

	@Column(name="MR_FECHA_APLI")
	private LocalDateTime mrFechaApli;

	public MrPK() {
	}
	public long getMrCia() {
		return this.mrCia;
	}
	public void setMrCia(long mrCia) {
		this.mrCia = mrCia;
	}
	public long getMrNumEmp() {
		return this.mrNumEmp;
	}
	public void setMrNumEmp(long mrNumEmp) {
		this.mrNumEmp = mrNumEmp;
	}
	public LocalDateTime getMrFechaApli() {
		return this.mrFechaApli;
	}
	public void setMrFechaApli(LocalDateTime mrFechaApli) {
		this.mrFechaApli = mrFechaApli;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MrPK)) {
			return false;
		}
		MrPK castOther = (MrPK)other;
		return 
			(this.mrCia == castOther.mrCia)
			&& (this.mrNumEmp == castOther.mrNumEmp)
			&& this.mrFechaApli.equals(castOther.mrFechaApli);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.mrCia ^ (this.mrCia >>> 32)));
		hash = hash * prime + ((int) (this.mrNumEmp ^ (this.mrNumEmp >>> 32)));
		hash = hash * prime + this.mrFechaApli.hashCode();
		
		return hash;
	}
}