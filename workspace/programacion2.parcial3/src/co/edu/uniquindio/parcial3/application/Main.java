package co.edu.uniquindio.parcial3.application;
	
import java.io.FileInputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/parcial3/view/ventanaEmpresa.fxml"));
			Scene scene = new Scene(root);
			primaryStage.getIcons().add(new Image(new FileInputStream("src/sources/logo.png")));
			primaryStage.setTitle("Edeq: Grupo EMP");
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
