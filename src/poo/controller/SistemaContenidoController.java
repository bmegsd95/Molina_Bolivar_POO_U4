package poo.controller;

import poo.model.*;
import poo.repository.*;

import java.io.IOException;
import java.util.List;

public class SistemaContenidoController {
	private final ContenidoRepository contenidoRepository;
	private final ActorRepository actorRepository;
	private final TemporadaRepository temporadaRepository;
	private final InvestigadorRepository investigadorRepository;

	public SistemaContenidoController(ContenidoRepository contenidoRepository, ActorRepository actorRepository,
			TemporadaRepository temporadaRepository, InvestigadorRepository investigadorRepository) {
		this.contenidoRepository = contenidoRepository;
		this.actorRepository = actorRepository;
		this.temporadaRepository = temporadaRepository;
		this.investigadorRepository = investigadorRepository;
	}

	// ---------- Persistencia general ----------

	public void cargarDatos() throws IOException {
		contenidoRepository.loadFromFile();
		actorRepository.loadFromFile();
		temporadaRepository.loadFromFile();
		investigadorRepository.loadFromFile();
	}

	public void guardarDatos() throws IOException {
		contenidoRepository.saveToFile();
		actorRepository.saveToFile();
		temporadaRepository.saveToFile();
		investigadorRepository.saveToFile();
	}

	// ---------- Contenidos ----------

	public List<ContenidoAudiovisual> obtenerTodosLosContenidos() {
		return contenidoRepository.findAll();
	}

	public void crearPelicula(String id, String titulo, int anio, int duracion, String genero, double calificacion,
			String director) {
		Pelicula peli = new Pelicula(id, titulo, anio, duracion, genero, calificacion, director);
		contenidoRepository.add(peli);
	}

	public void crearSerie(String id, String titulo, int anio, int duracion, String genero, double calificacion,
			int numTemporadas) {
		SerieTV serie = new SerieTV(id, titulo, anio, duracion, genero, calificacion, numTemporadas);
		contenidoRepository.add(serie);
	}

	public void crearDocumental(String id, String titulo, int anio, int duracion, String genero, double calificacion,
			String tema) {
		Documental doc = new Documental(id, titulo, anio, duracion, genero, calificacion, tema);
		contenidoRepository.add(doc);
	}

	// ---------- Actores ----------

	public List<Actor> obtenerActores() {
		return actorRepository.findAll();
	}

	public void crearActor(String id, String nombre, String nacionalidad) {
		Actor actor = new Actor(id, nombre, nacionalidad);
		actorRepository.add(actor);
	}

	// ---------- Temporadas ----------

	public List<Temporada> obtenerTemporadas() {
		return temporadaRepository.findAll();
	}

	public void crearTemporada(String id, String idSerie, int numero, int cantEpisodios) {
		Temporada temporada = new Temporada(id, idSerie, numero, cantEpisodios);
		temporadaRepository.add(temporada);
	}

	// ---------- Investigadores ----------

	public List<Investigador> obtenerInvestigadores() {
		return investigadorRepository.findAll();
	}

	public void crearInvestigador(String id, String nombre, String area) {
		Investigador investigador = new Investigador(id, nombre, area);
		investigadorRepository.add(investigador);
	}

}
