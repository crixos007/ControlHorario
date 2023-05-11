package com.vsm.db.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the RH database table.
 * 
 */
@Entity
@NamedQuery(name="Rh.findAll", query="SELECT r FROM Rh r")
public class Rh implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RhPK id;

	@Column(name="RH_AREA")
	private String rhArea;

	@Column(name="RH_CATEGORIA")
	private String rhCategoria;

	@Column(name="RH_CENTRO")
	private String rhCentro;

	@Column(name="RH_CVE_HOR")
	private BigDecimal rhCveHor;

	@Column(name="RH_CVE_TURNO")
	private BigDecimal rhCveTurno;

	@Column(name="RH_DESC_DOM")
	private String rhDescDom;

	@Column(name="RH_DESC_JUE")
	private String rhDescJue;

	@Column(name="RH_DESC_LUN")
	private String rhDescLun;

	@Column(name="RH_DESC_MAR")
	private String rhDescMar;

	@Column(name="RH_DESC_MIE")
	private String rhDescMie;

	@Column(name="RH_DESC_SAB")
	private String rhDescSab;

	@Column(name="RH_DESC_VIE")
	private String rhDescVie;

	@Column(name="RH_FEC_FIN")
	private LocalDateTime rhFecFin;

	@Column(name="RH_FECHA_MODIF")
	private LocalDateTime rhFechaModif;

	@Column(name="RH_HORA_MODIF")
	private LocalDateTime rhHoraModif;

	@Column(name="RH_LINEA")
	private String rhLinea;

	@Column(name="RH_STS")
	private String rhSts;

	@Column(name="RH_USUARIO")
	private String rhUsuario;

	@Column(name="RH_ZONA")
	private BigDecimal rhZona;

	public Rh() {
	}

	public RhPK getId() {
		return this.id;
	}

	public void setId(RhPK id) {
		this.id = id;
	}

	public String getRhArea() {
		return this.rhArea;
	}

	public void setRhArea(String rhArea) {
		this.rhArea = rhArea;
	}

	public String getRhCategoria() {
		return this.rhCategoria;
	}

	public void setRhCategoria(String rhCategoria) {
		this.rhCategoria = rhCategoria;
	}

	public String getRhCentro() {
		return this.rhCentro;
	}

	public void setRhCentro(String rhCentro) {
		this.rhCentro = rhCentro;
	}

	public BigDecimal getRhCveHor() {
		return this.rhCveHor;
	}

	public void setRhCveHor(BigDecimal rhCveHor) {
		this.rhCveHor = rhCveHor;
	}

	public BigDecimal getRhCveTurno() {
		return this.rhCveTurno;
	}

	public void setRhCveTurno(BigDecimal rhCveTurno) {
		this.rhCveTurno = rhCveTurno;
	}

	public String getRhDescDom() {
		return this.rhDescDom;
	}

	public void setRhDescDom(String rhDescDom) {
		this.rhDescDom = rhDescDom;
	}

	public String getRhDescJue() {
		return this.rhDescJue;
	}

	public void setRhDescJue(String rhDescJue) {
		this.rhDescJue = rhDescJue;
	}

	public String getRhDescLun() {
		return this.rhDescLun;
	}

	public void setRhDescLun(String rhDescLun) {
		this.rhDescLun = rhDescLun;
	}

	public String getRhDescMar() {
		return this.rhDescMar;
	}

	public void setRhDescMar(String rhDescMar) {
		this.rhDescMar = rhDescMar;
	}

	public String getRhDescMie() {
		return this.rhDescMie;
	}

	public void setRhDescMie(String rhDescMie) {
		this.rhDescMie = rhDescMie;
	}

	public String getRhDescSab() {
		return this.rhDescSab;
	}

	public void setRhDescSab(String rhDescSab) {
		this.rhDescSab = rhDescSab;
	}

	public String getRhDescVie() {
		return this.rhDescVie;
	}

	public void setRhDescVie(String rhDescVie) {
		this.rhDescVie = rhDescVie;
	}

	public LocalDateTime getRhFecFin() {
		return this.rhFecFin;
	}

	public void setRhFecFin(LocalDateTime rhFecFin) {
		this.rhFecFin = rhFecFin;
	}

	public LocalDateTime getRhFechaModif() {
		return this.rhFechaModif;
	}

	public void setRhFechaModif(LocalDateTime rhFechaModif) {
		this.rhFechaModif = rhFechaModif;
	}

	public LocalDateTime getRhHoraModif() {
		return this.rhHoraModif;
	}

	public void setRhHoraModif(LocalDateTime rhHoraModif) {
		this.rhHoraModif = rhHoraModif;
	}

	public String getRhLinea() {
		return this.rhLinea;
	}

	public void setRhLinea(String rhLinea) {
		this.rhLinea = rhLinea;
	}

	public String getRhSts() {
		return this.rhSts;
	}

	public void setRhSts(String rhSts) {
		this.rhSts = rhSts;
	}

	public String getRhUsuario() {
		return this.rhUsuario;
	}

	public void setRhUsuario(String rhUsuario) {
		this.rhUsuario = rhUsuario;
	}

	public BigDecimal getRhZona() {
		return this.rhZona;
	}

	public void setRhZona(BigDecimal rhZona) {
		this.rhZona = rhZona;
	}

}