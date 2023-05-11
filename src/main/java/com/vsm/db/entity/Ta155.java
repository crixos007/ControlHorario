package com.vsm.db.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the TA155 database table.
 * 
 */
@Entity
@NamedQuery(name="Ta155.findAll", query="SELECT t FROM Ta155 t")
public class Ta155 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Ta155PK id;

	@Column(name="TA155_AUT")
	private String ta155Aut;

	@Column(name="TA155_BEACON")
	private String ta155Beacon;

	@Column(name="TA155_BSSID")
	private String ta155Bssid;

	@Column(name="TA155_COORD_X")
	private BigDecimal ta155CoordX;

	@Column(name="TA155_COORD_Y")
	private BigDecimal ta155CoordY;

	@Column(name="TA155_DESCR")
	private String ta155Descr;

	@Column(name="TA155_DIRECC_IP")
	private String ta155DireccIp;

	@Column(name="TA155_EST_COM")
	private String ta155EstCom;

	@Column(name="TA155_FECHA_MOV")
	private LocalDateTime ta155FechaMov;

	@Column(name="TA155_GPO_PARM")
	private String ta155GpoParm;

	@Column(name="TA155_MODELO")
	private String ta155Modelo;

	@Column(name="TA155_RADIO")
	private BigDecimal ta155Radio;

	@Column(name="TA155_RUTA")
	private String ta155Ruta;

	@Column(name="TA155_STS")
	private String ta155Sts;

	@Column(name="TA155_TIPO")
	private String ta155Tipo;

	@Column(name="TA155_USER_ID")
	private String ta155UserId;

	@Column(name="TA155_ZONA")
	private BigDecimal ta155Zona;

	public Ta155() {
	}

	public Ta155PK getId() {
		return this.id;
	}

	public void setId(Ta155PK id) {
		this.id = id;
	}

	public String getTa155Aut() {
		return this.ta155Aut;
	}

	public void setTa155Aut(String ta155Aut) {
		this.ta155Aut = ta155Aut;
	}

	public String getTa155Beacon() {
		return this.ta155Beacon;
	}

	public void setTa155Beacon(String ta155Beacon) {
		this.ta155Beacon = ta155Beacon;
	}

	public String getTa155Bssid() {
		return this.ta155Bssid;
	}

	public void setTa155Bssid(String ta155Bssid) {
		this.ta155Bssid = ta155Bssid;
	}

	public BigDecimal getTa155CoordX() {
		return this.ta155CoordX;
	}

	public void setTa155CoordX(BigDecimal ta155CoordX) {
		this.ta155CoordX = ta155CoordX;
	}

	public BigDecimal getTa155CoordY() {
		return this.ta155CoordY;
	}

	public void setTa155CoordY(BigDecimal ta155CoordY) {
		this.ta155CoordY = ta155CoordY;
	}

	public String getTa155Descr() {
		return this.ta155Descr;
	}

	public void setTa155Descr(String ta155Descr) {
		this.ta155Descr = ta155Descr;
	}

	public String getTa155DireccIp() {
		return this.ta155DireccIp;
	}

	public void setTa155DireccIp(String ta155DireccIp) {
		this.ta155DireccIp = ta155DireccIp;
	}

	public String getTa155EstCom() {
		return this.ta155EstCom;
	}

	public void setTa155EstCom(String ta155EstCom) {
		this.ta155EstCom = ta155EstCom;
	}

	public LocalDateTime getTa155FechaMov() {
		return this.ta155FechaMov;
	}

	public void setTa155FechaMov(LocalDateTime ta155FechaMov) {
		this.ta155FechaMov = ta155FechaMov;
	}

	public String getTa155GpoParm() {
		return this.ta155GpoParm;
	}

	public void setTa155GpoParm(String ta155GpoParm) {
		this.ta155GpoParm = ta155GpoParm;
	}

	public String getTa155Modelo() {
		return this.ta155Modelo;
	}

	public void setTa155Modelo(String ta155Modelo) {
		this.ta155Modelo = ta155Modelo;
	}

	public BigDecimal getTa155Radio() {
		return this.ta155Radio;
	}

	public void setTa155Radio(BigDecimal ta155Radio) {
		this.ta155Radio = ta155Radio;
	}

	public String getTa155Ruta() {
		return this.ta155Ruta;
	}

	public void setTa155Ruta(String ta155Ruta) {
		this.ta155Ruta = ta155Ruta;
	}

	public String getTa155Sts() {
		return this.ta155Sts;
	}

	public void setTa155Sts(String ta155Sts) {
		this.ta155Sts = ta155Sts;
	}

	public String getTa155Tipo() {
		return this.ta155Tipo;
	}

	public void setTa155Tipo(String ta155Tipo) {
		this.ta155Tipo = ta155Tipo;
	}

	public String getTa155UserId() {
		return this.ta155UserId;
	}

	public void setTa155UserId(String ta155UserId) {
		this.ta155UserId = ta155UserId;
	}

	public BigDecimal getTa155Zona() {
		return this.ta155Zona;
	}

	public void setTa155Zona(BigDecimal ta155Zona) {
		this.ta155Zona = ta155Zona;
	}

}