package co.edu.uniquindio.parcial3.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

import co.edu.uniquindio.parcial3.exceptions.ClienteException;
import co.edu.uniquindio.parcial3.exceptions.FacturaException;

public class Edeq {
	private Map<String, Atendible> listaClientes;
	private Map<String, Factura> listaFacturas;

	public Edeq() {
		this.listaClientes = new HashMap<>();
		this.listaFacturas = new HashMap<>();
	}

	public Map<String, Atendible> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(Map<String, Atendible> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public Map<String, Factura> getListaFacturas() {
		return listaFacturas;
	}

	public void setListaFacturas(Map<String, Factura> listaFacturas) {
		this.listaFacturas = listaFacturas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(listaClientes, listaFacturas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edeq other = (Edeq) obj;
		return Objects.equals(listaClientes, other.listaClientes) && Objects.equals(listaFacturas, other.listaFacturas);
	}

	@Override
	public String toString() {
		return "Edeq [listaClientes =" + listaClientes + ", listaFacturas =" + listaFacturas + "]";
	}

	/**
	 * Retorna verdadero o falso dependiendo de si la <b>factura</b> existe en la lista.
	 * 
	 * @param factura
	 * @return
	 */
	private boolean verificarFactura(Factura factura) {
		return listaFacturas.containsKey(factura.getCodigo());
	}

	/**
	 * Lanza una exception si la <b>factura</b> existe en la lista.
	 * 
	 * @param factura
	 * @throws FacturaException
	 */
	private void throwFacturaExist(Factura factura) throws FacturaException {
		if (verificarFactura(factura))
			throw new FacturaException("La factura ya existe en la lista");
	}

	/**
	 * Lanza una exception si la <b>factura</b> no existe en la lista.
	 * 
	 * @param factura
	 * @throws FacturaException
	 */
	private void throwFacturaNonExist(Factura factura) throws FacturaException {
		if (!verificarFactura(factura))
			throw new FacturaException("La factura no existe en la lista");
	}

	/**
	 * Busca una <b>factura</b> en la lista. Lanza una exception si no la encuentra.
	 * 
	 * @param factura
	 * @return
	 * @throws FacturaException
	 */
	public Factura buscarFactura(Factura factura) throws FacturaException {
		throwFacturaNonExist(factura);
		return listaFacturas.get(factura.getCodigo());
	}

	/**
	 * Agregar una <b>factura</b> en la lista. Lanza una exception si la factura ya existe.
	 * @param factura
	 * @throws FacturaException
	 * @throws ClienteException 
	 */
	public void registrarFactura(Factura factura) throws FacturaException, ClienteException {
		throwFacturaExist(factura);
		throwClienteNonExist(factura.getCliente());
		throwClienteFecha(factura);
		listaFacturas.put(factura.getCodigo(), factura);
	}

	private void throwClienteFecha(Factura factura) throws FacturaException {
		if(validarFacturaClientePorFecha(factura.getCliente(), factura.getFechaFacturacion())) throw new FacturaException("Ya existe una factura para el cliente en esa fecha");
	}
	
	/**
	 * Elimina una <b>factura</b> de la lista. Lanza una exception si la factura no existe.
	 * @param factura
	 * @throws FacturaException
	 */
	public void eliminarFactura(Factura factura) throws FacturaException {
		throwFacturaNonExist(factura);
		listaFacturas.remove(factura.getCodigo(), factura);
	}
	
	/**
	 * Retorna verdadero o falso dependiendo de si el <b>cliente</b> existe en la lista.
	 * 
	 * @param cliente
	 * @return
	 */
	private boolean verificarCliente(Atendible cliente) {
		return listaClientes.containsKey(cliente.getId()) && listaClientes.get(cliente.getId()) != null;
	}
	
	/**
	 * Lanza una exception si el <b>cliente</b> existe en la lista.
	 * 
	 * @param cliente
	 * @throws ClienteException
	 */
	private void throwClienteExist(Atendible cliente) throws ClienteException {
		if (verificarCliente(cliente))
			throw new ClienteException("El cliente ya existe en la lista");
	}

	/**
	 * Lanza una exception si el <b>cliente</b> no existe en la lista.
	 * 
	 * @param cliente
	 * @throws ClienteException
	 */
	private void throwClienteNonExist(Atendible cliente) throws ClienteException {
		if (!verificarCliente(cliente))
			throw new ClienteException("El cliente no existe en la lista");
	}
	
	/**
	 * Busca un <b>cliente</b> en la lista. Lanza una exception si no existe en la lista.
	 * @param cliente
	 * @return
	 * @throws ClienteException
	 */
	public Atendible buscarCliente(Atendible cliente) throws ClienteException {
		throwClienteNonExist(cliente);
		
		return listaClientes.get(cliente.getId());
	}
	
	/**
	 * Registra un cliente en la lista. Lanza una exception si el cliente ya existe.
	 * @param cliente
	 * @throws ClienteException
	 */
	public void registrarCliente(Atendible cliente) throws ClienteException {
		throwClienteExist(cliente);
		listaClientes.put(cliente.getId(), cliente);
	}
	
	/**
	 * Elimina el cliente de la lista. Lanza una exception si el cliente no existe.
	 * @param cliente
	 * @throws ClienteException
	 */
	public void eliminarCliente(Atendible cliente)throws ClienteException {
		throwClienteNonExist(cliente);
		listaClientes.remove(cliente.getId(), cliente);
	}
	
	private boolean validarFacturaClientePorFecha(Atendible cliente, LocalDate fecha) {
		AtomicBoolean coincide = new AtomicBoolean(false);
		listaFacturas.forEach( (clave, valor) -> {
			if(valor.coincideFechaCliente(fecha, cliente)) coincide.set(true) ;
		}); return coincide.get();
	}

}
