package poo.repository;

import poo.model.ContenidoAudiovisual;
import poo.model.Documental;
import poo.model.Pelicula;
import poo.model.SerieTV;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementación de ContenidoRepository que usa un archivo CSV.
 */

public class CsvContenidoRepository implements ContenidoRepository {

	private final Path filePath;
	private final List<ContenidoAudiovisual> contenidos = new ArrayList<>();

	public CsvContenidoRepository(Path filePath) {
		this.filePath = filePath;
	}

	@Override
	public List<ContenidoAudiovisual> findAll() {
		return new ArrayList<>(contenidos); // copia defensiva
	}

	@Override
	public Optional<ContenidoAudiovisual> findById(String id) {
		return contenidos.stream().filter(c -> c.getId().equals(id)).findFirst();
	}

	@Override
	public void add(ContenidoAudiovisual contenido) {
		// Evitamos duplicados por id
		removeById(contenido.getId());
		contenidos.add(contenido);
	}

	@Override
	public void removeById(String id) {
		contenidos.removeIf(c -> c.getId().equals(id));
	}

	@Override
	public void loadFromFile() throws IOException {
		contenidos.clear();

		if (Files.notExists(filePath)) {
			// Si el archivo no existe, no es error: simplemente no hay datos
			return;
		}

		List<String> lineas = Files.readAllLines(filePath, StandardCharsets.UTF_8);
		for (String linea : lineas) {
			if (linea.isBlank()) {
				continue;
			}
			ContenidoAudiovisual contenido = parseLineaCsv(linea);
			if (contenido != null) {
				contenidos.add(contenido);
			}
		}
	}

	@Override
	public void saveToFile() throws IOException {
		// Aseguramos que la carpeta exista
		if (filePath.getParent() != null) {
			Files.createDirectories(filePath.getParent());
		}

		try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8)) {
			for (ContenidoAudiovisual contenido : contenidos) {
				String linea = toCsv(contenido);
				writer.write(linea);
				writer.newLine();
			}
		}
	}

	/**
	 * Convierte una línea CSV en un objeto ContenidoAudiovisual. Formatos esperados
	 * (separador ';'):
	 * PELICULA;id;titulo;anio;duracion;genero;calificacion;director
	 * SERIE;id;titulo;anio;duracion;genero;calificacion;numTemporadas
	 * DOCUMENTAL;id;titulo;anio;duracion;genero;calificacion;temaPrincipal
	 */
	private ContenidoAudiovisual parseLineaCsv(String linea) {
		String[] partes = linea.split(";");
		if (partes.length < 8) {
			// Línea corrupta, podríamos loguear algo aquí
			return null;
		}

		String tipo = partes[0];
		String id = partes[1];
		String titulo = partes[2];
		int anioEstreno = Integer.parseInt(partes[3]);
		int duracion = Integer.parseInt(partes[4]);
		String genero = partes[5];
		double calificacion = Double.parseDouble(partes[6]);

		return switch (tipo) {
		case "PELICULA" -> {
			String director = partes[7];
			yield new Pelicula(id, titulo, anioEstreno, duracion, genero, calificacion, director);
		}
		case "SERIE" -> {
			int numTemporadas = Integer.parseInt(partes[7]);
			yield new SerieTV(id, titulo, anioEstreno, duracion, genero, calificacion, numTemporadas);
		}
		case "DOCUMENTAL" -> {
			String temaPrincipal = partes[7];
			yield new Documental(id, titulo, anioEstreno, duracion, genero, calificacion, temaPrincipal);
		}
		default -> null; // tipo desconocido
		};
	}

	/**
	 * Convierte un contenido en una línea CSV.
	 */
	private String toCsv(ContenidoAudiovisual contenido) {
		if (contenido instanceof Pelicula peli) {
			return peli.toCsv();
		} else if (contenido instanceof SerieTV serie) {
			return serie.toCsv();
		} else if (contenido instanceof Documental doc) {
			return doc.toCsv();
		}
		// Si se añade un nuevo tipo, se podría extender aquí (OCP)
		throw new IllegalArgumentException("Tipo de contenido no soportado: " + contenido.getClass());
	}

}
