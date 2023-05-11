package com.vsm.db.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the PR database table.
 * 
 */
@Entity
@NamedQuery(name="Pr.findAll", query="SELECT p FROM Pr p")
public class Pr implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PrPK id;

	@Column(name="PR_CONCEPTO")
	private BigDecimal prConcepto;

	@Column(name="PR_FECHA_CAP")
	private LocalDateTime prFechaCap;

	@Column(name="PR_HORA_CAP")
	private LocalDateTime prHoraCap;

	@Column(name="PR_HRS_PER1")
	private BigDecimal prHrsPer1;

	@Column(name="PR_HRS_PER2")
	private BigDecimal prHrsPer2;

	@Column(name="PR_MIN_RETARDO")
	private BigDecimal prMinRetardo;

	@Column(name="PR_OBSERVACION")
	private String prObservacion;

	@Column(name="PR_PERM_HH_FIN")
	private LocalDateTime prPermHhFin;

	@Column(name="PR_STS")
	private String prSts;

	@Column(name="PR_USUARIO")
	private String prUsuario;

	public Pr() {
	}

	public PrPK getId() {
		return this.id;
	}

	public void setId(PrPK id) {
		this.id = id;
	}

	public BigDecimal getPrConcepto() {
		return this.prConcepto;
	}

	public void setPrConcepto(BigDecimal prConcepto) {
		this.prConcepto = prConcepto;
	}

	public LocalDateTime getPrFechaCap() {
		return this.prFechaCap;
	}

	public void setPrFechaCap(LocalDateTime prFechaCap) {
		this.prFechaCap = prFechaCap;
	}

	public LocalDateTime getPrHoraCap() {
		return this.prHoraCap;
	}

	public void setPrHoraCap(LocalDateTime prHoraCap) {
		this.prHoraCap = prHoraCap;
	}

	public BigDecimal getPrHrsPer1() {
		return this.prHrsPer1;
	}

	public void setPrHrsPer1(BigDecimal prHrsPer1) {
		this.prHrsPer1 = prHrsPer1;
	}

	public BigDecimal getPrHrsPer2() {
		return this.prHrsPer2;
	}

	public void setPrHrsPer2(BigDecimal prHrsPer2) {
		this.prHrsPer2 = prHrsPer2;
	}

	public BigDecimal getPrMinRetardo() {
		return this.prMinRetardo;
	}

	public void setPrMinRetardo(BigDecimal prMinRetardo) {
		this.prMinRetardo = prMinRetardo;
	}

	public String getPrObservacion() {
		return this.prObservacion;
	}

	public void setPrObservacion(String prObservacion) {
		this.prObservacion = prObservacion;
	}

	public LocalDateTime getPrPermHhFin() {
		return this.prPermHhFin;
	}

	public void setPrPermHhFin(LocalDateTime prPermHhFin) {
		this.prPermHhFin = prPermHhFin;
	}

	public String getPrSts() {
		return this.prSts;
	}

	public void setPrSts(String prSts) {
		this.prSts = prSts;
	}

	public String getPrUsuario() {
		return this.prUsuario;
	}

	public void setPrUsuario(String prUsuario) {
		this.prUsuario = prUsuario;
	}

	@Override
	public String toString() {
		return "Pr [id=" + id + ", prConcepto=" + prConcepto + ", prFechaCap=" + prFechaCap + ", prHoraCap=" + prHoraCap
				+ ", prHrsPer1=" + prHrsPer1 + ", prHrsPer2=" + prHrsPer2 + ", prMinRetardo=" + prMinRetardo
				+ ", prObservacion=" + prObservacion + ", prPermHhFin=" + prPermHhFin + ", prSts=" + prSts
				+ ", prUsuario=" + prUsuario + "]";
	}
}