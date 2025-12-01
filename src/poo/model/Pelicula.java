package poo.model;

/**
 * Película concreta. Extiende de ContenidoAudiovisual
 */
public class Pelicula extends ContenidoAudiovisual {
	private String director;

	public Pelicula(String id, String titulo, int anioEstreno, int duracionMinutos, String genero, double calificacion,
			String director) {
		super(id, titulo, anioEstreno, duracionMinutos, genero, calificacion);
		this.director = director;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String toCsv() {
		// tipo;datosBase;campoEspecifico
		return String.join(";", "PELICULA", toCsvBase(), director);
	}

}
