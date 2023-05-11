package com.vsm.db.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the HU_EMPLS_EST_CHECADO database table.
 * 
 */
@Entity
@Table(name="HU_EMPLS_EST_CHECADO")
@NamedQuery(name="HuEmplsEstChecado.findAll", query="SELECT h FROM HuEmplsEstChecado h")
public class HuEmplsEstChecado implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HuEmplsEstChecadoPK id;

	@Column(name="FECHA_MOV")
	private LocalDateTime fechaMov;

	private String status;

	private String tipo;

	@Column(name="USER_ID")
	private String userId;

	public HuEmplsEstChecado() {
	}

	public HuEmplsEstChecadoPK getId() {
		return this.id;
	}

	public void setId(HuEmplsEstChecadoPK id) {
		this.id = id;
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

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}