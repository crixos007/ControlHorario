package com.vsm.db.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the TA140 database table.
 * 
 */
@Embeddable
public class Ta140PK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="TA140_CIA")
	private long ta140Cia;

	@Column(name="TA140_CVE_TURNO")
	private long ta140CveTurno;

	public Ta140PK() {
	}
	public long getTa140Cia() {
		return this.ta140Cia;
	}
	public void setTa140Cia(long ta140Cia) {
		this.ta140Cia = ta140Cia;
	}
	public long getTa140CveTurno() {
		return this.ta140CveTurno;
	}
	public void setTa140CveTurno(long ta140CveTurno) {
		this.ta140CveTurno = ta140CveTurno;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Ta140PK)) {
			return false;
		}
		Ta140PK castOther = (Ta140PK)other;
		return 
			(this.ta140Cia == castOther.ta140Cia)
			&& (this.ta140CveTurno == castOther.ta140CveTurno);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.ta140Cia ^ (this.ta140Cia >>> 32)));
		hash = hash * prime + ((int) (this.ta140CveTurno ^ (this.ta140CveTurno >>> 32)));
		
		return hash;
	}
	
	@Override
	public String toString() {
		return "Ta140PK [ta140Cia=" + ta140Cia + ", ta140CveTurno=" + ta140CveTurno + "]";
	}	
}