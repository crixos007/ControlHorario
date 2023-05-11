package com.vsm.db.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the HU_EMPLS_ASISTENCIA database table.
 * 
 */
@Embeddable
public class HuEmplsAsistenciaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="AS_CIA")
	private long asCia;

	@Column(name="AS_NUM_EMP")
	private long asNumEmp;

	@Column(name="AS_AA_PROC")
	private long asAaProc;

	public HuEmplsAsistenciaPK() {
	}
	public long getAsCia() {
		return this.asCia;
	}
	public void setAsCia(long asCia) {
		this.asCia = asCia;
	}
	public long getAsNumEmp() {
		return this.asNumEmp;
	}
	public void setAsNumEmp(long asNumEmp) {
		this.asNumEmp = asNumEmp;
	}
	public long getAsAaProc() {
		return this.asAaProc;
	}
	public void setAsAaProc(long asAaProc) {
		this.asAaProc = asAaProc;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof HuEmplsAsistenciaPK)) {
			return false;
		}
		HuEmplsAsistenciaPK castOther = (HuEmplsAsistenciaPK)other;
		return 
			(this.asCia == castOther.asCia)
			&& (this.asNumEmp == castOther.asNumEmp)
			&& (this.asAaProc == castOther.asAaProc);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.asCia ^ (this.asCia >>> 32)));
		hash = hash * prime + ((int) (this.asNumEmp ^ (this.asNumEmp >>> 32)));
		hash = hash * prime + ((int) (this.asAaProc ^ (this.asAaProc >>> 32)));
		
		return hash;
	}
}