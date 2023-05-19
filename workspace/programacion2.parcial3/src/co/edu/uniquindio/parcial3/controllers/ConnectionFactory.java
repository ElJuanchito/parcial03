package co.edu.uniquindio.parcial3.controllers;

import java.sql.*;

public class ConnectionFactory {
	
	/**
	 * Entrega la conexion a la base de datos.
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("exports")
	public Connection obtenerConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost/edeq", "root", "ElJuancho18");
	}
}
