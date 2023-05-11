package com.vsm.util;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vsm.db.entity.Hd;
import com.vsm.db.entity.HdPK;
import com.vsm.db.entity.HuCatDiasFestivo;
import com.vsm.db.service.TransSelDbService;

@Service("getWekendsOfYearService")
public class GetWekendsOfYear {
	@Autowired
	TransSelDbService dbService;
	
	public List<String> getWekends(long numCia, long numEmp, long anio) throws Exception {
		List<String> wekends = new ArrayList<String>();
		HdPK hdId = new HdPK();		
		hdId.setHdAaProc(anio);
		hdId.setHdCia(numCia);
		hdId.setHdNumEmp(numEmp);
		
		//OBTIENBE LOS DIAS DE DESCANSO DE BD
		Hd descansos = dbService.getHdById(hdId);		
		if(descansos!=null) {
			String ene = descansos.getHdMes01();
			String[]enero = ene.split("|");
			int diaEne = 0;			
			for(String x:enero) {
				Formatter fmt = new Formatter();
				diaEne ++;								
				if(!x.isBlank()) {
					fmt.format("%02d",diaEne);
					wekends.add(fmt + "-01-" + anio);
					fmt.close();
				}
			}			
			String feb = descansos.getHdMes02();
			String[]febrero = feb.split("|");
			int diaFeb = 0;			
			for(String x:febrero) {
				Formatter fmt = new Formatter();
				diaFeb ++;								
				if(!x.isBlank()) {
					fmt.format("%02d",diaFeb);
					wekends.add(fmt + "-02-" + anio);
					fmt.close();
				}
			}			
			String mar = descansos.getHdMes03();
			String[]marzo = mar.split("|");
			int diaMar = 0;			
			for(String x:marzo) {
				Formatter fmt = new Formatter();
				diaMar ++;								
				if(!x.isBlank()) {
					fmt.format("%02d",diaMar);
					wekends.add(fmt + "-03-" + anio);
					fmt.close();
				}
			}			
			String abr = descansos.getHdMes04();
			String[]abril = abr.split("|");
			int diaAbr = 0;			
			for(String x:abril) {
				Formatter fmt = new Formatter();
				diaAbr ++;								
				if(!x.isBlank()) {
					fmt.format("%02d",diaAbr);
					wekends.add(fmt + "-04-" + anio);
					fmt.close();
				}
			}			
			String may = descansos.getHdMes05();
			String[]mayo = may.split("|");
			int diaMay = 0;			
			for(String x:mayo) {
				Formatter fmt = new Formatter();
				diaMay ++;								
				if(!x.isBlank()) {
					fmt.format("%02d",diaMay);
					wekends.add(fmt + "-05-" + anio);
					fmt.close();
				}
			}			
			String jun = descansos.getHdMes06();
			String[]junio = jun.split("|");
			int diaJun = 0;			
			for(String x:junio) {
				Formatter fmt = new Formatter();
				diaJun ++;								
				if(!x.isBlank()) {
					fmt.format("%02d",diaJun);
					wekends.add(fmt + "-06-" + anio);
					fmt.close();
				}
			}			
			String jul = descansos.getHdMes07();
			String[]julio = jul.split("|");
			int diaJul = 0;			
			for(String x:julio) {
				Formatter fmt = new Formatter();
				diaJul ++;								
				if(!x.isBlank()) {
					fmt.format("%02d",diaJul);
					wekends.add(fmt + "-07-" + anio);
					fmt.close();
				}
			}			
			String ago = descansos.getHdMes08();
			String[]agosto = ago.split("|");
			int diaAgo = 0;			
			for(String x:agosto) {
				Formatter fmt = new Formatter();
				diaAgo ++;								
				if(!x.isBlank()) {
					fmt.format("%02d",diaAgo);
					wekends.add(fmt + "-08-" + anio);
					fmt.close();
				}
			}			
			String sep = descansos.getHdMes09();
			String[]septiembre = sep.split("|");
			int diaSep = 0;			
			for(String x:septiembre) {
				Formatter fmt = new Formatter();
				diaSep ++;								
				if(!x.isBlank()) {
					fmt.format("%02d",diaSep);
					wekends.add(fmt + "-09-" + anio);
					fmt.close();
				}
			}			
			String oct = descansos.getHdMes10();
			String[]octubre = oct.split("|");
			int diaOct = 0;			
			for(String x:octubre) {
				Formatter fmt = new Formatter();
				diaOct ++;								
				if(!x.isBlank()) {
					fmt.format("%02d",diaOct);
					wekends.add(fmt + "-10-" + anio);
					fmt.close();
				}
			}			
			String nov = descansos.getHdMes11();
			String[]noviembre = nov.split("|");
			int diaNov = 0;			
			for(String x:noviembre) {
				Formatter fmt = new Formatter();
				diaNov ++;								
				if(!x.isBlank()) {
					fmt.format("%02d",diaNov);
					wekends.add(fmt + "-11-" + anio);
					fmt.close();
				}
			}			
			String dic = descansos.getHdMes12();
			String[]diciembre = dic.split("|");
			int diaDic = 0;			
			for(String x:diciembre) {
				Formatter fmt = new Formatter();
				diaDic ++;								
				if(!x.isBlank()) {
					fmt.format("%02d",diaDic);
					wekends.add(fmt + "-12-" + anio);
					fmt.close();
				}
			}
		}
		
		//RECUPERA LOS DIAS FESTIVOS DE BD
		List<HuCatDiasFestivo> festivos = dbService.getDiasFestivos(numCia, "01-01-" + anio, "31-12-" + anio); 
		if(festivos!=null && !festivos.isEmpty()) {
			for(HuCatDiasFestivo x:festivos) {
				String fecha = x.getId().getFecha().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
				wekends.add(fecha);
			}
		}
		return wekends;
	}	
}