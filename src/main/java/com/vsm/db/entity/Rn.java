package com.vsm.db.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the RN database table.
 * 
 */
@Entity
@NamedQuery(name="Rn.findAll", query="SELECT r FROM Rn r")
public class Rn implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RnPK id;

	@Column(name="RN_AREA")
	private String rnArea;

	@Column(name="RN_CAUSA_RETARDO")
	private String rnCausaRetardo;

	@Column(name="RN_CENTRO")
	private String rnCentro;

	@Column(name="RN_CHEQUEO")
	private String rnChequeo;

	@Column(name="RN_COMIDA")
	private BigDecimal rnComida;

	@Column(name="RN_ESTACION")
	private String rnEstacion;

	@Column(name="RN_FEC_MODIF")
	private LocalDateTime rnFecModif;

	@Column(name="RN_GAFETE")
	private BigDecimal rnGafete;

	@Column(name="RN_HOR_ENT")
	private LocalDateTime rnHorEnt;

	@Column(name="RN_HOR_SAL")
	private LocalDateTime rnHorSal;

	@Column(name="RN_HORA_MODIF")
	private LocalDateTime rnHoraModif;

	@Column(name="RN_LINEA")
	private String rnLinea;

	@Column(name="RN_STS")
	private String rnSts;

	@Column(name="RN_USUARIO")
	private String rnUsuario;

	@Column(name="RN_ZONA")
	private BigDecimal rnZona;

	public Rn() {
	}

	public RnPK getId() {
		return this.id;
	}

	public void setId(RnPK id) {
		this.id = id;
	}

	public String getRnArea() {
		return this.rnArea;
	}

	public void setRnArea(String rnArea) {
		this.rnArea = rnArea;
	}

	public String getRnCausaRetardo() {
		return this.rnCausaRetardo;
	}

	public void setRnCausaRetardo(String rnCausaRetardo) {
		this.rnCausaRetardo = rnCausaRetardo;
	}

	public String getRnCentro() {
		return this.rnCentro;
	}

	public void setRnCentro(String rnCentro) {
		this.rnCentro = rnCentro;
	}

	public String getRnChequeo() {
		return this.rnChequeo;
	}

	public void setRnChequeo(String rnChequeo) {
		this.rnChequeo = rnChequeo;
	}

	public BigDecimal getRnComida() {
		return this.rnComida;
	}

	public void setRnComida(BigDecimal rnComida) {
		this.rnComida = rnComida;
	}

	public String getRnEstacion() {
		return this.rnEstacion;
	}

	public void setRnEstacion(String rnEstacion) {
		this.rnEstacion = rnEstacion;
	}

	public LocalDateTime getRnFecModif() {
		return this.rnFecModif;
	}

	public void setRnFecModif(LocalDateTime rnFecModif) {
		this.rnFecModif = rnFecModif;
	}

	public BigDecimal getRnGafete() {
		return this.rnGafete;
	}

	public void setRnGafete(BigDecimal rnGafete) {
		this.rnGafete = rnGafete;
	}

	public LocalDateTime getRnHorEnt() {
		return this.rnHorEnt;
	}

	public void setRnHorEnt(LocalDateTime rnHorEnt) {
		this.rnHorEnt = rnHorEnt;
	}

	public LocalDateTime getRnHorSal() {
		return this.rnHorSal;
	}

	public void setRnHorSal(LocalDateTime rnHorSal) {
		this.rnHorSal = rnHorSal;
	}

	public LocalDateTime getRnHoraModif() {
		return this.rnHoraModif;
	}

	public void setRnHoraModif(LocalDateTime rnHoraModif) {
		this.rnHoraModif = rnHoraModif;
	}

	public String getRnLinea() {
		return this.rnLinea;
	}

	public void setRnLinea(String rnLinea) {
		this.rnLinea = rnLinea;
	}

	public String getRnSts() {
		return this.rnSts;
	}

	public void setRnSts(String rnSts) {
		this.rnSts = rnSts;
	}

	public String getRnUsuario() {
		return this.rnUsuario;
	}

	public void setRnUsuario(String rnUsuario) {
		this.rnUsuario = rnUsuario;
	}

	public BigDecimal getRnZona() {
		return this.rnZona;
	}

	public void setRnZona(BigDecimal rnZona) {
		this.rnZona = rnZona;
	}

	@Override
	public String toString() {
		return "Rn [id=" + id + ", rnArea=" + rnArea + ", rnCausaRetardo=" + rnCausaRetardo + ", rnCentro=" + rnCentro
				+ ", rnChequeo=" + rnChequeo + ", rnComida=" + rnComida + ", rnEstacion=" + rnEstacion + ", rnFecModif="
				+ rnFecModif + ", rnGafete=" + rnGafete + ", rnHorEnt=" + rnHorEnt + ", rnHorSal=" + rnHorSal
				+ ", rnHoraModif=" + rnHoraModif + ", rnLinea=" + rnLinea + ", rnSts=" + rnSts + ", rnUsuario="
				+ rnUsuario + ", rnZona=" + rnZona + "]";
	}
}