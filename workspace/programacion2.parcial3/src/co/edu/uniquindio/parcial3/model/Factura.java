package co.edu.uniquindio.parcial3.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import javafx.util.converter.LocalDateStringConverter;

public class Factura {
	private String codigo;
	private LocalDate fechaFacturacion;
	private Double totalPagar;
	private Atendible cliente;
	
	public Factura() {
	}

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

	public boolean coincideFechaCliente(LocalDate fecha, Atendible cliente) {
		return this.fechaFacturacion.isEqual(fecha) && this.cliente.equals(cliente);
	}
	
	
	
	
}
