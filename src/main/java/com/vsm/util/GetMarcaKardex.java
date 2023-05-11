package com.vsm.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vsm.db.entity.HuEmplsAsistencia;
import com.vsm.db.service.TransSelDbService;

@Service("getMarcaKardex")
public class GetMarcaKardex {
	private static final Logger LOG = LogManager.getLogger(GetMarcaKardex.class.getName());
	
	@Autowired
	TransSelDbService dbService;

	public List<String> consultaRango(long cia, long numEmpls, LocalDate fechaInicio, LocalDate fechaTermino) throws Exception {
		LocalDate ini = fechaInicio;
		LocalDate fin = fechaTermino;
		List<String> kardexMark = new ArrayList<String>();
		List<HuEmplsAsistencia> asisList = dbService.getHuAsisByDates(cia, numEmpls, Long.parseLong(fechaInicio.format(DateTimeFormatter.ofPattern("yyyy"))), Long.parseLong(fechaInicio.format(DateTimeFormatter.ofPattern("yyyy"))));		
		if(asisList == null || !asisList.isEmpty()) {
			LOG.error("Sin coincidencia en HU_EMPLS_ASISTENCIA parametros: " + cia + " | " + numEmpls  + " | " + fechaInicio  + " | " + fechaTermino);
			return kardexMark;
		}		
		for(HuEmplsAsistencia asis:asisList) {
			String ene = asis.getAsMes01();
			String[]enero = ene.split("|");
			int diaEne = 0;			
			for(String x:enero) {
				Formatter fmt = new Formatter();
				diaEne ++;								
				if(!x.isBlank()) {
					String y = "";
					fmt.format("%02d",diaEne);
					y = ((fmt + "-01-" + asis.getId().getAsAaProc()));					
					fmt.close();					
					if(LocalDate.parse(y, DateTimeFormatter.ofPattern("dd-MM-yyyy")).isAfter(ini) &&
					   LocalDate.parse(y, DateTimeFormatter.ofPattern("dd-MM-yyyy")).isBefore(fin)) {
						kardexMark.add(y);
					}
				}
			}			
			String feb = asis.getAsMes02();
			String[]febrero = feb.split("|");
			int diaFeb = 0;			
			for(String x:febrero) {
				Formatter fmt = new Formatter();
				diaFeb ++;								
				if(!x.isBlank()) {
					String y = "";
					fmt.format("%02d",diaFeb);
					y = (fmt + "-02-" + asis.getId().getAsAaProc());
					fmt.close();					
					if(LocalDate.parse(y, DateTimeFormatter.ofPattern("dd-MM-yyyy")).isAfter(ini) &&
					   LocalDate.parse(y, DateTimeFormatter.ofPattern("dd-MM-yyyy")).isBefore(fin)) {
						kardexMark.add(y);
					}
				}
			}			
			String mar = asis.getAsMes03();
			String[]marzo = mar.split("|");
			int diaMar = 0;			
			for(String x:marzo) {
				Formatter fmt = new Formatter();
				diaMar ++;								
				if(!x.isBlank()) {
					String y = "";
					fmt.format("%02d",diaMar);
					y = (fmt + "-03-" + asis.getId().getAsAaProc());
					fmt.close();					
					if(LocalDate.parse(y, DateTimeFormatter.ofPattern("dd-MM-yyyy")).isAfter(ini) &&
					   LocalDate.parse(y, DateTimeFormatter.ofPattern("dd-MM-yyyy")).isBefore(fin)) {
						kardexMark.add(y);
					}
				}
			}			
			String abr = asis.getAsMes04();
			String[]abril = abr.split("|");
			int diaAbr = 0;			
			for(String x:abril) {
				Formatter fmt = new Formatter();
				diaAbr ++;								
				if(!x.isBlank()) {
					String y = "";
					fmt.format("%02d",diaAbr);
					y = (fmt + "-04-" + asis.getId().getAsAaProc());					
					fmt.close();					
					if(LocalDate.parse(y, DateTimeFormatter.ofPattern("dd-MM-yyyy")).isAfter(ini) &&
					   LocalDate.parse(y, DateTimeFormatter.ofPattern("dd-MM-yyyy")).isBefore(fin)) {
						kardexMark.add(y);
					}
				}
			}			
			String may = asis.getAsMes05();
			String[]mayo = may.split("|");
			int diaMay = 0;			
			for(String x:mayo) {
				Formatter fmt = new Formatter();
				diaMay ++;								
				if(!x.isBlank()) {
					String y = "";
					fmt.format("%02d",diaMay);
					y = (fmt + "-05-" + asis.getId().getAsAaProc());
					fmt.close();					
					if(LocalDate.parse(y, DateTimeFormatter.ofPattern("dd-MM-yyyy")).isAfter(ini) &&
					   LocalDate.parse(y, DateTimeFormatter.ofPattern("dd-MM-yyyy")).isBefore(fin)) {
						kardexMark.add(y);
					}
				}
			}			
			String jun = asis.getAsMes06();
			String[]junio = jun.split("|");
			int diaJun = 0;			
			for(String x:junio) {
				Formatter fmt = new Formatter();
				diaJun ++;								
				if(!x.isBlank()) {
					String y = "";
					fmt.format("%02d",diaJun);
					y = (fmt + "-06-" + asis.getId().getAsAaProc());
					fmt.close();					
					if(LocalDate.parse(y, DateTimeFormatter.ofPattern("dd-MM-yyyy")).isAfter(ini) &&
					   LocalDate.parse(y, DateTimeFormatter.ofPattern("dd-MM-yyyy")).isBefore(fin)) {
						kardexMark.add(y);
					}
				}
			}			
			String jul = asis.getAsMes07();
			String[]julio = jul.split("|");
			int diaJul = 0;			
			for(String x:julio) {
				Formatter fmt = new Formatter();
				diaJul ++;								
				if(!x.isBlank()) {
					String y = "";
					fmt.format("%02d",diaJul);
					y = (fmt + "-07-" + asis.getId().getAsAaProc());
					fmt.close();					
					if(LocalDate.parse(y, DateTimeFormatter.ofPattern("dd-MM-yyyy")).isAfter(ini) &&
					   LocalDate.parse(y, DateTimeFormatter.ofPattern("dd-MM-yyyy")).isBefore(fin)) {
						kardexMark.add(y);
					}
				}
			}			
			String ago = asis.getAsMes08();
			String[]agosto = ago.split("|");
			int diaAgo = 0;			
			for(String x:agosto) {
				Formatter fmt = new Formatter();
				diaAgo ++;								
				if(!x.isBlank()) {
					String y = "";
					fmt.format("%02d",diaAgo);
					y = (fmt + "-08-" + asis.getId().getAsAaProc());
					fmt.close();					
					if(LocalDate.parse(y, DateTimeFormatter.ofPattern("dd-MM-yyyy")).isAfter(ini) &&
					   LocalDate.parse(y, DateTimeFormatter.ofPattern("dd-MM-yyyy")).isBefore(fin)) {
						kardexMark.add(y);
					}
				}
			}			
			String sep = asis.getAsMes09();
			String[]septiembre = sep.split("|");
			int diaSep = 0;			
			for(String x:septiembre) {
				Formatter fmt = new Formatter();
				diaSep ++;								
				if(!x.isBlank()) {
					String y = "";
					fmt.format("%02d",diaSep);
					y = (fmt + "-09-" + asis.getId().getAsAaProc());
					fmt.close();					
					if(LocalDate.parse(y, DateTimeFormatter.ofPattern("dd-MM-yyyy")).isAfter(ini) &&
					   LocalDate.parse(y, DateTimeFormatter.ofPattern("dd-MM-yyyy")).isBefore(fin)) {
						kardexMark.add(y);
					}
				}
			}			
			String oct = asis.getAsMes10();
			String[]octubre = oct.split("|");
			int diaOct = 0;			
			for(String x:octubre) {
				Formatter fmt = new Formatter();
				diaOct ++;								
				if(!x.isBlank()) {
					String y = "";
					fmt.format("%02d",diaOct);
					y = (fmt + "-10-" + asis.getId().getAsAaProc());
					fmt.close();					
					if(LocalDate.parse(y, DateTimeFormatter.ofPattern("dd-MM-yyyy")).isAfter(ini) &&
					   LocalDate.parse(y, DateTimeFormatter.ofPattern("dd-MM-yyyy")).isBefore(fin)) {
						kardexMark.add(y);
					}
				}
			}			
			String nov = asis.getAsMes11();
			String[]noviembre = nov.split("|");
			int diaNov = 0;			
			for(String x:noviembre) {
				Formatter fmt = new Formatter();
				diaNov ++;								
				if(!x.isBlank()) {
					String y = "";
					fmt.format("%02d",diaNov);
					y = (fmt + "-11-" + asis.getId().getAsAaProc());
					fmt.close();					
					if(LocalDate.parse(y, DateTimeFormatter.ofPattern("dd-MM-yyyy")).isAfter(ini) &&
					   LocalDate.parse(y, DateTimeFormatter.ofPattern("dd-MM-yyyy")).isBefore(fin)) {
						kardexMark.add(y);
					}
				}
			}			
			String dic = asis.getAsMes12();
			String[]diciembre = dic.split("|");
			int diaDic = 0;			
			for(String x:diciembre) {
				Formatter fmt = new Formatter();
				diaDic ++;								
				if(!x.isBlank()) {
					String y = "";
					fmt.format("%02d",diaDic);
					y = (fmt + "-12-" + asis.getId().getAsAaProc());
					fmt.close();					
					if(LocalDate.parse(y, DateTimeFormatter.ofPattern("dd-MM-yyyy")).isAfter(ini) &&
					   LocalDate.parse(y, DateTimeFormatter.ofPattern("dd-MM-yyyy")).isBefore(fin)) {
						kardexMark.add(y);
					}
				}
			}
		}		
		return kardexMark;
	}
}