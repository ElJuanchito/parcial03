package co.edu.uniquindio.parcial3.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.parcial3.exceptions.ClienteException;
import co.edu.uniquindio.parcial3.model.Atendible;
import co.edu.uniquindio.parcial3.model.ClienteJuridico;
import co.edu.uniquindio.parcial3.model.TipoEmpresa;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class CJuridicoController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnActualizar;

	@FXML
	private Button btnAgregar;

	@FXML
	private Button btnBuscar;

	@FXML
	private Button btnEliminar;

	@FXML
	private ComboBox<TipoEmpresa> cbTipoEmpresa;

	@FXML
	private VBox gestionJuridico;

	@FXML
	private TextField txtNit;

	@FXML
	private TextField txtNombre;

	@FXML
	private TextField txtTelefono;

	@FXML
	void initialize() {
		cbTipoEmpresa.setItems(FXCollections.observableList(TipoEmpresa.getTypes()));
		ModelFactoryController.getInstance().loadData();
	}

	@FXML
	void agregarEvent(ActionEvent event) {
		agregarAction();
		ModelFactoryController.getInstance().saveData();
	}

	@FXML
	void eliminarEvent(ActionEvent event) {
		eliminarAction();
		ModelFactoryController.getInstance().saveData();
	}

	@FXML
	void buscarEvent(ActionEvent event) {
		buscarAction();
		ModelFactoryController.getInstance().saveData();
		
	}

	@FXML
	void actualizarEvent(ActionEvent event) {
		actualizarAction();
		ModelFactoryController.getInstance().saveData();
	}
	
	/**
	 * Realiza los procesos necesarios de verificacion para agregar un clienteJuridico a la lista.
	 */
	private void agregarAction() {
		if (!verificarCampos()) {
			new Alert(AlertType.WARNING, "Llene todos los datos").show();
			return;
		}
		if (!verificarDatos(txtNit.getText().trim()))
			create();
		else
			new Alert(AlertType.WARNING, "El cliente ya existe").show();
	}

	/**
	 * Realiza los procesos necesarios de verificacion para eliminar un Cliente a la lista.
	 */
	private void eliminarAction() {
		if (!verificarCampos()) {
			new Alert(AlertType.WARNING, "Llene todos los datos").show();
			return;
		}
		if (verificarDatos(txtNit.getText().trim()))
			delete();
		else
			new Alert(AlertType.WARNING, "El cliente no existe").show();
	}

	/**
	 * Realiza los procesos necesarios para atualizar un cliente. Llama internamente a las funciones.
	 */
	private void actualizarAction() {
		if (!verificarCampos()) {
			new Alert(AlertType.WARNING, "Llene todos los datos").show();
			return;
		}
		if (verificarDatos(txtNit.getText().trim()))
			update();
		else
			new Alert(AlertType.WARNING, "El cliente no existe").show();

	}

	/**
	 * Realiza los procesos necesarios de verificacion para buscar un ClienteJuridico en la lista.
	 */
	private void buscarAction() {
		if (txtNit.getText().isBlank()) {
			new Alert(AlertType.WARNING, "Llene todos los datos").show();
			return;
		}
		if (verificarDatos(txtNit.getText().trim()))
			actualizarCampos();
		else
			new Alert(AlertType.WARNING, "El cliente no existe").show();
	}

	/**
	 * Verifica si el cliente existe llamando el metodo <b><code>verificarCliente(String)</code> </b> del modelo. 
	 * @param id
	 * @return
	 */
	private boolean verificarDatos(String id) {
		return ModelFactoryController.getInstance().verificarCliente(id);
	}

	/**
	 * Vacia los campos de los <code>TextField</code>  de la ventana.
	 */
	private void limpiarCampos() {
		txtNit.clear();
		txtNombre.clear();
		txtTelefono.clear();
	}

	/**
	 * Verifica que todos los campos de los <code>TextField</code> esten llenos
	 * @return
	 */
	private boolean verificarCampos() {
		if (txtNit.getText().isBlank() || txtNombre.getText().isBlank() || txtTelefono.getText().isBlank()
				|| cbTipoEmpresa.getValue() == null)
			return false;
		return true;
	}

	/**
	 * Crea la instancia de <b><code>ClienteJuridico</code></b> y la guarda en la lista. Lanza una <code>Alert</code> si ocurrio un error.
	 */
	private void create() {
		try {
			Atendible cliente = new ClienteJuridico(txtNit.getText().trim(), txtNombre.getText().trim(),
					txtTelefono.getText().trim(), cbTipoEmpresa.getValue());
			ModelFactoryController.getInstance().agregarCliente(cliente);
			limpiarCampos();
			new Alert(AlertType.CONFIRMATION, "Cliente anadido satisfactoriamente").show();
		} catch (ClienteException e) {
			new Alert(AlertType.ERROR, "Ocurrio un error al registrar el cliente").show();
		}
	}

	/**
	 * Elimina la instancia de <b><code>ClienteJuridico</code></b> identificada con el codigo ingresado. Lanza una <code>Alert</code> si ocurrio un error.
	 */
	private void delete() {
		try {
			ModelFactoryController.getInstance().eliminarCliente(txtNit.getText().trim());
			limpiarCampos();
			new Alert(AlertType.CONFIRMATION, "Cliente eliminado satisfactoriamente").show();
		} catch (ClienteException e) {
			new Alert(AlertType.ERROR, "Ocurrio un error al eliminar el cliente").show();
		}
	}

	/**
	 * busca un <code>ClienteJuridico</code> en la lista invocando el metodo <code>buscarCliente(String)</code>. Lanza una alerta si ocurrio algun error durante el proceso.
	 * @return
	 */
	private ClienteJuridico search() {
		ClienteJuridico cliente = null;
		try {
			if (verificarInstancia()) {
				cliente = (ClienteJuridico) ModelFactoryController.getInstance().buscarCliente(txtNit.getText());
			}
		} catch (ClienteException e) {
			new Alert(AlertType.ERROR, "Ocurrio un error al buscar el cliente").show();
		}
		return cliente;
	}

	/**
	 * Verifica si la instancia de la interface Atendible es del tipo ClienteJuridico.
	 * @return
	 * @throws ClienteException
	 */
	private boolean verificarInstancia() throws ClienteException {
		if (ModelFactoryController.getInstance().buscarCliente(txtNit.getText()) instanceof ClienteJuridico) {
			return true;
		}
		return false;
	}

	/**
	 * Actualiza los campos de los <code>TextField</code> con los datos entregados por por la funcion <code>search</code>
	 */
	private void actualizarCampos() {
		if (search() != null) {
			txtNit.setText(search().getId());
			txtNombre.setText(search().getNombre());
			txtTelefono.setText(search().getTelefono());
			cbTipoEmpresa.setValue(search().getTipoEmpresa());
		} else
			new Alert(AlertType.ERROR, "El cliente buscado no es de tipo juridico").show();
	}

	/**
	 *  Actualiza un ClienteJuridico de la lista del modelo con los datos obtenidos de los <code>TextField's</code>
	 */
	private void update() {
		Atendible cliente = new ClienteJuridico(txtNit.getText().trim(), txtNombre.getText().trim(),
				txtTelefono.getText().trim(), cbTipoEmpresa.getValue());
		try {
			if (verificarInstancia())
				ModelFactoryController.getInstance().actualizarCliente(cliente);
			else
				new Alert(AlertType.WARNING, "El cliente no es de tipo juridico").show();
			limpiarCampos();

		} catch (ClienteException e) {
			new Alert(AlertType.ERROR, "El cliente no existe").show();
		}
	}

}
