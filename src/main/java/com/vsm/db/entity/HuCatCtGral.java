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
 * The persistent class for the HU_CAT_CT_GRAL database table.
 * 
 */
@Entity
@Table(name="HU_CAT_CT_GRAL")
@NamedQuery(name="HuCatCtGral.findAll", query="SELECT h FROM HuCatCtGral h")
public class HuCatCtGral implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long cia;

	@Column(name="ANALISIS_COMEDOR")
	private String analisisComedor;

	@Column(name="ANALISIS_FALTAS")
	private String analisisFaltas;

	@Column(name="ANALISIS_RETARDOS")
	private String analisisRetardos;

	@Column(name="ASIGNA_TURNO_AUT")
	private String asignaTurnoAut;

	@Column(name="CALC_DISTRIBUIDO")
	private String calcDistribuido;

	@Column(name="CHECAR_RETARDO")
	private String checarRetardo;

	@Column(name="COMPLETA_MARCAJE")
	private String completaMarcaje;

	@Column(name="CORREO_NOTIF_CALCULO")
	private String correoNotifCalculo;

	@Column(name="CVE_ENTRADA")
	private String cveEntrada;

	@Column(name="CVE_ENTRADA_COM")
	private String cveEntradaCom;

	@Column(name="CVE_SALIDA")
	private String cveSalida;

	@Column(name="CVE_SALIDA_COM")
	private String cveSalidaCom;

	@Column(name="DEFAULT_GAFETE")
	private String defaultGafete;

	@Column(name="DEPURACION_CHECADAS")
	private BigDecimal depuracionChecadas;

	@Column(name="DEPURACION_CHECADAS_COM")
	private BigDecimal depuracionChecadasCom;

	@Column(name="DOBLE_TURNO")
	private String dobleTurno;

	private String email;

	private String etime;

	@Column(name="FECHA_HORA")
	private LocalDateTime fechaHora;

	@Column(name="FECHA_MOV")
	private LocalDateTime fechaMov;

	@Column(name="GRUPO_AVISO_AUS")
	private BigDecimal grupoAvisoAus;

	@Column(name="GRUPO_DESCARGA_TF")
	private BigDecimal grupoDescargaTf;

	@Column(name="HIST_LOG")
	private BigDecimal histLog;

	@Column(name="HRS_BANCO_TIEMPO_FLEXIBLE")
	private BigDecimal hrsBancoTiempoFlexible;

	@Column(name="LEYENDA_AUT")
	private String leyendaAut;

	@Column(name="MIN_CHEC_ANTES_ENTRADA")
	private BigDecimal minChecAntesEntrada;

	@Column(name="MINUTOS_REV_POLEO")
	private BigDecimal minutosRevPoleo;

	@Column(name="MTTO_CHECADAS_COMEDOR")
	private String mttoChecadasComedor;

	@Column(name="NIVELES_AUT_TE")
	private String nivelesAutTe;

	@Column(name="OMITE_BAJAS_INTERFASE")
	private String omiteBajasInterfase;

	@Column(name="POLEO_EJECUCION")
	private String poleoEjecucion;

	@Column(name="POLEO_USUARIO")
	private String poleoUsuario;

	@Column(name="REPITE_CHECADA")
	private String repiteChecada;

	@Column(name="RUTA_ARCH_BAT")
	private String rutaArchBat;

	@Column(name="RUTA_ARCH_PENDIENTE")
	private String rutaArchPendiente;

	@Column(name="RUTA_ARCH_POLEO")
	private String rutaArchPoleo;

	@Column(name="RUTA_EXE_POLEO")
	private String rutaExePoleo;

	@Column(name="SCHEDULER_X_CIA")
	private String schedulerXCia;

	@Column(name="SEGUNDOS_DESC_RELOJES")
	private BigDecimal segundosDescRelojes;

	@Column(name="SEGUNDOS_SCHEDULER")
	private BigDecimal segundosScheduler;

	@Column(name="SELECTIVO_POR_ESTACION")
	private String selectivoPorEstacion;

	private String status;

	@Column(name="STATUS_SCHEDULER")
	private String statusScheduler;

	@Column(name="TIPO_COMIDA")
	private String tipoComida;

	@Column(name="TIPO_RELOJ")
	private String tipoReloj;

	@Column(name="USER_ID")
	private String userId;

	public HuCatCtGral() {
	}

	public long getCia() {
		return this.cia;
	}

	public void setCia(long cia) {
		this.cia = cia;
	}

	public String getAnalisisComedor() {
		return this.analisisComedor;
	}

	public void setAnalisisComedor(String analisisComedor) {
		this.analisisComedor = analisisComedor;
	}

	public String getAnalisisFaltas() {
		return this.analisisFaltas;
	}

	public void setAnalisisFaltas(String analisisFaltas) {
		this.analisisFaltas = analisisFaltas;
	}

	public String getAnalisisRetardos() {
		return this.analisisRetardos;
	}

	public void setAnalisisRetardos(String analisisRetardos) {
		this.analisisRetardos = analisisRetardos;
	}

	public String getAsignaTurnoAut() {
		return this.asignaTurnoAut;
	}

	public void setAsignaTurnoAut(String asignaTurnoAut) {
		this.asignaTurnoAut = asignaTurnoAut;
	}

	public String getCalcDistribuido() {
		return this.calcDistribuido;
	}

	public void setCalcDistribuido(String calcDistribuido) {
		this.calcDistribuido = calcDistribuido;
	}

	public String getChecarRetardo() {
		return this.checarRetardo;
	}

	public void setChecarRetardo(String checarRetardo) {
		this.checarRetardo = checarRetardo;
	}

	public String getCompletaMarcaje() {
		return this.completaMarcaje;
	}

	public void setCompletaMarcaje(String completaMarcaje) {
		this.completaMarcaje = completaMarcaje;
	}

	public String getCorreoNotifCalculo() {
		return this.correoNotifCalculo;
	}

	public void setCorreoNotifCalculo(String correoNotifCalculo) {
		this.correoNotifCalculo = correoNotifCalculo;
	}

	public String getCveEntrada() {
		return this.cveEntrada;
	}

	public void setCveEntrada(String cveEntrada) {
		this.cveEntrada = cveEntrada;
	}

	public String getCveEntradaCom() {
		return this.cveEntradaCom;
	}

	public void setCveEntradaCom(String cveEntradaCom) {
		this.cveEntradaCom = cveEntradaCom;
	}

	public String getCveSalida() {
		return this.cveSalida;
	}

	public void setCveSalida(String cveSalida) {
		this.cveSalida = cveSalida;
	}

	public String getCveSalidaCom() {
		return this.cveSalidaCom;
	}

	public void setCveSalidaCom(String cveSalidaCom) {
		this.cveSalidaCom = cveSalidaCom;
	}

	public String getDefaultGafete() {
		return this.defaultGafete;
	}

	public void setDefaultGafete(String defaultGafete) {
		this.defaultGafete = defaultGafete;
	}

	public BigDecimal getDepuracionChecadas() {
		return this.depuracionChecadas;
	}

	public void setDepuracionChecadas(BigDecimal depuracionChecadas) {
		this.depuracionChecadas = depuracionChecadas;
	}

	public BigDecimal getDepuracionChecadasCom() {
		return this.depuracionChecadasCom;
	}

	public void setDepuracionChecadasCom(BigDecimal depuracionChecadasCom) {
		this.depuracionChecadasCom = depuracionChecadasCom;
	}

	public String getDobleTurno() {
		return this.dobleTurno;
	}

	public void setDobleTurno(String dobleTurno) {
		this.dobleTurno = dobleTurno;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEtime() {
		return this.etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}

	public LocalDateTime getFechaHora() {
		return this.fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	public LocalDateTime getFechaMov() {
		return this.fechaMov;
	}

	public void setFechaMov(LocalDateTime fechaMov) {
		this.fechaMov = fechaMov;
	}

	public BigDecimal getGrupoAvisoAus() {
		return this.grupoAvisoAus;
	}

	public void setGrupoAvisoAus(BigDecimal grupoAvisoAus) {
		this.grupoAvisoAus = grupoAvisoAus;
	}

	public BigDecimal getGrupoDescargaTf() {
		return this.grupoDescargaTf;
	}

	public void setGrupoDescargaTf(BigDecimal grupoDescargaTf) {
		this.grupoDescargaTf = grupoDescargaTf;
	}

	public BigDecimal getHistLog() {
		return this.histLog;
	}

	public void setHistLog(BigDecimal histLog) {
		this.histLog = histLog;
	}

	public BigDecimal getHrsBancoTiempoFlexible() {
		return this.hrsBancoTiempoFlexible;
	}

	public void setHrsBancoTiempoFlexible(BigDecimal hrsBancoTiempoFlexible) {
		this.hrsBancoTiempoFlexible = hrsBancoTiempoFlexible;
	}

	public String getLeyendaAut() {
		return this.leyendaAut;
	}

	public void setLeyendaAut(String leyendaAut) {
		this.leyendaAut = leyendaAut;
	}

	public BigDecimal getMinChecAntesEntrada() {
		return this.minChecAntesEntrada;
	}

	public void setMinChecAntesEntrada(BigDecimal minChecAntesEntrada) {
		this.minChecAntesEntrada = minChecAntesEntrada;
	}

	public BigDecimal getMinutosRevPoleo() {
		return this.minutosRevPoleo;
	}

	public void setMinutosRevPoleo(BigDecimal minutosRevPoleo) {
		this.minutosRevPoleo = minutosRevPoleo;
	}

	public String getMttoChecadasComedor() {
		return this.mttoChecadasComedor;
	}

	public void setMttoChecadasComedor(String mttoChecadasComedor) {
		this.mttoChecadasComedor = mttoChecadasComedor;
	}

	public String getNivelesAutTe() {
		return this.nivelesAutTe;
	}

	public void setNivelesAutTe(String nivelesAutTe) {
		this.nivelesAutTe = nivelesAutTe;
	}

	public String getOmiteBajasInterfase() {
		return this.omiteBajasInterfase;
	}

	public void setOmiteBajasInterfase(String omiteBajasInterfase) {
		this.omiteBajasInterfase = omiteBajasInterfase;
	}

	public String getPoleoEjecucion() {
		return this.poleoEjecucion;
	}

	public void setPoleoEjecucion(String poleoEjecucion) {
		this.poleoEjecucion = poleoEjecucion;
	}

	public String getPoleoUsuario() {
		return this.poleoUsuario;
	}

	public void setPoleoUsuario(String poleoUsuario) {
		this.poleoUsuario = poleoUsuario;
	}

	public String getRepiteChecada() {
		return this.repiteChecada;
	}

	public void setRepiteChecada(String repiteChecada) {
		this.repiteChecada = repiteChecada;
	}

	public String getRutaArchBat() {
		return this.rutaArchBat;
	}

	public void setRutaArchBat(String rutaArchBat) {
		this.rutaArchBat = rutaArchBat;
	}

	public String getRutaArchPendiente() {
		return this.rutaArchPendiente;
	}

	public void setRutaArchPendiente(String rutaArchPendiente) {
		this.rutaArchPendiente = rutaArchPendiente;
	}

	public String getRutaArchPoleo() {
		return this.rutaArchPoleo;
	}

	public void setRutaArchPoleo(String rutaArchPoleo) {
		this.rutaArchPoleo = rutaArchPoleo;
	}

	public String getRutaExePoleo() {
		return this.rutaExePoleo;
	}

	public void setRutaExePoleo(String rutaExePoleo) {
		this.rutaExePoleo = rutaExePoleo;
	}

	public String getSchedulerXCia() {
		return this.schedulerXCia;
	}

	public void setSchedulerXCia(String schedulerXCia) {
		this.schedulerXCia = schedulerXCia;
	}

	public BigDecimal getSegundosDescRelojes() {
		return this.segundosDescRelojes;
	}

	public void setSegundosDescRelojes(BigDecimal segundosDescRelojes) {
		this.segundosDescRelojes = segundosDescRelojes;
	}

	public BigDecimal getSegundosScheduler() {
		return this.segundosScheduler;
	}

	public void setSegundosScheduler(BigDecimal segundosScheduler) {
		this.segundosScheduler = segundosScheduler;
	}

	public String getSelectivoPorEstacion() {
		return this.selectivoPorEstacion;
	}

	public void setSelectivoPorEstacion(String selectivoPorEstacion) {
		this.selectivoPorEstacion = selectivoPorEstacion;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusScheduler() {
		return this.statusScheduler;
	}

	public void setStatusScheduler(String statusScheduler) {
		this.statusScheduler = statusScheduler;
	}

	public String getTipoComida() {
		return this.tipoComida;
	}

	public void setTipoComida(String tipoComida) {
		this.tipoComida = tipoComida;
	}

	public String getTipoReloj() {
		return this.tipoReloj;
	}

	public void setTipoReloj(String tipoReloj) {
		this.tipoReloj = tipoReloj;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}