package com.vsm.db.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the HU_CAT_TO_GRAL database table.
 * 
 */
@Entity
@Table(name="HU_CAT_TO_GRAL")
@NamedQuery(name="HuCatToGral.findAll", query="SELECT h FROM HuCatToGral h")
public class HuCatToGral implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String parametro;

	@Column(name="FECHA_MOV")
	private LocalDateTime fechaMov;

	private String status;

	@Column(name="USER_ID")
	private String userId;

	@Column(name="VALOR_ALFANUMERICO")
	private String valorAlfanumerico;

	@Column(name="VALOR_NUMERICO")
	private BigDecimal valorNumerico;

	public HuCatToGral() {
	}

	public String getParametro() {
		return this.parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public LocalDateTime getFechaMov() {
		return this.fechaMov;
	}

	public void setFechaMov(LocalDateTime fechaMov) {
		this.fechaMov = fechaMov;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getValorAlfanumerico() {
		return this.valorAlfanumerico;
	}

	public void setValorAlfanumerico(String valorAlfanumerico) {
		this.valorAlfanumerico = valorAlfanumerico;
	}

	public BigDecimal getValorNumerico() {
		return this.valorNumerico;
	}

	public void setValorNumerico(BigDecimal valorNumerico) {
		this.valorNumerico = valorNumerico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fechaMov == null) ? 0 : fechaMov.hashCode());
		result = prime * result + ((parametro == null) ? 0 : parametro.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((valorAlfanumerico == null) ? 0 : valorAlfanumerico.hashCode());
		result = prime * result + ((valorNumerico == null) ? 0 : valorNumerico.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HuCatToGral other = (HuCatToGral) obj;
		if (fechaMov == null) {
			if (other.fechaMov != null)
				return false;
		} else if (!fechaMov.equals(other.fechaMov))
			return false;
		if (parametro == null) {
			if (other.parametro != null)
				return false;
		} else if (!parametro.equals(other.parametro))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (valorAlfanumerico == null) {
			if (other.valorAlfanumerico != null)
				return false;
		} else if (!valorAlfanumerico.equals(other.valorAlfanumerico))
			return false;
		if (valorNumerico == null) {
			if (other.valorNumerico != null)
				return false;
		} else if (!valorNumerico.equals(other.valorNumerico))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HuCatToGral [parametro=" + parametro + ", fechaMov=" + fechaMov + ", status=" + status + ", userId="
				+ userId + ", valorAlfanumerico=" + valorAlfanumerico + ", valorNumerico=" + valorNumerico + "]";
	}

	
}