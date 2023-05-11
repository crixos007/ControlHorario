package com.vsm.db.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the HD database table.
 * 
 */
@Embeddable
public class HdPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="HD_CIA")
	private long hdCia;

	@Column(name="HD_NUM_EMP")
	private long hdNumEmp;

	@Column(name="HD_AA_PROC")
	private long hdAaProc;

	public HdPK() {
	}
	public long getHdCia() {
		return this.hdCia;
	}
	public void setHdCia(long hdCia) {
		this.hdCia = hdCia;
	}
	public long getHdNumEmp() {
		return this.hdNumEmp;
	}
	public void setHdNumEmp(long hdNumEmp) {
		this.hdNumEmp = hdNumEmp;
	}
	public long getHdAaProc() {
		return this.hdAaProc;
	}
	public void setHdAaProc(long hdAaProc) {
		this.hdAaProc = hdAaProc;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof HdPK)) {
			return false;
		}
		HdPK castOther = (HdPK)other;
		return 
			(this.hdCia == castOther.hdCia)
			&& (this.hdNumEmp == castOther.hdNumEmp)
			&& (this.hdAaProc == castOther.hdAaProc);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.hdCia ^ (this.hdCia >>> 32)));
		hash = hash * prime + ((int) (this.hdNumEmp ^ (this.hdNumEmp >>> 32)));
		hash = hash * prime + ((int) (this.hdAaProc ^ (this.hdAaProc >>> 32)));
		
		return hash;
	}
	
	@Override
	public String toString() {
		return "HdPK [hdCia=" + hdCia + ", hdNumEmp=" + hdNumEmp + ", hdAaProc=" + hdAaProc + "]";
	}	
}