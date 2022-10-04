package ar.com.alura.controller;


import java.sql.Date;
import java.util.List;

import ar.com.alura.dao.HuespedesDAO;
import ar.com.alura.factory.ConnectionFactory;
import ar.com.alura.model.Huespedes;

public class HuespedesController {
	private HuespedesDAO huespedesDAO;
	
	public HuespedesController() {
		ConnectionFactory factory = new ConnectionFactory();
		this.huespedesDAO = new HuespedesDAO(factory.recuperarConnexion());
	}
	
	public void guardar(Huespedes huesped) {
		this.huespedesDAO.guardar(huesped);
	}
	
	public List<Huespedes> listarHuespedes() {
		return this.huespedesDAO.listarHuespedes();
	}
	
	public List<Huespedes> listarHuespedesId(String id) {
		return this.huespedesDAO.buscarId(id);
	}
	
	public void actualizar(String nombre, String apellido, Date fechaN, String nacionalidad, String telefono, Integer idReserva, Integer id) {
		this.huespedesDAO.Actualizar(nombre, apellido, fechaN, nacionalidad, telefono, idReserva, id);
	}
	
	public void Eliminar(Integer id) {
		this.huespedesDAO.Eliminar(id);
	}
	
}
