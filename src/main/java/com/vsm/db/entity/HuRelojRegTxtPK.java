package com.vsm.db.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the HU_RELOJ_REG_TXT database table.
 * 
 */
@Embeddable
public class HuRelojRegTxtPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private long cia;

	@Column(name="NUM_EMP")
	private long numEmp;

	private LocalDate fecha;

	private LocalDateTime hora;

	public HuRelojRegTxtPK() {
	}
	public long getCia() {
		return this.cia;
	}
	public void setCia(long cia) {
		this.cia = cia;
	}
	public long getNumEmp() {
		return this.numEmp;
	}
	public void setNumEmp(long numEmp) {
		this.numEmp = numEmp;
	}
	public LocalDate getFecha() {
		return this.fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public LocalDateTime getHora() {
		return this.hora;
	}
	public void setHora(LocalDateTime hora) {
		this.hora = hora;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof HuRelojRegTxtPK)) {
			return false;
		}
		HuRelojRegTxtPK castOther = (HuRelojRegTxtPK)other;
		return 
			(this.cia == castOther.cia)
			&& (this.numEmp == castOther.numEmp)
			&& this.fecha.equals(castOther.fecha)
			&& this.hora.equals(castOther.hora);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.cia ^ (this.cia >>> 32)));
		hash = hash * prime + ((int) (this.numEmp ^ (this.numEmp >>> 32)));
		hash = hash * prime + this.fecha.hashCode();
		hash = hash * prime + this.hora.hashCode();
		
		return hash;
	}
	
	@Override
	public String toString() {
		return "HuRelojRegTxtPK [cia=" + cia + ", numEmp=" + numEmp + ", fecha=" + fecha + ", hora=" + hora + "]";
	}
}