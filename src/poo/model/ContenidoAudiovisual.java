package poo.model;

import java.util.Objects;

/**
 * Clase base para cualquier tipo de contenido audiovisual. SRP: solo modela
 * datos y reglas básicas del contenido.
 */

public abstract class ContenidoAudiovisual {
	private final String id; // con esto aseguramos que el id sea único
	private String titulo;
	private int anioEstreno;
	private int duracionMinutos;
	private String genero;
	private double calificacion;

	protected ContenidoAudiovisual(String id, String titulo, int anioEstreno, int duracionMinutos, String genero,
			double calificacion) {
		if (id == null || id.trim().isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser nulo o vacío");
		}
		this.id = id;
		this.titulo = titulo;
		this.anioEstreno = anioEstreno;
		this.duracionMinutos = duracionMinutos;
		this.genero = genero;
		this.calificacion = calificacion;
	}

	public String getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAnioEstreno() {
		return anioEstreno;
	}

	public void setAnioEstreno(int anioEstreno) {
		this.anioEstreno = anioEstreno;
	}

	public int getDuracionMinutos() {
		return duracionMinutos;
	}

	public void setDuracionMinutos(int duracionMinutos) {
		this.duracionMinutos = duracionMinutos;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public double getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(double calificacion) {
		this.calificacion = calificacion;
	}

	/**
	 * Representación base para CSV (las subclases la reutilizan).
	 */
	public String toCsvBase() {
		return String.join(";", id, titulo, String.valueOf(anioEstreno), String.valueOf(duracionMinutos), genero,
				String.valueOf(calificacion));
	}

	@Override
	public String toString() {
		return "ContenidoAudiovisual{" + "id='" + id + '\'' + ", titulo='" + titulo + '\'' + ", anioEstreno="
				+ anioEstreno + ", duracionMinutos=" + duracionMinutos + ", genero='" + genero + '\''
				+ ", calificacion=" + calificacion + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ContenidoAudiovisual))
			return false;
		ContenidoAudiovisual that = (ContenidoAudiovisual) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

}
