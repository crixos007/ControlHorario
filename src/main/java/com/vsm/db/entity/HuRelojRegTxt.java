package com.vsm.db.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the HU_RELOJ_REG_TXT database table.
 * 
 */
@Entity
@Table(name="HU_RELOJ_REG_TXT")
@NamedQuery(name="HuRelojRegTxt.findAll", query="SELECT h FROM HuRelojRegTxt h")
public class HuRelojRegTxt implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HuRelojRegTxtPK id;

	private BigDecimal comida;

	@Column(name="CVE_CHECADA")
	private String cveChecada;

	private String estacion;

	@Column(name="FECHA_MOV")
	private LocalDateTime fechaMov;

	@Column(name="MOT_CHECADA")
	private String motChecada;

	private String status;

	@Column(name="USER_ID")
	private String userId;

	public HuRelojRegTxt() {
	}

	public HuRelojRegTxtPK getId() {
		return this.id;
	}

	public void setId(HuRelojRegTxtPK id) {
		this.id = id;
	}

	public BigDecimal getComida() {
		return this.comida;
	}

	public void setComida(BigDecimal comida) {
		this.comida = comida;
	}

	public String getCveChecada() {
		return this.cveChecada;
	}

	public void setCveChecada(String cveChecada) {
		this.cveChecada = cveChecada;
	}

	public String getEstacion() {
		return this.estacion;
	}

	public void setEstacion(String estacion) {
		this.estacion = estacion;
	}

	public LocalDateTime getFechaMov() {
		return this.fechaMov;
	}

	public void setFechaMov(LocalDateTime fechaMov) {
		this.fechaMov = fechaMov;
	}

	public String getMotChecada() {
		return this.motChecada;
	}

	public void setMotChecada(String motChecada) {
		this.motChecada = motChecada;
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

}