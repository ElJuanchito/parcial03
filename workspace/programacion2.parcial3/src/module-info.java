module programacion2.parcial3 {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	requires java.desktop;
	requires java.sql;
	
	exports co.edu.uniquindio.parcial3.controllers to javafx.fxml;
	
	opens co.edu.uniquindio.parcial3.application to javafx.graphics, javafx.fxml;
	opens co.edu.uniquindio.parcial3.controllers to javafx.fxml;
}
