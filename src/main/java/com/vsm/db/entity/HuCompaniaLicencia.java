package com.vsm.db.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The persistent class for the HU_COMPANIA database table.
 * 
 */
@Entity
@Table(name="HU_COMPANIA")
@NamedQuery(name="HuCompaniaLicencia.findAll", query="SELECT h FROM HuCompaniaLicencia h")
public class HuCompaniaLicencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NUMERO_COMPANIA")
	private long numeroCompania;

	@Column(name="ADMINISTRACION_TIEMPOS")
	private String administracionTiempos;

	@Column(name="CAPTURA_DESCANSOS")
	private String capturaDescansos;

	@Column(name="CARGA_FOTO")
	private String cargaFoto;

	@Column(name="CIA01_CONSOLIDAR")
	private BigDecimal cia01Consolidar;

	@Column(name="CIA02_CONSOLIDAR")
	private BigDecimal cia02Consolidar;

	@Column(name="CIA03_CONSOLIDAR")
	private BigDecimal cia03Consolidar;

	@Column(name="CIA04_CONSOLIDAR")
	private BigDecimal cia04Consolidar;

	@Column(name="CIA05_CONSOLIDAR")
	private BigDecimal cia05Consolidar;

	@Column(name="CIA06_CONSOLIDAR")
	private BigDecimal cia06Consolidar;

	@Column(name="CIA07_CONSOLIDAR")
	private BigDecimal cia07Consolidar;

	@Column(name="CIA08_CONSOLIDAR")
	private BigDecimal cia08Consolidar;

	@Column(name="CIA09_CONSOLIDAR")
	private BigDecimal cia09Consolidar;

	@Column(name="CIA10_CONSOLIDAR")
	private BigDecimal cia10Consolidar;

	@Column(name="CIA11_CONSOLIDAR")
	private BigDecimal cia11Consolidar;

	@Column(name="CIA12_CONSOLIDAR")
	private BigDecimal cia12Consolidar;

	@Column(name="CIA13_CONSOLIDAR")
	private BigDecimal cia13Consolidar;

	@Column(name="CIA14_CONSOLIDAR")
	private BigDecimal cia14Consolidar;

	@Column(name="CIA15_CONSOLIDAR")
	private BigDecimal cia15Consolidar;

	@Column(name="CIA16_CONSOLIDAR")
	private BigDecimal cia16Consolidar;

	@Column(name="CIA17_CONSOLIDAR")
	private BigDecimal cia17Consolidar;

	@Column(name="CIA18_CONSOLIDAR")
	private BigDecimal cia18Consolidar;

	@Column(name="CIA19_CONSOLIDAR")
	private BigDecimal cia19Consolidar;

	@Column(name="CIA20_CONSOLIDAR")
	private BigDecimal cia20Consolidar;

	@Column(name="CLAVE_COMPANIA")
	private String claveCompania;

	@Column(name="CLAVE_FIEL")
	private String claveFiel;

	@Column(name="CLAVE_FL")
	private BigDecimal claveFl;

	@Column(name="CLAVE_LLAVE")
	private String claveLlave;

	@Column(name="COMPANIA_GOBIERNO")
	private String companiaGobierno;

	@Column(name="CONTROL_DE")
	private String controlDe;

	@Column(name="CONTROL_ESTACION")
	private String controlEstacion;

	@Column(name="CONTROL_HEADCOUNT")
	private String controlHeadcount;

	private String descr1;

	private String descr2;

	private String descr3;

	private String descr4;

	private String descr5;

	private String descr6;

	@Column(name="DIAS_ANTERIORES_AUS")
	private BigDecimal diasAnterioresAus;

	@Column(name="DIAS_ANTERIORES_INC")
	private BigDecimal diasAnterioresInc;

	@Column(name="DIAS_ANTERIORES_VAC")
	private BigDecimal diasAnterioresVac;

	@Column(name="DIGITO_V_FINAL")
	private BigDecimal digitoVFinal;

	@Column(name="DIGITO_V_INICIAL")
	private BigDecimal digitoVInicial;

	@Column(name="FECHA_MOV")
	private LocalDateTime fechaMov;

	@Column(name="MATRIZ_TOPE")
	private BigDecimal matrizTope;

	@Column(name="MAXIMO_RETARDOS")
	private BigDecimal maximoRetardos;

	private BigDecimal nivel1;

	private BigDecimal nivel2;

	private BigDecimal nivel3;

	private BigDecimal nivel4;

	private BigDecimal nivel5;

	private BigDecimal nivel6;

	@Column(name="NUM_CLIENTE")
	private BigDecimal numCliente;

	@Column(name="NUM_EMP")
	private BigDecimal numEmp;

	@Column(name="PASSWORD_COMPANIA")
	private String passwordCompania;

	@Column(name="PATH_CERTIFICADO")
	private String pathCertificado;

	@Column(name="PATH_CERTIFICADO_FIEL")
	private String pathCertificadoFiel;

	@Column(name="PATH_FIEL")
	private String pathFiel;

	@Column(name="PATH_LLAVE")
	private String pathLlave;

	@Column(name="PATH_LOGO")
	private String pathLogo;

	@Column(name="PRIMA_CONTROL_ASIST")
	private String primaControlAsist;

	@Column(name="RAZON_SOCIAL")
	private String razonSocial;

	@Column(name="SEGURIDAD_NIP")
	private String seguridadNip;

	private String status;

	@Column(name="USER_ID")
	private String userId;
	
	@Column(name="SIGLAS_CLIENTE")
	private String siglasCliente;

	//uni-directional one-to-one association to HuCatCoGral
	@OneToOne
	@JoinColumn(name="NUMERO_COMPANIA")
	private HuCatCoGral huCatCoGral;

	public HuCompaniaLicencia() {
	}

	public long getNumeroCompania() {
		return this.numeroCompania;
	}

	public void setNumeroCompania(long numeroCompania) {
		this.numeroCompania = numeroCompania;
	}

	public String getAdministracionTiempos() {
		return this.administracionTiempos;
	}

	public void setAdministracionTiempos(String administracionTiempos) {
		this.administracionTiempos = administracionTiempos;
	}

	public String getCapturaDescansos() {
		return this.capturaDescansos;
	}

	public void setCapturaDescansos(String capturaDescansos) {
		this.capturaDescansos = capturaDescansos;
	}

	public String getCargaFoto() {
		return this.cargaFoto;
	}

	public void setCargaFoto(String cargaFoto) {
		this.cargaFoto = cargaFoto;
	}

	public BigDecimal getCia01Consolidar() {
		return this.cia01Consolidar;
	}

	public void setCia01Consolidar(BigDecimal cia01Consolidar) {
		this.cia01Consolidar = cia01Consolidar;
	}

	public BigDecimal getCia02Consolidar() {
		return this.cia02Consolidar;
	}

	public void setCia02Consolidar(BigDecimal cia02Consolidar) {
		this.cia02Consolidar = cia02Consolidar;
	}

	public BigDecimal getCia03Consolidar() {
		return this.cia03Consolidar;
	}

	public void setCia03Consolidar(BigDecimal cia03Consolidar) {
		this.cia03Consolidar = cia03Consolidar;
	}

	public BigDecimal getCia04Consolidar() {
		return this.cia04Consolidar;
	}

	public void setCia04Consolidar(BigDecimal cia04Consolidar) {
		this.cia04Consolidar = cia04Consolidar;
	}

	public BigDecimal getCia05Consolidar() {
		return this.cia05Consolidar;
	}

	public void setCia05Consolidar(BigDecimal cia05Consolidar) {
		this.cia05Consolidar = cia05Consolidar;
	}

	public BigDecimal getCia06Consolidar() {
		return this.cia06Consolidar;
	}

	public void setCia06Consolidar(BigDecimal cia06Consolidar) {
		this.cia06Consolidar = cia06Consolidar;
	}

	public BigDecimal getCia07Consolidar() {
		return this.cia07Consolidar;
	}

	public void setCia07Consolidar(BigDecimal cia07Consolidar) {
		this.cia07Consolidar = cia07Consolidar;
	}

	public BigDecimal getCia08Consolidar() {
		return this.cia08Consolidar;
	}

	public void setCia08Consolidar(BigDecimal cia08Consolidar) {
		this.cia08Consolidar = cia08Consolidar;
	}

	public BigDecimal getCia09Consolidar() {
		return this.cia09Consolidar;
	}

	public void setCia09Consolidar(BigDecimal cia09Consolidar) {
		this.cia09Consolidar = cia09Consolidar;
	}

	public BigDecimal getCia10Consolidar() {
		return this.cia10Consolidar;
	}

	public void setCia10Consolidar(BigDecimal cia10Consolidar) {
		this.cia10Consolidar = cia10Consolidar;
	}

	public BigDecimal getCia11Consolidar() {
		return this.cia11Consolidar;
	}

	public void setCia11Consolidar(BigDecimal cia11Consolidar) {
		this.cia11Consolidar = cia11Consolidar;
	}

	public BigDecimal getCia12Consolidar() {
		return this.cia12Consolidar;
	}

	public void setCia12Consolidar(BigDecimal cia12Consolidar) {
		this.cia12Consolidar = cia12Consolidar;
	}

	public BigDecimal getCia13Consolidar() {
		return this.cia13Consolidar;
	}

	public void setCia13Consolidar(BigDecimal cia13Consolidar) {
		this.cia13Consolidar = cia13Consolidar;
	}

	public BigDecimal getCia14Consolidar() {
		return this.cia14Consolidar;
	}

	public void setCia14Consolidar(BigDecimal cia14Consolidar) {
		this.cia14Consolidar = cia14Consolidar;
	}

	public BigDecimal getCia15Consolidar() {
		return this.cia15Consolidar;
	}

	public void setCia15Consolidar(BigDecimal cia15Consolidar) {
		this.cia15Consolidar = cia15Consolidar;
	}

	public BigDecimal getCia16Consolidar() {
		return this.cia16Consolidar;
	}

	public void setCia16Consolidar(BigDecimal cia16Consolidar) {
		this.cia16Consolidar = cia16Consolidar;
	}

	public BigDecimal getCia17Consolidar() {
		return this.cia17Consolidar;
	}

	public void setCia17Consolidar(BigDecimal cia17Consolidar) {
		this.cia17Consolidar = cia17Consolidar;
	}

	public BigDecimal getCia18Consolidar() {
		return this.cia18Consolidar;
	}

	public void setCia18Consolidar(BigDecimal cia18Consolidar) {
		this.cia18Consolidar = cia18Consolidar;
	}

	public BigDecimal getCia19Consolidar() {
		return this.cia19Consolidar;
	}

	public void setCia19Consolidar(BigDecimal cia19Consolidar) {
		this.cia19Consolidar = cia19Consolidar;
	}

	public BigDecimal getCia20Consolidar() {
		return this.cia20Consolidar;
	}

	public void setCia20Consolidar(BigDecimal cia20Consolidar) {
		this.cia20Consolidar = cia20Consolidar;
	}

	public String getClaveCompania() {
		return this.claveCompania;
	}

	public void setClaveCompania(String claveCompania) {
		this.claveCompania = claveCompania;
	}

	public String getClaveFiel() {
		return this.claveFiel;
	}

	public void setClaveFiel(String claveFiel) {
		this.claveFiel = claveFiel;
	}

	public BigDecimal getClaveFl() {
		return this.claveFl;
	}

	public void setClaveFl(BigDecimal claveFl) {
		this.claveFl = claveFl;
	}

	public String getClaveLlave() {
		return this.claveLlave;
	}

	public void setClaveLlave(String claveLlave) {
		this.claveLlave = claveLlave;
	}

	public String getCompaniaGobierno() {
		return this.companiaGobierno;
	}

	public void setCompaniaGobierno(String companiaGobierno) {
		this.companiaGobierno = companiaGobierno;
	}

	public String getControlDe() {
		return this.controlDe;
	}

	public void setControlDe(String controlDe) {
		this.controlDe = controlDe;
	}

	public String getControlEstacion() {
		return this.controlEstacion;
	}

	public void setControlEstacion(String controlEstacion) {
		this.controlEstacion = controlEstacion;
	}

	public String getControlHeadcount() {
		return this.controlHeadcount;
	}

	public void setControlHeadcount(String controlHeadcount) {
		this.controlHeadcount = controlHeadcount;
	}

	public String getDescr1() {
		return this.descr1;
	}

	public void setDescr1(String descr1) {
		this.descr1 = descr1;
	}

	public String getDescr2() {
		return this.descr2;
	}

	public void setDescr2(String descr2) {
		this.descr2 = descr2;
	}

	public String getDescr3() {
		return this.descr3;
	}

	public void setDescr3(String descr3) {
		this.descr3 = descr3;
	}

	public String getDescr4() {
		return this.descr4;
	}

	public void setDescr4(String descr4) {
		this.descr4 = descr4;
	}

	public String getDescr5() {
		return this.descr5;
	}

	public void setDescr5(String descr5) {
		this.descr5 = descr5;
	}

	public String getDescr6() {
		return this.descr6;
	}

	public void setDescr6(String descr6) {
		this.descr6 = descr6;
	}

	public BigDecimal getDiasAnterioresAus() {
		return this.diasAnterioresAus;
	}

	public void setDiasAnterioresAus(BigDecimal diasAnterioresAus) {
		this.diasAnterioresAus = diasAnterioresAus;
	}

	public BigDecimal getDiasAnterioresInc() {
		return this.diasAnterioresInc;
	}

	public void setDiasAnterioresInc(BigDecimal diasAnterioresInc) {
		this.diasAnterioresInc = diasAnterioresInc;
	}

	public BigDecimal getDiasAnterioresVac() {
		return this.diasAnterioresVac;
	}

	public void setDiasAnterioresVac(BigDecimal diasAnterioresVac) {
		this.diasAnterioresVac = diasAnterioresVac;
	}

	public BigDecimal getDigitoVFinal() {
		return this.digitoVFinal;
	}

	public void setDigitoVFinal(BigDecimal digitoVFinal) {
		this.digitoVFinal = digitoVFinal;
	}

	public BigDecimal getDigitoVInicial() {
		return this.digitoVInicial;
	}

	public void setDigitoVInicial(BigDecimal digitoVInicial) {
		this.digitoVInicial = digitoVInicial;
	}

	public LocalDateTime getFechaMov() {
		return this.fechaMov;
	}

	public void setFechaMov(LocalDateTime fechaMov) {
		this.fechaMov = fechaMov;
	}

	public BigDecimal getMatrizTope() {
		return this.matrizTope;
	}

	public void setMatrizTope(BigDecimal matrizTope) {
		this.matrizTope = matrizTope;
	}

	public BigDecimal getMaximoRetardos() {
		return this.maximoRetardos;
	}

	public void setMaximoRetardos(BigDecimal maximoRetardos) {
		this.maximoRetardos = maximoRetardos;
	}

	public BigDecimal getNivel1() {
		return this.nivel1;
	}

	public void setNivel1(BigDecimal nivel1) {
		this.nivel1 = nivel1;
	}

	public BigDecimal getNivel2() {
		return this.nivel2;
	}

	public void setNivel2(BigDecimal nivel2) {
		this.nivel2 = nivel2;
	}

	public BigDecimal getNivel3() {
		return this.nivel3;
	}

	public void setNivel3(BigDecimal nivel3) {
		this.nivel3 = nivel3;
	}

	public BigDecimal getNivel4() {
		return this.nivel4;
	}

	public void setNivel4(BigDecimal nivel4) {
		this.nivel4 = nivel4;
	}

	public BigDecimal getNivel5() {
		return this.nivel5;
	}

	public void setNivel5(BigDecimal nivel5) {
		this.nivel5 = nivel5;
	}

	public BigDecimal getNivel6() {
		return this.nivel6;
	}

	public void setNivel6(BigDecimal nivel6) {
		this.nivel6 = nivel6;
	}

	public BigDecimal getNumCliente() {
		return this.numCliente;
	}

	public void setNumCliente(BigDecimal numCliente) {
		this.numCliente = numCliente;
	}

	public BigDecimal getNumEmp() {
		return this.numEmp;
	}

	public void setNumEmp(BigDecimal numEmp) {
		this.numEmp = numEmp;
	}

	public String getPasswordCompania() {
		return this.passwordCompania;
	}

	public void setPasswordCompania(String passwordCompania) {
		this.passwordCompania = passwordCompania;
	}

	public String getPathCertificado() {
		return this.pathCertificado;
	}

	public void setPathCertificado(String pathCertificado) {
		this.pathCertificado = pathCertificado;
	}

	public String getPathCertificadoFiel() {
		return this.pathCertificadoFiel;
	}

	public void setPathCertificadoFiel(String pathCertificadoFiel) {
		this.pathCertificadoFiel = pathCertificadoFiel;
	}

	public String getPathFiel() {
		return this.pathFiel;
	}

	public void setPathFiel(String pathFiel) {
		this.pathFiel = pathFiel;
	}

	public String getPathLlave() {
		return this.pathLlave;
	}

	public void setPathLlave(String pathLlave) {
		this.pathLlave = pathLlave;
	}

	public String getPathLogo() {
		return this.pathLogo;
	}

	public void setPathLogo(String pathLogo) {
		this.pathLogo = pathLogo;
	}

	public String getPrimaControlAsist() {
		return this.primaControlAsist;
	}

	public void setPrimaControlAsist(String primaControlAsist) {
		this.primaControlAsist = primaControlAsist;
	}

	public String getRazonSocial() {
		return this.razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getSeguridadNip() {
		return this.seguridadNip;
	}

	public void setSeguridadNip(String seguridadNip) {
		this.seguridadNip = seguridadNip;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public HuCatCoGral getHuCatCoGral() {
		return this.huCatCoGral;
	}

	public void setHuCatCoGral(HuCatCoGral huCatCoGral) {
		this.huCatCoGral = huCatCoGral;
	}

	public String getSiglasCliente() {
		return siglasCliente;
	}

	public void setSiglasCliente(String siglasCliente) {
		this.siglasCliente = siglasCliente;
	}
}