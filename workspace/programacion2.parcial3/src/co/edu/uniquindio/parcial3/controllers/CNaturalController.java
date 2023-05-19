package co.edu.uniquindio.parcial3.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.parcial3.exceptions.ClienteException;
import co.edu.uniquindio.parcial3.model.Atendible;
import co.edu.uniquindio.parcial3.model.ClienteNatural;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class CNaturalController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private VBox gestionNatural;

	@FXML
	private Spinner<Integer> spinnerEstrato;

	@FXML
	private TextField txtApellidos;

	@FXML
	private TextField txtCedula;

	@FXML
	private TextField txtNombre;

	@FXML
	private Button btnActualizar;

	@FXML
	private Button btnAgregar;

	@FXML
	private Button btnBuscar;

	@FXML
	private Button btnEliminar;

	@FXML
	void initialize() {
		ModelFactoryController.getInstance().loadData();
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5);
		valueFactory.setValue(1);

		spinnerEstrato.setValueFactory(valueFactory);
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
	 * Realiza los procesos necesarios para atualizar un cliente. Llama internamente a las funciones 
	 */
	private void actualizarAction() {
		if (!verificarCampos()) {
			new Alert(AlertType.WARNING, "Llene todos los datos").show();
			return;
		}
		if (verificarDatos(txtCedula.getText().trim()))
			update();
		else
			new Alert(AlertType.WARNING, "El cliente no existe").show();

	}

	/**
	 * Actualiza un clienteNatural de la lista del modelo con los datos obtenidos de los <code>TextField's</code>
	 */
	private void update() {
		Atendible cliente = new ClienteNatural(txtNombre.getText().trim(), txtApellidos.getText().trim(),
				txtCedula.getText().trim(), spinnerEstrato.getValue());
		try {
			if (verificarInstancia())
				ModelFactoryController.getInstance().actualizarCliente(cliente);
			else
				new Alert(AlertType.WARNING, "El cliente no es de tipo Natural").show();
			limpiarCampos();

		} catch (ClienteException e) {
			new Alert(AlertType.ERROR, "El cliente no existe").show();
		}
	}

	/**
	 * Realiza los procesos necesarios de verificacion para buscar un ClienteNatural en la lista.
	 */
	private void buscarAction() {
		if (txtCedula.getText().isBlank()) {
			new Alert(AlertType.WARNING, "Llene todos los datos").show();
			return;
		}
		if (verificarDatos(txtCedula.getText().trim()))
			actualizarCampos();
		else
			new Alert(AlertType.WARNING, "El cliente no existe").show();

	}
	
	/**
	 * Actualiza los campos de los <code>TextField</code> con los datos entregados por por la funcion <code>search</code>
	 */
	private void actualizarCampos() {
		if (search() != null) {
			txtCedula.setText(search().getId());
			txtNombre.setText(search().getNombre());
			txtApellidos.setText(search().getApellidos());
			spinnerEstrato.getValueFactory().setValue(search().getEstrato());
		} else
			new Alert(AlertType.ERROR, "El cliente buscado no es de tipo Natural").show();

	}

	/**
	 * Realiza los procesos necesarios de verificacion para eliminar un Cliente a la lista.
	 */
	private void eliminarAction() {
		if (!verificarCampos()) {
			new Alert(AlertType.WARNING, "Llene todos los datos").show();
			return;
		}
		if (verificarDatos(txtCedula.getText().trim()))
			delete();
		else
			new Alert(AlertType.WARNING, "El cliente no existe").show();
	}

	/**
	 * Elimina la instancia de <b><code>ClienteNatural</code></b> identificada con el codigo ingresado. Lanza una <code>Alert</code> si ocurrio un error.
	 */
	private void delete() {
		try {
			ModelFactoryController.getInstance().eliminarCliente(txtCedula.getText().trim());
			limpiarCampos();
			new Alert(AlertType.CONFIRMATION, "Cliente eliminado satisfactoriamente").show();
		} catch (ClienteException e) {
			new Alert(AlertType.ERROR, "Ocurrio un error al eliminar el cliente").show();
		}

	}
	
	/**
	 * Realiza los procesos necesarios de verificacion para agregar un clienteNatural a la lista.
	 */
	private void agregarAction() {
		if (!verificarCampos()) {
			new Alert(AlertType.WARNING, "Llene todos los datos").show();
			return;
		}
		if (!verificarDatos(txtCedula.getText().trim()))
			create();
		else
			new Alert(AlertType.WARNING, "El cliente ya existe").show();
	}

	/**
	 * Crea la instancia de <b><code>ClienteNatural</code></b> y la guarda en la lista. Lanza una <code>Alert</code> si ocurrio un error.
	 */
	private void create() {
		try {
			Atendible cliente = new ClienteNatural(txtNombre.getText().trim(), txtApellidos.getText().trim(),
					txtCedula.getText().trim(), spinnerEstrato.getValue());
			ModelFactoryController.getInstance().agregarCliente(cliente);
			limpiarCampos();
			new Alert(AlertType.CONFIRMATION, "Cliente anadido satisfactoriamente").show();
		} catch (ClienteException e) {
			new Alert(AlertType.ERROR, "Ocurrio un error al registrar el cliente").show();
		}
	}

	/**
	 * Vacia los campos de los <code>TextField</code>  de la ventana.
	 */
	private void limpiarCampos() {
		txtNombre.clear();
		txtApellidos.clear();
		txtCedula.clear();
		spinnerEstrato.getValueFactory().setValue(1);
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
	 * Verifica que todos los campos de los <code>TextField</code> esten llenos
	 * @return
	 */
	private boolean verificarCampos() {
		if (txtCedula.getText().isBlank() || txtNombre.getText().isBlank() || txtApellidos.getText().isBlank())
			return false;
		return true;
	}

	/**
	 * busca un <code>ClienteNatural</code> en la lista invocando el metodo <code>buscarCliente(String)</code>. Lanza una alerta si ocurrio algun error durante el proceso.
	 * @return
	 */
	private ClienteNatural search() {
		ClienteNatural cliente = null;
		try {
			if (verificarInstancia()) {
				cliente = (ClienteNatural) ModelFactoryController.getInstance().buscarCliente(txtCedula.getText());
			}
		} catch (ClienteException e) {
			new Alert(AlertType.ERROR, "Ocurrio un error al buscar el cliente").show();
		}
		return cliente;
	}

	/**
	 * Verifica si la instancia de la interface Atendible es del tipo ClienteNatural.
	 * @return
	 * @throws ClienteException
	 */
	private boolean verificarInstancia() throws ClienteException {
		if (ModelFactoryController.getInstance().buscarCliente(txtCedula.getText()) instanceof ClienteNatural) {
			return true;
		}
		return false;
	}

}