package com.vsm.db.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the HU_EMPLS_AUDIT_ETIME database table.
 * 
 */
@Embeddable
public class HuEmplsAuditEtimePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="NUM_CIA")
	private long numCia;

	@Column(name="NUM_EMP")
	private long numEmp;

	private String id;

	public HuEmplsAuditEtimePK() {
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
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof HuEmplsAuditEtimePK)) {
			return false;
		}
		HuEmplsAuditEtimePK castOther = (HuEmplsAuditEtimePK)other;
		return 
			(this.numCia == castOther.numCia)
			&& (this.numEmp == castOther.numEmp)
			&& this.id.equals(castOther.id);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.numCia ^ (this.numCia >>> 32)));
		hash = hash * prime + ((int) (this.numEmp ^ (this.numEmp >>> 32)));
		hash = hash * prime + this.id.hashCode();
		
		return hash;
	}
	
	@Override
	public String toString() {
		return "HuEmplsAuditEtimePK [numCia=" + numCia + ", numEmp=" + numEmp + ", id=" + id + "]";
	}	
}