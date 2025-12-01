package poo.model;

public class Temporada {
	private final String id;
	private final String idSerie; // referencia a SerieTV
	private int numero;
	private int cantidadEpisodios;

	public Temporada(String id, String idSerie, int numero, int cantidadEpisodios) {
		this.id = id;
		this.idSerie = idSerie;
		this.numero = numero;
		this.cantidadEpisodios = cantidadEpisodios;
	}

	public String getId() {
		return id;
	}

	public String getIdSerie() {
		return idSerie;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getCantidadEpisodios() {
		return cantidadEpisodios;
	}

	public void setCantidadEpisodios(int cantidadEpisodios) {
		this.cantidadEpisodios = cantidadEpisodios;
	}

	public String toCsv() {
		return String.join(";", id, idSerie, String.valueOf(numero), String.valueOf(cantidadEpisodios));
	}

}
