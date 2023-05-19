package co.edu.uniquindio.parcial3.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.parcial3.exceptions.ClienteException;
import co.edu.uniquindio.parcial3.exceptions.FacturaException;
import co.edu.uniquindio.parcial3.model.Factura;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class FacturaController {

	ObservableList<Factura> listaObservable;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnAgregar;

	@FXML
	private Button btnBuscar;

	@FXML
	private Button btnEliminar;

	@FXML
	private TableColumn<Factura, String> colCliente;

	@FXML
	private TableColumn<Factura, String> colFecha;

	@FXML
	private TableColumn<Factura, String> colId;

	@FXML
	private TableColumn<Factura, String> colPagar;

	@FXML
	TableColumn<Factura, String> colClienteId;

	@FXML
	private DatePicker fechaPicker;

	@FXML
	private TableView<Factura> tablaFacturas;

	@FXML
	private TextField txtCodigo;

	@FXML
	private TextField txtId;

	@FXML
	private TextField txtPagar;

	@FXML
	private BorderPane ventanaFactura;

	@FXML
	void initialize() {
		ModelFactoryController.getInstance().loadData();
		listaObservable = FXCollections.observableList(ModelFactoryController.getInstance().getListaFacturas());
		tablaFacturas.setItems(listaObservable);
		colId.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getCodigo()));
		colFecha.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getFecha()));
		colClienteId.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getClienteId()));
		colPagar.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getTotal()));
		colCliente.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getClienteNombre()));
		tablaFacturas.refresh();
	}

	@FXML
	void agregarEvent(ActionEvent event) {
		agregarAction();
		actualizarTabla();
	}

	@FXML
	void buscarEvent(ActionEvent event) {
		buscarAction();
	}

	@FXML
	void eliminarEvent(ActionEvent event) {
		eliminarAction();
		actualizarTabla();
	}

	@FXML
	void tablaEvent(MouseEvent event) {
		tablaAction();
	}

	private void tablaAction() {
		actualizarCampos(tablaFacturas.getSelectionModel().getSelectedItem());
	}

	/**
	 * Actualiza despues de un cambio en la lista.
	 */
	public void actualizarTabla() {
		listaObservable = FXCollections.observableArrayList(ModelFactoryController.getInstance().getListaFacturas());
		tablaFacturas.setItems(listaObservable);
		tablaFacturas.refresh();
		ModelFactoryController.getInstance().saveData();
	}

	/**
	 * Realiza los procesos necesarios de verificacion para agregar una factura a la lista.
	 */
	private void agregarAction() {
		if(!verificarCliente()) {
			new Alert(AlertType.WARNING, "El cliente no existe").show();
			return;
		}
		if (!verificarCampos()) {
			new Alert(AlertType.WARNING, "Llene todos los datos").show();
			return;
		}
		if (!verificarDatos(txtCodigo.getText().trim()))
			create();
		else
			new Alert(AlertType.WARNING, "La factura ya existe").show();
	}

	/**
	 * Verifica que todos los campos de los <code>TextField</code> esten llenos
	 * @return
	 */
	private boolean verificarCampos() {
		if (txtCodigo.getText().isBlank() || txtId.getText().isBlank() || txtPagar.getText().isBlank()
				|| fechaPicker == null)
			return false;
		return true;
	}

	/**
	 * Verifica si la factura existe llamando el metodo <b><code>verificarFactura(String)</code> </b> del modelo. 
	 * @param id
	 * @return
	 */
	private boolean verificarDatos(String id) {
		return ModelFactoryController.getInstance().verificarFactura(id);
	}

	/**
	 * Crea la instancia de <b><code>Factura</code></b> y la guarda en la lista. Lanza una <code>Alert</code> si ocurrio un error.
	 */
	private void create() {
		try {
			Factura factura = new Factura(txtCodigo.getText().trim(), fechaPicker.getValue(),
					Double.parseDouble(txtPagar.getText().trim()),
					ModelFactoryController.getInstance().buscarCliente(txtId.getText().trim()));
			ModelFactoryController.getInstance().agregarFactura(factura);
			limpiarCampos();
			new Alert(AlertType.CONFIRMATION, "Factura anadida satisfactoriamente").show();
		} catch (FacturaException | ClienteException e) {
			new Alert(AlertType.ERROR, "Ocurrio un error al registrar la factura").show();
		}
	}

	/**
	 * Vacia los campos de los <code>TextField</code>  de la ventana.
	 */
	private void limpiarCampos() {
		txtCodigo.clear();
		txtId.clear();
		txtPagar.clear();
		fechaPicker.setValue(null);
	}

	/**
	 * Realiza los procesos necesarios de verificacion para eliminar una factura a la lista.
	 */
	private void eliminarAction() {
		if (!verificarCampos()) {
			new Alert(AlertType.WARNING, "Llene todos los datos").show();
			return;
		}
		if (verificarDatos(txtCodigo.getText().trim()))
			delete();
		else
			new Alert(AlertType.WARNING, "La factura no existe").show();
	}

	/**
	 * Elimina la instancia de <b><code>Factura</code></b> identificada con el codigo ingresado. Lanza una <code>Alert</code> si ocurrio un error.
	 */
	private void delete() {
		try {
			ModelFactoryController.getInstance().eliminarFactura(txtCodigo.getText().trim());
			limpiarCampos();
			new Alert(AlertType.CONFIRMATION, "Factura eliminada satisfactoriamente").show();
		} catch (FacturaException e) {
			new Alert(AlertType.ERROR, "Ocurrio un error al eliminar la factura").show();
		}

	}

	/**
	 * Realiza los procesos necesarios de verificacion para buscar una factura en la lista.
	 */
	private void buscarAction() {
		if (txtCodigo.getText().isBlank()) {
			new Alert(AlertType.WARNING, "Llene todos los datos").show();
			return;
		}
		if (verificarDatos(txtCodigo.getText().trim()))
			actualizarCampos();
		else
			new Alert(AlertType.WARNING, "El cliente no existe").show();
	}

	/**
	 * Actualiza los campos de los <code>TextField</code> con los datos recibidos por la funcion <code>search()</code>.
	 */
	private void actualizarCampos() {

		txtCodigo.setText(search().getCodigo());
		txtId.setText(search().getClienteId());
		txtPagar.setText(search().getTotal());
		fechaPicker.setValue(search().getFechaFacturacion());

	}

	/**
	 * Actualiza los campos de los <code>TextField</code> con los datos entregados por la parametro.
	 * @param factura
	 */
	private void actualizarCampos(Factura factura) {
		if(factura != null) {
			txtCodigo.setText(factura.getCodigo());
			txtId.setText(factura.getClienteId());
			txtPagar.setText(factura.getTotal());
			fechaPicker.setValue(factura.getFechaFacturacion());
		}
	}

	/**
	 * busca una <code>Factura</code> en la lista invocando el metodo <code>buscarFactura(String)</code>. Lanza una alerta si ocurrio algun error durante el proceso.
	 * @return
	 */
	private Factura search() {
		Factura cliente = null;
		try {
			cliente = ModelFactoryController.getInstance().buscarFactura(txtCodigo.getText());
		} catch (FacturaException e) {
			new Alert(AlertType.ERROR, "Ocurrio un error al buscar la factura").show();
		}
		return cliente;
	}
	
	/**
	 * verifica si el cliente existe mediante el metodo <code>verificarCliente</code>. 
	 * @return
	 */
	private boolean verificarCliente() {
		if(ModelFactoryController.getInstance().verificarCliente(txtId.getText())) return true;
		return false;
	}

}
