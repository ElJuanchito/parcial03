package co.edu.uniquindio.parcial3.model;

public enum TipoEmpresa {
	PUBLICA("Publica"), PRIVADA("Privada");
	
	private final String tipo;
	
	private TipoEmpresa(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
	
	public String[] getTipos() {
		return new String [] {TipoEmpresa.PUBLICA.tipo, TipoEmpresa.PRIVADA.tipo};
	}
}
