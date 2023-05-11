package com.vsm.db.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the MR database table.
 * 
 */
@Entity
@NamedQuery(name="Mr.findAll", query="SELECT m FROM Mr m")
public class Mr implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MrPK id;

	@Column(name="MR_COND_1")
	private String mrCond1;

	@Column(name="MR_COND_10")
	private String mrCond10;

	@Column(name="MR_COND_2")
	private String mrCond2;

	@Column(name="MR_COND_3")
	private String mrCond3;

	@Column(name="MR_COND_4")
	private String mrCond4;

	@Column(name="MR_COND_5")
	private String mrCond5;

	@Column(name="MR_COND_6")
	private String mrCond6;

	@Column(name="MR_COND_7")
	private String mrCond7;

	@Column(name="MR_COND_8")
	private String mrCond8;

	@Column(name="MR_COND_9")
	private String mrCond9;

	@Column(name="MR_CVE_CAL")
	private BigDecimal mrCveCal;

	@Column(name="MR_CVE_HOR")
	private BigDecimal mrCveHor;

	@Column(name="MR_CVE_TURNO")
	private BigDecimal mrCveTurno;

	@Column(name="MR_DESC_DOM")
	private String mrDescDom;

	@Column(name="MR_DESC_JUE")
	private String mrDescJue;

	@Column(name="MR_DESC_LUN")
	private String mrDescLun;

	@Column(name="MR_DESC_MAR")
	private String mrDescMar;

	@Column(name="MR_DESC_MIE")
	private String mrDescMie;

	@Column(name="MR_DESC_SAB")
	private String mrDescSab;

	@Column(name="MR_DESC_VIE")
	private String mrDescVie;

	@Column(name="MR_FECHA_MOV")
	private LocalDateTime mrFechaMov;

	@Column(name="MR_GAFETE")
	private long mrGafete;

	@Column(name="MR_STS")
	private String mrSts;

	@Column(name="MR_USER_ID")
	private String mrUserId;

	public Mr() {
	}

	public MrPK getId() {
		return this.id;
	}

	public void setId(MrPK id) {
		this.id = id;
	}

	public String getMrCond1() {
		return this.mrCond1;
	}

	public void setMrCond1(String mrCond1) {
		this.mrCond1 = mrCond1;
	}

	public String getMrCond10() {
		return this.mrCond10;
	}

	public void setMrCond10(String mrCond10) {
		this.mrCond10 = mrCond10;
	}

	public String getMrCond2() {
		return this.mrCond2;
	}

	public void setMrCond2(String mrCond2) {
		this.mrCond2 = mrCond2;
	}

	public String getMrCond3() {
		return this.mrCond3;
	}

	public void setMrCond3(String mrCond3) {
		this.mrCond3 = mrCond3;
	}

	public String getMrCond4() {
		return this.mrCond4;
	}

	public void setMrCond4(String mrCond4) {
		this.mrCond4 = mrCond4;
	}

	public String getMrCond5() {
		return this.mrCond5;
	}

	public void setMrCond5(String mrCond5) {
		this.mrCond5 = mrCond5;
	}

	public String getMrCond6() {
		return this.mrCond6;
	}

	public void setMrCond6(String mrCond6) {
		this.mrCond6 = mrCond6;
	}

	public String getMrCond7() {
		return this.mrCond7;
	}

	public void setMrCond7(String mrCond7) {
		this.mrCond7 = mrCond7;
	}

	public String getMrCond8() {
		return this.mrCond8;
	}

	public void setMrCond8(String mrCond8) {
		this.mrCond8 = mrCond8;
	}

	public String getMrCond9() {
		return this.mrCond9;
	}

	public void setMrCond9(String mrCond9) {
		this.mrCond9 = mrCond9;
	}

	public BigDecimal getMrCveCal() {
		return this.mrCveCal;
	}

	public void setMrCveCal(BigDecimal mrCveCal) {
		this.mrCveCal = mrCveCal;
	}

	public BigDecimal getMrCveHor() {
		return this.mrCveHor;
	}

	public void setMrCveHor(BigDecimal mrCveHor) {
		this.mrCveHor = mrCveHor;
	}

	public BigDecimal getMrCveTurno() {
		return this.mrCveTurno;
	}

	public void setMrCveTurno(BigDecimal mrCveTurno) {
		this.mrCveTurno = mrCveTurno;
	}

	public String getMrDescDom() {
		return this.mrDescDom;
	}

	public void setMrDescDom(String mrDescDom) {
		this.mrDescDom = mrDescDom;
	}

	public String getMrDescJue() {
		return this.mrDescJue;
	}

	public void setMrDescJue(String mrDescJue) {
		this.mrDescJue = mrDescJue;
	}

	public String getMrDescLun() {
		return this.mrDescLun;
	}

	public void setMrDescLun(String mrDescLun) {
		this.mrDescLun = mrDescLun;
	}

	public String getMrDescMar() {
		return this.mrDescMar;
	}

	public void setMrDescMar(String mrDescMar) {
		this.mrDescMar = mrDescMar;
	}

	public String getMrDescMie() {
		return this.mrDescMie;
	}

	public void setMrDescMie(String mrDescMie) {
		this.mrDescMie = mrDescMie;
	}

	public String getMrDescSab() {
		return this.mrDescSab;
	}

	public void setMrDescSab(String mrDescSab) {
		this.mrDescSab = mrDescSab;
	}

	public String getMrDescVie() {
		return this.mrDescVie;
	}

	public void setMrDescVie(String mrDescVie) {
		this.mrDescVie = mrDescVie;
	}

	public LocalDateTime getMrFechaMov() {
		return this.mrFechaMov;
	}

	public void setMrFechaMov(LocalDateTime mrFechaMov) {
		this.mrFechaMov = mrFechaMov;
	}

	public long getMrGafete() {
		return this.mrGafete;
	}

	public void setMrGafete(long mrGafete) {
		this.mrGafete = mrGafete;
	}

	public String getMrSts() {
		return this.mrSts;
	}

	public void setMrSts(String mrSts) {
		this.mrSts = mrSts;
	}

	public String getMrUserId() {
		return this.mrUserId;
	}

	public void setMrUserId(String mrUserId) {
		this.mrUserId = mrUserId;
	}

}