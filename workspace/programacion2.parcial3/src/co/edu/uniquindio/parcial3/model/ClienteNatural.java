package co.edu.uniquindio.parcial3.model;

import java.util.Objects;

public class ClienteNatural implements Atendible {

	private String nombre;
	private String apellidos;
	private String cedula;
	private Integer estrato;

	public ClienteNatural() {
	}

	public ClienteNatural(String nombre, String apellidos, String cedula, Integer estrato) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.cedula = cedula;
		this.estrato = estrato;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public Integer getEstrato() {
		return estrato;
	}

	public void setEstrato(Integer estrato) {
		this.estrato = estrato;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cedula);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClienteNatural other = (ClienteNatural) obj;
		return Objects.equals(cedula, other.cedula);
	}

	@Override
	public String toString() {
		return "ClienteNatural [nombre =" + nombre + ", apellidos =" + apellidos + ", cedula =" + cedula + ", estrato ="
				+ estrato + "]";
	}

	@Override
	public Atendible buscarCliente(String id) {
		// TODO
		return null;
	}

	@Override
	public String getId() {
		return this.cedula;
	}

}
