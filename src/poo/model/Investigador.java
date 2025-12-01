package poo.model;

public class Investigador {
	private final String id;
	private String nombre;
	private String areaEspecialidad;

	public Investigador(String id, String nombre, String areaEspecialidad) {
		this.id = id;
		this.nombre = nombre;
		this.areaEspecialidad = areaEspecialidad;
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

	public String getAreaEspecialidad() {
		return areaEspecialidad;
	}

	public void setAreaEspecialidad(String areaEspecialidad) {
		this.areaEspecialidad = areaEspecialidad;
	}

	public String toCsv() {
		return String.join(";", id, nombre, areaEspecialidad);
	}

}
