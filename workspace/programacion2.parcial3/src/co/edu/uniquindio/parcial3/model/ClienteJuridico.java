package co.edu.uniquindio.parcial3.model;

import java.io.Serializable;
import java.util.Objects;

public class ClienteJuridico implements Atendible, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nit;
	private String nombre;
	private String telefono;
	private TipoEmpresa tipoEmpresa;

	/**
	 * Este es el constructor base de la clase <code>ClienteJuridico</code>
	 */
	private ClienteJuridico() {
	}

	/**
	 * Constructor de la clase Cliente Juridico
	 * @param nit
	 * @param nombre
	 * @param telefono
	 * @param tipoEmpresa
	 */
	public ClienteJuridico(String nit, String nombre, String telefono, TipoEmpresa tipoEmpresa) {
		this();
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

	@Override
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
	public String getId() {
		return this.nit;
	}

	@Override
	public String getTipoCliente() {
		return "Juridico";
	}
	
	/**
	 * Retorna el tipo de la empresa ya sea "PRIVADA" o "PUBLICA".
	 * @return
	 */
	public String getTipo() {
		return tipoEmpresa.getTipo();
	}

}
