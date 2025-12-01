package poo.model;

import java.util.Objects;

public class Actor {
	private final String id;
	private String nombre;
	private String nacionalidad;

	public Actor(String id, String nombre, String nacionalidad) {
		if (id == null || id.trim().isEmpty()) {
			throw new IllegalArgumentException("El id del actor no puede ser vacío");
		}
		this.id = id;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
	}

	public String getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String toCsv() {
		return String.join(";", id, nombre, nacionalidad);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Actor))
			return false;
		Actor actor = (Actor) o;
		return Objects.equals(id, actor.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

}
