package co.edu.uniquindio.parcial3.model;

public interface Atendible {
	
	/**
	 * Retorna el Id del cliente.
	 * @return
	 */
	String getId();
	
	/**
	 * Retorna el nombre del cliente.
	 * @return
	 */
	String getNombre();
	
	/**
	 * Retorna el tipo del cliente ya sea 'Natural' o 'Juridico'. 
	 * @return
	 */
	String getTipoCliente();
}
