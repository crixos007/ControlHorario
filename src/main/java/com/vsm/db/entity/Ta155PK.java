package com.vsm.db.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the TA155 database table.
 * 
 */
@Embeddable
public class Ta155PK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="TA155_CIA")
	private long ta155Cia;

	@Column(name="TA155_ESTACION")
	private String ta155Estacion;

	public Ta155PK() {
	}
	public long getTa155Cia() {
		return this.ta155Cia;
	}
	public void setTa155Cia(long ta155Cia) {
		this.ta155Cia = ta155Cia;
	}
	public String getTa155Estacion() {
		return this.ta155Estacion;
	}
	public void setTa155Estacion(String ta155Estacion) {
		this.ta155Estacion = ta155Estacion;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Ta155PK)) {
			return false;
		}
		Ta155PK castOther = (Ta155PK)other;
		return 
			(this.ta155Cia == castOther.ta155Cia)
			&& this.ta155Estacion.equals(castOther.ta155Estacion);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.ta155Cia ^ (this.ta155Cia >>> 32)));
		hash = hash * prime + this.ta155Estacion.hashCode();
		
		return hash;
	}
}