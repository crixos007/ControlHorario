package com.vsm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vsm.constant.ServiciosConstante;
import com.vsm.db.entity.HuCatToGral;
import com.vsm.db.entity.HuRutasFileServer;
import com.vsm.db.service.TransSelDbService;
import com.vsm.lib.dto.etime.EtParametroDto;

@Service("getFoto")
public class GetFoto {
	private static final Logger LOG = LogManager.getLogger(GetFoto.class.getName());
	EtParametroDto[] responseEtParam;
	
	@Autowired
	TransSelDbService dbSelService;
	
	@Autowired
	RestTemplate template;
		
	public Map<String,String> GetFotoET(long numCia, long numEmp) throws Exception {
		Formatter fmtCia = new Formatter();
		fmtCia.format("%05d",numCia);		
		Formatter fmtEmp = new Formatter();
		fmtEmp.format("%010d",numEmp);
		Map<String, String> map = new HashMap<String, String>();		
		try {
			responseEtParam = template.getForObject(ServiciosConstante.GET_PARAMETRO_ET_SERVICE, EtParametroDto[].class, "FileHumaneTime");
		}catch (Exception e) {
			LOG.error("GetFoto GetFotoET error HU_CAT_TO_GRAL: " + e.toString());
			map.put("foto","Exception while reading the file ");
			fmtEmp.close();
			fmtCia.close();
			return map;
		}
		List<EtParametroDto> listGral = new ArrayList<EtParametroDto>();
		listGral = Arrays.asList(responseEtParam);
		String valorAlfanumerico = null;
		for (int i = 0; i < listGral.size(); i++) {
			EtParametroDto e = listGral.get(i);
			valorAlfanumerico = e.getId().getValor();
		}
		String filePath = valorAlfanumerico + "\\" + fmtCia + "\\" + fmtEmp	+ ".JPG";
		fmtCia.close();
		fmtEmp.close();
		filePath = filePath.replace("\\", "/");
		filePath = filePath.replace("/", File.separator);
		File file = new File(filePath);
		String lastModified = String.valueOf(file.lastModified());		
		map.put("fechaFoto", lastModified);		
		try (FileInputStream imageInFile = new FileInputStream(file)) {
			// Reading a file from file system
			byte fileData[] = new byte[(int) file.length()];
			imageInFile.read(fileData);
			map.put("foto",Base64.getEncoder().encodeToString(fileData));
		} catch (FileNotFoundException e) {
			LOG.error("GetFoto GetFotoET File not found" + e);
			map.put("foto","File not found" + e);
			return map;
		} catch (IOException ioe) {
			LOG.error("GetFoto GetFotoET Exception while reading the file " + ioe);
			map.put("foto","Exception while reading the file " + ioe);
			return map;
		}		
		return map;
	}
	
	public Map<String,String> GetFotoHU(long numCia, long numEmp) throws Exception {
		Formatter fmt = new Formatter();
		fmt.format("%010d",numEmp);
		Map<String, String> map = new HashMap<String, String>();		
		List<HuCatToGral> listGral = dbSelService.getHuCatToGralByParam("FileServer");
		String valorAlfanumerico = null;
		String valorRuta = null;
		for (int i = 0; i < listGral.size(); i++) {
			HuCatToGral e = listGral.get(i);
			valorAlfanumerico = e.getValorAlfanumerico();
		}
		List<HuRutasFileServer> listFile = dbSelService.getHuRutasFSByCve("FOTO");
		if (listFile == null || listFile.isEmpty()) {
			LOG.error("GetFoto GetFotoHU error sin datos en HU_RUTAS_FILE_SERVER cia: " + numCia + " emp: " + numEmp + " clave: FOTO");
			map.put("foto","Exception while reading the file ");
			fmt.close();
			return map;
		}		
		for (int i = 0; i < listFile.size(); i++) {
			HuRutasFileServer e = listFile.get(i);
			valorRuta = e.getValorRuta();
		}
		String numcia = Long.toString(numCia);
		String filePath = valorAlfanumerico + "\\" + numcia + "\\" + valorRuta + "\\" + fmt	+ ".JPG";
		fmt.close();
		filePath = filePath.replace("\\", "/");
		filePath = filePath.replace("/", File.separator);
		File file = new File(filePath);
		String lastModified = String.valueOf(file.lastModified());		
		map.put("fechaFoto", lastModified);		
		try (FileInputStream imageInFile = new FileInputStream(file)) {
			// Reading a file from file system
			byte fileData[] = new byte[(int) file.length()];
			imageInFile.read(fileData);
			map.put("foto",Base64.getEncoder().encodeToString(fileData));
		} catch (FileNotFoundException e) {
			LOG.error("GetFoto GetFotoHU File not found" + e);
			map.put("foto","File not found" + e);
			return map;
		} catch (IOException ioe) {
			LOG.error("GetFoto GetFotoHU Exception while reading the file " + ioe);
			map.put("foto","Exception while reading the file " + ioe);
			return map;
		}		
		return map;
	}
}