package ar.com.alura.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import ar.com.alura.model.Huespedes;

public class HuespedesDAO {
	final private Connection con ;
	
	public HuespedesDAO(Connection con) {
		this.con = con;
	}
	
	public void guardar(Huespedes huesped) {
		try {
			final PreparedStatement statement = con.prepareStatement("INSERT INTO huespedes "
					+ "(nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono, id_reserva) "
					+ "VALUES (?, ?, ?, ?,?,?)",Statement.RETURN_GENERATED_KEYS);

			try (statement) {

				statement.setString(1, huesped.getNombre());
				statement.setString(2, huesped.getApellido());
				statement.setDate(3, (Date) huesped.getFechaNacimiento());
				statement.setString(4, huesped.getNacionalidad());
				statement.setString(5, huesped.getTelefono());
				statement.setInt(6, huesped.getId_reserva());

				statement.execute();

				try (ResultSet rst = statement.getGeneratedKeys()) {
					while (rst.next()) {
						huesped.setTask_Id(rst.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public List<Huespedes> listarHuespedes() {
		List<Huespedes> huespedes = new ArrayList<Huespedes>();
		try {
			final PreparedStatement stmt = con.prepareStatement("SELECT * "
					+ "FROM huespedes");

			try (stmt) {
				stmt.execute();

				transformarResultSetEnHuesped(huespedes, stmt);
			}
			return huespedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Huespedes> buscarId(String id) {
		List<Huespedes> huespedes = new ArrayList<Huespedes>();
		try {
			final PreparedStatement stmt = con.prepareStatement("SELECT "
					+ "task_id, nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono, id_reserva"
					+ " FROM huespedes WHERE id_reserva = ?");
			

			try (stmt) {
				stmt.setString(1, id);
				stmt.execute();

				transformarResultSetEnHuesped(huespedes, stmt);
			}
			return huespedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void Actualizar(String nombre, String apellido, Date fechaN, String nacionalidad, String telefono, Integer idReserva, Integer id) {
		try{
			final PreparedStatement stmt = con.prepareStatement("UPDATE huespedes"
					+ " SET nombre = ?, apellido = ?, fecha_de_nacimiento = ?, nacionalidad = ?, telefono = ?, id_reserva = ?"
					+ " WHERE task_id = ?");
			try (stmt) {
				stmt.setString(1, nombre);
				stmt.setString(2, apellido);
				stmt.setDate(3, fechaN);
				stmt.setString(4, nacionalidad);
				stmt.setString(5, telefono);
				stmt.setInt(6, idReserva);
				stmt.setInt(7, id);
				stmt.execute();
			}
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void Eliminar(Integer id) {
		try (PreparedStatement stmt = con.prepareStatement("DELETE FROM huespedes WHERE task_id = ?")) {
			stmt.setInt(1, id);
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void transformarResultSetEnHuesped(List<Huespedes> reservas, PreparedStatement pstm){
		
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Huespedes huespedes = new Huespedes(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getDate(4), rst.getString(5), rst.getString(6), rst.getInt(7));
				reservas.add(huespedes);
			}
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}				
	}

}
