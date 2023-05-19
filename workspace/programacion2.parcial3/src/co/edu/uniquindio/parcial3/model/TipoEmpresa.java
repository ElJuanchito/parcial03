package co.edu.uniquindio.parcial3.model;

import java.util.Arrays;
import java.util.List;

public enum TipoEmpresa{
	PUBLICA("PUBLICA"), PRIVADA("PRIVADA");
	
	private final String tipo;
	
	private TipoEmpresa(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Retorna el valor String del TipoEmpresa.
	 * @return
	 */
	public String getTipo() {
		return tipo;
	}
	
	/**
	 * Retorna un Array de String's con los tipos de TipoEmpresa.
	 * @return
	 */
	public static String[] getTipos() {
		return new String [] {TipoEmpresa.PUBLICA.tipo, TipoEmpresa.PRIVADA.tipo};
	}
	
	/**
	 * Retorna una lista con los tipos de TipoEmpresa.
	 * @return
	 */
	public static List<TipoEmpresa> getTypes(){
		return Arrays.asList(values());
	}
	
}
