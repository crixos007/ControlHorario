package com.vsm.db.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the HU_RUTAS_FILE_SERVER database table.
 * 
 */
@Entity
@Table(name="HU_RUTAS_FILE_SERVER")
@NamedQuery(name="HuRutasFileServer.findAll", query="SELECT h FROM HuRutasFileServer h")
public class HuRutasFileServer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CLAVE_RUTA")
	private String claveRuta;

	private String descripcion;

	@Column(name="FECHA_MOV")
	private LocalDateTime fechaMov;

	private String status;

	private String usuario;

	@Column(name="VALOR_RUTA")
	private String valorRuta;

	public HuRutasFileServer() {
	}

	public String getClaveRuta() {
		return this.claveRuta;
	}

	public void setClaveRuta(String claveRuta) {
		this.claveRuta = claveRuta;
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getValorRuta() {
		return this.valorRuta;
	}

	public void setValorRuta(String valorRuta) {
		this.valorRuta = valorRuta;
	}

}