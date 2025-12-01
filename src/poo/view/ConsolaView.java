package poo.view;

import poo.controller.SistemaContenidoController;
import poo.model.Actor;
import poo.model.ContenidoAudiovisual;
import poo.model.Investigador;
import poo.model.Temporada;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ConsolaView {
	private final SistemaContenidoController controller;
	private final Scanner scanner = new Scanner(System.in);

	public ConsolaView(SistemaContenidoController controller) {
		this.controller = controller;
	}

	public void ejecutar() {
		try {
			controller.cargarDatos();
			System.out.println("Datos cargados correctamente.\n");
		} catch (IOException e) {
			System.out.println("No se pudieron cargar los datos existentes: " + e.getMessage());
		}

		boolean salir = false;
		while (!salir) {
			mostrarMenu();
			int opcion = leerEntero("Elige una opción: ");
			System.out.println();

			switch (opcion) {
			case 1 -> mostrarContenidos();
			case 2 -> crearPelicula();
			case 3 -> crearSerie();
			case 4 -> crearDocumental();
			case 5 -> mostrarActores();
			case 6 -> crearActor();
			case 7 -> mostrarTemporadas();
			case 8 -> crearTemporada();
			case 9 -> mostrarInvestigadores();
			case 10 -> crearInvestigador();
			case 0 -> salir = salirAplicacion();
			default -> System.out.println("Opción no válida.\n");
			}
		}
	}

	private void mostrarMenu() {
		System.out.println("===== SISTEMA DE CONTENIDO AUDIOVISUAL =====");
		System.out.println("1. Listar contenidos");
		System.out.println("2. Crear película");
		System.out.println("3. Crear serie de TV");
		System.out.println("4. Crear documental");
		System.out.println("5. Listar actores");
		System.out.println("6. Crear actor");
		System.out.println("7. Listar temporadas");
		System.out.println("8. Crear temporada");
		System.out.println("9. Listar investigadores");
		System.out.println("10. Crear investigador");
		System.out.println("0. Guardar y salir");
	}

	// --------- Opciones de menú ---------

	private void mostrarContenidos() {
		List<ContenidoAudiovisual> contenidos = controller.obtenerTodosLosContenidos();
		if (contenidos.isEmpty()) {
			System.out.println("No hay contenidos registrados.\n");
		} else {
			System.out.println("Contenidos registrados:");
			contenidos.forEach(System.out::println);
			System.out.println();
		}
	}

	private void crearPelicula() {
		System.out.println("=== Nueva Película ===");
		String id = leerTexto("ID: ");
		String titulo = leerTexto("Título: ");
		int anio = leerEntero("Año de estreno: ");
		int duracion = leerEntero("Duración en minutos: ");
		String genero = leerTexto("Género: ");
		double calificacion = leerDouble("Calificación (0-10): ");
		String director = leerTexto("Director: ");

		controller.crearPelicula(id, titulo, anio, duracion, genero, calificacion, director);
		System.out.println("Película creada correctamente.\n");
	}

	private void crearSerie() {
		System.out.println("=== Nueva Serie de TV ===");
		String id = leerTexto("ID: ");
		String titulo = leerTexto("Título: ");
		int anio = leerEntero("Año de estreno: ");
		int duracion = leerEntero("Duración promedio de episodio (minutos): ");
		String genero = leerTexto("Género: ");
		double calificacion = leerDouble("Calificación (0-10): ");
		int numTemporadas = leerEntero("Número de temporadas: ");

		controller.crearSerie(id, titulo, anio, duracion, genero, calificacion, numTemporadas);
		System.out.println("Serie creada correctamente.\n");
	}

	private void crearDocumental() {
		System.out.println("=== Nuevo Documental ===");
		String id = leerTexto("ID: ");
		String titulo = leerTexto("Título: ");
		int anio = leerEntero("Año de estreno: ");
		int duracion = leerEntero("Duración en minutos: ");
		String genero = leerTexto("Género: ");
		double calificacion = leerDouble("Calificación (0-10): ");
		String tema = leerTexto("Tema principal: ");

		controller.crearDocumental(id, titulo, anio, duracion, genero, calificacion, tema);
		System.out.println("Documental creado correctamente.\n");
	}

	private void mostrarActores() {
		List<Actor> actores = controller.obtenerActores();
		if (actores.isEmpty()) {
			System.out.println("No hay actores registrados.\n");
		} else {
			System.out.println("Actores registrados:");
			actores.forEach(System.out::println);
			System.out.println();
		}
	}

	private void crearActor() {
		System.out.println("=== Nuevo Actor ===");
		String id = leerTexto("ID: ");
		String nombre = leerTexto("Nombre: ");
		String nacionalidad = leerTexto("Nacionalidad: ");

		controller.crearActor(id, nombre, nacionalidad);
		System.out.println("Actor creado correctamente.\n");
	}

	private void mostrarTemporadas() {
		List<Temporada> temporadas = controller.obtenerTemporadas();
		if (temporadas.isEmpty()) {
			System.out.println("No hay temporadas registradas.\n");
		} else {
			System.out.println("Temporadas registradas:");
			temporadas.forEach(System.out::println);
			System.out.println();
		}
	}

	private void crearTemporada() {
		System.out.println("=== Nueva Temporada ===");
		String id = leerTexto("ID: ");
		String idSerie = leerTexto("ID de la serie: ");
		int numero = leerEntero("Número de temporada: ");
		int cantEpisodios = leerEntero("Cantidad de episodios: ");

		controller.crearTemporada(id, idSerie, numero, cantEpisodios);
		System.out.println("Temporada creada correctamente.\n");
	}

	private void mostrarInvestigadores() {
		List<Investigador> investigadores = controller.obtenerInvestigadores();
		if (investigadores.isEmpty()) {
			System.out.println("No hay investigadores registrados.\n");
		} else {
			System.out.println("Investigadores registrados:");
			investigadores.forEach(System.out::println);
			System.out.println();
		}
	}

	private void crearInvestigador() {
		System.out.println("=== Nuevo Investigador ===");
		String id = leerTexto("ID: ");
		String nombre = leerTexto("Nombre: ");
		String area = leerTexto("Área de especialidad: ");

		controller.crearInvestigador(id, nombre, area);
		System.out.println("Investigador creado correctamente.\n");
	}

	private boolean salirAplicacion() {
		try {
			controller.guardarDatos();
			System.out.println("Datos guardados. ¡Hasta luego!");
		} catch (IOException e) {
			System.out.println("Error al guardar los datos: " + e.getMessage());
		}
		return true;
	}

	// --------- Helpers para lectura segura ---------

	private String leerTexto(String mensaje) {
		System.out.print(mensaje);
		return scanner.nextLine().trim();
	}

	private int leerEntero(String mensaje) {
		while (true) {
			System.out.print(mensaje);
			try {
				int valor = Integer.parseInt(scanner.nextLine());
				return valor;
			} catch (NumberFormatException e) {
				System.out.println("Por favor ingrese un número entero válido.");
			}
		}
	}

	private double leerDouble(String mensaje) {
		while (true) {
			System.out.print(mensaje);
			try {
				double valor = Double.parseDouble(scanner.nextLine());
				return valor;
			} catch (NumberFormatException e) {
				System.out.println("Por favor ingrese un número decimal válido.");
			}
		}
	}
}
