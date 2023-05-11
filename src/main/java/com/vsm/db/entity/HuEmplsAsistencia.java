package com.vsm.db.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the HU_EMPLS_ASISTENCIA database table.
 * 
 */
@Entity
@Table(name="HU_EMPLS_ASISTENCIA")
@NamedQuery(name="HuEmplsAsistencia.findAll", query="SELECT h FROM HuEmplsAsistencia h")
public class HuEmplsAsistencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HuEmplsAsistenciaPK id;

	@Column(name="AS_MES01")
	private String asMes01;

	@Column(name="AS_MES02")
	private String asMes02;

	@Column(name="AS_MES03")
	private String asMes03;

	@Column(name="AS_MES04")
	private String asMes04;

	@Column(name="AS_MES05")
	private String asMes05;

	@Column(name="AS_MES06")
	private String asMes06;

	@Column(name="AS_MES07")
	private String asMes07;

	@Column(name="AS_MES08")
	private String asMes08;

	@Column(name="AS_MES09")
	private String asMes09;

	@Column(name="AS_MES10")
	private String asMes10;

	@Column(name="AS_MES11")
	private String asMes11;

	@Column(name="AS_MES12")
	private String asMes12;

	public HuEmplsAsistencia() {
	}

	public HuEmplsAsistenciaPK getId() {
		return this.id;
	}

	public void setId(HuEmplsAsistenciaPK id) {
		this.id = id;
	}

	public String getAsMes01() {
		return this.asMes01;
	}

	public void setAsMes01(String asMes01) {
		this.asMes01 = asMes01;
	}

	public String getAsMes02() {
		return this.asMes02;
	}

	public void setAsMes02(String asMes02) {
		this.asMes02 = asMes02;
	}

	public String getAsMes03() {
		return this.asMes03;
	}

	public void setAsMes03(String asMes03) {
		this.asMes03 = asMes03;
	}

	public String getAsMes04() {
		return this.asMes04;
	}

	public void setAsMes04(String asMes04) {
		this.asMes04 = asMes04;
	}

	public String getAsMes05() {
		return this.asMes05;
	}

	public void setAsMes05(String asMes05) {
		this.asMes05 = asMes05;
	}

	public String getAsMes06() {
		return this.asMes06;
	}

	public void setAsMes06(String asMes06) {
		this.asMes06 = asMes06;
	}

	public String getAsMes07() {
		return this.asMes07;
	}

	public void setAsMes07(String asMes07) {
		this.asMes07 = asMes07;
	}

	public String getAsMes08() {
		return this.asMes08;
	}

	public void setAsMes08(String asMes08) {
		this.asMes08 = asMes08;
	}

	public String getAsMes09() {
		return this.asMes09;
	}

	public void setAsMes09(String asMes09) {
		this.asMes09 = asMes09;
	}

	public String getAsMes10() {
		return this.asMes10;
	}

	public void setAsMes10(String asMes10) {
		this.asMes10 = asMes10;
	}

	public String getAsMes11() {
		return this.asMes11;
	}

	public void setAsMes11(String asMes11) {
		this.asMes11 = asMes11;
	}

	public String getAsMes12() {
		return this.asMes12;
	}

	public void setAsMes12(String asMes12) {
		this.asMes12 = asMes12;
	}

}