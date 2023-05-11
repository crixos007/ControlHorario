package com.vsm.db.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the HU_CAT_ROL_DET database table.
 * 
 */
@Embeddable
public class HuCatRolDetPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="NUM_CIA")
	private long numCia;

	@Column(name="CLAVE_ROL")
	private long claveRol;

	@Column(name="CLAVE_TURNO")
	private long claveTurno;

	@Column(name="FECHA_INI")
	private LocalDateTime fechaIni;

	public HuCatRolDetPK() {
	}
	public long getNumCia() {
		return this.numCia;
	}
	public void setNumCia(long numCia) {
		this.numCia = numCia;
	}
	public long getClaveRol() {
		return this.claveRol;
	}
	public void setClaveRol(long claveRol) {
		this.claveRol = claveRol;
	}
	public long getClaveTurno() {
		return this.claveTurno;
	}
	public void setClaveTurno(long claveTurno) {
		this.claveTurno = claveTurno;
	}
	public LocalDateTime getFechaIni() {
		return this.fechaIni;
	}
	public void setFechaIni(LocalDateTime fechaIni) {
		this.fechaIni = fechaIni;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof HuCatRolDetPK)) {
			return false;
		}
		HuCatRolDetPK castOther = (HuCatRolDetPK)other;
		return 
			(this.numCia == castOther.numCia)
			&& (this.claveRol == castOther.claveRol)
			&& (this.claveTurno == castOther.claveTurno)
			&& this.fechaIni.equals(castOther.fechaIni);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.numCia ^ (this.numCia >>> 32)));
		hash = hash * prime + ((int) (this.claveRol ^ (this.claveRol >>> 32)));
		hash = hash * prime + ((int) (this.claveTurno ^ (this.claveTurno >>> 32)));
		hash = hash * prime + this.fechaIni.hashCode();
		
		return hash;
	}
}