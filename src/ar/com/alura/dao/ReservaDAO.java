package ar.com.alura.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import ar.com.alura.model.Reserva;


public class ReservaDAO {
	private Connection con ;
	
	public ReservaDAO(Connection con) {
		this.con = con;
	}
	
	public  List<Reserva> listar (){
		try {
			final PreparedStatement statement = con.prepareStatement("SELECT * FROM reservas;");
			try(statement){
				
				statement.execute();
				
				ResultSet resultSet = statement.getResultSet();
				
				List<Reserva> resultado = new ArrayList<>();
				
				while(resultSet.next()) {
					Reserva fila = new Reserva(
							resultSet.getInt("ID"),
							resultSet.getDate("FECHA_ENTRADA"),
							resultSet.getDate("FECHA_SALIDA"),
							resultSet.getInt("VALOR"),
							resultSet.getString("FORMA_DE_PAGO"));
					
					resultado.add(fila);
					
				}
				
				System.out.println(resultado);
			return resultado;	
			}
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public void guardar(Reserva reserva) {
		System.out.println("llamando guardar");
		try {
			final PreparedStatement statement = con.prepareStatement("INSERT INTO reservas "
					+ "( fecha_entrada, fecha_salida, valor, forma_de_pago) "
					+ "VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			
			try(statement){
				statement.setDate(1,reserva.getFechaEntrada());
				statement.setDate(2,reserva.getFechaSalida());
				statement.setInt(3,reserva.getValor());
				statement.setString(4,reserva.getFormaPago());
				statement.execute();
				
				try(ResultSet resultSet = statement.getGeneratedKeys()){
					while(resultSet.next()) {
						reserva.setId(resultSet.getInt(1));
					}
				}
			}
		}catch (SQLException e) {
			System.out.println("algo Paso");
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public List<Reserva> buscar() {
		List<Reserva> reservas = new ArrayList<Reserva>();
		try {
			final PreparedStatement stmt = con.prepareStatement(
					"SELECT id, fecha_entrada, fecha_salida, valor, forma_de_pago "
					+ "FROM reservas");
			try (stmt) {
				stmt.execute();

				transformarResultSetEnReserva(reservas, stmt);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Reserva> buscarId(String id) {
		List<Reserva> reservas = new ArrayList<Reserva>();
		try {

			String sql = "SELECT id, fecha_entrada, fecha_salida, valor, forma_de_pago FROM reservas WHERE id = ?";

			try (PreparedStatement pstm = con.prepareStatement(sql)) {
				pstm.setString(1, id);
				pstm.execute();

				transformarResultSetEnReserva(reservas, pstm);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void Eliminar(Integer id) {
		try {
			try (PreparedStatement stm = con.prepareStatement("DELETE FROM reservas WHERE id = ?")) {
				stm.setInt(1, id);
				stm.execute();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void Actualizar(Date fechaE, Date fechaS, Integer valor, String formaPago, Integer id) {
		try {
			final PreparedStatement stmt = con.prepareStatement("UPDATE reservas "
					+ "SET fecha_entrada = ?, fecha_salida = ?, valor = ?, forma_de_pago = ? "
					+ "WHERE id = ?");
			try (stmt) {
				stmt.setDate(1, fechaE);
				stmt.setDate(2, fechaS);
				stmt.setInt(3, valor);
				stmt.setString(4, formaPago);
				stmt.setInt(5, id);
				stmt.executeQuery();
			
		}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
						
	private void transformarResultSetEnReserva(List<Reserva> reservas, PreparedStatement pstm) {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Reserva produto = new Reserva(rst.getInt(1), rst.getDate(2), rst.getDate(3), rst.getInt(4), rst.getString(5));

				reservas.add(produto);
			}
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
