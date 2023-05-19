package co.edu.uniquindio.parcial3.controllers;

import java.io.IOException;

import co.edu.uniquindio.parcial3.model.Atendible;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ClientesController {
	
	private ObservableList<Atendible> listaObservable;
	
	@FXML private BorderPane ventanaClientes;
	
	@FXML private TableView<Atendible> tabla;
	
	@FXML private TableColumn<Atendible, String> colNombres;
	
	@FXML private TableColumn<Atendible, String> colIds;
	
	@FXML private TableColumn<Atendible, String> colTipos;
	
	@FXML private RadioButton btnJuridico;
	
	@FXML private RadioButton btnNatural;
	
	@FXML private VBox gestionClientes;
	
	
	@FXML
	void initialize() {
		ModelFactoryController.getInstance().loadData();
		listaObservable = FXCollections.observableList(ModelFactoryController.getInstance().getListaClientes());
		tabla.setItems(listaObservable);
		colNombres.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getNombre()));
		colIds.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getId()));
		colTipos.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getTipoCliente()));
		tabla.refresh();
	}
	
	@FXML
    void actualizar(MouseEvent event) {
		actualizarTabla();
    }
	
	@FXML
	private void btnJuridicoAction(ActionEvent action) {
		if(btnJuridico.isSelected()) btnNatural.setSelected(false);
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/parcial3/view/gestionJuridico.fxml"));
	        VBox gestionJuridico = (VBox)loader.load();
	        ventanaClientes.setCenter(gestionJuridico);
		} catch (IOException e) {
			new Alert(AlertType.ERROR, "Error con la seleccion de cliente").show();
			e.printStackTrace();
		}
		
	}
	
	@FXML
	private void btnNaturalAction(ActionEvent action) {
		if(btnNatural.isSelected()) btnJuridico.setSelected(false);
		try {
			ventanaClientes.setCenter((VBox)FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/parcial3/view/gestionNatural.fxml")));
		} catch (IOException e) {
			new Alert(AlertType.ERROR, "Error con la seleccion de cliente").show();
			e.printStackTrace();
		}
	}
	
	public void actualizarTabla() {
		listaObservable = FXCollections.observableArrayList(ModelFactoryController.getInstance().getListaClientes());
		tabla.setItems(listaObservable);
		tabla.refresh();
		ModelFactoryController.getInstance().saveData();
	}
}
