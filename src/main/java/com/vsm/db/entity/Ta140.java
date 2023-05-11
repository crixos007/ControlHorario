package com.vsm.db.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the TA140 database table.
 * 
 */
@Entity
@NamedQuery(name="Ta140.findAll", query="SELECT t FROM Ta140 t")
public class Ta140 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Ta140PK id;

	@Column(name="TA140_AVANZA_CHECADA")
	private String ta140AvanzaChecada;

	@Column(name="TA140_AVANZA_T_EXTRA")
	private String ta140AvanzaTExtra;

	@Column(name="TA140_DESC_DOM")
	private String ta140DescDom;

	@Column(name="TA140_DESC_JUE")
	private String ta140DescJue;

	@Column(name="TA140_DESC_LUN")
	private String ta140DescLun;

	@Column(name="TA140_DESC_MAR")
	private String ta140DescMar;

	@Column(name="TA140_DESC_MIE")
	private String ta140DescMie;

	@Column(name="TA140_DESC_SAB")
	private String ta140DescSab;

	@Column(name="TA140_DESC_VIE")
	private String ta140DescVie;

	@Column(name="TA140_FECHA_MOV")
	private LocalDateTime ta140FechaMov;

	@Column(name="TA140_GPO_PARM")
	private String ta140GpoParm;

	@Column(name="TA140_HORA_E1_DOM")
	private LocalDateTime ta140HoraE1Dom;

	@Column(name="TA140_HORA_E1_JUE")
	private LocalDateTime ta140HoraE1Jue;

	@Column(name="TA140_HORA_E1_LUN")
	private LocalDateTime ta140HoraE1Lun;

	@Column(name="TA140_HORA_E1_MAR")
	private LocalDateTime ta140HoraE1Mar;

	@Column(name="TA140_HORA_E1_MIE")
	private LocalDateTime ta140HoraE1Mie;

	@Column(name="TA140_HORA_E1_SAB")
	private LocalDateTime ta140HoraE1Sab;

	@Column(name="TA140_HORA_E1_VIE")
	private LocalDateTime ta140HoraE1Vie;

	@Column(name="TA140_HORA_E2_DOM")
	private LocalDateTime ta140HoraE2Dom;

	@Column(name="TA140_HORA_E2_JUE")
	private LocalDateTime ta140HoraE2Jue;

	@Column(name="TA140_HORA_E2_LUN")
	private LocalDateTime ta140HoraE2Lun;

	@Column(name="TA140_HORA_E2_MAR")
	private LocalDateTime ta140HoraE2Mar;

	@Column(name="TA140_HORA_E2_MIE")
	private LocalDateTime ta140HoraE2Mie;

	@Column(name="TA140_HORA_E2_SAB")
	private LocalDateTime ta140HoraE2Sab;

	@Column(name="TA140_HORA_E2_VIE")
	private LocalDateTime ta140HoraE2Vie;

	@Column(name="TA140_HORA_EC1_DOM")
	private LocalDateTime ta140HoraEc1Dom;

	@Column(name="TA140_HORA_EC1_JUE")
	private LocalDateTime ta140HoraEc1Jue;

	@Column(name="TA140_HORA_EC1_LUN")
	private LocalDateTime ta140HoraEc1Lun;

	@Column(name="TA140_HORA_EC1_MAR")
	private LocalDateTime ta140HoraEc1Mar;

	@Column(name="TA140_HORA_EC1_MIE")
	private LocalDateTime ta140HoraEc1Mie;

	@Column(name="TA140_HORA_EC1_SAB")
	private LocalDateTime ta140HoraEc1Sab;

	@Column(name="TA140_HORA_EC1_VIE")
	private LocalDateTime ta140HoraEc1Vie;

	@Column(name="TA140_HORA_EC2_DOM")
	private LocalDateTime ta140HoraEc2Dom;

	@Column(name="TA140_HORA_EC2_JUE")
	private LocalDateTime ta140HoraEc2Jue;

	@Column(name="TA140_HORA_EC2_LUN")
	private LocalDateTime ta140HoraEc2Lun;

	@Column(name="TA140_HORA_EC2_MAR")
	private LocalDateTime ta140HoraEc2Mar;

	@Column(name="TA140_HORA_EC2_MIE")
	private LocalDateTime ta140HoraEc2Mie;

	@Column(name="TA140_HORA_EC2_SAB")
	private LocalDateTime ta140HoraEc2Sab;

	@Column(name="TA140_HORA_EC2_VIE")
	private LocalDateTime ta140HoraEc2Vie;

	@Column(name="TA140_HORA_S1_DOM")
	private LocalDateTime ta140HoraS1Dom;

	@Column(name="TA140_HORA_S1_JUE")
	private LocalDateTime ta140HoraS1Jue;

	@Column(name="TA140_HORA_S1_LUN")
	private LocalDateTime ta140HoraS1Lun;

	@Column(name="TA140_HORA_S1_MAR")
	private LocalDateTime ta140HoraS1Mar;

	@Column(name="TA140_HORA_S1_MIE")
	private LocalDateTime ta140HoraS1Mie;

	@Column(name="TA140_HORA_S1_SAB")
	private LocalDateTime ta140HoraS1Sab;

	@Column(name="TA140_HORA_S1_VIE")
	private LocalDateTime ta140HoraS1Vie;

	@Column(name="TA140_HORA_S2_DOM")
	private LocalDateTime ta140HoraS2Dom;

	@Column(name="TA140_HORA_S2_JUE")
	private LocalDateTime ta140HoraS2Jue;

	@Column(name="TA140_HORA_S2_LUN")
	private LocalDateTime ta140HoraS2Lun;

	@Column(name="TA140_HORA_S2_MAR")
	private LocalDateTime ta140HoraS2Mar;

	@Column(name="TA140_HORA_S2_MIE")
	private LocalDateTime ta140HoraS2Mie;

	@Column(name="TA140_HORA_S2_SAB")
	private LocalDateTime ta140HoraS2Sab;

	@Column(name="TA140_HORA_S2_VIE")
	private LocalDateTime ta140HoraS2Vie;

	@Column(name="TA140_HORA_SC1_DOM")
	private LocalDateTime ta140HoraSc1Dom;

	@Column(name="TA140_HORA_SC1_JUE")
	private LocalDateTime ta140HoraSc1Jue;

	@Column(name="TA140_HORA_SC1_LUN")
	private LocalDateTime ta140HoraSc1Lun;

	@Column(name="TA140_HORA_SC1_MAR")
	private LocalDateTime ta140HoraSc1Mar;

	@Column(name="TA140_HORA_SC1_MIE")
	private LocalDateTime ta140HoraSc1Mie;

	@Column(name="TA140_HORA_SC1_SAB")
	private LocalDateTime ta140HoraSc1Sab;

	@Column(name="TA140_HORA_SC1_VIE")
	private LocalDateTime ta140HoraSc1Vie;

	@Column(name="TA140_HORA_SC2_DOM")
	private LocalDateTime ta140HoraSc2Dom;

	@Column(name="TA140_HORA_SC2_JUE")
	private LocalDateTime ta140HoraSc2Jue;

	@Column(name="TA140_HORA_SC2_LUN")
	private LocalDateTime ta140HoraSc2Lun;

	@Column(name="TA140_HORA_SC2_MAR")
	private LocalDateTime ta140HoraSc2Mar;

	@Column(name="TA140_HORA_SC2_MIE")
	private LocalDateTime ta140HoraSc2Mie;

	@Column(name="TA140_HORA_SC2_SAB")
	private LocalDateTime ta140HoraSc2Sab;

	@Column(name="TA140_HORA_SC2_VIE")
	private LocalDateTime ta140HoraSc2Vie;

	@Column(name="TA140_INICIAL")
	private String ta140Inicial;

	@Column(name="TA140_MIN_CAL_ENT")
	private BigDecimal ta140MinCalEnt;

	@Column(name="TA140_MIN_CAL_SAL")
	private BigDecimal ta140MinCalSal;

	@Column(name="TA140_MIN_COM_DOM")
	private BigDecimal ta140MinComDom;

	@Column(name="TA140_MIN_COM_JUE")
	private BigDecimal ta140MinComJue;

	@Column(name="TA140_MIN_COM_LUN")
	private BigDecimal ta140MinComLun;

	@Column(name="TA140_MIN_COM_MAR")
	private BigDecimal ta140MinComMar;

	@Column(name="TA140_MIN_COM_MIE")
	private BigDecimal ta140MinComMie;

	@Column(name="TA140_MIN_COM_SAB")
	private BigDecimal ta140MinComSab;

	@Column(name="TA140_MIN_COM_VIE")
	private BigDecimal ta140MinComVie;

	@Column(name="TA140_MIN_DIF_HOR")
	private BigDecimal ta140MinDifHor;

	@Column(name="TA140_MIN_TF_S1")
	private BigDecimal ta140MinTfS1;

	@Column(name="TA140_MIN_TIEMPO_FLEXIBLE")
	private BigDecimal ta140MinTiempoFlexible;

	@Column(name="TA140_MIN_TOL")
	private BigDecimal ta140MinTol;

	@Column(name="TA140_SAL_DOM_TRAB")
	private String ta140SalDomTrab;

	@Column(name="TA140_STS")
	private String ta140Sts;

	@Column(name="TA140_TIPO_TURNO")
	private String ta140TipoTurno;

	@Column(name="TA140_TURNO_DESCR")
	private String ta140TurnoDescr;

	@Column(name="TA140_USER_ID")
	private String ta140UserId;

	@Column(name="TA140_VAC_DESC")
	private String ta140VacDesc;

	@Column(name="TA140_VAC_FACTOR")
	private BigDecimal ta140VacFactor;

	public Ta140() {
	}

	public Ta140PK getId() {
		return this.id;
	}

	public void setId(Ta140PK id) {
		this.id = id;
	}

	public String getTa140AvanzaChecada() {
		return this.ta140AvanzaChecada;
	}

	public void setTa140AvanzaChecada(String ta140AvanzaChecada) {
		this.ta140AvanzaChecada = ta140AvanzaChecada;
	}

	public String getTa140AvanzaTExtra() {
		return this.ta140AvanzaTExtra;
	}

	public void setTa140AvanzaTExtra(String ta140AvanzaTExtra) {
		this.ta140AvanzaTExtra = ta140AvanzaTExtra;
	}

	public String getTa140DescDom() {
		return this.ta140DescDom;
	}

	public void setTa140DescDom(String ta140DescDom) {
		this.ta140DescDom = ta140DescDom;
	}

	public String getTa140DescJue() {
		return this.ta140DescJue;
	}

	public void setTa140DescJue(String ta140DescJue) {
		this.ta140DescJue = ta140DescJue;
	}

	public String getTa140DescLun() {
		return this.ta140DescLun;
	}

	public void setTa140DescLun(String ta140DescLun) {
		this.ta140DescLun = ta140DescLun;
	}

	public String getTa140DescMar() {
		return this.ta140DescMar;
	}

	public void setTa140DescMar(String ta140DescMar) {
		this.ta140DescMar = ta140DescMar;
	}

	public String getTa140DescMie() {
		return this.ta140DescMie;
	}

	public void setTa140DescMie(String ta140DescMie) {
		this.ta140DescMie = ta140DescMie;
	}

	public String getTa140DescSab() {
		return this.ta140DescSab;
	}

	public void setTa140DescSab(String ta140DescSab) {
		this.ta140DescSab = ta140DescSab;
	}

	public String getTa140DescVie() {
		return this.ta140DescVie;
	}

	public void setTa140DescVie(String ta140DescVie) {
		this.ta140DescVie = ta140DescVie;
	}

	public LocalDateTime getTa140FechaMov() {
		return this.ta140FechaMov;
	}

	public void setTa140FechaMov(LocalDateTime ta140FechaMov) {
		this.ta140FechaMov = ta140FechaMov;
	}

	public String getTa140GpoParm() {
		return this.ta140GpoParm;
	}

	public void setTa140GpoParm(String ta140GpoParm) {
		this.ta140GpoParm = ta140GpoParm;
	}

	public LocalDateTime getTa140HoraE1Dom() {
		return this.ta140HoraE1Dom;
	}

	public void setTa140HoraE1Dom(LocalDateTime ta140HoraE1Dom) {
		this.ta140HoraE1Dom = ta140HoraE1Dom;
	}

	public LocalDateTime getTa140HoraE1Jue() {
		return this.ta140HoraE1Jue;
	}

	public void setTa140HoraE1Jue(LocalDateTime ta140HoraE1Jue) {
		this.ta140HoraE1Jue = ta140HoraE1Jue;
	}

	public LocalDateTime getTa140HoraE1Lun() {
		return this.ta140HoraE1Lun;
	}

	public void setTa140HoraE1Lun(LocalDateTime ta140HoraE1Lun) {
		this.ta140HoraE1Lun = ta140HoraE1Lun;
	}

	public LocalDateTime getTa140HoraE1Mar() {
		return this.ta140HoraE1Mar;
	}

	public void setTa140HoraE1Mar(LocalDateTime ta140HoraE1Mar) {
		this.ta140HoraE1Mar = ta140HoraE1Mar;
	}

	public LocalDateTime getTa140HoraE1Mie() {
		return this.ta140HoraE1Mie;
	}

	public void setTa140HoraE1Mie(LocalDateTime ta140HoraE1Mie) {
		this.ta140HoraE1Mie = ta140HoraE1Mie;
	}

	public LocalDateTime getTa140HoraE1Sab() {
		return this.ta140HoraE1Sab;
	}

	public void setTa140HoraE1Sab(LocalDateTime ta140HoraE1Sab) {
		this.ta140HoraE1Sab = ta140HoraE1Sab;
	}

	public LocalDateTime getTa140HoraE1Vie() {
		return this.ta140HoraE1Vie;
	}

	public void setTa140HoraE1Vie(LocalDateTime ta140HoraE1Vie) {
		this.ta140HoraE1Vie = ta140HoraE1Vie;
	}

	public LocalDateTime getTa140HoraE2Dom() {
		return this.ta140HoraE2Dom;
	}

	public void setTa140HoraE2Dom(LocalDateTime ta140HoraE2Dom) {
		this.ta140HoraE2Dom = ta140HoraE2Dom;
	}

	public LocalDateTime getTa140HoraE2Jue() {
		return this.ta140HoraE2Jue;
	}

	public void setTa140HoraE2Jue(LocalDateTime ta140HoraE2Jue) {
		this.ta140HoraE2Jue = ta140HoraE2Jue;
	}

	public LocalDateTime getTa140HoraE2Lun() {
		return this.ta140HoraE2Lun;
	}

	public void setTa140HoraE2Lun(LocalDateTime ta140HoraE2Lun) {
		this.ta140HoraE2Lun = ta140HoraE2Lun;
	}

	public LocalDateTime getTa140HoraE2Mar() {
		return this.ta140HoraE2Mar;
	}

	public void setTa140HoraE2Mar(LocalDateTime ta140HoraE2Mar) {
		this.ta140HoraE2Mar = ta140HoraE2Mar;
	}

	public LocalDateTime getTa140HoraE2Mie() {
		return this.ta140HoraE2Mie;
	}

	public void setTa140HoraE2Mie(LocalDateTime ta140HoraE2Mie) {
		this.ta140HoraE2Mie = ta140HoraE2Mie;
	}

	public LocalDateTime getTa140HoraE2Sab() {
		return this.ta140HoraE2Sab;
	}

	public void setTa140HoraE2Sab(LocalDateTime ta140HoraE2Sab) {
		this.ta140HoraE2Sab = ta140HoraE2Sab;
	}

	public LocalDateTime getTa140HoraE2Vie() {
		return this.ta140HoraE2Vie;
	}

	public void setTa140HoraE2Vie(LocalDateTime ta140HoraE2Vie) {
		this.ta140HoraE2Vie = ta140HoraE2Vie;
	}

	public LocalDateTime getTa140HoraEc1Dom() {
		return this.ta140HoraEc1Dom;
	}

	public void setTa140HoraEc1Dom(LocalDateTime ta140HoraEc1Dom) {
		this.ta140HoraEc1Dom = ta140HoraEc1Dom;
	}

	public LocalDateTime getTa140HoraEc1Jue() {
		return this.ta140HoraEc1Jue;
	}

	public void setTa140HoraEc1Jue(LocalDateTime ta140HoraEc1Jue) {
		this.ta140HoraEc1Jue = ta140HoraEc1Jue;
	}

	public LocalDateTime getTa140HoraEc1Lun() {
		return this.ta140HoraEc1Lun;
	}

	public void setTa140HoraEc1Lun(LocalDateTime ta140HoraEc1Lun) {
		this.ta140HoraEc1Lun = ta140HoraEc1Lun;
	}

	public LocalDateTime getTa140HoraEc1Mar() {
		return this.ta140HoraEc1Mar;
	}

	public void setTa140HoraEc1Mar(LocalDateTime ta140HoraEc1Mar) {
		this.ta140HoraEc1Mar = ta140HoraEc1Mar;
	}

	public LocalDateTime getTa140HoraEc1Mie() {
		return this.ta140HoraEc1Mie;
	}

	public void setTa140HoraEc1Mie(LocalDateTime ta140HoraEc1Mie) {
		this.ta140HoraEc1Mie = ta140HoraEc1Mie;
	}

	public LocalDateTime getTa140HoraEc1Sab() {
		return this.ta140HoraEc1Sab;
	}

	public void setTa140HoraEc1Sab(LocalDateTime ta140HoraEc1Sab) {
		this.ta140HoraEc1Sab = ta140HoraEc1Sab;
	}

	public LocalDateTime getTa140HoraEc1Vie() {
		return this.ta140HoraEc1Vie;
	}

	public void setTa140HoraEc1Vie(LocalDateTime ta140HoraEc1Vie) {
		this.ta140HoraEc1Vie = ta140HoraEc1Vie;
	}

	public LocalDateTime getTa140HoraEc2Dom() {
		return this.ta140HoraEc2Dom;
	}

	public void setTa140HoraEc2Dom(LocalDateTime ta140HoraEc2Dom) {
		this.ta140HoraEc2Dom = ta140HoraEc2Dom;
	}

	public LocalDateTime getTa140HoraEc2Jue() {
		return this.ta140HoraEc2Jue;
	}

	public void setTa140HoraEc2Jue(LocalDateTime ta140HoraEc2Jue) {
		this.ta140HoraEc2Jue = ta140HoraEc2Jue;
	}

	public LocalDateTime getTa140HoraEc2Lun() {
		return this.ta140HoraEc2Lun;
	}

	public void setTa140HoraEc2Lun(LocalDateTime ta140HoraEc2Lun) {
		this.ta140HoraEc2Lun = ta140HoraEc2Lun;
	}

	public LocalDateTime getTa140HoraEc2Mar() {
		return this.ta140HoraEc2Mar;
	}

	public void setTa140HoraEc2Mar(LocalDateTime ta140HoraEc2Mar) {
		this.ta140HoraEc2Mar = ta140HoraEc2Mar;
	}

	public LocalDateTime getTa140HoraEc2Mie() {
		return this.ta140HoraEc2Mie;
	}

	public void setTa140HoraEc2Mie(LocalDateTime ta140HoraEc2Mie) {
		this.ta140HoraEc2Mie = ta140HoraEc2Mie;
	}

	public LocalDateTime getTa140HoraEc2Sab() {
		return this.ta140HoraEc2Sab;
	}

	public void setTa140HoraEc2Sab(LocalDateTime ta140HoraEc2Sab) {
		this.ta140HoraEc2Sab = ta140HoraEc2Sab;
	}

	public LocalDateTime getTa140HoraEc2Vie() {
		return this.ta140HoraEc2Vie;
	}

	public void setTa140HoraEc2Vie(LocalDateTime ta140HoraEc2Vie) {
		this.ta140HoraEc2Vie = ta140HoraEc2Vie;
	}

	public LocalDateTime getTa140HoraS1Dom() {
		return this.ta140HoraS1Dom;
	}

	public void setTa140HoraS1Dom(LocalDateTime ta140HoraS1Dom) {
		this.ta140HoraS1Dom = ta140HoraS1Dom;
	}

	public LocalDateTime getTa140HoraS1Jue() {
		return this.ta140HoraS1Jue;
	}

	public void setTa140HoraS1Jue(LocalDateTime ta140HoraS1Jue) {
		this.ta140HoraS1Jue = ta140HoraS1Jue;
	}

	public LocalDateTime getTa140HoraS1Lun() {
		return this.ta140HoraS1Lun;
	}

	public void setTa140HoraS1Lun(LocalDateTime ta140HoraS1Lun) {
		this.ta140HoraS1Lun = ta140HoraS1Lun;
	}

	public LocalDateTime getTa140HoraS1Mar() {
		return this.ta140HoraS1Mar;
	}

	public void setTa140HoraS1Mar(LocalDateTime ta140HoraS1Mar) {
		this.ta140HoraS1Mar = ta140HoraS1Mar;
	}

	public LocalDateTime getTa140HoraS1Mie() {
		return this.ta140HoraS1Mie;
	}

	public void setTa140HoraS1Mie(LocalDateTime ta140HoraS1Mie) {
		this.ta140HoraS1Mie = ta140HoraS1Mie;
	}

	public LocalDateTime getTa140HoraS1Sab() {
		return this.ta140HoraS1Sab;
	}

	public void setTa140HoraS1Sab(LocalDateTime ta140HoraS1Sab) {
		this.ta140HoraS1Sab = ta140HoraS1Sab;
	}

	public LocalDateTime getTa140HoraS1Vie() {
		return this.ta140HoraS1Vie;
	}

	public void setTa140HoraS1Vie(LocalDateTime ta140HoraS1Vie) {
		this.ta140HoraS1Vie = ta140HoraS1Vie;
	}

	public LocalDateTime getTa140HoraS2Dom() {
		return this.ta140HoraS2Dom;
	}

	public void setTa140HoraS2Dom(LocalDateTime ta140HoraS2Dom) {
		this.ta140HoraS2Dom = ta140HoraS2Dom;
	}

	public LocalDateTime getTa140HoraS2Jue() {
		return this.ta140HoraS2Jue;
	}

	public void setTa140HoraS2Jue(LocalDateTime ta140HoraS2Jue) {
		this.ta140HoraS2Jue = ta140HoraS2Jue;
	}

	public LocalDateTime getTa140HoraS2Lun() {
		return this.ta140HoraS2Lun;
	}

	public void setTa140HoraS2Lun(LocalDateTime ta140HoraS2Lun) {
		this.ta140HoraS2Lun = ta140HoraS2Lun;
	}

	public LocalDateTime getTa140HoraS2Mar() {
		return this.ta140HoraS2Mar;
	}

	public void setTa140HoraS2Mar(LocalDateTime ta140HoraS2Mar) {
		this.ta140HoraS2Mar = ta140HoraS2Mar;
	}

	public LocalDateTime getTa140HoraS2Mie() {
		return this.ta140HoraS2Mie;
	}

	public void setTa140HoraS2Mie(LocalDateTime ta140HoraS2Mie) {
		this.ta140HoraS2Mie = ta140HoraS2Mie;
	}

	public LocalDateTime getTa140HoraS2Sab() {
		return this.ta140HoraS2Sab;
	}

	public void setTa140HoraS2Sab(LocalDateTime ta140HoraS2Sab) {
		this.ta140HoraS2Sab = ta140HoraS2Sab;
	}

	public LocalDateTime getTa140HoraS2Vie() {
		return this.ta140HoraS2Vie;
	}

	public void setTa140HoraS2Vie(LocalDateTime ta140HoraS2Vie) {
		this.ta140HoraS2Vie = ta140HoraS2Vie;
	}

	public LocalDateTime getTa140HoraSc1Dom() {
		return this.ta140HoraSc1Dom;
	}

	public void setTa140HoraSc1Dom(LocalDateTime ta140HoraSc1Dom) {
		this.ta140HoraSc1Dom = ta140HoraSc1Dom;
	}

	public LocalDateTime getTa140HoraSc1Jue() {
		return this.ta140HoraSc1Jue;
	}

	public void setTa140HoraSc1Jue(LocalDateTime ta140HoraSc1Jue) {
		this.ta140HoraSc1Jue = ta140HoraSc1Jue;
	}

	public LocalDateTime getTa140HoraSc1Lun() {
		return this.ta140HoraSc1Lun;
	}

	public void setTa140HoraSc1Lun(LocalDateTime ta140HoraSc1Lun) {
		this.ta140HoraSc1Lun = ta140HoraSc1Lun;
	}

	public LocalDateTime getTa140HoraSc1Mar() {
		return this.ta140HoraSc1Mar;
	}

	public void setTa140HoraSc1Mar(LocalDateTime ta140HoraSc1Mar) {
		this.ta140HoraSc1Mar = ta140HoraSc1Mar;
	}

	public LocalDateTime getTa140HoraSc1Mie() {
		return this.ta140HoraSc1Mie;
	}

	public void setTa140HoraSc1Mie(LocalDateTime ta140HoraSc1Mie) {
		this.ta140HoraSc1Mie = ta140HoraSc1Mie;
	}

	public LocalDateTime getTa140HoraSc1Sab() {
		return this.ta140HoraSc1Sab;
	}

	public void setTa140HoraSc1Sab(LocalDateTime ta140HoraSc1Sab) {
		this.ta140HoraSc1Sab = ta140HoraSc1Sab;
	}

	public LocalDateTime getTa140HoraSc1Vie() {
		return this.ta140HoraSc1Vie;
	}

	public void setTa140HoraSc1Vie(LocalDateTime ta140HoraSc1Vie) {
		this.ta140HoraSc1Vie = ta140HoraSc1Vie;
	}

	public LocalDateTime getTa140HoraSc2Dom() {
		return this.ta140HoraSc2Dom;
	}

	public void setTa140HoraSc2Dom(LocalDateTime ta140HoraSc2Dom) {
		this.ta140HoraSc2Dom = ta140HoraSc2Dom;
	}

	public LocalDateTime getTa140HoraSc2Jue() {
		return this.ta140HoraSc2Jue;
	}

	public void setTa140HoraSc2Jue(LocalDateTime ta140HoraSc2Jue) {
		this.ta140HoraSc2Jue = ta140HoraSc2Jue;
	}

	public LocalDateTime getTa140HoraSc2Lun() {
		return this.ta140HoraSc2Lun;
	}

	public void setTa140HoraSc2Lun(LocalDateTime ta140HoraSc2Lun) {
		this.ta140HoraSc2Lun = ta140HoraSc2Lun;
	}

	public LocalDateTime getTa140HoraSc2Mar() {
		return this.ta140HoraSc2Mar;
	}

	public void setTa140HoraSc2Mar(LocalDateTime ta140HoraSc2Mar) {
		this.ta140HoraSc2Mar = ta140HoraSc2Mar;
	}

	public LocalDateTime getTa140HoraSc2Mie() {
		return this.ta140HoraSc2Mie;
	}

	public void setTa140HoraSc2Mie(LocalDateTime ta140HoraSc2Mie) {
		this.ta140HoraSc2Mie = ta140HoraSc2Mie;
	}

	public LocalDateTime getTa140HoraSc2Sab() {
		return this.ta140HoraSc2Sab;
	}

	public void setTa140HoraSc2Sab(LocalDateTime ta140HoraSc2Sab) {
		this.ta140HoraSc2Sab = ta140HoraSc2Sab;
	}

	public LocalDateTime getTa140HoraSc2Vie() {
		return this.ta140HoraSc2Vie;
	}

	public void setTa140HoraSc2Vie(LocalDateTime ta140HoraSc2Vie) {
		this.ta140HoraSc2Vie = ta140HoraSc2Vie;
	}

	public String getTa140Inicial() {
		return this.ta140Inicial;
	}

	public void setTa140Inicial(String ta140Inicial) {
		this.ta140Inicial = ta140Inicial;
	}

	public BigDecimal getTa140MinCalEnt() {
		return this.ta140MinCalEnt;
	}

	public void setTa140MinCalEnt(BigDecimal ta140MinCalEnt) {
		this.ta140MinCalEnt = ta140MinCalEnt;
	}

	public BigDecimal getTa140MinCalSal() {
		return this.ta140MinCalSal;
	}

	public void setTa140MinCalSal(BigDecimal ta140MinCalSal) {
		this.ta140MinCalSal = ta140MinCalSal;
	}

	public BigDecimal getTa140MinComDom() {
		return this.ta140MinComDom;
	}

	public void setTa140MinComDom(BigDecimal ta140MinComDom) {
		this.ta140MinComDom = ta140MinComDom;
	}

	public BigDecimal getTa140MinComJue() {
		return this.ta140MinComJue;
	}

	public void setTa140MinComJue(BigDecimal ta140MinComJue) {
		this.ta140MinComJue = ta140MinComJue;
	}

	public BigDecimal getTa140MinComLun() {
		return this.ta140MinComLun;
	}

	public void setTa140MinComLun(BigDecimal ta140MinComLun) {
		this.ta140MinComLun = ta140MinComLun;
	}

	public BigDecimal getTa140MinComMar() {
		return this.ta140MinComMar;
	}

	public void setTa140MinComMar(BigDecimal ta140MinComMar) {
		this.ta140MinComMar = ta140MinComMar;
	}

	public BigDecimal getTa140MinComMie() {
		return this.ta140MinComMie;
	}

	public void setTa140MinComMie(BigDecimal ta140MinComMie) {
		this.ta140MinComMie = ta140MinComMie;
	}

	public BigDecimal getTa140MinComSab() {
		return this.ta140MinComSab;
	}

	public void setTa140MinComSab(BigDecimal ta140MinComSab) {
		this.ta140MinComSab = ta140MinComSab;
	}

	public BigDecimal getTa140MinComVie() {
		return this.ta140MinComVie;
	}

	public void setTa140MinComVie(BigDecimal ta140MinComVie) {
		this.ta140MinComVie = ta140MinComVie;
	}

	public BigDecimal getTa140MinDifHor() {
		return this.ta140MinDifHor;
	}

	public void setTa140MinDifHor(BigDecimal ta140MinDifHor) {
		this.ta140MinDifHor = ta140MinDifHor;
	}

	public BigDecimal getTa140MinTfS1() {
		return this.ta140MinTfS1;
	}

	public void setTa140MinTfS1(BigDecimal ta140MinTfS1) {
		this.ta140MinTfS1 = ta140MinTfS1;
	}

	public BigDecimal getTa140MinTiempoFlexible() {
		return this.ta140MinTiempoFlexible;
	}

	public void setTa140MinTiempoFlexible(BigDecimal ta140MinTiempoFlexible) {
		this.ta140MinTiempoFlexible = ta140MinTiempoFlexible;
	}

	public BigDecimal getTa140MinTol() {
		return this.ta140MinTol;
	}

	public void setTa140MinTol(BigDecimal ta140MinTol) {
		this.ta140MinTol = ta140MinTol;
	}

	public String getTa140SalDomTrab() {
		return this.ta140SalDomTrab;
	}

	public void setTa140SalDomTrab(String ta140SalDomTrab) {
		this.ta140SalDomTrab = ta140SalDomTrab;
	}

	public String getTa140Sts() {
		return this.ta140Sts;
	}

	public void setTa140Sts(String ta140Sts) {
		this.ta140Sts = ta140Sts;
	}

	public String getTa140TipoTurno() {
		return this.ta140TipoTurno;
	}

	public void setTa140TipoTurno(String ta140TipoTurno) {
		this.ta140TipoTurno = ta140TipoTurno;
	}

	public String getTa140TurnoDescr() {
		return this.ta140TurnoDescr;
	}

	public void setTa140TurnoDescr(String ta140TurnoDescr) {
		this.ta140TurnoDescr = ta140TurnoDescr;
	}

	public String getTa140UserId() {
		return this.ta140UserId;
	}

	public void setTa140UserId(String ta140UserId) {
		this.ta140UserId = ta140UserId;
	}

	public String getTa140VacDesc() {
		return this.ta140VacDesc;
	}

	public void setTa140VacDesc(String ta140VacDesc) {
		this.ta140VacDesc = ta140VacDesc;
	}

	public BigDecimal getTa140VacFactor() {
		return this.ta140VacFactor;
	}

	public void setTa140VacFactor(BigDecimal ta140VacFactor) {
		this.ta140VacFactor = ta140VacFactor;
	}

}