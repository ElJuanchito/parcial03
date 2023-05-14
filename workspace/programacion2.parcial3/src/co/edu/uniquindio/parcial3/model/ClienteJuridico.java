package co.edu.uniquindio.parcial3.model;

import java.util.Objects;

public class ClienteJuridico implements Atendible {

	private String nit;
	private String nombre;
	private String telefono;
	private TipoEmpresa tipoEmpresa;

	public ClienteJuridico() {
	}

	public ClienteJuridico(String nit, String nombre, String telefono, TipoEmpresa tipoEmpresa) {
		this.nit = nit;
		this.nombre = nombre;
		this.telefono = telefono;
		this.tipoEmpresa = tipoEmpresa;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public TipoEmpresa getTipoEmpresa() {
		return tipoEmpresa;
	}

	public void setTipoEmpresa(TipoEmpresa tipoEmpresa) {
		this.tipoEmpresa = tipoEmpresa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nit);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClienteJuridico other = (ClienteJuridico) obj;
		return Objects.equals(nit, other.nit);
	}

	@Override
	public String toString() {
		return "ClienteJuridico [nit =" + nit + ", nombre =" + nombre + ", telefono =" + telefono + ", tipoEmpresa ="
				+ tipoEmpresa + "]";
	}

	@Override
	public Atendible buscarCliente(String id) {
		// TODO
		return null;
	}

	@Override
	public String getId() {
		return this.nit;
	}

}
