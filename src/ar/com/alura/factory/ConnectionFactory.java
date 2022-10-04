package ar.com.alura.factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	private DataSource datasurce ;
	
	public ConnectionFactory() {
		ComboPooledDataSource poolDataSource = new ComboPooledDataSource();
		poolDataSource.setJdbcUrl("jdbc:mysql://localhost/hotel_alura?useTimeZone=true&serverTimeZone=UTC");
		poolDataSource.setUser("root");
		poolDataSource.setPassword("321root");
		
		this.datasurce = poolDataSource;
	}
	public Connection recuperarConnexion(){
		try {
			return this.datasurce.getConnection();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
}
