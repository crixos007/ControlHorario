package com.vsm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("coneccionService")
public class ConnexionDB {
	@Value("${spring.datasource.url}")
    private String url;
	
	@Value("${spring.datasource.username}")
    private String user;
	
	@Value("${spring.datasource.password}")
    private String pass;
	
	public Connection getConeccion () throws Exception {		
		Connection connection ;	
		
		try {
        	connection = DriverManager. getConnection (url ,user, pass);
		}catch ( SQLException ex ) {
        	connection = null ;
        	ex.printStackTrace () ;
        }
		
		return connection;
	}
}