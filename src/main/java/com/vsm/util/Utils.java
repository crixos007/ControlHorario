package com.vsm.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("utils")
public class Utils {
	private static final Logger LOG = LogManager.getLogger(Utils.class.getName());
	private String indices[] = {"1324", "2134", "2143", "2341", "3214", "3412", "4123", "4132", "4231", "4321"};
	
	public String decodificaNip(String nip) throws Exception {		
	    StringBuffer cod_nip = new StringBuffer();
	    for (int i = nip.length(); i <= 4; i++) {
	    	cod_nip.append("0");
        }
        cod_nip.append(nip);        
        String aux[] = {"", "", "", ""};
        int primero = Integer.parseInt(cod_nip.substring(0, 1));
        StringBuffer nip_dec = new StringBuffer();
        for (int i = 1; i <= 4; i++) {
            int casilla = Integer.parseInt(indices[primero].substring(i - 1, i));
            aux[casilla - 1] = cod_nip.substring(i, i + 1);
        }
        for (int j = 1; j <= 2; j++) {
            aux[j] = "" + ((Integer.parseInt(aux[j]) * -1) + 10);
            if (aux[j].equals("10")) {
                aux[j] = "0";
            }
        }
        for (int x = 0; x < 4; x++) {
            nip_dec.append(aux[x]);
        }
        return (nip_dec.toString());
    }
	
    public String codificarNip(String nip) throws Exception {
        StringBuffer cod_nip = new StringBuffer("" + nip);
        for (int i = 1; i <= 2; i++) {
            int s_10 = Integer.parseInt(cod_nip.substring(i, i + 1)) - 10;
            if (s_10 == -10) {
                cod_nip.setCharAt(i, '0');
            } else {
                String tmp = "" + Math.abs(s_10);
                cod_nip.setCharAt(i, tmp.charAt(0));
            }
        }
        int indice_i = (int) Math.round(Math.random() * 10);
        StringBuffer nip_cod = new StringBuffer();
        nip_cod.append("" + indice_i);
        for (int i = 0; i < 4; i++) {
            int casilla = Integer.parseInt(indices[indice_i].substring(i, i + 1));
            nip_cod.append(cod_nip.substring(casilla - 1, casilla));
        }
        return (nip_cod.toString());        
    }
    
	public boolean isNumeric(String cadena) throws Exception {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	
	public boolean isDateValid(String cadena) throws Exception {
		String regex = "^([0-2][0-9]|3[0-1])(\\/)(0[1-9]|1[0-2])\\2(\\d{4})$";
		Pattern p = Pattern.compile(regex);		 
		if (cadena == null) {
			return false;
        }		 
		Matcher m = p.matcher(cadena);
		return m.matches();
	}
	
	public String encryptPassword(String password) throws Exception {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] enc = md.digest();
			String md5Sum = Base64.getEncoder().encodeToString(enc);
			return md5Sum;
		} catch (NoSuchAlgorithmException nsae) {
			LOG.error("Utils encryptPassword error trace: " + nsae.getMessage());
			return null;
		}
	}
}