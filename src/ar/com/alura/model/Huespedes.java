package ar.com.alura.model;


import java.util.Date;

public class Huespedes {
	
	private int task_id;

	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private String nacionalidad;
	private String telefono;
	private int id_reserva;
	
	
	public Huespedes(int task_id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad,
			String telefono, int id_reserva) {
		this.task_id = task_id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.id_reserva = id_reserva;
	}
	
	public Huespedes( String nombre, String apellido, Date fechaNacimiento, String nacionalidad,
			String telefono, int id_reserva) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.id_reserva = id_reserva;
	}
	
	public int getTask_id() {
		return task_id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public String getNacionalidad() {
		return nacionalidad;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public int getId_reserva() {
		return id_reserva;
	}

	public void setTask_Id(int int1) {
		this.task_id = int1;
		
	}
}
