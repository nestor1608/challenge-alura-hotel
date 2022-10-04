package ar.com.alura.controller;


import java.sql.Date;
import java.util.List;

import ar.com.alura.dao.ReservaDAO;
import ar.com.alura.factory.ConnectionFactory;
import ar.com.alura.model.Reserva;

public class ReservaController {
	private ReservaDAO reservaDAO ;
	
	public ReservaController(){
		ConnectionFactory factory = new ConnectionFactory();
		this.reservaDAO = new ReservaDAO(factory.recuperarConnexion());
	}
	
	public void guardar(Reserva reserva) {
		this.reservaDAO.guardar(reserva);
	}
	
	public List<Reserva> buscar() {
		return this.reservaDAO.buscar();
	}
	
	public List<Reserva> buscarId(String id) {
		return this.reservaDAO.buscarId(id);
	}
	
	public void actualizar(Date fechaE, Date fechaS, String valor, String formaPago, Integer id) {
		Integer valorNumero = Integer.parseInt(valor);
		this.reservaDAO.Actualizar(fechaE, fechaS, valorNumero, formaPago, id);
	}
	
	public void Eliminar(Integer id) {
		this.reservaDAO.Eliminar(id);
	}
	
	
}
