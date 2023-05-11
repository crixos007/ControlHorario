package com.vsm.db.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the HD database table.
 * 
 */
@Entity
@NamedQuery(name="Hd.findAll", query="SELECT h FROM Hd h")
public class Hd implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HdPK id;

	@Column(name="HD_MES01")
	private String hdMes01;

	@Column(name="HD_MES02")
	private String hdMes02;

	@Column(name="HD_MES03")
	private String hdMes03;

	@Column(name="HD_MES04")
	private String hdMes04;

	@Column(name="HD_MES05")
	private String hdMes05;

	@Column(name="HD_MES06")
	private String hdMes06;

	@Column(name="HD_MES07")
	private String hdMes07;

	@Column(name="HD_MES08")
	private String hdMes08;

	@Column(name="HD_MES09")
	private String hdMes09;

	@Column(name="HD_MES10")
	private String hdMes10;

	@Column(name="HD_MES11")
	private String hdMes11;

	@Column(name="HD_MES12")
	private String hdMes12;

	public Hd() {
	}

	public HdPK getId() {
		return this.id;
	}

	public void setId(HdPK id) {
		this.id = id;
	}

	public String getHdMes01() {
		return this.hdMes01;
	}

	public void setHdMes01(String hdMes01) {
		this.hdMes01 = hdMes01;
	}

	public String getHdMes02() {
		return this.hdMes02;
	}

	public void setHdMes02(String hdMes02) {
		this.hdMes02 = hdMes02;
	}

	public String getHdMes03() {
		return this.hdMes03;
	}

	public void setHdMes03(String hdMes03) {
		this.hdMes03 = hdMes03;
	}

	public String getHdMes04() {
		return this.hdMes04;
	}

	public void setHdMes04(String hdMes04) {
		this.hdMes04 = hdMes04;
	}

	public String getHdMes05() {
		return this.hdMes05;
	}

	public void setHdMes05(String hdMes05) {
		this.hdMes05 = hdMes05;
	}

	public String getHdMes06() {
		return this.hdMes06;
	}

	public void setHdMes06(String hdMes06) {
		this.hdMes06 = hdMes06;
	}

	public String getHdMes07() {
		return this.hdMes07;
	}

	public void setHdMes07(String hdMes07) {
		this.hdMes07 = hdMes07;
	}

	public String getHdMes08() {
		return this.hdMes08;
	}

	public void setHdMes08(String hdMes08) {
		this.hdMes08 = hdMes08;
	}

	public String getHdMes09() {
		return this.hdMes09;
	}

	public void setHdMes09(String hdMes09) {
		this.hdMes09 = hdMes09;
	}

	public String getHdMes10() {
		return this.hdMes10;
	}

	public void setHdMes10(String hdMes10) {
		this.hdMes10 = hdMes10;
	}

	public String getHdMes11() {
		return this.hdMes11;
	}

	public void setHdMes11(String hdMes11) {
		this.hdMes11 = hdMes11;
	}

	public String getHdMes12() {
		return this.hdMes12;
	}

	public void setHdMes12(String hdMes12) {
		this.hdMes12 = hdMes12;
	}

}