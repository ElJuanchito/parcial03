package co.edu.uniquindio.parcial3.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Factura implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigo;
	private LocalDate fechaFacturacion;
	private Double totalPagar;
	private Atendible cliente;

	/**
	 * Este es el constructor base de la clase <code>Factura</code>
	 */
	public Factura() {
	}

	/**
	 * Constructor de la clase <code>Factura</code> que recibe parametros para la creacion de la instancia.
	 * @param codigo
	 * @param fechaFacturacion
	 * @param totalPagar
	 * @param cliente
	 */
	public Factura(String codigo, LocalDate fechaFacturacion, Double totalPagar, Atendible cliente) {
		this.codigo = codigo;
		this.fechaFacturacion = fechaFacturacion;
		this.totalPagar = totalPagar;
		this.cliente = cliente;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public LocalDate getFechaFacturacion() {
		return fechaFacturacion;
	}

	public void setFechaFacturacion(LocalDate fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

	public Double getTotalPagar() {
		return totalPagar;
	}

	public void setTotalPagar(Double totalPagar) {
		this.totalPagar = totalPagar;
	}

	public Atendible getCliente() {
		return cliente;
	}

	public void setCliente(Atendible cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Factura other = (Factura) obj;
		return Objects.equals(codigo, other.codigo);
	}

	@Override
	public String toString() {
		return "Factura [codigo =" + codigo + ", fechaFacturacion =" + fechaFacturacion + ", totalPagar =" + totalPagar
				+ ", cliente =" + cliente + "]";
	}

	/**
	 * Verifica si el cliente y la fecha son iguales a las ingresadas por parametro.
	 * @param fecha
	 * @param cliente
	 * @return
	 */
	public boolean coincideFechaCliente(LocalDate fecha, Atendible cliente) {
		return this.fechaFacturacion.isEqual(fecha) && this.cliente.equals(cliente);
	}
	
	/**
	 * Retorna el valor String de la fecha(<code>LocalDate</code>).
	 * @return
	 */
	public String getFecha() {
		return this.fechaFacturacion.toString();
	}
	
	/**
	 * Retorna el id del cliente. Puede ser Juridico o Natural.
	 * @return
	 */
	public String getClienteId() {
		return this.cliente.getId();
	}
	
	/**
	 * Retorna el valor String del total al pagar(<code>Double</code>).
	 * @return
	 */
	public String getTotal() {
		return totalPagar.toString();
	}
	
	/**
	 * Retorna el nombre del cliente.
	 * @return
	 */
	public String getClienteNombre() {
		return cliente.getNombre();
	}

}
