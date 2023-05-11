package com.vsm.db.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the HU_CAT_CO_GRAL database table.
 * 
 */
@Entity
@Table(name="HU_CAT_CO_GRAL")
@NamedQuery(name="HuCatCoGral.findAll", query="SELECT h FROM HuCatCoGral h")
public class HuCatCoGral implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NUM_CIA")
	private long numCia;

	@Column(name="ANIO_PRESUP_REF")
	private String anioPresupRef;

	@Column(name="ASOCIAR_CONTRATO_PLAZA")
	private String asociarContratoPlaza;

	@Column(name="BANCA_CENTRALIZADA")
	private String bancaCentralizada;

	@Column(name="CALC_INTEGRADOS")
	private String calcIntegrados;

	@Column(name="CI_X_PUESTO")
	private String ciXPuesto;

	@Column(name="COMP_PTO_MAX")
	private BigDecimal compPtoMax;

	@Column(name="COMP_PTO_MIN")
	private BigDecimal compPtoMin;

	@Column(name="CTRL_PLAZAS_INDEP")
	private String ctrlPlazasIndep;

	@Column(name="DIAS_ALTA_ANT")
	private BigDecimal diasAltaAnt;

	@Column(name="DIAS_ALTA_DES")
	private BigDecimal diasAltaDes;

	@Column(name="DIAS_BAJA_ANT")
	private BigDecimal diasBajaAnt;

	@Column(name="DIAS_BAJA_DES")
	private BigDecimal diasBajaDes;

	@Column(name="DIAS_RETROACTIVOS")
	private BigDecimal diasRetroactivos;

	@Column(name="DIG_VER")
	private String digVer;

	@Column(name="EMPLS_GRUPO")
	private String emplsGrupo;

	@Column(name="ESCALA_CALIF_COMP")
	private String escalaCalifComp;

	private String evaluacion;

	@Column(name="FECHA_MERITO")
	private String fechaMerito;

	@Column(name="FECHA_MOV")
	private LocalDateTime fechaMov;

	private String licencias;

	@Column(name="LIMPIAR_REINGRESO")
	private String limpiarReingreso;

	@Column(name="MAPA_CARACTERES")
	private String mapaCaracteres;

	@Column(name="MES_FIN_PRESUP")
	private BigDecimal mesFinPresup;

	@Column(name="MES_INI_PRESUP")
	private BigDecimal mesIniPresup;

	@Column(name="METODO_VALUACION")
	private String metodoValuacion;

	@Column(name="MODIF_FOTO")
	private String modifFoto;

	@Column(name="MODIFICAR_NUM_EMP")
	private String modificarNumEmp;

	@Column(name="MULTIPLES_CONTRATOS")
	private String multiplesContratos;

	@Column(name="NUM_CIA_VER")
	private BigDecimal numCiaVer;

	@Column(name="ORGANIG_FACTOR_SUELDO")
	private BigDecimal organigFactorSueldo;

	private String pais;

	@Column(name="PERIODICIDAD_CONTRATOS")
	private String periodicidadContratos;

	@Column(name="PERIODICIDAD_FINIQUITOS")
	private String periodicidadFiniquitos;

	@Column(name="REINGRESO_SIN_FINIQUITO")
	private String reingresoSinFiniquito;

	private String retroactivo;

	private String status;

	private String subcontratacion;

	@Column(name="USER_ID")
	private String userId;

	@Column(name="VAL_CTA_BANCO")
	private String valCtaBanco;

	@Column(name="VAL_EMP_CURP")
	private String valEmpCurp;

	@Column(name="VAL_EMP_NUM_IMSS")
	private String valEmpNumImss;

	@Column(name="VAL_EMP_RFC")
	private String valEmpRfc;

	@Column(name="VAL_TABULADOR")
	private String valTabulador;

	@Column(name="VALIDAR_NIVEL_DEPTO")
	private String validarNivelDepto;

	@Column(name="VALIDAR_NIVEL_DEPTO_COMP")
	private String validarNivelDeptoComp;

	@Column(name="VISIBLE_CONTRATOS")
	private String visibleContratos;

	@Column(name="VISIBLE_FINIQUITOS")
	private String visibleFiniquitos;

	public HuCatCoGral() {
	}

	public long getNumCia() {
		return this.numCia;
	}

	public void setNumCia(long numCia) {
		this.numCia = numCia;
	}

	public String getAnioPresupRef() {
		return this.anioPresupRef;
	}

	public void setAnioPresupRef(String anioPresupRef) {
		this.anioPresupRef = anioPresupRef;
	}

	public String getAsociarContratoPlaza() {
		return this.asociarContratoPlaza;
	}

	public void setAsociarContratoPlaza(String asociarContratoPlaza) {
		this.asociarContratoPlaza = asociarContratoPlaza;
	}

	public String getBancaCentralizada() {
		return this.bancaCentralizada;
	}

	public void setBancaCentralizada(String bancaCentralizada) {
		this.bancaCentralizada = bancaCentralizada;
	}

	public String getCalcIntegrados() {
		return this.calcIntegrados;
	}

	public void setCalcIntegrados(String calcIntegrados) {
		this.calcIntegrados = calcIntegrados;
	}

	public String getCiXPuesto() {
		return this.ciXPuesto;
	}

	public void setCiXPuesto(String ciXPuesto) {
		this.ciXPuesto = ciXPuesto;
	}

	public BigDecimal getCompPtoMax() {
		return this.compPtoMax;
	}

	public void setCompPtoMax(BigDecimal compPtoMax) {
		this.compPtoMax = compPtoMax;
	}

	public BigDecimal getCompPtoMin() {
		return this.compPtoMin;
	}

	public void setCompPtoMin(BigDecimal compPtoMin) {
		this.compPtoMin = compPtoMin;
	}

	public String getCtrlPlazasIndep() {
		return this.ctrlPlazasIndep;
	}

	public void setCtrlPlazasIndep(String ctrlPlazasIndep) {
		this.ctrlPlazasIndep = ctrlPlazasIndep;
	}

	public BigDecimal getDiasAltaAnt() {
		return this.diasAltaAnt;
	}

	public void setDiasAltaAnt(BigDecimal diasAltaAnt) {
		this.diasAltaAnt = diasAltaAnt;
	}

	public BigDecimal getDiasAltaDes() {
		return this.diasAltaDes;
	}

	public void setDiasAltaDes(BigDecimal diasAltaDes) {
		this.diasAltaDes = diasAltaDes;
	}

	public BigDecimal getDiasBajaAnt() {
		return this.diasBajaAnt;
	}

	public void setDiasBajaAnt(BigDecimal diasBajaAnt) {
		this.diasBajaAnt = diasBajaAnt;
	}

	public BigDecimal getDiasBajaDes() {
		return this.diasBajaDes;
	}

	public void setDiasBajaDes(BigDecimal diasBajaDes) {
		this.diasBajaDes = diasBajaDes;
	}

	public BigDecimal getDiasRetroactivos() {
		return this.diasRetroactivos;
	}

	public void setDiasRetroactivos(BigDecimal diasRetroactivos) {
		this.diasRetroactivos = diasRetroactivos;
	}

	public String getDigVer() {
		return this.digVer;
	}

	public void setDigVer(String digVer) {
		this.digVer = digVer;
	}

	public String getEmplsGrupo() {
		return this.emplsGrupo;
	}

	public void setEmplsGrupo(String emplsGrupo) {
		this.emplsGrupo = emplsGrupo;
	}

	public String getEscalaCalifComp() {
		return this.escalaCalifComp;
	}

	public void setEscalaCalifComp(String escalaCalifComp) {
		this.escalaCalifComp = escalaCalifComp;
	}

	public String getEvaluacion() {
		return this.evaluacion;
	}

	public void setEvaluacion(String evaluacion) {
		this.evaluacion = evaluacion;
	}

	public String getFechaMerito() {
		return this.fechaMerito;
	}

	public void setFechaMerito(String fechaMerito) {
		this.fechaMerito = fechaMerito;
	}

	public LocalDateTime getFechaMov() {
		return this.fechaMov;
	}

	public void setFechaMov(LocalDateTime fechaMov) {
		this.fechaMov = fechaMov;
	}

	public String getLicencias() {
		return this.licencias;
	}

	public void setLicencias(String licencias) {
		this.licencias = licencias;
	}

	public String getLimpiarReingreso() {
		return this.limpiarReingreso;
	}

	public void setLimpiarReingreso(String limpiarReingreso) {
		this.limpiarReingreso = limpiarReingreso;
	}

	public String getMapaCaracteres() {
		return this.mapaCaracteres;
	}

	public void setMapaCaracteres(String mapaCaracteres) {
		this.mapaCaracteres = mapaCaracteres;
	}

	public BigDecimal getMesFinPresup() {
		return this.mesFinPresup;
	}

	public void setMesFinPresup(BigDecimal mesFinPresup) {
		this.mesFinPresup = mesFinPresup;
	}

	public BigDecimal getMesIniPresup() {
		return this.mesIniPresup;
	}

	public void setMesIniPresup(BigDecimal mesIniPresup) {
		this.mesIniPresup = mesIniPresup;
	}

	public String getMetodoValuacion() {
		return this.metodoValuacion;
	}

	public void setMetodoValuacion(String metodoValuacion) {
		this.metodoValuacion = metodoValuacion;
	}

	public String getModifFoto() {
		return this.modifFoto;
	}

	public void setModifFoto(String modifFoto) {
		this.modifFoto = modifFoto;
	}

	public String getModificarNumEmp() {
		return this.modificarNumEmp;
	}

	public void setModificarNumEmp(String modificarNumEmp) {
		this.modificarNumEmp = modificarNumEmp;
	}

	public String getMultiplesContratos() {
		return this.multiplesContratos;
	}

	public void setMultiplesContratos(String multiplesContratos) {
		this.multiplesContratos = multiplesContratos;
	}

	public BigDecimal getNumCiaVer() {
		return this.numCiaVer;
	}

	public void setNumCiaVer(BigDecimal numCiaVer) {
		this.numCiaVer = numCiaVer;
	}

	public BigDecimal getOrganigFactorSueldo() {
		return this.organigFactorSueldo;
	}

	public void setOrganigFactorSueldo(BigDecimal organigFactorSueldo) {
		this.organigFactorSueldo = organigFactorSueldo;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getPeriodicidadContratos() {
		return this.periodicidadContratos;
	}

	public void setPeriodicidadContratos(String periodicidadContratos) {
		this.periodicidadContratos = periodicidadContratos;
	}

	public String getPeriodicidadFiniquitos() {
		return this.periodicidadFiniquitos;
	}

	public void setPeriodicidadFiniquitos(String periodicidadFiniquitos) {
		this.periodicidadFiniquitos = periodicidadFiniquitos;
	}

	public String getReingresoSinFiniquito() {
		return this.reingresoSinFiniquito;
	}

	public void setReingresoSinFiniquito(String reingresoSinFiniquito) {
		this.reingresoSinFiniquito = reingresoSinFiniquito;
	}

	public String getRetroactivo() {
		return this.retroactivo;
	}

	public void setRetroactivo(String retroactivo) {
		this.retroactivo = retroactivo;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubcontratacion() {
		return this.subcontratacion;
	}

	public void setSubcontratacion(String subcontratacion) {
		this.subcontratacion = subcontratacion;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getValCtaBanco() {
		return this.valCtaBanco;
	}

	public void setValCtaBanco(String valCtaBanco) {
		this.valCtaBanco = valCtaBanco;
	}

	public String getValEmpCurp() {
		return this.valEmpCurp;
	}

	public void setValEmpCurp(String valEmpCurp) {
		this.valEmpCurp = valEmpCurp;
	}

	public String getValEmpNumImss() {
		return this.valEmpNumImss;
	}

	public void setValEmpNumImss(String valEmpNumImss) {
		this.valEmpNumImss = valEmpNumImss;
	}

	public String getValEmpRfc() {
		return this.valEmpRfc;
	}

	public void setValEmpRfc(String valEmpRfc) {
		this.valEmpRfc = valEmpRfc;
	}

	public String getValTabulador() {
		return this.valTabulador;
	}

	public void setValTabulador(String valTabulador) {
		this.valTabulador = valTabulador;
	}

	public String getValidarNivelDepto() {
		return this.validarNivelDepto;
	}

	public void setValidarNivelDepto(String validarNivelDepto) {
		this.validarNivelDepto = validarNivelDepto;
	}

	public String getValidarNivelDeptoComp() {
		return this.validarNivelDeptoComp;
	}

	public void setValidarNivelDeptoComp(String validarNivelDeptoComp) {
		this.validarNivelDeptoComp = validarNivelDeptoComp;
	}

	public String getVisibleContratos() {
		return this.visibleContratos;
	}

	public void setVisibleContratos(String visibleContratos) {
		this.visibleContratos = visibleContratos;
	}

	public String getVisibleFiniquitos() {
		return this.visibleFiniquitos;
	}

	public void setVisibleFiniquitos(String visibleFiniquitos) {
		this.visibleFiniquitos = visibleFiniquitos;
	}

}