package co.edu.uniquindio.parcial3.controllers;

import java.io.FileInputStream;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import co.edu.uniquindio.parcial3.exceptions.ClienteException;
import co.edu.uniquindio.parcial3.exceptions.FacturaException;
import co.edu.uniquindio.parcial3.model.Atendible;
import co.edu.uniquindio.parcial3.model.ClienteJuridico;
import co.edu.uniquindio.parcial3.model.ClienteNatural;
import co.edu.uniquindio.parcial3.model.Edeq;
import co.edu.uniquindio.parcial3.model.Factura;

@SuppressWarnings("exports")
public class ModelFactoryController {

	private Edeq edeq;

	/**
	 * Constructor del controlador del modelFactory.
	 */
	private ModelFactoryController() {
	}

	/**
	 * Implementacion del patron singleton.
	 * 
	 * @author juanp
	 *
	 */
	private static class Singleton {
		private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
	}

	/**
	 * Obtiene la instancia del controllador implementando el patron singleton.
	 * 
	 * @return
	 */
	public static ModelFactoryController getInstance() {
		return Singleton.eINSTANCE;
	}

	/**
	 * 
	 * @return
	 */
	public Edeq getEdeq() {
		if (edeq == null)
			edeq = new Edeq();
		return edeq;
	}

	public void setEdeq(Edeq edeq) {
		this.edeq = edeq;
	}

	/**
	 * Serializa los datos del objeto y los guarda en la ubicacion especificada.
	 * 
	 * @throws IOException
	 */
	public void saveData() {
		try {
			FileOutputStream fileOut = new FileOutputStream("src/co/edu/uniquindio/parcial3/controllers/data.txt");
			ObjectOutputStream obOut = new ObjectOutputStream(fileOut);
			obOut.writeObject(edeq);
			obOut.close();
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Serializa los datos del clienteJuridico y los guarda en la base de datos.
	 * 
	 * @throws IOException
	 */
	public void guardarJuridico(ClienteJuridico cliente) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream obOut = new ObjectOutputStream(baos);
			obOut.writeObject(cliente);
			byte[] serializado = baos.toByteArray();

			Connection con = new ConnectionFactory().obtenerConnection();
			PreparedStatement pst = con.prepareStatement("INSERT INTO juridicos(nit, cliente) VALUES (?, ?)");
			pst.setString(1, cliente.getNit());
			pst.setBytes(2, serializado);
			pst.executeUpdate();
			obOut.close();
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Serializa los datos del clienteNatural y los guarda en la base de datos.
	 * 
	 * @throws IOException
	 */
	public void guardarNatural(ClienteNatural cliente) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream obOut = new ObjectOutputStream(baos);
			obOut.writeObject(cliente);
			byte[] serializado = baos.toByteArray();

			Connection con = new ConnectionFactory().obtenerConnection();
			PreparedStatement pst = con.prepareStatement("INSERT INTO naturales(cedula, cliente) VALUES (?, ?)");
			pst.setString(1, cliente.getCedula());
			pst.setBytes(2, serializado);
			pst.executeUpdate();
			obOut.close();
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Toma los datos de la ubicacion especificada y los asigna a la instancia del
	 * objeto
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void loadData() {

		try {
			FileInputStream fileIn = new FileInputStream("src/co/edu/uniquindio/parcial3/controllers/data.txt");
			ObjectInputStream obIn = new ObjectInputStream(fileIn);
			this.edeq = (Edeq) obIn.readObject();
			obIn.close();
			fileIn.close();
		} catch (IOException | ClassNotFoundException e) {
			saveData();
		}

	}

	/**
	 * Retorna la lista de clientes de la empresa.
	 * 
	 * @return
	 */
	public List<Atendible> getListaClientes() {
		return getEdeq().getClientesLista();
	}

	/**
	 * Retorna la lista de facturas de la empresa.
	 * 
	 * @return
	 */
	public List<Factura> getListaFacturas() {
		return getEdeq().getFacturasLista();
	}

	/**
	 * Guarda una factura en la base de datos.
	 * 
	 * @param factura
	 * @throws FacturaException
	 * @throws ClienteException
	 */
	public void guardarFactura(Factura factura) throws FacturaException, ClienteException {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream obOut = new ObjectOutputStream(baos);
			obOut.writeObject(factura);
			byte[] serializado = baos.toByteArray();

			Connection con = new ConnectionFactory().obtenerConnection();
			PreparedStatement pst = con.prepareStatement("INSERT INTO facturas(codigo, factura) VALUES (?, ?)");
			pst.setString(1, factura.getCodigo());
			pst.setBytes(2, serializado);
			pst.executeUpdate();
			obOut.close();
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void agregarFactura(Factura factura) throws FacturaException, ClienteException {
		getEdeq().registrarFactura(factura);
	}

	/**
	 * Llama la funcion <b>eliminarFactura()</b> desde el modelo.
	 * 
	 * @param codigo
	 * @throws FacturaException
	 */
	public void eliminarFactura(String codigo) throws FacturaException {
		getEdeq().eliminarFactura(codigo);
	}

	/**
	 * Llama la funcion <b>buscarFactura()</b> desde el modelo.
	 * 
	 * @param codigo
	 * @return
	 * @throws FacturaException
	 */
	public Factura buscarFactura(String codigo) throws FacturaException {
		return getEdeq().buscarFactura(codigo);
	}

	/**
	 * Llama la funcion <b>verificarFactura()</b> desde el modelo.
	 * 
	 * @param codigo
	 * @return
	 */
	public boolean verificarFactura(String codigo) {
		return getEdeq().verificarFactura(codigo);
	}

	/**
	 * Agrega un nuevo cliente a la lista de la empresa.
	 * 
	 * @param cliente
	 * @throws ClienteException
	 */
	public void agregarCliente(Atendible cliente) throws ClienteException {
		getEdeq().registrarCliente(cliente);
	}

	/**
	 * llama la funcion <b>eliminarCliente</b> desde el modelo.
	 * 
	 * @param id
	 * @throws ClienteException
	 */
	public void eliminarCliente(String id) throws ClienteException {
		getEdeq().eliminarCliente(id);
	}

	/**
	 * llama la funcion <b>buscarCliente</b> desde el modelo.
	 * 
	 * @param id
	 * @return
	 * @throws ClienteException
	 */
	public Atendible buscarCliente(String id) throws ClienteException {
		return getEdeq().buscarCliente(id);

	}
	
	/**
	 * Busca el clienteJuridico en la base de datos.
	 * 
	 * @param id
	 * @return
	 * @throws ClienteException
	 */
	public ClienteJuridico buscarJuridico(String id) {
        try {
        	Connection con = new ConnectionFactory().obtenerConnection();
            PreparedStatement statement = con.prepareStatement("SELECT cliente FROM juridicos WHERE nit = ?");
            statement.setString(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                byte[] objetoBytes = resultSet.getBytes("cliente");

                ByteArrayInputStream bais = new ByteArrayInputStream(objetoBytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                return (ClienteJuridico) ois.readObject();
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
		return null;
    }
	
	/**
	 * Busca el clienteNatural en la base de datos.
	 * 
	 * @param id
	 * @return
	 * @throws ClienteException
	 */
	public ClienteNatural buscarNatural(String id) {
        try {
        	Connection con = new ConnectionFactory().obtenerConnection();
            PreparedStatement statement = con.prepareStatement("SELECT cliente FROM naturales WHERE cedula = ?");
            statement.setString(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                byte[] objetoBytes = resultSet.getBytes("cliente");

                ByteArrayInputStream bais = new ByteArrayInputStream(objetoBytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                return (ClienteNatural) ois.readObject();
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
		return null;
    }

	/**
	 * llama la funcion <b>verificarCliente</b> desde el modelo.
	 * 
	 * @param id
	 * @return
	 */
	public boolean verificarCliente(String id) {
		return getEdeq().verificarCliente(id);
	}

	/**
	 * llama la funcion <b>actualizarCliente</b> desde el modelo.
	 * 
	 * @param cliente
	 * @throws ClienteException
	 */
	public void actualizarCliente(Atendible cliente) throws ClienteException {
		getEdeq().actualizarCliente(cliente);
	}

	/**
	 * Elimina un <b>CLiente Juridico</b> de la base de datos.
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public void eliminarJuridico(String id) throws SQLException {
		Connection con = new ConnectionFactory().obtenerConnection();
		PreparedStatement pst = con.prepareStatement("DELETE FROM juridicos WHERE nit = ?");
		pst.setString(1, id);

		pst.executeUpdate();
	}
	
	/**
	 * Elimina un <b>CLiente Natural</b> de la base de datos.
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public void eliminarNatural(String id) throws SQLException {
		Connection con = new ConnectionFactory().obtenerConnection();
		PreparedStatement pst = con.prepareStatement("DELETE FROM naturales WHERE cedula = ?");
		pst.setString(1, id);

		pst.executeUpdate();
	}
}
