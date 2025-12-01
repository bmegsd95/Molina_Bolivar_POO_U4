package poo.model;

/**
 * Documental con un tema principal.
 */

public class Documental extends ContenidoAudiovisual {
	private String temaPrincipal;

	public Documental(String id, String titulo, int anioEstreno, int duracionMinutos, String genero,
			double calificacion, String temaPrincipal) {
		super(id, titulo, anioEstreno, duracionMinutos, genero, calificacion);
		this.temaPrincipal = temaPrincipal;
	}

	public String getTemaPrincipal() {
		return temaPrincipal;
	}

	public void setTemaPrincipal(String temaPrincipal) {
		this.temaPrincipal = temaPrincipal;
	}

	public String toCsv() {
		return String.join(";", "DOCUMENTAL", toCsvBase(), temaPrincipal);
	}

}
