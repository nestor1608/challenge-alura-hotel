package ar.com.alura.model;

import java.sql.Date;

public class Reserva {
	private int id;
	private Date fechaEntrada;
	private Date fechaSalida;
	private Integer valor;
	private String formaPago;
	

	public Reserva(int id, Date fechaEntrada, Date fechaSalida, Integer valor, String formaPago) {
		this.setId(id);
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formaPago = formaPago;
	}
	
	public Reserva( Date fechaEntrada, Date fechaSalida, Integer valor, String formaPago) {		
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formaPago = formaPago;
	}
	@Override
	public String toString() {
		
		return this.fechaEntrada.toString();
	}

	public int getId() {
		return id;
	}
	

	
	public Date getFechaEntrada() {
		return fechaEntrada;
	}
	

	
	public Date getFechaSalida() {
		return fechaSalida;
	}
	
	
	
	public Integer getValor() {
		return valor;
	}
	
	
	
	public String getFormaPago() {
		return formaPago;
	}


	public void setId(int id) {
		this.id = id;
	}
	

}
