package com.vsm.db.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the HU_CAT_DIAS_FESTIVOS database table.
 * 
 */
@Entity
@Table(name="HU_CAT_DIAS_FESTIVOS")
@NamedQuery(name="HuCatDiasFestivo.findAll", query="SELECT h FROM HuCatDiasFestivo h")
public class HuCatDiasFestivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HuCatDiasFestivoPK id;

	@Column(name="CAL_ESTANDAR")
	private String calEstandar;

	private String descripcion;

	@Column(name="FECHA_MOV")
	private LocalDateTime fechaMov;

	@Column(name="GPO_PARM")
	private String gpoParm;

	private String status;

	@Column(name="TIPO_FESTIVO")
	private String tipoFestivo;

	@Column(name="USER_ID")
	private String userId;

	public HuCatDiasFestivo() {
	}

	public HuCatDiasFestivoPK getId() {
		return this.id;
	}

	public void setId(HuCatDiasFestivoPK id) {
		this.id = id;
	}

	public String getCalEstandar() {
		return this.calEstandar;
	}

	public void setCalEstandar(String calEstandar) {
		this.calEstandar = calEstandar;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDateTime getFechaMov() {
		return this.fechaMov;
	}

	public void setFechaMov(LocalDateTime fechaMov) {
		this.fechaMov = fechaMov;
	}

	public String getGpoParm() {
		return this.gpoParm;
	}

	public void setGpoParm(String gpoParm) {
		this.gpoParm = gpoParm;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTipoFestivo() {
		return this.tipoFestivo;
	}

	public void setTipoFestivo(String tipoFestivo) {
		this.tipoFestivo = tipoFestivo;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}