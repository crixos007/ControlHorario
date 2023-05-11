package com.vsm.db.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the HU_EMPLS_AUDIT_ETIME database table.
 * 
 */
@Entity
@Table(name="HU_EMPLS_AUDIT_ETIME")
@NamedQuery(name="HuEmplsAuditEtime.findAll", query="SELECT h FROM HuEmplsAuditEtime h")
public class HuEmplsAuditEtime implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HuEmplsAuditEtimePK id;

	@Column(name="BEACON_01")
	private String beacon01;

	@Column(name="BEACON_02")
	private String beacon02;

	@Column(name="BEACON_03")
	private String beacon03;

	@Column(name="BEACON_04")
	private String beacon04;

	@Column(name="BEACON_05")
	private String beacon05;

	@Column(name="CODIGO_ETIME")
	private String codigoEtime;

	private String estacion;

	@Column(name="ESTACION_01")
	private String estacion01;

	@Column(name="ESTACION_02")
	private String estacion02;

	@Column(name="ESTACION_03")
	private String estacion03;

	@Column(name="ESTACION_04")
	private String estacion04;

	@Column(name="ESTACION_05")
	private String estacion05;

	@Column(name="FECHA_CHECADA")
	private LocalDateTime fechaChecada;

	@Column(name="FECHA_MOV")
	private LocalDateTime fechaMov;

	@Lob
	private String fotografia;

	private BigDecimal latitud;

	private BigDecimal longitud;

	private String status;

	private String tipo;

	public HuEmplsAuditEtime() {
	}

	public HuEmplsAuditEtimePK getId() {
		return this.id;
	}

	public void setId(HuEmplsAuditEtimePK id) {
		this.id = id;
	}

	public String getBeacon01() {
		return this.beacon01;
	}

	public void setBeacon01(String beacon01) {
		this.beacon01 = beacon01;
	}

	public String getBeacon02() {
		return this.beacon02;
	}

	public void setBeacon02(String beacon02) {
		this.beacon02 = beacon02;
	}

	public String getBeacon03() {
		return this.beacon03;
	}

	public void setBeacon03(String beacon03) {
		this.beacon03 = beacon03;
	}

	public String getBeacon04() {
		return this.beacon04;
	}

	public void setBeacon04(String beacon04) {
		this.beacon04 = beacon04;
	}

	public String getBeacon05() {
		return this.beacon05;
	}

	public void setBeacon05(String beacon05) {
		this.beacon05 = beacon05;
	}

	public String getCodigoEtime() {
		return this.codigoEtime;
	}

	public void setCodigoEtime(String codigoEtime) {
		this.codigoEtime = codigoEtime;
	}

	public String getEstacion() {
		return this.estacion;
	}

	public void setEstacion(String estacion) {
		this.estacion = estacion;
	}

	public String getEstacion01() {
		return this.estacion01;
	}

	public void setEstacion01(String estacion01) {
		this.estacion01 = estacion01;
	}

	public String getEstacion02() {
		return this.estacion02;
	}

	public void setEstacion02(String estacion02) {
		this.estacion02 = estacion02;
	}

	public String getEstacion03() {
		return this.estacion03;
	}

	public void setEstacion03(String estacion03) {
		this.estacion03 = estacion03;
	}

	public String getEstacion04() {
		return this.estacion04;
	}

	public void setEstacion04(String estacion04) {
		this.estacion04 = estacion04;
	}

	public String getEstacion05() {
		return this.estacion05;
	}

	public void setEstacion05(String estacion05) {
		this.estacion05 = estacion05;
	}

	public LocalDateTime getFechaChecada() {
		return this.fechaChecada;
	}

	public void setFechaChecada(LocalDateTime fechaChecada) {
		this.fechaChecada = fechaChecada;
	}

	public LocalDateTime getFechaMov() {
		return this.fechaMov;
	}

	public void setFechaMov(LocalDateTime fechaMov) {
		this.fechaMov = fechaMov;
	}

	public String getFotografia() {
		return this.fotografia;
	}

	public void setFotografia(String fotografia) {
		this.fotografia = fotografia;
	}

	public BigDecimal getLatitud() {
		return this.latitud;
	}

	public void setLatitud(BigDecimal latitud) {
		this.latitud = latitud;
	}

	public BigDecimal getLongitud() {
		return this.longitud;
	}

	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
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

	@Override
	public String toString() {
		return "HuEmplsAuditEtime [id=" + id + ", beacon01=" + beacon01 + ", beacon02=" + beacon02 + ", beacon03="
				+ beacon03 + ", beacon04=" + beacon04 + ", beacon05=" + beacon05 + ", codigoEtime=" + codigoEtime
				+ ", estacion=" + estacion + ", estacion01=" + estacion01 + ", estacion02=" + estacion02
				+ ", estacion03=" + estacion03 + ", estacion04=" + estacion04 + ", estacion05=" + estacion05
				+ ", fechaChecada=" + fechaChecada + ", fechaMov=" + fechaMov + ", fotografia=" + fotografia
				+ ", latitud=" + latitud + ", longitud=" + longitud + ", status=" + status + ", tipo=" + tipo + "]";
	}
}