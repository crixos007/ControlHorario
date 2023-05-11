package com.vsm.db.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the HU_CAT_ROL_DET database table.
 * 
 */
@Entity
@Table(name="HU_CAT_ROL_DET")
@NamedQuery(name="HuCatRolDet.findAll", query="SELECT h FROM HuCatRolDet h")
public class HuCatRolDet implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HuCatRolDetPK id;

	@Column(name="DIAS_DESCANSO")
	private String diasDescanso;

	@Column(name="FECHA_FIN")
	private LocalDateTime fechaFin;

	@Column(name="FECHA_MOV")
	private LocalDateTime fechaMov;

	@Column(name="SECUENCIA_INICIAL")
	private String secuenciaInicial;

	private String status;

	@Column(name="USER_ID")
	private String userId;

	public HuCatRolDet() {
	}

	public HuCatRolDetPK getId() {
		return this.id;
	}

	public void setId(HuCatRolDetPK id) {
		this.id = id;
	}

	public String getDiasDescanso() {
		return this.diasDescanso;
	}

	public void setDiasDescanso(String diasDescanso) {
		this.diasDescanso = diasDescanso;
	}

	public LocalDateTime getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
	}

	public LocalDateTime getFechaMov() {
		return this.fechaMov;
	}

	public void setFechaMov(LocalDateTime fechaMov) {
		this.fechaMov = fechaMov;
	}

	public String getSecuenciaInicial() {
		return this.secuenciaInicial;
	}

	public void setSecuenciaInicial(String secuenciaInicial) {
		this.secuenciaInicial = secuenciaInicial;
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